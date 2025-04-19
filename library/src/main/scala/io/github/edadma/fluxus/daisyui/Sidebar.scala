package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Navigation item model for sidebar menus */
case class NavItem(
    id: String,                         // Unique identifier
    title: String,                      // Menu item text
    icon: Option[FluxusNode] = None,    // Icon for the item
    href: Option[String] = None,        // Link URL
    onClick: Option[() => Unit] = None, // Click handler
    badge: Option[String] = None,       // Badge text
    badgeVariant: String = "primary",   // Badge color variant
    items: List[NavItem] = List(),      // Child menu items
    isActive: Boolean = false,          // Whether item is active
    disabled: Boolean = false,          // Whether item is disabled
)

/** Props for Sidebar component */
case class SidebarProps(
    // Core props
    items: List[NavItem], // Navigation items to display in the sidebar

    // Styling options
    variant: String = "normal", // Sidebar style: normal, compact, boxed
    size: String = "md",        // Menu size: xs, sm, md, lg
    bordered: Boolean = false,  // Whether to show border
    rounded: Boolean = true,    // Whether to use rounded corners
    bgClass: String = "",       // Background CSS class
    textClass: String = "",     // Text color CSS class

    // Dimensions
    width: String = "w-64",          // Width when expanded
    collapsedWidth: String = "w-16", // Width when collapsed

    // Collapsible behavior
    collapsible: Boolean = false,                     // Whether sidebar can collapse
    collapsed: Boolean = false,                       // Whether sidebar is collapsed
    onCollapseChange: Option[Boolean => Unit] = None, // Collapse state change handler

    // Navigation state
    expandedSections: List[String] = List(),                // IDs of initially expanded sections
    onNavigation: Option[(String, NavItem) => Unit] = None, // Navigation handler

    // UI options
    collapseButtonPosition: String = "top", // Position: top, bottom
    showToggleIcons: Boolean = true,        // Whether to show section toggle icons

    // Additional styling
    className: String = "",    // Additional sidebar CSS classes
    menuClassName: String = "", // Additional menu CSS classes
)

/** Sidebar component using DaisyUI Menu
  *
  * Features:
  *   - Collapsible sidebar with icons and labels
  *   - Support for nested menu sections
  *   - Integration with badges and active states
  *   - Customizable styling options
  */
val Sidebar = (props: SidebarProps) => {
  // Local state for collapsed status (controlled or uncontrolled)
  val (isCollapsed, setIsCollapsed, _) = useState(props.collapsed)

  // Handler for toggling collapse state
  def toggleCollapse(): Unit = {
    val newState = !isCollapsed
    setIsCollapsed(newState)
    props.onCollapseChange.foreach(_(newState))
  }

  // Navigate to a menu item
  def handleNavigation(id: String, item: NavItem): Unit = {
    props.onNavigation.foreach(_(id, item))
  }

  // Build sidebar classes
  val sidebarClasses = List.newBuilder[String]

  // Base classes
  sidebarClasses += "h-full"
  sidebarClasses += "transition-all"
  sidebarClasses += "duration-300"
  sidebarClasses += "flex"
  sidebarClasses += "flex-col"

  // Width classes
  sidebarClasses += (if (isCollapsed) props.collapsedWidth else props.width)

  // Styling classes
  if (props.bordered) sidebarClasses += "border-r"
  if (props.bordered) sidebarClasses += "border-base-300"
  if (props.rounded) sidebarClasses += "rounded-r-box"
  if (props.bgClass.nonEmpty) sidebarClasses += props.bgClass
  if (props.textClass.nonEmpty) sidebarClasses += props.textClass

  // Custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => sidebarClasses += cls)
  }

  // Build the menu items recursively
  def buildMenuItems(items: List[NavItem], parentId: String = ""): Seq[FluxusNode] = {
    items.map { item =>
      val itemId = if (parentId.isEmpty) item.id else s"$parentId-${item.id}"

      if (item.items.nonEmpty) {
        // Item with children - render as a submenu
        MenuSubmenu <> MenuSubmenuProps(
          title = if (isCollapsed && item.icon.isDefined) "" else item.title,
          icon = item.icon,
          active = item.isActive,
          expanded = props.expandedSections.contains(itemId),
          disabled = item.disabled,
          children = buildMenuItems(item.items, itemId),
        )
      } else {
        // Regular menu item
        MenuItem <> MenuItemProps(
          title = if (isCollapsed && item.icon.isDefined) "" else item.title,
          icon = item.icon,
          badge = if (isCollapsed) None else item.badge,
          badgeVariant = item.badgeVariant,
          active = item.isActive,
          disabled = item.disabled,
          href = item.href,
          onClick = Some(() => {
            item.onClick.foreach(_())
            handleNavigation(itemId, item)
          }),
        )
      }
    }
  }

  // Create the sidebar component
  div(
    cls := sidebarClasses.result().mkString(" "),

    // Collapse toggle button (if at top position)
    if (props.collapsible && props.collapseButtonPosition == "top") {
      div(
        cls := "flex justify-end p-2",
        button(
          cls        := "btn btn-sm btn-ghost",
          aria_label := (if (isCollapsed) "Expand sidebar" else "Collapse sidebar"),
          onClick    := (() => toggleCollapse()),
          if (isCollapsed) {
            // Arrow pointing right when collapsed
            svg(
              xmlns          := "http://www.w3.org/2000/svg",
              width          := "16",
              height         := "16",
              viewBox        := "0 0 24 24",
              fill           := "none",
              stroke         := "currentColor",
              strokeWidth    := "2",
              strokeLinecap  := "round",
              strokeLinejoin := "round",
              polyline(points := "9 18 15 12 9 6"),
            )
          } else {
            // Arrow pointing left when expanded
            svg(
              xmlns          := "http://www.w3.org/2000/svg",
              width          := "16",
              height         := "16",
              viewBox        := "0 0 24 24",
              fill           := "none",
              stroke         := "currentColor",
              strokeWidth    := "2",
              strokeLinecap  := "round",
              strokeLinejoin := "round",
              polyline(points := "15 18 9 12 15 6"),
            )
          },
        ),
      )
    } else null,

    // Menu component
    div(
      cls := "flex-grow overflow-y-auto",
      Menu <> MenuProps(
        size = props.size,
        variant = if (isCollapsed) "compact" else props.variant,
        bordered = false, // We're using the sidebar's border
        rounded = false,  // We're using the sidebar's rounded corners
        className = props.menuClassName,
        children = buildMenuItems(props.items),
      ),
    ),

    // Collapse toggle button (if at bottom position)
    if (props.collapsible && props.collapseButtonPosition == "bottom") {
      div(
        cls := "flex justify-end p-2 border-t border-base-300",
        button(
          cls        := "btn btn-sm btn-ghost",
          aria_label := (if (isCollapsed) "Expand sidebar" else "Collapse sidebar"),
          onClick    := (() => toggleCollapse()),
          if (isCollapsed) {
            // Arrow pointing right when collapsed
            svg(
              xmlns          := "http://www.w3.org/2000/svg",
              width          := "16",
              height         := "16",
              viewBox        := "0 0 24 24",
              fill           := "none",
              stroke         := "currentColor",
              strokeWidth    := "2",
              strokeLinecap  := "round",
              strokeLinejoin := "round",
              polyline(points := "9 18 15 12 9 6"),
            )
          } else {
            // Arrow pointing left when expanded
            svg(
              xmlns          := "http://www.w3.org/2000/svg",
              width          := "16",
              height         := "16",
              viewBox        := "0 0 24 24",
              fill           := "none",
              stroke         := "currentColor",
              strokeWidth    := "2",
              strokeLinecap  := "round",
              strokeLinejoin := "round",
              polyline(points := "15 18 9 12 15 6"),
            )
          },
        ),
      )
    } else null,
  )
}
