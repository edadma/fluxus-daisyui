package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Model for Select options */
case class SelectOption(
    value: String,
    label: String,
)

/** Select component props */
case class SelectProps(
    // Core data props
    options: List[SelectOption] = List(),
    value: Option[String] = None,               // None means nothing selected (cleared state)
    onChange: Option[String] => Unit = _ => (), // Callback receives None when cleared

    // Display options
    placeholder: String = "Select an option",
    allowClear: Boolean = false, // Whether to show clear button

    // Styling options
    width: Option[String] = None, // Custom width
    bordered: Boolean = true,     // Show border
    size: String = "md",          // sm, md, lg

    // State options
    disabled: Boolean = false,
    loading: Boolean = false,

    // Additional styling
    className: String = "",
)

/** Select component with dropdown
  *
  * Features:
  *   - Custom options with label/value
  *   - Optional clear button
  *   - Support for placeholder text
  *   - Different sizes and styles
  *   - Disabled state
  */
val Select = (props: SelectProps) => {
  // State for dropdown visibility
  val (isOpen, setIsOpen, _) = useState(false)

  // Create a reference to detect clicks outside the component
  val selectRef = useRef[dom.html.Div]()

  // Handle click outside to close dropdown
  useEffect(
    () => {
      if (isOpen) {
        val handleClickOutside = (e: dom.Event) => {
          if (selectRef.current != null && !selectRef.current.contains(e.target.asInstanceOf[dom.Node])) {
            setIsOpen(false)
          }
        }

        dom.document.addEventListener("mousedown", handleClickOutside)

        // Cleanup
        () => dom.document.removeEventListener("mousedown", handleClickOutside)
      } else {
        () => ()
      }
    },
    Seq(isOpen),
  )

  // Handle option selection
  def handleSelect(value: String): Unit = {
    props.onChange(Some(value))
    setIsOpen(false)
  }

  // Handle clearing the selection
  def handleClear(e: dom.MouseEvent): Unit = {
    e.stopPropagation() // Prevent dropdown from opening
    props.onChange(None)
  }

  // Calculate classes for the select container
  val containerClasses = List.newBuilder[String]

  // Base class
  containerClasses += "relative"

  // Width class (if specified)
  if (props.width.isDefined) {
    containerClasses += "w-full" // We'll override with inline style
  } else {
    containerClasses += "w-full"
  }

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => containerClasses += cls)
  }

  // Calculate size padding based on size
  val sizePadding = props.size match {
    case "sm" => "py-1 px-3 text-sm"
    case "lg" => "py-3 px-5 text-lg"
    case _    => "py-2 px-4" // Default is medium
  }

  // Calculate display value and placeholder
  val selectedOption  = props.value.flatMap(v => props.options.find(_.value == v))
  val displayText     = selectedOption.map(_.label).getOrElse(props.placeholder)
  val showPlaceholder = selectedOption.isEmpty

  // Check if we need to show the clear button
  val showClear = props.allowClear && props.value.isDefined && !props.disabled

  // Dropdown indicator icon (changes based on dropdown state)
  def ChevronIcon(isUp: Boolean): FluxusNode = {
    svg(
      xmlns   := "http://www.w3.org/2000/svg",
      cls     := "h-4 w-4",
      fill    := "none",
      viewBox := "0 0 24 24",
      stroke  := "currentColor",
      path(
        strokeLinecap  := "round",
        strokeLinejoin := "round",
        strokeWidth    := "2",
        d              := (if (isUp) "M5 15l7-7 7 7" else "M19 9l-7 7-7-7"),
      ),
    )
  }

  // Main component structure
  div(
    cls := containerClasses.result().mkString(" "),
    if (props.width.isDefined) style := s"width: ${props.width.get};" else null,
    ref := selectRef,

    // Custom select trigger (not using DaisyUI's select class)
    div(
      cls := s"flex items-center justify-between rounded-lg cursor-pointer ${sizePadding} ${
          if (props.bordered) "border border-base-300" else "border-none"
        } ${if (props.disabled) "opacity-50 cursor-not-allowed" else ""}",
      onClick := (() => if (!props.disabled) setIsOpen(!isOpen)),

      // Display text
      span(
        cls := (if (showPlaceholder) "text-base-content/50" else "text-base-content"),
        displayText,
      ),

      // Right section with control elements
      div(
        cls := "flex items-center space-x-2",

        // Clear button if enabled and value is selected
        if (showClear)
          button(
            typ     := "button",
            cls     := "flex items-center justify-center h-5 w-5 rounded-full hover:bg-base-300",
            onClick := ((e: dom.MouseEvent) => handleClear(e)),
            svg(
              xmlns       := "http://www.w3.org/2000/svg",
              cls         := "h-3 w-3",
              fill        := "none",
              viewBox     := "0 0 24 24",
              stroke      := "currentColor",
              strokeWidth := "2",
              path(
                strokeLinecap  := "round",
                strokeLinejoin := "round",
                d              := "M6 18L18 6M6 6l12 12",
              ),
            ),
          )
        else null,

        // Loading spinner
        if (props.loading)
          Spinner <> SpinnerProps(
            size = "xs",
            variant = "spinner-dots",
          )
        else null,

        // Chevron (only this indicator now)
        ChevronIcon(isOpen),
      ),
    ),

    // Dropdown menu
    if (isOpen && !props.disabled)
      div(
        cls := "absolute z-50 mt-1 w-full rounded-md bg-base-100 shadow-lg border border-base-300",
        div(
          cls := "py-1 max-h-60 overflow-auto",
          props.options.map(option =>
            div(
              key := option.value,
              cls := s"px-4 py-2 cursor-pointer hover:bg-base-200 ${
                  if (props.value.contains(option.value)) "bg-primary/10" else ""
                }",
              onClick := (() => handleSelect(option.value)),
              option.label,
            ),
          ),
        ),
      )
    else null,
  )
}
