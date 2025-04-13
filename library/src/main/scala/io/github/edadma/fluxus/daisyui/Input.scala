package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Input component props with DaisyUI styling options
  */
case class InputProps(
    // Value handling
    value: Option[String] = None,
    defaultValue: Option[String] = None,
    onChange: String => Unit = _ => (),

    // Input attributes
    typ: String = "text",
    name: Option[String] = None,
    placeholder: Option[String] = None,
    disabled: Boolean = false,
    readOnly: Boolean = false,
    required: Boolean = false,
    autoFocus: Boolean = false,
    maxLength: Option[Int] = None,
    min: Option[String] = None,
    max: Option[String] = None,
    step: Option[String] = None,

    // Styling
    size: String = "md",  // xs, sm, md, lg
    variant: String = "", // primary, secondary, accent, info, success, warning, error, ghost
    bordered: Boolean = true,
    ghost: Boolean = false,
    fullWidth: Boolean = true,

    // State
    loading: Boolean = false,
    error: Option[String] = None,

    // Layout and content
    label: Option[String] = None,
    labelFloat: Boolean = false,
    helperText: Option[String] = None,
    leadingIcon: Option[FluxusNode] = None,
    trailingIcon: Option[FluxusNode] = None,
    prefix: Option[FluxusNode] = None,
    suffix: Option[FluxusNode] = None,

    // Special features
    showPasswordToggle: Boolean = false,
    showClearButton: Boolean = false,
    showCharCount: Boolean = false,

    // Additional customization
    className: String = "",
    inputClassName: String = "",
    labelClassName: String = "",
    helperTextClassName: String = "",

    // Textarea specific
    rows: Option[Int] = None,
    resize: Boolean = true,

    // Events
    onFocus: Option[dom.FocusEvent => Unit] = None,
    onBlur: Option[dom.FocusEvent => Unit] = None,
    onKeyDown: Option[dom.KeyboardEvent => Unit] = None,
    onKeyUp: Option[dom.KeyboardEvent => Unit] = None,

    // Accessibility
    ariaLabel: Option[String] = None,
    ariaDescribedBy: Option[String] = None,
    ariaInvalid: Option[Boolean] = None,
)

/** Input component using DaisyUI styling
  *
  * Features:
  *   - Support for all input types (text, number, password, etc.)
  *   - Textarea support
  *   - Various sizes and variants with DaisyUI styling
  *   - Label and helper text
  *   - Error state with validation message
  *   - Icon support (leading and trailing)
  *   - Prefix and suffix add-ons
  *   - Special features like password toggle, clear button, character count
  */
val Input = (props: InputProps) => {
  // State for internal input value (for controlled component)
  val (inputValue, setInputValue, _) = useState(props.value.orElse(props.defaultValue).getOrElse(""))

  // State for password visibility
  val (passwordVisible, setPasswordVisible, _) = useState(false)

  // State for input focus
  val (isFocused, setIsFocused, _) = useState(false)

  // State for whether the input has been touched
  val (isTouched, setIsTouched, _) = useState(false)

  // Create a unique ID for the input and associated elements
  val inputId = useRef[String]()

  // Initialize the ID if not set
  if (inputId.current == null) {
    inputId.current = s"input-${scala.util.Random.alphanumeric.take(8).mkString}"
  }

  // Helper ID for aria-describedby
  val helperId = s"${inputId.current}-helper"
  val errorId  = s"${inputId.current}-error"

  // Handle value change
  def handleChange(e: dom.Event): Unit = {
    val newValue = e.target.asInstanceOf[dom.html.Input].value

    // Update internal state
    setInputValue(newValue)

    // Call external handler
    props.onChange(newValue)

    // Mark as touched on first change
    if (!isTouched) {
      setIsTouched(true)
    }
  }

  // Handle focus event
  def handleFocus(e: dom.FocusEvent): Unit = {
    setIsFocused(true)
    props.onFocus.foreach(_(e))
  }

  // Handle blur event
  def handleBlur(e: dom.FocusEvent): Unit = {
    setIsFocused(false)
    setIsTouched(true)
    props.onBlur.foreach(_(e))
  }

  // Handle clear button click
  def handleClear(): Unit = {
    setInputValue("")
    props.onChange("")
  }

  // Handle password toggle click
  def handlePasswordToggle(): Unit = {
    setPasswordVisible(!passwordVisible)
  }

  // Determine the effective input type (for password toggle)
  val effectiveType =
    if (props.typ == "password" && passwordVisible) "text"
    else props.typ

  // Determine if we're showing a textarea instead of input
  val isTextarea = props.typ == "textarea"

  // Calculate input classes
  val inputClasses = List.newBuilder[String]

  // Base class is always present
  inputClasses += "input"

  // Bordered or borderless
  if (props.bordered) inputClasses += "input-bordered"

  // Ghost style
  if (props.ghost) inputClasses += "input-ghost"

  // Size classes
  val sizeClass = props.size match {
    case "xs" => "input-xs"
    case "sm" => "input-sm"
    case "lg" => "input-lg"
    case _    => "input-md" // Default is medium
  }

  // Input variants
  val variantClass = props.variant match {
    case "primary"   => "input-primary"
    case "secondary" => "input-secondary"
    case "accent"    => "input-accent"
    case "info"      => "input-info"
    case "success"   => "input-success"
    case "warning"   => "input-warning"
    case "error"     => "input-error"
    case _           => "" // No variant class for default
  }

  // Conditional classes
  inputClasses += sizeClass
  if (variantClass.nonEmpty) inputClasses += variantClass

  // Error state (takes precedence over variant)
  if (props.error.isDefined) inputClasses += "input-error"

  // Width setting
  if (props.fullWidth) inputClasses += "w-full"

  // Loading state
  if (props.loading) inputClasses += "input-loading"

  // Extra customizations
  if (props.inputClassName.nonEmpty) {
    props.inputClassName.split(" ").foreach(cls => inputClasses += cls)
  }

  // For textarea, change some classes
  val textareaClasses =
    if (isTextarea) {
      val builder = List.newBuilder[String]
      builder += "textarea"
      if (props.bordered) builder += "textarea-bordered"
      if (props.ghost) builder += "textarea-ghost"

      // Size classes for textarea
      val textareaSizeClass = props.size match {
        case "xs" => "textarea-xs"
        case "sm" => "textarea-sm"
        case "lg" => "textarea-lg"
        case _    => "textarea-md" // Default is medium
      }

      // Textarea variants
      val textareaVariantClass = props.variant match {
        case "primary"   => "textarea-primary"
        case "secondary" => "textarea-secondary"
        case "accent"    => "textarea-accent"
        case "info"      => "textarea-info"
        case "success"   => "textarea-success"
        case "warning"   => "textarea-warning"
        case "error"     => "textarea-error"
        case _           => "" // No variant class for default
      }

      builder += textareaSizeClass
      if (textareaVariantClass.nonEmpty) builder += textareaVariantClass

      // Error state (takes precedence over variant)
      if (props.error.isDefined) builder += "textarea-error"

      // Width setting
      if (props.fullWidth) builder += "w-full"

      // Resize control
      if (!props.resize) builder += "resize-none"

      // Extra customizations
      if (props.inputClassName.nonEmpty) {
        props.inputClassName.split(" ").foreach(cls => builder += cls)
      }

      builder.result()
    } else {
      List()
    }

  // Calculate wrapper classes
  val wrapperClasses = List.newBuilder[String]

  // Base class
  wrapperClasses += "form-control"

  // Width setting
  if (props.fullWidth) wrapperClasses += "w-full"

  // Extra customizations
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => wrapperClasses += cls)
  }

  // Join classes
  val wrapperClass  = wrapperClasses.result().mkString(" ")
  val inputClass    = inputClasses.result().mkString(" ")
  val textareaClass = textareaClasses.mkString(" ")

  // Calculate label classes
  val labelClasses = List.newBuilder[String]

  // Base class
  labelClasses += "label"

  // For floating labels
  if (props.labelFloat) {
    labelClasses += "absolute"
    labelClasses += "top-0"
    labelClasses += "left-0"
    labelClasses += "h-full"
    labelClasses += "pointer-events-none"
    labelClasses += "transform-gpu"

    val paddingClass = props.size match {
      case "xs" => "px-2"
      case "sm" => "px-3"
      case "lg" => "px-5"
      case _    => "px-4" // Default is medium
    }

    labelClasses += paddingClass

    // Float animation classes - float on focus or when there's content
    if (isFocused || inputValue.nonEmpty) {
      labelClasses += "-translate-y-3"
      labelClasses += "scale-75"
      labelClasses += "origin-top-left"
      labelClasses += "bg-base-100"
      labelClasses += "px-1"
      labelClasses += "z-10"
    } else {
      labelClasses += "flex"
      labelClasses += "items-center"
    }
  }

  // Extra customizations
  if (props.labelClassName.nonEmpty) {
    props.labelClassName.split(" ").foreach(cls => labelClasses += cls)
  }

  // Join label classes
  val labelClass = labelClasses.result().mkString(" ")

  // Helper/error text classes
  val helperClasses = List.newBuilder[String]

  // Base class
  helperClasses += "text-sm"
  helperClasses += "mt-1"

  // Error state
  if (props.error.isDefined) {
    helperClasses += "text-error"
  } else {
    helperClasses += "text-base-content/70"
  }

  // Extra customizations
  if (props.helperTextClassName.nonEmpty) {
    props.helperTextClassName.split(" ").foreach(cls => helperClasses += cls)
  }

  // Join helper classes
  val helperClass = helperClasses.result().mkString(" ")

  // Calculate the padding style based on prefix/suffix/icons
  def getPaddingStyle: String = {
    val paddingLeft = if (props.prefix.isDefined || props.leadingIcon.isDefined) "2.5rem" else null
    val paddingRight =
      if (
        props.suffix.isDefined || props.trailingIcon.isDefined ||
        props.showClearButton ||
        (props.typ == "password" && props.showPasswordToggle)
      ) "2.5rem"
      else null

    List(
      if (paddingLeft != null) s"padding-left: $paddingLeft" else "",
      if (paddingRight != null) s"padding-right: $paddingRight" else "",
    ).filter(_.nonEmpty).mkString("; ")
  }

  // Create aria-describedby value
  def getAriaDescribedBy: Option[String] = {
    val ids = List(
      if (props.helperText.isDefined) Some(helperId) else None,
      if (props.error.isDefined) Some(errorId) else None,
      props.ariaDescribedBy,
    ).flatten

    if (ids.isEmpty) None else Some(ids.mkString(" "))
  }

  // Render the component
  div(
    cls := wrapperClass,

    // Label above input (non-floating)
    if (props.label.isDefined && !props.labelFloat) {
      label(
        cls     := "label",
        htmlFor := inputId.current,
        span(
          cls := "label-text",
          props.label.get,
        ),

        // Optional character count on the right side of label
        if (props.showCharCount && props.maxLength.isDefined) {
          span(
            cls := "label-text-alt",
            s"${inputValue.length}/${props.maxLength.get}",
          )
        } else null,
      )
    } else null,

    // Input group with prefix/suffix and icons
    div(
      cls := "relative",

      // Prefix (if provided)
      if (props.prefix.isDefined) {
        div(
          cls := "flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none",
          props.prefix.get,
        )
      } else null,

      // Leading icon (if provided)
      if (props.leadingIcon.isDefined) {
        div(
          cls := "flex absolute inset-y-0 left-0 items-center pl-3",
          props.leadingIcon.get,
        )
      } else null,

      // Floating label within input
      if (props.label.isDefined && props.labelFloat) {
        label(
          cls     := labelClass,
          htmlFor := inputId.current,
          span(
            props.label.get,
          ),
        )
      } else null,

      // Input element or textarea (with attributes applied individually)
      if (isTextarea) {
        textarea(
          cls := textareaClass,
          id  := inputId.current,

          // Apply rows if defined
          if (props.rows.isDefined) rows := props.rows.get.toString else null,

          // Apply value or defaultValue if defined (but not both)
          if (props.value.isDefined) value                                      := inputValue else null,
          if (props.defaultValue.isDefined && props.value.isEmpty) defaultValue := props.defaultValue.get else null,

          // Apply other input attributes if defined
          if (props.name.isDefined) name               := props.name.get else null,
          if (props.placeholder.isDefined) placeholder := props.placeholder.get else null,
          if (props.disabled) disabled                 := true else null,
          if (props.readOnly) "readOnly"               := true else null,
          if (props.required) required                 := true else null,
          if (props.autoFocus) autofocus               := true else null,
          if (props.maxLength.isDefined) maxLength     := props.maxLength.get.toString else null,

          // Apply event handlers
          onInput := ((e: dom.Event) => handleChange(e)),
          onFocus := ((e: dom.FocusEvent) => handleFocus(e)),
          onBlur  := ((e: dom.FocusEvent) => handleBlur(e)),
          if (props.onKeyDown.isDefined) onKeyDown := props.onKeyDown.get else null,
          if (props.onKeyUp.isDefined) onKeyUp     := props.onKeyUp.get else null,

          // Apply accessibility attributes
          if (props.ariaLabel.isDefined) aria_label                            := props.ariaLabel.get else null,
          if (getAriaDescribedBy.isDefined) aria_describedby                   := getAriaDescribedBy.get else null,
          if (props.ariaInvalid.getOrElse(props.error.isDefined)) aria_invalid := "true" else null,

          // Apply padding style if needed
          if (getPaddingStyle.nonEmpty) style := getPaddingStyle else null,
        )
      } else {
        input(
          typ := effectiveType,
          cls := inputClass,
          id  := inputId.current,

          // Apply value or defaultValue if defined (but not both)
          if (props.value.isDefined) value                                      := inputValue else null,
          if (props.defaultValue.isDefined && props.value.isEmpty) defaultValue := props.defaultValue.get else null,

          // Apply other input attributes if defined
          if (props.name.isDefined) name               := props.name.get else null,
          if (props.placeholder.isDefined) placeholder := props.placeholder.get else null,
          if (props.disabled) disabled                 := true else null,
          if (props.readOnly) "readOnly"               := true else null,
          if (props.required) required                 := true else null,
          if (props.autoFocus) autofocus               := true else null,
          if (props.maxLength.isDefined) maxLength     := props.maxLength.get.toString else null,
          if (props.min.isDefined) min                 := props.min.get else null,
          if (props.max.isDefined) max                 := props.max.get else null,
          if (props.step.isDefined) step               := props.step.get else null,

          // Apply event handlers
          onInput := ((e: dom.Event) => handleChange(e)),
          onFocus := ((e: dom.FocusEvent) => handleFocus(e)),
          onBlur  := ((e: dom.FocusEvent) => handleBlur(e)),
          if (props.onKeyDown.isDefined) onKeyDown := props.onKeyDown.get else null,
          if (props.onKeyUp.isDefined) onKeyUp     := props.onKeyUp.get else null,

          // Apply accessibility attributes
          if (props.ariaLabel.isDefined) aria_label                            := props.ariaLabel.get else null,
          if (getAriaDescribedBy.isDefined) aria_describedby                   := getAriaDescribedBy.get else null,
          if (props.ariaInvalid.getOrElse(props.error.isDefined)) aria_invalid := "true" else null,

          // Apply padding style if needed
          if (getPaddingStyle.nonEmpty) style := getPaddingStyle else null,
        )
      },

      // Password toggle button (if enabled)
      if (props.typ == "password" && props.showPasswordToggle) {
        button(
          typ        := "button",
          cls        := "absolute inset-y-0 right-0 flex items-center pr-3",
          onClick    := (() => handlePasswordToggle()),
          aria_label := (if (passwordVisible) "Hide password" else "Show password"),

          // Show appropriate icon
          if (passwordVisible) {
            // Eye off icon
            svg(
              xmlns   := "http://www.w3.org/2000/svg",
              cls     := "h-5 w-5 text-base-content/70",
              fill    := "none",
              viewBox := "0 0 24 24",
              stroke  := "currentColor",
              path(
                strokeLinecap  := "round",
                strokeLinejoin := "round",
                strokeWidth    := "2",
                d := "M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.88 9.88l-3.29-3.29m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21",
              ),
            )
          } else {
            // Eye icon
            svg(
              xmlns   := "http://www.w3.org/2000/svg",
              cls     := "h-5 w-5 text-base-content/70",
              fill    := "none",
              viewBox := "0 0 24 24",
              stroke  := "currentColor",
              path(
                strokeLinecap  := "round",
                strokeLinejoin := "round",
                strokeWidth    := "2",
                d              := "M15 12a3 3 0 11-6 0 3 3 0 016 0z",
              ),
              path(
                strokeLinecap  := "round",
                strokeLinejoin := "round",
                strokeWidth    := "2",
                d := "M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z",
              ),
            )
          },
        )
      } else null,

      // Clear button
      if (props.showClearButton && inputValue.nonEmpty) {
        button(
          typ        := "button",
          cls        := "absolute inset-y-0 right-0 flex items-center pr-3",
          onClick    := (() => handleClear()),
          aria_label := "Clear input",

          // X icon
          svg(
            xmlns   := "http://www.w3.org/2000/svg",
            cls     := "h-5 w-5 text-base-content/70",
            fill    := "none",
            viewBox := "0 0 24 24",
            stroke  := "currentColor",
            path(
              strokeLinecap  := "round",
              strokeLinejoin := "round",
              strokeWidth    := "2",
              d              := "M6 18L18 6M6 6l12 12",
            ),
          ),
        )
      } else null,

      // Trailing icon (if provided)
      if (props.trailingIcon.isDefined) {
        div(
          cls := "flex absolute inset-y-0 right-0 items-center pr-3",
          props.trailingIcon.get,
        )
      } else null,

      // Suffix (if provided)
      if (props.suffix.isDefined) {
        div(
          cls := "flex absolute inset-y-0 right-0 items-center pr-3 pointer-events-none",
          props.suffix.get,
        )
      } else null,
    ),

    // Helper text or error message
    if (props.error.isDefined || props.helperText.isDefined) {
      div(
        cls := helperClass,
        id  := (if (props.error.isDefined) errorId else helperId),
        props.error.getOrElse(props.helperText.getOrElse("")),
      )
    } else null,
  )
}
