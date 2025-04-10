package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Enhanced Card props with additional DaisyUI styling options
  */
case class CardProps(
    title: Option[String] = None,
    children: FluxusNode,
    imageSrc: Option[String] = None,
    imageAlt: String = "",
    variant: String = "normal", // normal, compact, side
    bordered: Boolean = true,
    bgClass: String = "bg-base-200", // Complete class name: "bg-base-100", "bg-base-200", etc.
    textClass: String = "",          // Complete class name: "text-primary", "text-secondary", etc.
    shadowClass: String = "shadow",  // Complete class name: "shadow-sm", "shadow", etc.
    glass: Boolean = false,
    hover: Boolean = false,
    footer: Option[FluxusNode] = None,
    actions: Option[FluxusNode] = None,
    headerActions: Option[FluxusNode] = None,
    padding: String = "normal", // normal, compact, none
    className: String = "",
)

/** Enhanced Card component with additional DaisyUI styling options Features:
  *   - Default styling with slightly different interior color
  *   - Visible border by default
  *   - Theme-aware styling using DaisyUI classes
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

  // Always add a border by default, unless explicitly turned off
  if (props.bordered) classes += "border border-base-300"

  // Add glass effect if specified
  if (props.glass) classes += "glass"

  // Add hover effect classes if specified
  if (props.hover) {
    classes += "hover:shadow-lg"
    classes += "transition-all"
    classes += "duration-200"
  }

  // Add background class (complete class name)
  if (props.bgClass.nonEmpty) {
    classes += props.bgClass
  }

  // Add text color class (complete class name)
  if (props.textClass.nonEmpty) classes += props.textClass

  // Add shadow class (complete class name)
  if (props.shadowClass.nonEmpty) classes += props.shadowClass

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val cardClass = classes.result().mkString(" ")

  // Determine card body padding class
  val bodyPaddingClass = props.padding match {
    case "compact" => "p-2"
    case "none"    => "p-0"
    case _         => "" // normal padding is default in card-body
  }

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
      cls := s"card-body ${bodyPaddingClass}",
      // Header section with title and optional actions
      if (props.title.isDefined || props.headerActions.isDefined)
        div(
          cls := "flex justify-between items-center mb-2",
          props.title.map(title =>
            h2(
              cls := "card-title",
              title,
            ),
          ).getOrElse(div()),
          props.headerActions.orNull,
        )
      else null,
      // Main content
      props.children,
      // Actions section if specified
      props.actions.map(actions =>
        div(
          cls := "card-actions justify-end mt-4",
          actions,
        ),
      ).orNull,
    ),
    // Footer if specified
    props.footer.map(footerContent =>
      div(
        cls := "card-footer p-4 border-t border-base-300",
        footerContent,
      ),
    ).orNull,
  )
}
