package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Base button props with essential DaisyUI button properties
  */
case class ButtonProps(
    text: String,
    variant: String = "primary",  // primary, secondary, accent, info, success, warning, error, ghost, link
    size: String = "md",          // lg, md, sm, xs
    shape: Option[String] = None, // circle, square
    outline: Boolean = false,
    wide: Boolean = false,
    glass: Boolean = false,
    loading: Boolean = false,
    disabled: Boolean = false,
    noAnimation: Boolean = false,
    onClick: () => Unit = () => (),
    className: String = "",
)

/** Button component using DaisyUI styling
  */
val Button = (props: ButtonProps) => {
  val classes = List.newBuilder[String]

  // Base class is always present
  classes += "btn"

  // Handle variant - must use predefined Tailwind classes
  val variantClass = props.variant match {
    case "primary" => "btn-primary"
    case "secondary" => "btn-secondary"
    case "accent" => "btn-accent"
    case "info" => "btn-info"
    case "success" => "btn-success"
    case "warning" => "btn-warning"
    case "error" => "btn-error"
    case "ghost" => "btn-ghost"
    case "link" => "btn-link"
    case _ => ""
  }

  // Handle size - must use predefined Tailwind classes
  val sizeClass = props.size match {
    case "lg" => "btn-lg"
    case "md" => "btn-md"
    case "sm" => "btn-sm"
    case "xs" => "btn-xs"
    case _ => ""
  }

  // Handle shape - must use predefined Tailwind classes
  val shapeClass = props.shape match {
    case Some("circle") => "btn-circle"
    case Some("square") => "btn-square"
    case _ => ""
  }

  // Add conditional classes
  if (variantClass.nonEmpty) classes += variantClass
  if (sizeClass.nonEmpty) classes += sizeClass
  if (shapeClass.nonEmpty) classes += shapeClass
  if (props.outline) classes += "btn-outline"
  if (props.wide) classes += "btn-wide"
  if (props.glass) classes += "btn-glass"
  if (props.loading) classes += "btn-loading"
  if (props.noAnimation) classes += "no-animation"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val buttonClass = classes.result().mkString(" ")

  button(
    cls     := buttonClass,
    onClick := (() => props.onClick()),
    if (props.disabled) disabled := true else null,
    props.text,
  )
}
