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
  val baseClass        = "btn"
  val variantClass     = s"btn-${props.variant}"
  val sizeClass        = s"btn-${props.size}"
  val shapeClass       = props.shape.map(s => s"btn-$s").getOrElse("")
  val outlineClass     = if (props.outline) "btn-outline" else ""
  val wideClass        = if (props.wide) "btn-wide" else ""
  val glassClass       = if (props.glass) "btn-glass" else ""
  val loadingClass     = if (props.loading) "btn-loading" else ""
  val disabledAttr     = if (props.disabled) disabled := true else null
  val noAnimationClass = if (props.noAnimation) "no-animation" else ""

  val buttonClass = List(
    baseClass,
    variantClass,
    sizeClass,
    shapeClass,
    outlineClass,
    wideClass,
    glassClass,
    loadingClass,
    noAnimationClass,
    props.className,
  ).filter(_.nonEmpty).mkString(" ")

  button(
    cls     := buttonClass,
    onClick := (() => props.onClick()),
    disabledAttr,
    props.text,
  )
}
