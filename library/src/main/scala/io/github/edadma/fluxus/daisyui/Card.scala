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
  val baseClass     = "card"
  val variantClass  = if (props.variant != "normal") s"card-${props.variant}" else ""
  val borderedClass = if (props.bordered) "card-bordered" else ""

  val cardClass = List(
    baseClass,
    variantClass,
    borderedClass,
    props.className,
  ).filter(_.nonEmpty).mkString(" ")

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
