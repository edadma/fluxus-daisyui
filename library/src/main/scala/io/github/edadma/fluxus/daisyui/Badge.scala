package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Badge/Tag component props with DaisyUI styling options
  */
case class BadgeProps(
    // Core properties
    text: String = "",
    children: Option[FluxusNode] = None,

    // Style variants
    variant: String = "primary", // primary, secondary, accent, info, success, warning, error, ghost, neutral
    size: String = "md",         // xs, sm, md, lg
    outline: Boolean = false,

    // Special rendering options
    empty: Boolean = false,     // Render an empty badge (useful for indicators)
    fullWidth: Boolean = false, // Make the badge full width

    // Link properties
    href: Option[String] = None,   // Make the badge a link
    target: Option[String] = None, // Target for link (_blank, _self, etc.)

    // Accessibility
    ariaLabel: Option[String] = None,

    // Other styling options
    className: String = "",
)

/** Badge/Tag component using DaisyUI styling
  *
  * Features:
  *   - Multiple variants (primary, secondary, etc.)
  *   - Size options (xs, sm, md, lg)
  *   - Outline style option
  *   - Can be rendered as a link
  *   - Support for custom content via children
  */
val Badge = (props: BadgeProps) => {
  val classes = List.newBuilder[String]

  // Base class is always present
  classes += "badge"

  // Handle variant - must use predefined Tailwind classes
  val variantClass = props.variant match {
    case "primary"   => "badge-primary"
    case "secondary" => "badge-secondary"
    case "accent"    => "badge-accent"
    case "info"      => "badge-info"
    case "success"   => "badge-success"
    case "warning"   => "badge-warning"
    case "error"     => "badge-error"
    case "ghost"     => "badge-ghost"
    case "neutral"   => "badge-neutral"
    case _           => ""
  }

  // Handle size - must use predefined Tailwind classes
  val sizeClass = props.size match {
    case "xs" => "badge-xs"
    case "sm" => "badge-sm"
    case "lg" => "badge-lg"
    case _    => "" // Default (md) has no specific class
  }

  // Add conditional classes
  if (variantClass.nonEmpty) classes += variantClass
  if (sizeClass.nonEmpty) classes += sizeClass
  if (props.outline) classes += "badge-outline"
  if (props.fullWidth) classes += "w-full"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val badgeClass = classes.result().mkString(" ")

  // Determine content
  val content =
    if (props.empty) {
      // Empty badge (no content)
      null
    } else if (props.children.isDefined) {
      // Custom children content (overrides text)
      props.children.get
    } else {
      // Text content
      props.text
    }

  // Render as a link or a span
  props.href match {
    case Some(url) =>
      // Render as a link
      a(
        cls  := badgeClass,
        href := url,
        props.target.map(t => target := t).orNull,
        props.ariaLabel.map(label => aria_label := label).orNull,
        content,
      )
    case None =>
      // Render as a span
      span(
        cls := badgeClass,
        props.ariaLabel.map(label => aria_label := label).orNull,
        content,
      )
  }
}
