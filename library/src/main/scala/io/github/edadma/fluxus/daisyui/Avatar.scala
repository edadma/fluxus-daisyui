package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Avatar props with comprehensive DaisyUI styling options
  */
case class AvatarProps(
    // Core display options
    src: Option[String] = None,      // Image source URL
    alt: String = "avatar",          // Alt text for image
    text: Option[String] = None,     // Text to display when no image (initials, etc.)
    icon: Option[FluxusNode] = None, // Icon to display when no image or text

    // Sizing options
    size: String = "md", // xs, sm, md, lg

    // Shape options
    shape: String = "rounded", // rounded (default), square

    // Border & outline options
    border: Boolean = false,    // Add border
    borderColor: String = "",   // border-primary, border-secondary, etc.
    borderWidth: String = "",   // border-2, border-4, etc.
    outline: Boolean = false,   // Add outline effect
    outlineColor: String = "",  // outline-primary, outline-secondary, etc.
    outlineOffset: String = "", // outline-offset-2, outline-offset-4, etc.
    ring: Boolean = false,      // Add ring effect
    ringColor: String = "",     // ring-primary, ring-secondary, etc.
    ringOffset: String = "",    // ring-offset-2, ring-offset-4, etc.

    // Status indicator options
    online: Boolean = false,  // Show online status indicator
    offline: Boolean = false, // Show offline status indicator
    busy: Boolean = false,    // Show busy status indicator
    away: Boolean = false,    // Show away status indicator

    // Placeholder options
    placeholder: Boolean = false, // Force placeholder style even with image

    // Additional styling
    bgClass: String = "",        // Background class for placeholder
    textColorClass: String = "", // Text color class
    className: String = "",      // Additional custom classes
)

/** Avatar component using DaisyUI styling Features:
  *   - Image, text and icon support
  *   - Multiple sizes and shapes
  *   - Border and outline customization
  *   - Status indicators
  *   - Placeholder styling
  */
val Avatar = (props: AvatarProps) => {
  val classes = List.newBuilder[String]

  // Base class is always present
  classes += "avatar"

  // Handle placeholder
  if (props.placeholder || (props.src.isEmpty && props.text.isEmpty && props.icon.isEmpty)) {
    classes += "placeholder"
  }

  // Add online/offline status classes
  if (props.online) classes += "online"
  if (props.offline) classes += "offline"
  if (props.busy) classes += "busy"
  if (props.away) classes += "away"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val avatarClass = classes.result().mkString(" ")

  // Inner element classes (for the actual avatar display)
  val innerClasses = List.newBuilder[String]

  // Base inner element class
  innerClasses += "w-auto"

  // Size classes - must use predefined Tailwind classes
  val sizeClass = props.size match {
    case "xs" => "w-8 h-8"   // Extra small
    case "sm" => "w-12 h-12" // Small
    case "md" => "w-16 h-16" // Medium (default)
    case "lg" => "w-24 h-24" // Large
    case _    => "w-16 h-16" // Default to medium
  }

  innerClasses += sizeClass

  // Shape class - must use predefined Tailwind classes
  val shapeClass = props.shape match {
    case "square"   => "mask mask-square"
    case "rounded"  => "rounded-full" // Default is rounded
    case "hexagon"  => "mask mask-hexagon"
    case "triangle" => "mask mask-triangle"
    case "squircle" => "mask mask-squircle"
    case _          => "rounded-full" // Default to rounded
  }

  innerClasses += shapeClass

  // Add conditional classes for border, outline, and ring
  if (props.border) {
    innerClasses += "border"
    if (props.borderWidth.nonEmpty) innerClasses += props.borderWidth
    if (props.borderColor.nonEmpty) innerClasses += props.borderColor
  }

  if (props.outline) {
    innerClasses += "outline"
    if (props.outlineColor.nonEmpty) innerClasses += props.outlineColor
    if (props.outlineOffset.nonEmpty) innerClasses += props.outlineOffset
  }

  if (props.ring) {
    innerClasses += "ring"
    if (props.ringColor.nonEmpty) innerClasses += props.ringColor
    if (props.ringOffset.nonEmpty) innerClasses += props.ringOffset
  }

  // Add background and text color classes
  if (props.bgClass.nonEmpty) innerClasses += props.bgClass
  if (props.textColorClass.nonEmpty) innerClasses += props.textColorClass

  // Join all inner classes with spaces
  val innerClass = innerClasses.result().mkString(" ")

  // Create avatar structure
  div(
    cls := avatarClass,

    // Create inner content based on what's provided
    if (props.src.isDefined && !props.placeholder) {
      // Image avatar
      div(
        cls := innerClass,
        img(
          src := props.src.get,
          alt := props.alt,
        ),
      )
    } else if (props.text.isDefined) {
      // Text placeholder
      div(
        cls := innerClass + " flex items-center justify-center bg-neutral-focus text-neutral-content",
        span(props.text.get),
      )
    } else if (props.icon.isDefined) {
      // Icon placeholder
      div(
        cls := innerClass + " flex items-center justify-center bg-neutral-focus text-neutral-content",
        props.icon.get,
      )
    } else {
      // Empty placeholder
      div(
        cls := innerClass + " flex items-center justify-center bg-neutral-focus text-neutral-content",
        span("?"),
      )
    },
  )
}

/** AvatarGroup props
  */
case class AvatarGroupProps(
    children: List[FluxusNode],
    size: String = "md",    // xs, sm, md, lg
    offset: String = "end", // "start", "center", "end"
    className: String = "",
)

/** Avatar Group component Renders multiple avatars with overlap styling
  */
val AvatarGroup = (props: AvatarGroupProps) => {
  val classes = List.newBuilder[String]

  // Base class is always present
  classes += "avatar-group"

  // Set positioning offset
  val offsetClass = props.offset match {
    case "start"  => "-space-x-4 justify-start"
    case "center" => "-space-x-4 justify-center"
    case "end"    => "-space-x-4 justify-end"
    case _        => "-space-x-4 justify-end" // Default to end
  }

  classes += offsetClass

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val groupClass = classes.result().mkString(" ")

  div(
    cls := groupClass,
    props.children,
  )
}
