package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Props for Menu component */
case class MenuProps(
    // Core props
    children: Seq[FluxusNode],

    // Styling options
    size: String = "md",        // xs, sm, md, lg
    variant: String = "normal", // normal, compact, horizontal
    bordered: Boolean = false,  // Whether to add border
    rounded: Boolean = false,   // Whether to add rounded corners

    // Background and styling
    bgClass: String = "",    // Background class (e.g., "bg-base-200")
    shadow: Boolean = false, // Whether to add shadow
    glass: Boolean = false,  // Whether to use glass effect

    // Additional styling
    className: String = "",

    // ARIA attributes
    ariaLabel: Option[String] = None,
)

/** Props for MenuItem component */
case class MenuItemProps(
    // Core props
    title: String = "",                  // Menu item text
    children: Option[FluxusNode] = None, // Alternative to text for custom content
    href: Option[String] = None,         // Link URL

    // State and interaction
    active: Boolean = false,            // Whether item is marked as active
    disabled: Boolean = false,          // Whether item is disabled
    onClick: Option[() => Unit] = None, // Click handler

    // Visual elements
    icon: Option[FluxusNode] = None,    // Icon to display at start of item
    endIcon: Option[FluxusNode] = None, // Icon to display at end of item
    badge: Option[String] = None,       // Badge text
    badgeVariant: String = "primary",   // Badge color variant

    // Additional styling
    className: String = "",
)

/** Props for MenuTitle component */
case class MenuTitleProps(
    // Core props
    title: String,                       // Section title text
    children: Option[FluxusNode] = None, // Alternative to text for custom content

    // Additional styling
    className: String = "",
)

/** Props for MenuDivider component */
case class MenuDividerProps(
    className: String = "",
)

/** Menu component using DaisyUI styling
  *
  * Features:
  *   - Vertical or horizontal navigation menu
  *   - Multiple size options
  *   - Support for active and disabled states
  *   - Integration with icons and badges
  *   - Section headers and dividers
  */
val Menu = (props: MenuProps) => {
  // Build menu classes
  val menuClasses = List.newBuilder[String]

  // Base class is always present
  menuClasses += "menu"

  // Size classes
  val sizeClass = props.size match {
    case "xs" => "menu-xs"
    case "sm" => "menu-sm"
    case "lg" => "menu-lg"
    case _    => "menu-md" // Default is medium
  }

  // Variant classes
  val variantClass = props.variant match {
    case "compact"    => "menu-compact"
    case "horizontal" => "menu-horizontal"
    case _            => "" // Default is normal
  }

  // Add conditional classes
  menuClasses += sizeClass
  if (variantClass.nonEmpty) menuClasses += variantClass
  if (props.bordered) menuClasses += "bordered"
  if (props.rounded) menuClasses += "rounded-box"
  if (props.shadow) menuClasses += "shadow"
  if (props.glass) menuClasses += "glass"
  if (props.bgClass.nonEmpty) menuClasses += props.bgClass

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => menuClasses += cls)
  }

  // Join all classes with spaces
  val menuClass = menuClasses.result().mkString(" ")

  // Render the menu component
  ul(
    cls  := menuClass,
    role := "menu",
    props.ariaLabel.map(label => aria_label := label).orNull,
    props.children,
  )
}

/** MenuItem component for Menu items */
val MenuItem = (props: MenuItemProps) => {
  // Build item classes
  val itemClasses = List.newBuilder[String]

  // Add state classes
  if (props.active) itemClasses += "active"
  if (props.disabled) itemClasses += "disabled"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => itemClasses += cls)
  }

  // Join all item classes with spaces
  val itemClass = itemClasses.result().mkString(" ")

  // Create badge element if needed
  val badgeElement = props.badge.map(text =>
    div(
      cls := s"badge badge-sm badge-${props.badgeVariant}",
      text,
    ),
  ).orNull

  // Create the list item
  li(
    cls := itemClass,

    // Create the anchor or div element
    if (props.href.isDefined && !props.disabled) {
      a(
        href    := props.href.get,
        cls     := itemClass,
        onClick := (props.onClick.map(handler => (() => handler())).getOrElse(null)),

        // Icon (if provided)
        props.icon.orNull,

        // Content
        if (props.children.isDefined) {
          props.children.get
        } else {
          span(props.title)
        },

        // Badge (if provided)
        badgeElement,

        // End icon (if provided)
        props.endIcon.orNull,
      )
    } else {
      // Regular menu item without link
      a(
        cls      := itemClass,
        onClick  := (if (props.disabled) null else props.onClick.map(handler => (() => handler())).getOrElse(null)),
        tabIndex := (if (props.disabled) -1 else 0),

        // Icon (if provided)
        props.icon.orNull,

        // Content
        if (props.children.isDefined) {
          props.children.get
        } else {
          span(props.title)
        },

        // Badge (if provided)
        badgeElement,

        // End icon (if provided)
        props.endIcon.orNull,
      )
    },
  )
}

/** MenuTitle component for section headers in Menu */
val MenuTitle = (props: MenuTitleProps) => {
  // Build title classes
  val titleClasses = List.newBuilder[String]

  // Base class
  titleClasses += "menu-title"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => titleClasses += cls)
  }

  // Join all title classes with spaces
  val titleClass = titleClasses.result().mkString(" ")

  // Create the title element
  li(
    cls := titleClass,
    if (props.children.isDefined) {
      props.children.get
    } else {
      span(props.title)
    },
  )
}

/** MenuDivider component for visual separation in Menu */
val MenuDivider = (props: MenuDividerProps) => {
  // Build divider classes
  val dividerClasses = List.newBuilder[String]

  // Base class
  dividerClasses += "divider"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => dividerClasses += cls)
  }

  // Join all divider classes with spaces
  val dividerClass = dividerClasses.result().mkString(" ")

  // Create the divider element
  li(
    cls := dividerClass,
  )
}

/** MenuSubmenu component for nested menus */
case class MenuSubmenuProps(
    // Core props
    title: String = "",              // Submenu title
    icon: Option[FluxusNode] = None, // Icon for the submenu
    children: Seq[FluxusNode],       // Submenu items

    // State and interaction
    active: Boolean = false,   // Whether submenu is marked as active
    expanded: Boolean = false, // Whether submenu is expanded by default
    disabled: Boolean = false, // Whether submenu is disabled

    // Additional styling
    className: String = "",
)

/** MenuSubmenu component - allows nested menus */
val MenuSubmenu = (props: MenuSubmenuProps) => {
  // Local state for submenu expansion
  val (isExpanded, setIsExpanded, _) = useState(props.expanded)

  // Build item classes
  val itemClasses = List.newBuilder[String]

  // Add state classes
  if (props.active) itemClasses += "active"
  if (props.disabled) itemClasses += "disabled"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => itemClasses += cls)
  }

  // Join all item classes with spaces
  val itemClass = itemClasses.result().mkString(" ")

  // Create the submenu
  li(
    // Main submenu title item
    details(
      open := isExpanded,
      onToggle := ((e: dom.Event) =>
        if (!props.disabled) setIsExpanded(!isExpanded)
      ),

      // Summary element (clickable header)
      summary(
        cls      := itemClass,
        tabIndex := (if (props.disabled) -1 else 0),

        // Icon (if provided)
        props.icon.orNull,

        // Title
        span(props.title),
      ),

      // Submenu items
      ul(
        cls := "p-2",
        props.children,
      ),
    ),
  )
}
