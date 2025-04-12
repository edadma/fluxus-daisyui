package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Tab panel props for individual tab content
  */
case class TabPanelProps(
    id: String,
    title: String,
    icon: Option[FluxusNode] = None,
    badge: Option[String] = None,
    badgeVariant: String = "primary",
    disabled: Boolean = false,
    children: FluxusNode,
)

/** Tabs component props with DaisyUI styling options
  */
case class TabsProps(
    // Content
    children: List[TabPanelProps] = List(),

    // State
    activeTab: String = "",                // ID of the active tab
    onTabChange: String => Unit = _ => (), // Callback when tab changes

    // Styling
    variant: String = "bordered", // bordered, lifted, boxed
    size: String = "md",          // xs, sm, md, lg
    alignment: String = "start",  // start, center, end
    fullWidth: Boolean = false,   // Make tabs take full width

    // Additional styling
    tabsClassName: String = "",
    contentClassName: String = "",
    contentContainerClassName: String = "",
)

/** Tabs component using DaisyUI styling
  *
  * Features:
  *   - Multiple style variants (bordered, lifted, boxed)
  *   - Different sizes (xs, sm, md, lg)
  *   - Tab alignment options (start, center, end)
  *   - Support for icons and badges in tab headers
  *   - Controlled or uncontrolled usage
  */
val Tabs = (props: TabsProps) => {
  // State for internal active tab (used when not controlled externally)
  val (internalActiveTab, setInternalActiveTab, _) = useState(
    if (props.activeTab.nonEmpty) props.activeTab
    else if (props.children.nonEmpty) props.children.head.id
    else "",
  )

  // Determine active tab - use props if controlled, otherwise use internal state
  val activeTabId = if (props.activeTab.nonEmpty) props.activeTab else internalActiveTab

  // Handle tab change
  def handleTabChange(tabId: String): Unit = {
    // Call external handler if provided
    props.onTabChange(tabId)

    // Update internal state if not controlled externally
    if (props.activeTab.isEmpty) {
      setInternalActiveTab(tabId)
    }
  }

  // Calculate tab bar classes
  val tabsClasses = List.newBuilder[String]

  // Base class is always present
  tabsClasses += "tabs"

  // Handle variant - must use predefined Tailwind classes
  val variantClass = props.variant match {
    case "lifted" => "tabs-lifted"
    case "boxed"  => "tabs-boxed"
    case _        => "tabs-bordered" // Default to bordered
  }

  // Handle size - must use predefined Tailwind classes
  val sizeClass = props.size match {
    case "xs" => "tabs-xs"
    case "sm" => "tabs-sm"
    case "lg" => "tabs-lg"
    case _    => "tabs-md" // Default to medium
  }

  // Handle alignment - must use predefined Tailwind classes
  val alignmentClass = props.alignment match {
    case "center" => "tabs-center"
    case "end"    => "tabs-end"
    case _        => "tabs-start" // Default to start
  }

  // Add conditional classes
  tabsClasses += variantClass
  tabsClasses += sizeClass
  tabsClasses += alignmentClass

  if (props.fullWidth) tabsClasses += "w-full"

  // Add any custom classes
  if (props.tabsClassName.nonEmpty) {
    props.tabsClassName.split(" ").foreach(tabsClasses += _)
  }

  // Join all tab classes with spaces
  val tabsClass = tabsClasses.result().mkString(" ")

  // Find the active tab content
  val activeTabContent = props.children
    .find(_.id == activeTabId)
    .map(_.children)
    .getOrElse(div("No content available"))

  div(
    cls := props.contentContainerClassName,

    // Tab bar
    div(
      cls                := tabsClass,
      role               := "tablist",
      "aria_orientation" := "horizontal",

      // Render tabs
      props.children.map(tab => {
        val isActive = tab.id == activeTabId

        // Calculate tab item classes
        val tabItemClasses = List.newBuilder[String]

        // Base class is always present
        tabItemClasses += "tab"

        // Active state
        if (isActive) tabItemClasses += "tab-active"

        // Disabled state
        if (tab.disabled) tabItemClasses += "tab-disabled"

        // Generate final tab item class
        val tabItemClass = tabItemClasses.result().mkString(" ")

        div(
          key           := s"tab-${tab.id}",
          cls           := tabItemClass,
          role          := "tab",
          aria_selected := isActive.toString,
          aria_controls := s"panel-${tab.id}",
          aria_disabled := tab.disabled.toString,
          tabIndex      := (if (isActive && !tab.disabled) 0 else -1),
          onClick := (() => {
            if (!tab.disabled) {
              handleTabChange(tab.id)
            }
          }),

          // Tab content with optional icon and badge
          div(
            cls := "flex items-center gap-2",

            // Optional icon
            tab.icon.orNull,

            // Title
            span(tab.title),

            // Optional badge
            tab.badge.map(badgeText =>
              Badge <> BadgeProps(
                text = badgeText,
                variant = tab.badgeVariant,
                size = "xs",
              ),
            ).orNull,
          ),
        )
      }),
    ),

    // Tab content area
    div(
      cls             := s"mt-4 ${props.contentClassName}",
      role            := "tabpanel",
      id              := s"panel-${activeTabId}",
      aria_labelledby := s"tab-${activeTabId}",

      // Render active tab content
      activeTabContent,
    ),
  )
}
