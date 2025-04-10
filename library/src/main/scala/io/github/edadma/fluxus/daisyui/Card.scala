package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Card props
  */
case class CardProps(
    title: Option[String] = None,
    children: FluxusNode,
    imageSrc: Option[String] = None,
    imageAlt: String = "",
    variant: String = "normal", // normal, compact, side
    bordered: Boolean = true,
    className: String = "",
)

/** Card component
  */
val Card = (props: CardProps) => {
  val classes = List.newBuilder[String]

  // Base class is always present
  classes += "card"

  // Handle variant - must use predefined Tailwind classes
  val variantClass = props.variant match {
    case "compact" => "card-compact"
    case "side"    => "card-side"
    case _         => ""
  }

  // Add conditional classes
  if (variantClass.nonEmpty) classes += variantClass
  if (props.bordered) classes += "card-bordered"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val cardClass = classes.result().mkString(" ")

  div(
    cls := cardClass,
    props.imageSrc.map(src =>
      figure(
        img(
          src := src,
          alt := props.imageAlt,
        ),
      ),
    ).orNull,
    div(
      cls := "card-body",
      props.title.map(title =>
        h2(
          cls := "card-title",
          title,
        ),
      ).orNull,
      props.children,
    ),
  )
}
