package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Alert component props with DaisyUI styling options
  */
case class AlertProps(
    // Content options
    title: Option[String] = None,
    message: Option[String] = None,
    children: Option[FluxusNode] = None,

    // Style variants
    variant: String = "info", // info, success, warning, error, neutral

    // Icon options
    icon: Option[FluxusNode] = None,
    showIcon: Boolean = true,  // Whether to show the default icon for the variant
    hideIcon: Boolean = false, // Alternative way to hide the icon (takes precedence)

    // Action options
    actions: Option[FluxusNode] = None,
    onClose: Option[() => Unit] = None, // Will add a close button if provided

    // Additional styling
    className: String = "",
    compact: Boolean = false, // Reduces padding
)

/** Alert component using DaisyUI styling
  *
  * Features:
  *   - Multiple style variants (info, success, warning, error, neutral)
  *   - Optional title and body content
  *   - Default variant icons or custom icons
  *   - Support for action buttons
  *   - Optional close button
  */
val Alert = (props: AlertProps) => {
  val classes = List.newBuilder[String]

  // Base class is always present
  classes += "alert"

  // Handle variant - must use predefined Tailwind classes
  val variantClass = props.variant match {
    case "info"    => "alert-info"
    case "success" => "alert-success"
    case "warning" => "alert-warning"
    case "error"   => "alert-error"
    case "neutral" => "alert-neutral"
    case _         => "alert-info" // Default to info
  }

  // Add conditional classes
  if (variantClass.nonEmpty) classes += variantClass
  if (props.compact) classes += "alert-sm" // Smaller padding for compact alerts

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val alertClass = classes.result().mkString(" ")

  // Determine if we should show an icon
  val showIcon = props.showIcon && !props.hideIcon

  // Default icons for each variant
  def getDefaultIcon(): FluxusNode = {
    props.variant match {
      case "info" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          fill           := "none",
          viewBox        := "0 0 24 24",
          cls            := "stroke-current shrink-0 w-6 h-6",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
      case "success" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "stroke-current shrink-0 h-6 w-6",
          fill           := "none",
          viewBox        := "0 0 24 24",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
      case "warning" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "stroke-current shrink-0 h-6 w-6",
          fill           := "none",
          viewBox        := "0 0 24 24",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(
            d := "M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z",
          ),
        )
      case "error" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "stroke-current shrink-0 h-6 w-6",
          fill           := "none",
          viewBox        := "0 0 24 24",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
      case _ =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          fill           := "none",
          viewBox        := "0 0 24 24",
          cls            := "stroke-current shrink-0 w-6 h-6",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
    }
  }

  // Close button for dismissible alerts
  def closeButton(): FluxusNode = {
    button(
      cls     := "btn btn-sm btn-ghost",
      onClick := (() => props.onClose.foreach(_())),
      svg(
        xmlns          := "http://www.w3.org/2000/svg",
        cls            := "h-4 w-4",
        fill           := "none",
        viewBox        := "0 0 24 24",
        stroke         := "currentColor",
        strokeWidth    := "2",
        strokeLinecap  := "round",
        strokeLinejoin := "round",
        path(d := "M6 18L18 6M6 6l12 12"),
      ),
    )
  }

  // Determine content
  val content =
    if (props.children.isDefined) {
      // Use custom children content
      props.children.get
    } else {
      // Use title and/or message
      div(
        props.title.map(titleText =>
          h3(cls := "font-bold", titleText),
        ).orNull,
        props.message.map(messageText =>
          div(cls := (if (props.title.isDefined) "mt-1" else ""), messageText),
        ).orNull,
      )
    }

  // Main component render
  div(
    cls  := alertClass,
    role := "alert",

    // Left side: Icon (if enabled)
    if (showIcon) {
      props.icon.getOrElse(getDefaultIcon())
    } else null,

    // Middle: Content
    div(
      cls := "flex-1",
      content,
    ),

    // Right side: Actions and/or close button
    if (props.actions.isDefined || props.onClose.isDefined) {
      div(
        cls := "flex-none flex items-center gap-2",
        props.actions.orNull,
        props.onClose.map(_ => closeButton()).orNull,
      )
    } else null,
  )
}
