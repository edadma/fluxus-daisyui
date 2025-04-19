package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Navigation item structure for the Sidebar component
  */
case class NavItem(
    id: String,
    title: String,
    icon: Option[FluxusNode] = None,
    href: Option[String] = None,
    onClick: Option[() => Unit] = None,
    badge: Option[String] = None,
    badgeVariant: String = "primary",
    items: List[NavItem] = List(),
    isActive: Boolean = false,
    disabled: Boolean = false,
)

/** Props for the revised Sidebar component
  */
case class SidebarProps(
    items: List[NavItem] = List(),
    variant: String = "normal", // normal, compact, boxed
    size: String = "md",        // xs, sm, md, lg
    bordered: Boolean = false,
    rounded: Boolean = true,
    bgClass: String = "",                             // Complete background class e.g. "bg-base-200"
    textClass: String = "",                           // Complete text class e.g. "text-primary"
    width: String = "w-64",                           // Width when expanded
    collapsedWidth: String = "w-16",                  // Width when collapsed
    collapsible: Boolean = false,                     // Whether the sidebar can collapse
    collapsed: Boolean = false,                       // Controlled collapsed state
    onCollapseChange: Option[Boolean => Unit] = None, // Callback when collapse state changes
    expandedSections: List[String] = List(),          // IDs of sections that start expanded
    onNavigation: Option[(String, NavItem) => Unit] = None,
    collapseButtonPosition: String = "top", // top, bottom
    showToggleIcons: Boolean = true,        // Whether to show the toggle icons for collapsible sections
    className: String = "",
    menuClassName: String = "",
)

/** Reworked Sidebar component that uses Menu internally
  *
  * This implementation maintains all the features of the original Sidebar:
  *   - Collapsible sections
  *   - Icon and badge support
  *   - Active item highlighting
  *   - Nested navigation
  *   - Collapse/expand functionality
  *
  * But leverages the Menu component for consistent styling and behavior
  */
val Sidebar = (props: SidebarProps) => {
  // State for tracking which sections are expanded
  val (expandedSections, setExpandedSections, _) = useState[Set[String]](props.expandedSections.toSet)

  // State to track which sections were manually closed by the user
  val (manuallyClosedSections, setManuallyClosedSections, _) = useState[Set[String]](Set())

  // Internal collapsed state - used when component is uncontrolled
  val (internalCollapsed, setInternalCollapsed, _) = useState(props.collapsed)

  // Determine if sidebar is collapsed - use props if controlled, otherwise use internal state
  val isCollapsed = props.onCollapseChange.isDefined match {
    case true  => props.collapsed   // Controlled component
    case false => internalCollapsed // Uncontrolled component
  }

  // Handle collapse toggle
  def toggleCollapsed(): Unit = {
    val newCollapsedState = !isCollapsed

    // If controlled externally, call the callback
    props.onCollapseChange match {
      case Some(callback) => callback(newCollapsedState)
      case None           => setInternalCollapsed(newCollapsedState) // Only update internal state if uncontrolled
    }
  }

  // Function to check if a section is expanded
  def isSectionExpanded(path: String): Boolean = expandedSections.contains(path)

  // Get parent sections from path
  def getParentSections(path: String): Set[String] = {
    if (!path.contains("-")) return Set()

    val parts       = path.split("-")
    val parentPaths = (1 until parts.length).map(i => parts.take(i).mkString("-")).toSet
    parentPaths
  }

  // Toggle a section's expanded state
  def toggleSection(sectionId: String, forceState: Option[Boolean] = None): Unit = {
    val newState = forceState.getOrElse(!expandedSections.contains(sectionId))

    if (newState) {
      // Expanding section
      setExpandedSections(expandedSections + sectionId)
      setManuallyClosedSections(manuallyClosedSections - sectionId)
    } else {
      // Collapsing section - mark as manually closed
      setExpandedSections(expandedSections - sectionId)
      setManuallyClosedSections(manuallyClosedSections + sectionId)
    }
  }

  // Handle navigation click
  def handleNavigation(id: String, item: NavItem): Unit = {
    if (!item.disabled) {
      // Execute onClick handler if provided
      item.onClick.foreach(_())

      // Keep parent sections expanded by adding them to expanded sections
      // But only expand parents that weren't manually closed by the user
      val parentSections = getParentSections(id).filterNot(manuallyClosedSections.contains)
      if (parentSections.nonEmpty) {
        setExpandedSections(expandedSections ++ parentSections)
      }

      // Call navigation callback if provided
      props.onNavigation.foreach(_(id, item))
    }
  }

  // Convert NavItems to Menu components (recursive function)
  def renderMenuItems(items: List[NavItem], level: Int = 0, parentPath: String = ""): Seq[FluxusNode] = {
    items.map { item =>
      val itemPath = if (parentPath.isEmpty) item.id else s"$parentPath-${item.id}"

      if (item.items.nonEmpty) {
        // This is a submenu item
        MenuSubmenu <> MenuSubmenuProps(
          title = item.title,
          icon = item.icon,
          active = item.isActive,
          disabled = item.disabled,
          expanded = isSectionExpanded(itemPath),
          children = renderMenuItems(item.items, level + 1, itemPath),
        )
      } else {
        // This is a regular menu item
        MenuItem <> MenuItemProps(
          title = item.title,
          href = item.href,
          icon = item.icon,
          active = item.isActive,
          disabled = item.disabled,
          badge = item.badge,
          badgeVariant = item.badgeVariant,
          onClick = Some(() => handleNavigation(itemPath, item)),
        )
      }
    }
  }

  // Auto-expand parent sections of active items on mount and when active items change
  useEffect(
    () => {
      // Find all active items and their paths
      def findActiveItems(items: List[NavItem], parentPath: String = ""): Set[String] = {
        items.flatMap { item =>
          val currentPath = if (parentPath.isEmpty) item.id else s"$parentPath-${item.id}"

          if (item.isActive) {
            // This item is active, so we'll get its parent sections that haven't been manually closed
            val parents = getParentSections(currentPath).filterNot(manuallyClosedSections.contains)
            parents + currentPath // Include the current path too if it has children
          } else if (item.items.nonEmpty && item.items.exists(_.isActive)) {
            // A child is active, keep this section expanded too if not manually closed
            if (!manuallyClosedSections.contains(currentPath)) {
              val childActivePaths = findActiveItems(item.items, currentPath)
              childActivePaths + currentPath
            } else {
              findActiveItems(item.items, currentPath)
            }
          } else if (
            item.items.nonEmpty && item.items.exists(childItem =>
              childItem.items.nonEmpty && childItem.items.exists(_.isActive),
            )
          ) {
            // A grandchild is active - expand if not manually closed
            if (!manuallyClosedSections.contains(currentPath)) {
              val childActivePaths = findActiveItems(item.items, currentPath)
              childActivePaths + currentPath
            } else {
              findActiveItems(item.items, currentPath)
            }
          } else {
            Set.empty[String]
          }
        }.toSet
      }

      val activeItemPaths = findActiveItems(props.items)
      if (activeItemPaths.nonEmpty) {
        setExpandedSections(expandedSections ++ activeItemPaths)
      }
      ()
    },
    Seq(props.items), // Run when items change
  )

  // Calculate base container classes
  val containerClasses = List.newBuilder[String]

  // Base class
  containerClasses += "sidebar"

  // Width class - toggle between expanded and collapsed widths
  containerClasses += (if (isCollapsed) props.collapsedWidth else props.width)

  // Border class
  if (props.bordered) containerClasses += "border border-base-300"

  // Rounded class
  if (props.rounded) containerClasses += "rounded-box"

  // Background class
  if (props.bgClass.nonEmpty) containerClasses += props.bgClass

  // Text class
  if (props.textClass.nonEmpty) containerClasses += props.textClass

  // Transition class for collapsible sidebar
  if (props.collapsible) {
    containerClasses += "transition-all duration-300"
  }

  // Additional custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(containerClasses += _)
  }

  // Render collapse toggle button
  def renderCollapseButton: FluxusNode = {
    if (props.collapsible) {
      button(
        cls     := "btn btn-sm btn-ghost",
        onClick := (() => toggleCollapsed()),
        svg(
          xmlns   := "http://www.w3.org/2000/svg",
          cls     := "h-5 w-5",
          viewBox := "0 0 20 20",
          fill    := "currentColor",
          if (isCollapsed) {
            // When collapsed, show right-facing chevron (expand)
            path(
              fillRule := "evenodd",
              d := "M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z",
              clipRule := "evenodd",
            )
          } else {
            // When expanded, show left-facing chevron (collapse)
            path(
              fillRule := "evenodd",
              d := "M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z",
              clipRule := "evenodd",
            )
          },
        ),
      )
    } else null
  }

  // Main component render
  div(
    cls := containerClasses.result().mkString(" "),

    // Top collapse button
    if (props.collapsible && props.collapseButtonPosition == "top") {
      div(
        cls := "flex justify-end p-2",
        renderCollapseButton,
      )
    } else null,

    // Menu component
    Menu <> MenuProps(
      size = props.size,
      variant = props.variant,
      bordered = false, // We're already handling borders on the sidebar container
      rounded = false,  // We're already handling rounded corners on the sidebar container
      bgClass = "",     // Using the sidebar's background
      shadow = false,
      glass = false,
      className = props.menuClassName,
      children = renderMenuItems(props.items),
    ),

    // Bottom collapse button
    if (props.collapsible && props.collapseButtonPosition == "bottom") {
      div(
        cls := "flex justify-end p-2 mt-auto",
        renderCollapseButton,
      )
    } else null,
  )
}
