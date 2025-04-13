package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Label props with DaisyUI styling options
  */
case class LabelProps(
    // Core content
    text: String = "",                   // Text content of the label
    children: Option[FluxusNode] = None, // Alternative to text for complex content
    altText: Option[String] = None,      // Additional text (hint, error)

    // HTML attributes
    htmlFor: Option[String] = None, // Associates label with a form control

    // Display options
    required: Boolean = false,       // Show required indicator (asterisk)
    requiredIndicator: String = "*", // Character to use for required indicator

    // Positioning options
    position: String = "default", // default, top, left, right, floating
    swap: Boolean = false,        // Swap label and alt text positions

    // Styling options
    size: String = "md",       // xs, sm, md, lg
    variant: String = "",      // primary, secondary, accent, etc.
    altVariant: String = "",   // Variant for alt text (e.g., error)
    textClass: String = "",    // Text styling class (text-primary, etc.)
    altTextClass: String = "", // Class for alt text

    // Additional styling
    className: String = "", // Additional custom classes
)

/** Label component using DaisyUI styling
  *
  * Features:
  *   - Associates with form controls via htmlFor
  *   - Supports required field indication
  *   - Multiple positioning options (top, left, right, floating)
  *   - Support for alternative text (hints, errors)
  *   - Follows DaisyUI's form styling patterns
  */
val Label = (props: LabelProps) => {
  // Determine if this is a standalone label or one to be used in a form-control context
  val isFormControl = props.position != "default"

  // Base container classes
  val containerClasses = List.newBuilder[String]

  // If used in form-control context, just use label class
  if (!isFormControl) {
    containerClasses += "label"
  } else {
    // For positional usage, we'll apply different classes
    val positionClass = props.position match {
      case "floating" => "label-floating"
      case _          => "label"
    }
    containerClasses += positionClass
  }

  // Add any custom container classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(containerClasses += _)
  }

  // Label text specific classes
  val textClasses = List.newBuilder[String]

  // Base label-text class
  val sizeClass = props.size match {
    case "xs" => "label-text-xs"
    case "sm" => "label-text-sm"
    case "lg" => "label-text-lg"
    case _    => "label-text" // Default is medium
  }
  textClasses += sizeClass

  // Handle variant classes for main text
  if (props.variant.nonEmpty) {
    val variantClass = props.variant match {
      case "primary"   => "text-primary"
      case "secondary" => "text-secondary"
      case "accent"    => "text-accent"
      case "info"      => "text-info"
      case "success"   => "text-success"
      case "warning"   => "text-warning"
      case "error"     => "text-error"
      case _           => ""
    }
    if (variantClass.nonEmpty) textClasses += variantClass
  }

  // Add any specific text classes
  if (props.textClass.nonEmpty) {
    props.textClass.split(" ").foreach(textClasses += _)
  }

  // Join text classes
  val textClass = textClasses.result().mkString(" ")

  // Alternative text classes
  val altTextClasses = List.newBuilder[String]

  // Base alt text class
  altTextClasses += "label-text-alt"

  // Handle variant classes for alt text
  if (props.altVariant.nonEmpty) {
    val altVariantClass = props.altVariant match {
      case "primary"   => "text-primary"
      case "secondary" => "text-secondary"
      case "accent"    => "text-accent"
      case "info"      => "text-info"
      case "success"   => "text-success"
      case "warning"   => "text-warning"
      case "error"     => "text-error"
      case _           => ""
    }
    if (altVariantClass.nonEmpty) altTextClasses += altVariantClass
  }

  // Add any specific alt text classes
  if (props.altTextClass.nonEmpty) {
    props.altTextClass.split(" ").foreach(altTextClasses += _)
  }

  // Join alt text classes
  val altTextClass = altTextClasses.result().mkString(" ")

  // Create main text content
  val mainContent = {
    if (props.children.isDefined) {
      props.children.get
    } else {
      div(
        cls := textClass,
        props.text,
        if (props.required) span(cls := "text-error ml-1", props.requiredIndicator) else null,
      )
    }
  }

  // Create alt text content if provided
  val altContent = props.altText.map(alt =>
    div(
      cls := altTextClass,
      alt,
    ),
  ).orNull

  // Render the label
  label(
    cls := containerClasses.result().mkString(" "),
    props.htmlFor.map(id => "for" := id).orNull,

    // Handle different positioning components
    props.position match {
      case "floating" =>
        // Floating label (special case)
        mainContent

      case _ if props.swap && props.altText.isDefined =>
        // Swapped labels (alt text first, then main text)
        Seq(div(cls := "label-text-alt", altContent), div(cls := "label-text", mainContent))

      case _ =>
        // Normal positioning (main text first, then alt)
        Seq(mainContent, altContent)
    },
  )
}
