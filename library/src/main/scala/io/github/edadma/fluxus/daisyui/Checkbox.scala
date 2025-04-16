package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Checkbox component props with DaisyUI styling options
  */
case class CheckboxProps(
    // Core properties
    checked: Boolean = false,                 // Whether the checkbox is checked
    defaultChecked: Option[Boolean] = None,   // Default checked state (uncontrolled)
    onChange: Option[Boolean => Unit] = None, // Change handler
    name: Option[String] = None,              // Input name attribute
    value: Option[String] = None,             // Input value attribute

    // Style variants
    variant: String = "", // primary, secondary, accent, info, success, warning, error
    size: String = "md",  // xs, sm, md, lg

    // Label options
    label: Option[String] = None,    // Text label
    labelPosition: String = "right", // right, left
    labelClassName: String = "",     // Additional CSS classes for label

    // State
    disabled: Boolean = false,      // Whether checkbox is disabled
    indeterminate: Boolean = false, // Whether checkbox is in indeterminate state

    // Additional styling
    className: String = "", // Additional CSS classes for the checkbox

    // Accessibility
    ariaLabel: Option[String] = None,      // Accessibility label
    ariaLabelledBy: Option[String] = None, // ID of element that labels the checkbox
    ariaDescribedBy: Option[String] = None, // ID of element that describes the checkbox
)

/** Checkbox component using DaisyUI styling
  *
  * Features:
  *   - Support for all daisyUI styling options
  *   - Label support with positioning
  *   - Controlled and uncontrolled usage
  *   - Support for indeterminate state
  *   - Accessibility attributes
  */
val Checkbox = (props: CheckboxProps) => {
  // For uncontrolled checkbox, we need to track checked state internally
  // For controlled checkbox, we'll use the props.checked value directly
  val (isChecked, setIsChecked, _) = useState(props.defaultChecked.getOrElse(props.checked))

  // Effect to sync internal state with controlled prop
  useEffect(
    () => {
      // Only update internal state if we're in controlled mode
      if (props.defaultChecked.isEmpty) {
        setIsChecked(props.checked)
      }
      ()
    },
    Seq(props.checked),
  )

  // Generate a unique ID for accessibility
  val checkboxId = useRef[String]()
  if (checkboxId.current == null) {
    checkboxId.current = s"checkbox-${scala.util.Random.alphanumeric.take(8).mkString}"
  }

  // Handle change event
  def handleChange(e: dom.Event): Unit = {
    val newChecked = e.target.asInstanceOf[dom.html.Input].checked

    // Update internal state for uncontrolled component
    if (props.defaultChecked.isDefined) {
      setIsChecked(newChecked)
    }

    // Call external handler if provided
    props.onChange.foreach(_(newChecked))
  }

  // Set indeterminate property via useEffect as it's not available as an HTML attribute
  val checkboxRef = useRef[dom.html.Input]()

  useEffect(
    () => {
      // Set indeterminate property if checkbox ref is available
      if (checkboxRef.current != null) {
        checkboxRef.current.indeterminate = props.indeterminate
      }
      ()
    },
    Seq(props.indeterminate),
  )

  // Calculate checkbox classes
  val checkboxClasses = List.newBuilder[String]

  // Base class
  checkboxClasses += "checkbox"

  // Variant classes - must use predefined classes
  val variantClass = props.variant match {
    case "primary"   => "checkbox-primary"
    case "secondary" => "checkbox-secondary"
    case "accent"    => "checkbox-accent"
    case "info"      => "checkbox-info"
    case "success"   => "checkbox-success"
    case "warning"   => "checkbox-warning"
    case "error"     => "checkbox-error"
    case _           => "" // Default has no specific class
  }

  // Size classes - must use predefined classes
  val sizeClass = props.size match {
    case "xs" => "checkbox-xs"
    case "sm" => "checkbox-sm"
    case "lg" => "checkbox-lg"
    case _    => "" // Default (md) has no specific class
  }

  // Add conditional classes
  if (variantClass.nonEmpty) checkboxClasses += variantClass
  if (sizeClass.nonEmpty) checkboxClasses += sizeClass

  // Add custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => checkboxClasses += cls)
  }

  // Join into a single string
  val checkboxClass = checkboxClasses.result().mkString(" ")

  // Determine whether we need a label wrapper
  if (props.label.isDefined) {
    // Label container classes
    val containerClasses = List.newBuilder[String]
    containerClasses += "flex items-center gap-2 cursor-pointer"

    // Adjust alignment based on label position
    if (props.labelPosition == "left") {
      containerClasses += "flex-row-reverse"
    }

    // Handle disabled state
    if (props.disabled) {
      containerClasses += "opacity-50 cursor-not-allowed"
    }

    // Label className if provided
    if (props.labelClassName.nonEmpty) {
      props.labelClassName.split(" ").foreach(cls => containerClasses += cls)
    }

    // Join container classes
    val containerClass = containerClasses.result().mkString(" ")

    // Render checkbox with label
    label(
      cls     := containerClass,
      htmlFor := checkboxId.current,

      // Checkbox input
      input(
        ref              := checkboxRef,
        typ              := "checkbox",
        id               := checkboxId.current,
        cls              := checkboxClass,
        checked          := (if (props.defaultChecked.isDefined) isChecked else props.checked),
        defaultChecked   := props.defaultChecked.orNull,
        disabled         := props.disabled,
        onChange         := (handleChange(_)),
        name             := props.name.orNull,
        value            := props.value.orNull,
        aria_label       := props.ariaLabel.orNull,
        aria_labelledby  := props.ariaLabelledBy.orNull,
        aria_describedby := props.ariaDescribedBy.orNull,
      ),

      // Label text
      span(
        cls := "label-text",
        props.label.get,
      ),
    )
  } else {
    // Render just the checkbox without label
    input(
      ref              := checkboxRef,
      typ              := "checkbox",
      id               := checkboxId.current,
      cls              := checkboxClass,
      checked          := (if (props.defaultChecked.isDefined) isChecked else props.checked),
      defaultChecked   := props.defaultChecked.orNull,
      disabled         := props.disabled,
      onChange         := (handleChange(_)),
      name             := props.name.orNull,
      value            := props.value.orNull,
      aria_label       := props.ariaLabel.orNull,
      aria_labelledby  := props.ariaLabelledBy.orNull,
      aria_describedby := props.ariaDescribedBy.orNull,
    )
  }
}
