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

/** Props for the Sidebar component
  */
case class SidebarProps(
    items: List[NavItem] = List(),
    variant: String = "normal", // normal, compact, boxed
    size: String = "md",        // xs, sm, md, lg
    bordered: Boolean = false,
    rounded: Boolean = true,
    bgClass: String = "",                    // Complete background class e.g. "bg-base-200"
    textClass: String = "",                  // Complete text class e.g. "text-primary"
    width: String = "w-64",                  // Fixed width using Tailwind classes
    collapsible: Boolean = false,            // Whether the entire sidebar can collapse
    initialCollapsed: Boolean = false,       // Initial collapse state if collapsible
    expandedSections: List[String] = List(), // IDs of sections that start expanded
    onNavigation: Option[(String, NavItem) => Unit] = None,
    collapseButtonPosition: String = "top", // top, bottom
    showToggleIcons: Boolean = true,        // Whether to show the toggle icons for collapsible sections
    className: String = "",
    menuClassName: String = "",
)

/** Sidebar component with collapsible sections and responsive design
  *
  * Features:
  *   - Collapsible sections
  *   - Nested navigation support
  *   - Icon integration
  *   - Active item highlighting
  *   - Badges for menu items
  *   - Mobile-friendly
  */
val Sidebar = (props: SidebarProps) => {
  // State for tracking which sections are expanded
  val (expandedSections, setExpandedSections, _) = useState[Set[String]](props.expandedSections.toSet)

  // State to track which sections were explicitly closed by the user
  val (manuallyClosedSections, setManuallyClosedSections, _) = useState[Set[String]](Set())

  // State for tracking if the entire sidebar is collapsed (when collapsible=true)
  val (collapsed, setCollapsed, _) = useState(props.initialCollapsed)

  // Function to check if a nav item or any of its children is active
  def isItemOrChildActive(item: NavItem, currentPath: String = ""): Boolean = {
    if (item.isActive) true
    else {
      val itemPath = if (currentPath.isEmpty) item.id else s"$currentPath-${item.id}"
      item.items.exists(child => isItemOrChildActive(child, itemPath))
    }
  }

  // Function to get all parent section IDs for a given path
  def getParentSections(path: String): Set[String] = {
    if (!path.contains("-")) return Set()

    val parts       = path.split("-")
    val parentPaths = (1 until parts.length).map(i => parts.take(i).mkString("-")).toSet
    parentPaths
  }

  // Toggle a section's expanded state - now respects manual closing
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
  def handleNavClick(id: String, item: NavItem): Unit = {
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
              childItem.items.nonEmpty && isItemOrChildActive(childItem, currentPath),
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
    Seq(), // Empty deps - only run on mount
  )

  // Calculate base container classes
  val containerClasses = List.newBuilder[String]

  // Base class
  containerClasses += "sidebar"

  // Width class
  containerClasses += props.width

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
    if (collapsed) {
      containerClasses += "w-16 overflow-hidden"
    }
  }

  // Additional custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(containerClasses += _)
  }

  // Calculate menu classes
  val menuClasses = List.newBuilder[String]

  // Base menu class
  menuClasses += "menu"

  // Size class
  val menuSizeClass = props.size match {
    case "xs" => "menu-xs"
    case "sm" => "menu-sm"
    case "lg" => "menu-lg"
    case _    => "menu-md" // Default
  }
  menuClasses += menuSizeClass

  // Menu variant
  val menuVariantClass = props.variant match {
    case "compact" => "menu-compact"
    case "boxed"   => "bg-base-200 p-2 rounded-box"
    case _         => "" // Default is normal
  }
  if (menuVariantClass.nonEmpty) menuClasses += menuVariantClass

  // Additional menu classes
  if (props.menuClassName.nonEmpty) {
    props.menuClassName.split(" ").foreach(menuClasses += _)
  }

  // Function to render a navigation item
  def renderNavItem(item: NavItem, level: Int = 0, parentPath: String = ""): FluxusNode = {
    val itemPath    = if (parentPath.isEmpty) item.id else s"$parentPath-${item.id}"
    val hasChildren = item.items.nonEmpty
    val isExpanded  = expandedSections.contains(itemPath)

    // Check if this item or any of its children is active
    val isActive = item.isActive || item.items.exists(child => isItemOrChildActive(child, itemPath))

    li(
      key := itemPath,
      // Render nav item as a link or a collapsible section
      if (hasChildren) {
        // Element for a collapsible section header
        div(
          cls := s"flex justify-between items-center ${if (isActive) "active font-medium" else ""} ${
              if (item.disabled) "disabled opacity-50" else "cursor-pointer"
            }",
          onClick := (() => {
            if (!item.disabled) {
              // The key change: toggle this specific section and mark manual closure
              toggleSection(itemPath)
              item.onClick.foreach(_())
            }
          }),

          // Icon and Title section
          div(
            cls := "flex items-center gap-2",
            item.icon.map(icon =>
              div(
                cls := "w-5 h-5 flex items-center justify-center",
                icon,
              ),
            ).orNull,
            if (!collapsed || !props.collapsible) {
              span(item.title)
            } else null,
          ),

          // Right side with badge and toggle icon
          div(
            cls := "flex items-center gap-2",

            // Badge if present
            item.badge.map(badgeText =>
              if (!collapsed || !props.collapsible) {
                div(
                  cls := s"badge badge-sm badge-${item.badgeVariant}",
                  badgeText,
                )
              } else null,
            ).orNull,

            // Toggle icon if showing
            if (props.showToggleIcons && (!collapsed || !props.collapsible)) {
              div(
                cls := "text-base-content/70",
                if (isExpanded) {
                  svg(
                    xmlns   := "http://www.w3.org/2000/svg",
                    cls     := "h-4 w-4",
                    viewBox := "0 0 20 20",
                    fill    := "currentColor",
                    path(
                      fillRule := "evenodd",
                      d := "M14.707 12.707a1 1 0 01-1.414 0L10 9.414l-3.293 3.293a1 1 0 01-1.414-1.414l4-4a1 1 0 011.414 0l4 4a1 1 0 010 1.414z",
                      clipRule := "evenodd",
                    ),
                  )
                } else {
                  svg(
                    xmlns   := "http://www.w3.org/2000/svg",
                    cls     := "h-4 w-4",
                    viewBox := "0 0 20 20",
                    fill    := "currentColor",
                    path(
                      fillRule := "evenodd",
                      d := "M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z",
                      clipRule := "evenodd",
                    ),
                  )
                },
              )
            } else null,
          ),
        )
      } else {
        // Element for a regular nav item (leaf node)
        a(
          href := item.href.getOrElse("#"),
          cls := s"${if (item.isActive) "active" else ""} ${
              if (item.disabled) "disabled opacity-50 pointer-events-none" else ""
            }",
          onClick := ((e: dom.MouseEvent) => {
            if (item.href.isEmpty || item.href.contains("#")) {
              e.preventDefault()
            }
            if (!item.disabled) {
              handleNavClick(itemPath, item)
            }
          }),

          // Icon and title
          div(
            cls := "flex items-center gap-2",
            item.icon.map(icon =>
              div(
                cls := "w-5 h-5 flex items-center justify-center",
                icon,
              ),
            ).orNull,
            if (!collapsed || !props.collapsible) {
              span(item.title)
            } else null,
          ),

          // Badge if present
          item.badge.map(badgeText =>
            if (!collapsed || !props.collapsible) {
              div(
                cls := s"badge badge-sm badge-${item.badgeVariant}",
                badgeText,
              )
            } else null,
          ).orNull,
        )
      },

      // Render children if expanded
      if (hasChildren && isExpanded && (!collapsed || !props.collapsible)) {
        ul(
          cls := "pl-4 mt-1",
          item.items.map(childItem =>
            renderNavItem(childItem, level + 1, itemPath),
          ),
        )
      } else null,
    )
  }

  // Render collapse toggle button
  def renderCollapseButton: FluxusNode = {
    if (props.collapsible) {
      button(
        cls     := "btn btn-sm btn-ghost",
        onClick := (() => setCollapsed(!collapsed)),
        svg(
          xmlns   := "http://www.w3.org/2000/svg",
          cls     := "h-5 w-5",
          viewBox := "0 0 20 20",
          fill    := "currentColor",
          if (collapsed) {
            path(
              fillRule := "evenodd",
              d := "M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z",
              clipRule := "evenodd",
            )
          } else {
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

    // Menu
    ul(
      cls := menuClasses.result().mkString(" "),
      props.items.map(item => renderNavItem(item)),
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
