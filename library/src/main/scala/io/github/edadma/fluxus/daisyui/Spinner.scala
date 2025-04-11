package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Spinner props with comprehensive DaisyUI styling options
  */
case class SpinnerProps(
    // Core display options
    variant: String = "spinner", // spinner (border), spinner-dots, spinner-ring, spinner-ball, spinner-infinity

    // Sizing options
    size: String = "md", // xs, sm, md, lg

    // Color options
    color: String =
      "", // text-primary, text-secondary, text-accent, text-neutral, text-info, text-success, text-warning, text-error

    // Animation options
    animation: String = "", // animate-spin (default for spinner/ring), animate-pulse, animate-ping, animate-bounce
    noAnimation: Boolean = false, // Disable animation

    // Additional styling
    className: String = "",
)

/** Spinner component using DaisyUI styling Features:
  *   - Multiple spinner types (border, dots, ring, ball, infinity)
  *   - Size variants
  *   - Color customization
  *   - Animation options
  */
val Spinner = (props: SpinnerProps) => {
  val classes = List.newBuilder[String]

  // Base class depends on variant
  val baseClass = props.variant match {
    case "spinner-dots"     => "loading loading-dots"
    case "spinner-ring"     => "loading loading-ring"
    case "spinner-ball"     => "loading loading-ball"
    case "spinner-infinity" => "loading loading-infinity"
    case _                  => "loading loading-spinner" // Default to spinner
  }

  classes += baseClass

  // Size classes - must use predefined Tailwind classes
  val sizeClass = props.size match {
    case "xs" => "loading-xs"
    case "sm" => "loading-sm"
    case "md" => "loading-md"
    case "lg" => "loading-lg"
    case _    => "loading-md" // Default to medium
  }

  classes += sizeClass

  // Add color class if specified
  if (props.color.nonEmpty) {
    classes += props.color
  }

  // Add animation class if specified and animation is not disabled
  if (!props.noAnimation && props.animation.nonEmpty) {
    classes += props.animation
  }

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val spinnerClass = classes.result().mkString(" ")

  // Create spinner component
  span(
    cls        := spinnerClass,
    aria_label := "Loading",
  )
}
