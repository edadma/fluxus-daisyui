package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Enhanced button props with comprehensive DaisyUI button properties
  */
case class ButtonProps(
    // Core properties
    text: String = "",
    children: Option[FluxusNode] = None,
    variant: String = "primary",  // primary, secondary, accent, info, success, warning, error, ghost, link, neutral
    size: String = "md",          // lg, md, sm, xs
    shape: Option[String] = None, // circle, square

    // Style modifiers
    outline: Boolean = false,
    wide: Boolean = false,
    glass: Boolean = false,
    block: Boolean = false,  // Full width button
    active: Boolean = false, // Force active state

    // State indicators
    loading: Boolean = false,
    disabled: Boolean = false,
    noAnimation: Boolean = false,

    // Events
    onClick: () => Unit = () => (),
    onMouseEnter: Option[dom.MouseEvent => Unit] = None,
    onMouseLeave: Option[dom.MouseEvent => Unit] = None,

    // Additional styling
    startIcon: Option[FluxusNode] = None,
    endIcon: Option[FluxusNode] = None,
    className: String = "",

    // Accessibility
    ariaLabel: Option[String] = None,
    tabIndex: Option[Int] = None,
    buttonRole: Option[String] = None,

    // HTML button attributes
    buttonType: String = "button", // button, submit, reset
    name: Option[String] = None,
    value: Option[String] = None,
)

/** Enhanced Button component using DaisyUI styling Features:
  *   - Full DaisyUI button variant support
  *   - Icon placement (start/end)
  *   - Support for both text and children content
  *   - Accessibility attributes
  *   - Extended event handlers
  */
val Button = (props: ButtonProps) => {
  val classes = List.newBuilder[String]

  // Base class is always present
  classes += "btn"

  // Handle variant - must use predefined Tailwind classes
  val variantClass = props.variant match {
    case "primary"   => "btn-primary"
    case "secondary" => "btn-secondary"
    case "accent"    => "btn-accent"
    case "info"      => "btn-info"
    case "success"   => "btn-success"
    case "warning"   => "btn-warning"
    case "error"     => "btn-error"
    case "ghost"     => "btn-ghost"
    case "link"      => "btn-link"
    case "neutral"   => "btn-neutral"
    case _           => ""
  }

  // Handle size - must use predefined Tailwind classes
  val sizeClass = props.size match {
    case "lg" => "btn-lg"
    case "md" => "btn-md"
    case "sm" => "btn-sm"
    case "xs" => "btn-xs"
    case _    => ""
  }

  // Handle shape - must use predefined Tailwind classes
  val shapeClass = props.shape match {
    case Some("circle") => "btn-circle"
    case Some("square") => "btn-square"
    case _              => ""
  }

  // Add conditional classes
  if (variantClass.nonEmpty) classes += variantClass
  if (sizeClass.nonEmpty) classes += sizeClass
  if (shapeClass.nonEmpty) classes += shapeClass
  if (props.outline) classes += "btn-outline"
  if (props.wide) classes += "btn-wide"
  if (props.block) classes += "btn-block"
  if (props.glass) classes += "glass"
  if (props.loading) classes += "btn-loading"
  if (props.active) classes += "btn-active"
  if (props.noAnimation) classes += "no-animation"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val buttonClass = classes.result().mkString(" ")

  // Determine content: icons + text/children
  val contentNodes = List.newBuilder[FluxusNode]

  // Add start icon if specified
  if (props.startIcon.isDefined) {
    contentNodes += div(cls := "mr-2", props.startIcon.get)
  }

  // Add text content if specified
  if (props.text.nonEmpty) {
    contentNodes += span(props.text)
  }

  // Add children if specified (overrides text)
  if (props.children.isDefined) {
    contentNodes += props.children.get
  }

  // Add end icon if specified
  if (props.endIcon.isDefined) {
    contentNodes += div(cls := "ml-2", props.endIcon.get)
  }

  button(
    cls     := buttonClass,
    typ     := props.buttonType,
    onClick := (() => props.onClick()),

    // Conditional event handlers
    if (props.onMouseEnter.isDefined) onMouseEnter := props.onMouseEnter.get else null,
    if (props.onMouseLeave.isDefined) onMouseLeave := props.onMouseLeave.get else null,

    // Conditional attributes
    if (props.disabled) disabled := true else null,
    props.ariaLabel.map(al => aria_label := al).orNull,
    props.tabIndex.map(ti => tabIndex := ti).orNull,
    props.buttonRole.map(r => role := r).orNull,
    props.name.map(n => name := n).orNull,
    props.value.map(v => value := v).orNull,

    // Content
    contentNodes.result(),
  )
}
