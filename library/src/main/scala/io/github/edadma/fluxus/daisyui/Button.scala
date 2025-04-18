package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

case class ButtonProps(
    // Core properties
    text: String = "",
    children: Option[FluxusNode] = None,
    variant: String = "primary",  // primary, secondary, accent, info, success, warning, error, ghost, link, neutral
    size: String = "md",          // lg, md, sm, xs
    shape: Option[String] = None, // circle, square

    // Link properties (new)
    href: Option[String] = None,   // URL when button should act as a link
    target: Option[String] = None, // Target for link (_blank, _self, etc.)

    // Button style modifiers
    soft: Boolean = false, // Softer, lower-opacity version of the variant color
    dash: Boolean = false, // Dashed border style
    outline: Boolean = false,
    wide: Boolean = false,
    glass: Boolean = false,
    block: Boolean = false,        // Full width button
    active: Boolean = false,       // Force active state
    focusVisible: Boolean = false, // Only show focus styles with keyboard navigation

    // Responsive variants
    smVariant: Option[String] = None, // variant at sm breakpoint
    mdVariant: Option[String] = None, // variant at md breakpoint
    lgVariant: Option[String] = None, // variant at lg breakpoint
    xlVariant: Option[String] = None, // variant at xl breakpoint

    // Responsive sizes
    smSize: Option[String] = None, // size at sm breakpoint
    mdSize: Option[String] = None, // size at md breakpoint
    lgSize: Option[String] = None, // size at lg breakpoint
    xlSize: Option[String] = None, // size at xl breakpoint

    // State indicators
    loading: Boolean = false,
    disabled: Boolean = false,
    noAnimation: Boolean = false,

    // Events
    onClick: () => Unit = () => (),
    onMouseEnter: Option[dom.MouseEvent => Unit] = None,
    onMouseLeave: Option[dom.MouseEvent => Unit] = None,

    // Additional styling
    startIcon: Option[FluxusNode] = None,
    endIcon: Option[FluxusNode] = None,
    className: String = "",

    // Accessibility
    ariaLabel: Option[String] = None,
    tabIndex: Option[Int] = None,
    buttonRole: Option[String] = None,

    // HTML button attributes
    buttonType: String = "button", // button, submit, reset
    name: Option[String] = None,
    value: Option[String] = None,
)

val Button = (props: ButtonProps) => {
  val classes = List.newBuilder[String]

  // Base class is always present
  classes += "btn"

  // Handle variant - must use predefined Tailwind classes
  val variantClass = props.variant match {
    case "primary"   => "btn-primary"
    case "secondary" => "btn-secondary"
    case "accent"    => "btn-accent"
    case "info"      => "btn-info"
    case "success"   => "btn-success"
    case "warning"   => "btn-warning"
    case "error"     => "btn-error"
    case "ghost"     => "btn-ghost"
    case "link"      => "btn-link"
    case "neutral"   => "btn-neutral"
    case _           => ""
  }

  // Handle size - must use predefined Tailwind classes
  val sizeClass = props.size match {
    case "lg" => "btn-lg"
    case "md" => "btn-md"
    case "sm" => "btn-sm"
    case "xs" => "btn-xs"
    case _    => ""
  }

  // Handle shape - must use predefined Tailwind classes
  val shapeClass = props.shape match {
    case Some("circle") => "btn-circle"
    case Some("square") => "btn-square"
    case _              => ""
  }

  // Handle responsive variants - must use predefined Tailwind classes
  val smVariantClass = props.smVariant match {
    case Some("primary")   => "sm:btn-primary"
    case Some("secondary") => "sm:btn-secondary"
    case Some("accent")    => "sm:btn-accent"
    case Some("info")      => "sm:btn-info"
    case Some("success")   => "sm:btn-success"
    case Some("warning")   => "sm:btn-warning"
    case Some("error")     => "sm:btn-error"
    case Some("ghost")     => "sm:btn-ghost"
    case Some("link")      => "sm:btn-link"
    case Some("neutral")   => "sm:btn-neutral"
    case _                 => ""
  }

  val mdVariantClass = props.mdVariant match {
    case Some("primary")   => "md:btn-primary"
    case Some("secondary") => "md:btn-secondary"
    case Some("accent")    => "md:btn-accent"
    case Some("info")      => "md:btn-info"
    case Some("success")   => "md:btn-success"
    case Some("warning")   => "md:btn-warning"
    case Some("error")     => "md:btn-error"
    case Some("ghost")     => "md:btn-ghost"
    case Some("link")      => "md:btn-link"
    case Some("neutral")   => "md:btn-neutral"
    case _                 => ""
  }

  val lgVariantClass = props.lgVariant match {
    case Some("primary")   => "lg:btn-primary"
    case Some("secondary") => "lg:btn-secondary"
    case Some("accent")    => "lg:btn-accent"
    case Some("info")      => "lg:btn-info"
    case Some("success")   => "lg:btn-success"
    case Some("warning")   => "lg:btn-warning"
    case Some("error")     => "lg:btn-error"
    case Some("ghost")     => "lg:btn-ghost"
    case Some("link")      => "lg:btn-link"
    case Some("neutral")   => "lg:btn-neutral"
    case _                 => ""
  }

  val xlVariantClass = props.xlVariant match {
    case Some("primary")   => "xl:btn-primary"
    case Some("secondary") => "xl:btn-secondary"
    case Some("accent")    => "xl:btn-accent"
    case Some("info")      => "xl:btn-info"
    case Some("success")   => "xl:btn-success"
    case Some("warning")   => "xl:btn-warning"
    case Some("error")     => "xl:btn-error"
    case Some("ghost")     => "xl:btn-ghost"
    case Some("link")      => "xl:btn-link"
    case Some("neutral")   => "xl:btn-neutral"
    case _                 => ""
  }

  // Handle responsive sizes - must use predefined Tailwind classes
  val smSizeClass = props.smSize match {
    case Some("lg") => "sm:btn-lg"
    case Some("md") => "sm:btn-md"
    case Some("sm") => "sm:btn-sm"
    case Some("xs") => "sm:btn-xs"
    case _          => ""
  }

  val mdSizeClass = props.mdSize match {
    case Some("lg") => "md:btn-lg"
    case Some("md") => "md:btn-md"
    case Some("sm") => "md:btn-sm"
    case Some("xs") => "md:btn-xs"
    case _          => ""
  }

  val lgSizeClass = props.lgSize match {
    case Some("lg") => "lg:btn-lg"
    case Some("md") => "lg:btn-md"
    case Some("sm") => "lg:btn-sm"
    case Some("xs") => "lg:btn-xs"
    case _          => ""
  }

  val xlSizeClass = props.xlSize match {
    case Some("lg") => "xl:btn-lg"
    case Some("md") => "xl:btn-md"
    case Some("sm") => "xl:btn-sm"
    case Some("xs") => "xl:btn-xs"
    case _          => ""
  }

  // Add conditional classes
  if (variantClass.nonEmpty) classes += variantClass
  if (sizeClass.nonEmpty) classes += sizeClass
  if (shapeClass.nonEmpty) classes += shapeClass
  if (smVariantClass.nonEmpty) classes += smVariantClass
  if (mdVariantClass.nonEmpty) classes += mdVariantClass
  if (lgVariantClass.nonEmpty) classes += lgVariantClass
  if (xlVariantClass.nonEmpty) classes += xlVariantClass
  if (smSizeClass.nonEmpty) classes += smSizeClass
  if (mdSizeClass.nonEmpty) classes += mdSizeClass
  if (lgSizeClass.nonEmpty) classes += lgSizeClass
  if (xlSizeClass.nonEmpty) classes += xlSizeClass

  // Apply soft variant styling
  if (props.soft) {
    classes += "btn-soft"
  }

  // Apply dash variant styling
  if (props.dash) {
    classes += "btn-dash"
  }

  if (props.outline) classes += "btn-outline"
  if (props.wide) classes += "btn-wide"
  if (props.block) classes += "btn-block"
  if (props.glass) classes += "glass"
  if (props.loading) classes += "btn-loading"
  if (props.active) classes += "btn-active"
  if (props.focusVisible)
    classes += "focus-visible:outline-primary focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2"
  if (props.noAnimation) classes += "no-animation"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val buttonClass = classes.result().mkString(" ")

  // Determine content: icons + text/children
  val contentNodes = List.newBuilder[FluxusNode]

  // Add start icon if specified
  if (props.startIcon.isDefined) {
    contentNodes += div(cls := "mr-2", props.startIcon.get)
  }

  // Add text content if specified
  if (props.text.nonEmpty) {
    contentNodes += span(props.text)
  }

  // Add children if specified (overrides text)
  if (props.children.isDefined) {
    contentNodes += props.children.get
  }

  // Add end icon if specified
  if (props.endIcon.isDefined) {
    contentNodes += div(cls := "ml-2", props.endIcon.get)
  }

  // Render as either a link or a button based on href prop
  props.href match {
    case Some(url) =>
      // Render as anchor when href is provided
      a(
        cls  := buttonClass,
        href := url,
        props.target.map(t => target := t).orNull,
        onClick := (() => props.onClick()),

        // Conditional event handlers
        if (props.onMouseEnter.isDefined) onMouseEnter := props.onMouseEnter.get else null,
        if (props.onMouseLeave.isDefined) onMouseLeave := props.onMouseLeave.get else null,

        // Conditional attributes
        if (props.disabled) {
          Seq(
            tabIndex      := -1,
            aria_disabled := "true",
            cls           := "pointer-events-none opacity-50",
          )
        } else null,
        props.ariaLabel.map(al => aria_label := al).orNull,
        props.tabIndex.map(ti => tabIndex := ti).orNull,
        props.buttonRole.map(r => role := r).orNull,

        // Content
        contentNodes.result(),
      )

    case None =>
      // Render as button when no href
      button(
        cls     := buttonClass,
        typ     := props.buttonType,
        onClick := (() => props.onClick()),

        // Conditional event handlers
        if (props.onMouseEnter.isDefined) onMouseEnter := props.onMouseEnter.get else null,
        if (props.onMouseLeave.isDefined) onMouseLeave := props.onMouseLeave.get else null,

        // Conditional attributes
        if (props.disabled) disabled := true else null,
        props.ariaLabel.map(al => aria_label := al).orNull,
        props.tabIndex.map(ti => tabIndex := ti).orNull,
        props.buttonRole.map(r => role := r).orNull,
        props.name.map(n => name := n).orNull,
        props.value.map(v => value := v).orNull,

        // Content
        contentNodes.result(),
      )
  }
}
