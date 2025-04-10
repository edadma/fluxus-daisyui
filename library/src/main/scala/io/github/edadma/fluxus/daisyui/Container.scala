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
  val classes = List.newBuilder[String]

  // Add conditional classes
  if (props.center) {
    classes += "container"
    classes += "mx-auto"
  }
  if (props.padding) classes += "px-4"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val containerClass = classes.result().mkString(" ")

  div(
    cls := containerClass,
    props.children,
  )
}
