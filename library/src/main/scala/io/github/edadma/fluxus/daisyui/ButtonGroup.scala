package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Button Group props for controlling how buttons are grouped
  */
case class ButtonGroupProps(
    // Core properties
    children: Seq[FluxusNode], // Buttons to be grouped
    vertical: Boolean = false, // Whether buttons should be stacked vertically

    // Styling
    size: Option[String] = None,    // Size override for all buttons: lg, md, sm, xs
    variant: Option[String] = None, // Variant override for all buttons
    joined: Boolean = true,         // Whether buttons should be joined with no space between

    // Responsive options
    stacked: Option[String] = None, // Breakpoint at which buttons stack vertically: sm, md, lg, xl
    // e.g., "sm" means vertical on mobile, horizontal on sm+

    // Additional styling
    className: String = "",

    // Accessibility
    ariaLabel: Option[String] = None,
    role: Option[String] = None,
)

/** Button Group component for grouping multiple buttons
  *
  * Features:
  *   - Horizontal or vertical button grouping
  *   - Responsive stacking
  *   - Consistent styling across buttons
  *   - Proper accessibility attributes
  */
val ButtonGroup = (props: ButtonGroupProps) => {
  val classes = List.newBuilder[String]

  // Base class - using DaisyUI's 'join' class
  classes += "join"

  // Vertical orientation - using DaisyUI's vertical join
  if (props.vertical) {
    classes += "join-vertical"
  }

  // Responsive stacking - we'll use flex direction utilities
  val stackedClass = props.stacked match {
    case Some("sm") => "flex-col sm:flex-row"
    case Some("md") => "flex-col md:flex-row"
    case Some("lg") => "flex-col lg:flex-row"
    case Some("xl") => "flex-col xl:flex-row"
    case _          => ""
  }

  if (stackedClass.nonEmpty) classes += stackedClass

  // If not joined, we'll add gap
  if (!props.joined) {
    classes += "gap-2"
  }

  // Add custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(classes += _)
  }

  // Join all classes
  val groupClass = classes.result().mkString(" ")

  // Process children to apply consistent styling if needed and add join-item class
  val processedChildren =
    props.children.map {
      case btn: ComponentNode if btn.component.toString.contains("Button") =>
        // Extract props and update with size/variant override and add join-item class
        val originalProps = btn.props.asInstanceOf[ButtonProps]

        // Create new class that combines existing classes with join-item
        val newClassName = if (originalProps.className.isEmpty) "join-item"
        else s"${originalProps.className} join-item"

        val newProps = originalProps.copy(
          size = props.size.getOrElse(originalProps.size),
          variant = props.variant.getOrElse(originalProps.variant),
          className = newClassName,
        )

        Button <> newProps

      case other =>
        // For non-Button components, wrap in a div with join-item class to maintain consistency
        div(
          cls := "join-item",
          other,
        )
    }

  // Render the button group
  div(
    cls := groupClass,
    props.ariaLabel.map(label => aria_label := label).orNull,
    props.role.map(r => role := r).orNull,
    processedChildren,
  )
}
