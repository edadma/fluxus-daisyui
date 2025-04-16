package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** TooltipProps defines the properties for the Tooltip component.
  *
  * @param tip
  *   the text content of the tooltip.
  * @param position
  *   optional position specifier. Allowed values are "top", "bottom", "left", or "right".
  * @param color
  *   optional color variant. Allowed values are "primary", "secondary", etc.
  * @param className
  *   additional custom classes that will be appended.
  * @param children
  *   the content which the tooltip wraps.
  */
case class TooltipProps(
    tip: String,
    position: Option[String] = None,
    color: Option[String] = None,
    className: Option[String] = None,
    children: FluxusNode,
)

/** Tooltip component using DaisyUI styling.
  *
  * This component wraps its children in a <div> that applies the DaisyUI tooltip classes, and injects the tooltip text
  * via the "data-tip" attribute.
  */
val Tooltip = (props: TooltipProps) => {
  val baseClass = "tooltip"

  // Use fixed mapping to set tooltip position class so that Tailwind can detect it at compile-time.
  val positionClass = props.position match {
    case Some("top")    => "tooltip-top"
    case Some("bottom") => "tooltip-bottom"
    case Some("left")   => "tooltip-left"
    case Some("right")  => "tooltip-right"
    case _              => ""
  }

  // Fixed mapping for color variants.
  val colorClass = props.color match {
    case Some("primary")   => "tooltip-primary"
    case Some("secondary") => "tooltip-secondary"
    case Some("accent")    => "tooltip-accent"
    case Some("info")      => "tooltip-info"
    case Some("success")   => "tooltip-success"
    case Some("warning")   => "tooltip-warning"
    case Some("error")     => "tooltip-error"
    case _                 => ""
  }

  val extraClass = props.className.getOrElse("")
  val classes    = Seq(baseClass, positionClass, colorClass, extraClass).filter(_.nonEmpty).mkString(" ")

  div(
    cls        := classes,
    "data-tip" := props.tip,
    props.children,
  )
}
