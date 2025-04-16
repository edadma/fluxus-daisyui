package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Model for a radio option in a RadioGroup */
case class RadioOption(
    value: String,
    label: String,
    disabled: Boolean = false,
)

/** Props for individual RadioButton component */
case class RadioButtonProps(
    // Core props
    value: String,                // Value for this radio button
    name: String,                 // Group name (for HTML form submission)
    label: Option[String] = None, // Optional label text
    checked: Boolean = false,     // Whether this radio is checked
    disabled: Boolean = false,    // Whether this radio is disabled

    // Styling options
    variant: String = "", // primary, secondary, accent, info, success, warning, error
    size: String = "md",  // xs, sm, md, lg

    // Event handler
    onChange: Option[String => Unit] = None, // Change handler for the radio value

    // Additional styling
    className: String = "",     // Additional CSS classes for the radio input
    labelClassName: String = "", // Additional CSS classes for the label
)

/** Individual RadioButton component using DaisyUI styling */
val RadioButton = (props: RadioButtonProps) => {
  // Build radio CSS classes
  val radioClasses = List.newBuilder[String]

  // Base class is always present
  radioClasses += "radio"

  // Add variant class if specified - must use predefined classes
  val variantClass = props.variant match {
    case "primary"   => "radio-primary"
    case "secondary" => "radio-secondary"
    case "accent"    => "radio-accent"
    case "info"      => "radio-info"
    case "success"   => "radio-success"
    case "warning"   => "radio-warning"
    case "error"     => "radio-error"
    case _           => "" // Default has no class
  }

  if (variantClass.nonEmpty) radioClasses += variantClass

  // Add size class if specified - must use predefined classes
  val sizeClass = props.size match {
    case "xs" => "radio-xs"
    case "sm" => "radio-sm"
    case "lg" => "radio-lg"
    case _    => "" // Default (md) has no specific class
  }

  if (sizeClass.nonEmpty) radioClasses += sizeClass

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => radioClasses += cls)
  }

  // Join all classes with spaces
  val radioClass = radioClasses.result().mkString(" ")

  // Determine if we need a label wrapper
  if (props.label.isDefined) {
    // With label - build label classes
    val labelClasses = List.newBuilder[String]
    labelClasses += "label"
    labelClasses += "cursor-pointer"
    labelClasses += "justify-start"
    labelClasses += "gap-2"

    // Add any custom label classes
    if (props.labelClassName.nonEmpty) {
      props.labelClassName.split(" ").foreach(cls => labelClasses += cls)
    }

    val labelClass = labelClasses.result().mkString(" ")

    // Render radio with label
    label(
      cls := labelClass,
      input(
        typ      := "radio",
        cls      := radioClass,
        name     := props.name,
        value    := props.value,
        checked  := props.checked,
        disabled := props.disabled,
        onChange := ((e: dom.Event) =>
          props.onChange.foreach(_(e.target.asInstanceOf[dom.html.Input].value))
        ),
      ),
      span(cls := "label-text", props.label.get),
    )
  } else {
    // Without label - just the radio input
    input(
      typ      := "radio",
      cls      := radioClass,
      name     := props.name,
      value    := props.value,
      checked  := props.checked,
      disabled := props.disabled,
      onChange := ((e: dom.Event) =>
        props.onChange.foreach(_(e.target.asInstanceOf[dom.html.Input].value))
      ),
    )
  }
}

/** Props for RadioGroup component */
case class RadioGroupProps(
    // Core props
    name: String,                        // Group name for all radio buttons
    value: Option[String] = None,        // Currently selected value
    onChange: String => Unit = _ => (),  // Change handler
    options: List[RadioOption] = List(), // List of options to display

    // Styling options
    variant: String = "",           // primary, secondary, accent, info, success, warning, error
    size: String = "md",            // xs, sm, md, lg
    direction: String = "vertical", // vertical, horizontal

    // Additional styling
    className: String = "", // Additional CSS classes
)

/** RadioGroup component for managing a group of radio buttons */
val RadioGroup = (props: RadioGroupProps) => {
  // Build container classes
  val containerClasses = List.newBuilder[String]

  // Add direction class
  val directionClass = props.direction match {
    case "horizontal" => "flex flex-row flex-wrap gap-4"
    case _            => "flex flex-col gap-2" // Default is vertical
  }

  containerClasses += directionClass

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => containerClasses += cls)
  }

  // Join all classes with spaces
  val containerClass = containerClasses.result().mkString(" ")

  // Render the radio group
  div(
    cls  := containerClass,
    role := "radiogroup",
    props.options.map(option =>
      RadioButton <> RadioButtonProps(
        value = option.value,
        name = props.name,
        label = Some(option.label),
        checked = props.value.contains(option.value),
        disabled = option.disabled,
        variant = props.variant,
        size = props.size,
        onChange = Some(props.onChange),
      ),
    ),
  )
}
