package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Props for Progress component with DaisyUI styling options
  */
case class ProgressProps(
    // Core value props
    value: Option[Double] = None, // Current progress value (within min-max range)
    min: Double = 0,              // Minimum value
    max: Double = 100,            // Maximum value

    // Styling options
    variant: String = "",           // primary, secondary, accent, info, success, warning, error
    size: String = "md",            // xs, sm, md, lg
    indeterminate: Boolean = false, // Whether to show indeterminate animation

    // Display options
    showValue: Boolean = false,                                      // Whether to show progress value as text
    valuePrefix: String = "",                                        // Prefix for displayed value (e.g., "$")
    valueSuffix: String = "%",                                       // Suffix for displayed value (e.g., "%")
    valueFormat: Double => String = (v: Double) => v.toInt.toString, // Function to format value

    // Additional styling
    className: String = "", // Additional CSS classes

    // Accessibility
    ariaLabel: Option[String] = None,    // Accessibility label for screen readers
    ariaValuenow: Option[String] = None, // Current value for ARIA (falls back to value)
    ariaValuemin: Option[String] = None, // Min value for ARIA (falls back to min)
    ariaValuemax: Option[String] = None, // Max value for ARIA (falls back to max)
)

/** Radial progress component props (circular progress) */
case class RadialProgressProps(
    // Core value props
    value: Double = 0, // Current progress value (within min-max range)
    min: Double = 0,   // Minimum value
    max: Double = 100, // Maximum value

    // Styling options
    variant: String = "",   // primary, secondary, accent, etc.
    size: String = "md",    // Size variations: xs, sm, md, lg
    thickness: String = "", // Thickness of progress ring

    // Display options
    showValue: Boolean = true,                                       // Whether to show the value in the center
    valuePrefix: String = "",                                        // Prefix for displayed value
    valueSuffix: String = "%",                                       // Suffix for displayed value
    valueFormat: Double => String = (v: Double) => v.toInt.toString, // Function to format value

    // Custom content
    children: Option[FluxusNode] = None, // Custom content to show inside (overrides showValue)

    // Additional styling
    className: String = "", // Additional CSS classes

    // Accessibility
    ariaLabel: Option[String] = None,
)

/** Progress component using DaisyUI styling
  *
  * Features:
  *   - Standard and indeterminate progress bars
  *   - Multiple color variants (primary, secondary, etc.)
  *   - Value display options
  *   - Accessibility attributes
  */
val Progress = (props: ProgressProps) => {
  // Calculate the percentage based on value, min, and max
  val percentage = props.value match {
    case Some(value) =>
      val range = props.max - props.min
      if (range <= 0) 0 // Avoid division by zero
      else ((value - props.min) / range) * 100
    case None => 0 // Default to 0 if no value provided
  }

  // Build CSS classes for the progress container
  val containerClasses = List.newBuilder[String]

  // Base class is always present
  containerClasses += "relative"

  // Show value adds extra height and padding for the text
  if (props.showValue) {
    containerClasses += "h-[1.5rem]" // Fix height with a little extra space for the text
    containerClasses += "mb-4"       // Add margin bottom for the text position
  }

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => containerClasses += cls)
  }

  // Join all container classes with spaces
  val containerClass = containerClasses.result().mkString(" ")

  // Build CSS classes for the progress element
  val progressClasses = List.newBuilder[String]

  // Base class is always present
  progressClasses += "progress"

  // Handle variant - must use predefined classes
  val variantClass = props.variant match {
    case "primary"   => "progress-primary"
    case "secondary" => "progress-secondary"
    case "accent"    => "progress-accent"
    case "info"      => "progress-info"
    case "success"   => "progress-success"
    case "warning"   => "progress-warning"
    case "error"     => "progress-error"
    case _           => "" // Default has no specific class
  }

  // Handle size - must use predefined classes
  val sizeClass = props.size match {
    case "xs" => "progress-xs"
    case "sm" => "progress-sm"
    case "lg" => "progress-lg"
    case _    => "" // Default (md) has no specific class
  }

  // Add indeterminate animation class if specified
  if (props.indeterminate) {
    progressClasses += "progress-indeterminate"
  }

  // Add conditional classes
  if (variantClass.nonEmpty) progressClasses += variantClass
  if (sizeClass.nonEmpty) progressClasses += sizeClass

  // Join all progress classes with spaces
  val progressClass = progressClasses.result().mkString(" ")

  // Format the display value if needed
  val displayValue = props.value.map(v => s"${props.valuePrefix}${props.valueFormat(v)}${props.valueSuffix}")

  // Generate ARIA attributes
  val ariaValueNow = props.ariaValuenow.getOrElse(props.value.map(_.toString).getOrElse(""))
  val ariaValueMin = props.ariaValuemin.getOrElse(props.min.toString)
  val ariaValueMax = props.ariaValuemax.getOrElse(props.max.toString)

  // Render the progress component
  div(
    cls := containerClass,

    // Progress bar element
    progress(
      cls   := progressClass,
      value := props.value.map(_.toString).getOrElse(""),
      max   := props.max.toString,

      // ARIA attributes
      props.ariaLabel.map(label => aria_label := label).orNull,
      if (ariaValueNow.nonEmpty) aria_valuenow := ariaValueNow else null,
      aria_valuemin := ariaValueMin,
      aria_valuemax := ariaValueMax,
    ),

    // Value display if enabled
    if (props.showValue && displayValue.isDefined) {
      div(
        cls := "absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 text-xs font-medium",
        displayValue.get,
      )
    } else null,
  )
}

/** Radial progress component using DaisyUI styling
  *
  * Features:
  *   - Circular progress indicator
  *   - Multiple color variants
  *   - Value display in center
  *   - Custom thickness options
  */
val RadialProgress = (props: RadialProgressProps) => {
  // Calculate the percentage based on value, min, and max
  val range      = props.max - props.min
  val percentage = if (range <= 0) 0 else ((props.value - props.min) / range) * 100

  // Build CSS classes
  val classes = List.newBuilder[String]

  // Base class is always present
  classes += "radial-progress"

  // Handle variant - must use predefined classes
  val variantClass = props.variant match {
    case "primary"   => "text-primary"
    case "secondary" => "text-secondary"
    case "accent"    => "text-accent"
    case "info"      => "text-info"
    case "success"   => "text-success"
    case "warning"   => "text-warning"
    case "error"     => "text-error"
    case _           => "" // Default has no specific class
  }

  // Handle size - must use predefined Tailwind classes
  val sizeClass = props.size match {
    case "xs" => "w-16 h-16"
    case "sm" => "w-24 h-24"
    case "md" => "w-32 h-32" // Default
    case "lg" => "w-40 h-40"
    case _    => "w-32 h-32" // Default to medium
  }

  // Add size class
  classes += sizeClass

  // Add variant class if specified
  if (variantClass.nonEmpty) classes += variantClass

  // Add thickness class if specified
  if (props.thickness.nonEmpty) classes += props.thickness

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val radialClass = classes.result().mkString(" ")

  // Format the display value
  val displayValue = s"${props.valuePrefix}${props.valueFormat(props.value)}${props.valueSuffix}"

  // Create inline style attribute for the progress value
  val progressStyle = s"--value: ${percentage.toInt};"

  // Render the radial progress component
  div(
    cls   := radialClass,
    style := progressStyle,
    role  := "progressbar",
    props.ariaLabel.map(label => aria_label := label).getOrElse(aria_label := "Progress"),
    aria_valuenow := props.value.toString,
    aria_valuemin := props.min.toString,
    aria_valuemax := props.max.toString,

    // Center content - either custom children or the value
    if (props.children.isDefined) {
      props.children.get
    } else if (props.showValue) {
      span(displayValue)
    } else null,
  )
}
