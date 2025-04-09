package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Container props
  */
case class ContainerProps(
    children: FluxusNode,
    padding: Boolean = true,
    center: Boolean = true,
    className: String = "",
)

/** Container component
  */
val Container = (props: ContainerProps) => {
  val containerClass = List(
    if (props.center) "container mx-auto" else "",
    if (props.padding) "px-4" else "",
    props.className,
  ).filter(_.nonEmpty).mkString(" ")

  div(
    cls := containerClass,
    props.children,
  )
}
