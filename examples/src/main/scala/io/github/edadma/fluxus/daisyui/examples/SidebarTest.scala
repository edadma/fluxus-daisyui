package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def SidebarTest: FluxusNode = {
  // State for tracking active navigation item and sidebar state
  val (activeNavId, setActiveNavId, _)                       = useState("dashboard")
  val (isCollapsed, setIsCollapsed, _)                       = useState(false)
  val (selectedVariant, setSelectedVariant, _)               = useState("normal")
  val (selectedSize, setSelectedSize, _)                     = useState("md")
  val (showBorder, setShowBorder, _)                         = useState(true)
  val (collapseButtonPosition, setCollapseButtonPosition, _) = useState("top")
  val (selectedBgClass, setSelectedBgClass, _)               = useState("bg-base-200")
  val (lastInteraction, setLastInteraction, _)               = useState("")

  // Common icons for navigation items
  def HomeIcon: FluxusNode = {
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
      path(d          := "M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"),
      polyline(points := "9 22 9 12 15 12 15 22"),
    )
  }

  def UsersIcon: FluxusNode = {
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
      path(d    := "M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"),
      circle(cx := "9", cy := "7", r := "4"),
      path(d    := "M23 21v-2a4 4 0 0 0-3-3.87"),
      path(d    := "M16 3.13a4 4 0 0 1 0 7.75"),
    )
  }

  def SettingsIcon: FluxusNode = {
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
      circle(cx := "12", cy := "12", r := "3"),
      path(
        d := "M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z",
      ),
    )
  }

  def ReportsIcon: FluxusNode = {
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
      path(d          := "M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"),
      polyline(points := "14 2 14 8 20 8"),
      line(x1         := "16", y1 := "13", x2 := "8", y2 := "13"),
      line(x1         := "16", y1 := "17", x2 := "8", y2 := "17"),
      polyline(points := "10 9 9 9 8 9"),
    )
  }

  def NotificationsIcon: FluxusNode = {
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
      path(d := "M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"),
      path(d := "M13.73 21a2 2 0 0 1-3.46 0"),
    )
  }

  def ProjectsIcon: FluxusNode = {
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
      rect(x  := "3", y  := "3", width := "18", height := "18", rx := "2", ry := "2"),
      line(x1 := "3", y1 := "9", x2    := "21", y2     := "9"),
      line(x1 := "9", y1 := "21", x2   := "9", y2      := "9"),
    )
  }

  def HelpIcon: FluxusNode = {
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
      circle(cx := "12", cy := "12", r  := "10"),
      path(d    := "M9.09 9a3 3 0 0 1 5.83 1c0 2-3 3-3 3"),
      line(x1   := "12", y1 := "17", x2 := "12.01", y2 := "17"),
    )
  }

  // Create navigation items
  val sidebarItems = List(
    NavItem(
      id = "dashboard",
      title = "Dashboard",
      icon = Some(HomeIcon),
      href = Some("#dashboard"),
      isActive = activeNavId == "dashboard",
    ),
    NavItem(
      id = "users",
      title = "Users Management",
      icon = Some(UsersIcon),
      items = List(
        NavItem(
          id = "all-users",
          title = "All Users",
          href = Some("#all-users"),
          isActive = activeNavId == "users-all-users",
        ),
        NavItem(
          id = "add-user",
          title = "Add User",
          href = Some("#add-user"),
          isActive = activeNavId == "users-add-user",
          badge = Some("New"),
          badgeVariant = "success",
        ),
        NavItem(
          id = "user-groups",
          title = "User Groups",
          href = Some("#user-groups"),
          isActive = activeNavId == "users-user-groups",
        ),
      ),
    ),
    NavItem(
      id = "projects",
      title = "Projects",
      icon = Some(ProjectsIcon),
      href = Some("#projects"),
      isActive = activeNavId == "projects",
      badge = Some("8"),
      badgeVariant = "primary",
    ),
    NavItem(
      id = "reports",
      title = "Reports",
      icon = Some(ReportsIcon),
      href = Some("#reports"),
      isActive = activeNavId == "reports",
    ),
    NavItem(
      id = "notifications",
      title = "Notifications",
      icon = Some(NotificationsIcon),
      href = Some("#notifications"),
      isActive = activeNavId == "notifications",
      badge = Some("3"),
      badgeVariant = "error",
    ),
    NavItem(
      id = "settings",
      title = "Settings",
      icon = Some(SettingsIcon),
      items = List(
        NavItem(
          id = "general",
          title = "General",
          href = Some("#settings-general"),
          isActive = activeNavId == "settings-general",
        ),
        NavItem(
          id = "security",
          title = "Security",
          href = Some("#settings-security"),
          isActive = activeNavId == "settings-security",
        ),
        NavItem(
          id = "preferences",
          title = "Preferences",
          href = Some("#settings-preferences"),
          isActive = activeNavId == "settings-preferences",
        ),
      ),
    ),
    NavItem(
      id = "help",
      title = "Help & Support",
      icon = Some(HelpIcon),
      href = Some("#help"),
      isActive = activeNavId == "help",
    ),
  )

  // Handle navigation
  def handleNavigation(id: String, item: NavItem): Unit = {
    setActiveNavId(id)
    setLastInteraction(s"Navigated to: ${item.title} (ID: $id)")
  }

  // Handle collapse state change
  def handleCollapseChange(collapsed: Boolean): Unit = {
    setIsCollapsed(collapsed)
    setLastInteraction(s"Sidebar ${if (collapsed) "collapsed" else "expanded"}")
  }

  Container <> ContainerProps(
    className = "p-6 mb-6",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Sidebar Component Demo",
      ),

      // Display two columns on larger screens
      div(
        cls := "grid grid-cols-1 lg:grid-cols-2 gap-8",

        // Control panel
        Card <> CardProps(
          title = Some("Customize Sidebar"),
          bordered = true,
          className = "mb-6 lg:mb-0",
          children = div(
            cls := "space-y-6",

            // Variant selector
            div(
              cls := "form-control",
              label(cls := "label font-medium", "Style Variant"),
              div(
                cls := "flex flex-wrap gap-2",
                Button <> ButtonProps(
                  text = "Normal",
                  variant = if (selectedVariant == "normal") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedVariant("normal"),
                ),
                Button <> ButtonProps(
                  text = "Compact",
                  variant = if (selectedVariant == "compact") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedVariant("compact"),
                ),
                Button <> ButtonProps(
                  text = "Boxed",
                  variant = if (selectedVariant == "boxed") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedVariant("boxed"),
                ),
              ),
            ),

            // Size selector
            div(
              cls := "form-control",
              label(cls := "label font-medium", "Size"),
              div(
                cls := "flex flex-wrap gap-2",
                Button <> ButtonProps(
                  text = "XS",
                  variant = if (selectedSize == "xs") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedSize("xs"),
                ),
                Button <> ButtonProps(
                  text = "SM",
                  variant = if (selectedSize == "sm") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedSize("sm"),
                ),
                Button <> ButtonProps(
                  text = "MD",
                  variant = if (selectedSize == "md") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedSize("md"),
                ),
                Button <> ButtonProps(
                  text = "LG",
                  variant = if (selectedSize == "lg") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedSize("lg"),
                ),
              ),
            ),

            // Background selector
            div(
              cls := "form-control",
              label(cls := "label font-medium", "Background"),
              div(
                cls := "flex flex-wrap gap-2",
                Button <> ButtonProps(
                  text = "Base 100",
                  variant = if (selectedBgClass == "bg-base-100") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedBgClass("bg-base-100"),
                ),
                Button <> ButtonProps(
                  text = "Base 200",
                  variant = if (selectedBgClass == "bg-base-200") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedBgClass("bg-base-200"),
                ),
                Button <> ButtonProps(
                  text = "Base 300",
                  variant = if (selectedBgClass == "bg-base-300") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedBgClass("bg-base-300"),
                ),
                Button <> ButtonProps(
                  text = "Primary",
                  variant = if (selectedBgClass == "bg-primary bg-opacity-10") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedBgClass("bg-primary bg-opacity-10"),
                ),
                Button <> ButtonProps(
                  text = "Secondary",
                  variant = if (selectedBgClass == "bg-secondary bg-opacity-10") "primary" else "outline",
                  size = "sm",
                  onClick = () => setSelectedBgClass("bg-secondary bg-opacity-10"),
                ),
              ),
            ),

            // Toggle options
            div(
              cls := "flex flex-wrap gap-4 items-center",
              div(
                cls := "form-control",
                label(
                  cls := "label cursor-pointer",
                  div(
                    span(cls := "label-text mr-2", "Show Border"),
                    input(
                      typ      := "checkbox",
                      cls      := "toggle toggle-primary",
                      checked  := showBorder,
                      onChange := (() => setShowBorder(!showBorder)),
                    ),
                  ),
                ),
              ),
              div(
                cls := "form-control",
                label(
                  cls := "label cursor-pointer",
                  div(
                    span(cls := "label-text mr-2", "Is Collapsed"),
                    input(
                      typ      := "checkbox",
                      cls      := "toggle toggle-primary",
                      checked  := isCollapsed,
                      onChange := (() => handleCollapseChange(!isCollapsed)),
                    ),
                  ),
                ),
              ),
            ),

            // Button position selector
            div(
              cls := "form-control",
              label(cls := "label font-medium", "Collapse Button Position"),
              div(
                cls := "flex gap-2",
                Button <> ButtonProps(
                  text = "Top",
                  variant = if (collapseButtonPosition == "top") "primary" else "outline",
                  size = "sm",
                  onClick = () => setCollapseButtonPosition("top"),
                ),
                Button <> ButtonProps(
                  text = "Bottom",
                  variant = if (collapseButtonPosition == "bottom") "primary" else "outline",
                  size = "sm",
                  onClick = () => setCollapseButtonPosition("bottom"),
                ),
              ),
            ),

            // Interaction log
            div(
              cls := "mt-6",
              label(cls := "label font-medium", "Last Interaction"),
              div(
                cls := "bg-base-200 p-3 rounded-box min-h-12",
                if (lastInteraction.nonEmpty) {
                  p(cls := "text-sm", lastInteraction)
                } else {
                  p(cls := "text-sm opacity-70", "No interactions yet")
                },
              ),
            ),

            // Active item display
            div(
              cls := "mt-2",
              label(cls := "label font-medium", "Active Item"),
              div(
                cls := "bg-base-200 p-3 rounded-box",
                p(cls := "text-sm", if (activeNavId.nonEmpty) activeNavId else "None"),
              ),
            ),
          ),
        ),

        // Sidebar container with fixed height to showcase scrolling
        div(
          cls := "bg-base-100 rounded-box border border-base-300 h-[600px] overflow-hidden flex",

          // Sidebar component
          div(
            cls := "h-full",
            Sidebar <> SidebarProps(
              items = sidebarItems,
              variant = selectedVariant,
              size = selectedSize,
              bordered = showBorder,
              bgClass = selectedBgClass,
              collapsible = true,
              collapsed = isCollapsed,
              onCollapseChange = Some(handleCollapseChange),
              onNavigation = Some(handleNavigation),
              expandedSections = List("users"),
              collapseButtonPosition = collapseButtonPosition,
            ),
          ),

          // Content area to show what would be next to sidebar
          div(
            cls := "flex-grow p-6 overflow-auto",
            h2(cls := "text-2xl font-bold mb-4", "Content Area"),
            p(
              cls := "mb-4",
              "This is the main content area that would typically appear next to the sidebar in a real application.",
            ),
            div(
              cls := "alert alert-info shadow-lg",
              div(
                svg(
                  xmlns   := "http://www.w3.org/2000/svg",
                  cls     := "stroke-current flex-shrink-0 h-6 w-6",
                  fill    := "none",
                  viewBox := "0 0 24 24",
                  path(
                    strokeLinecap  := "round",
                    strokeLinejoin := "round",
                    strokeWidth    := "2",
                    d              := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z",
                  ),
                ),
                span(
                  "Click on sidebar items to navigate and see the active state change.",
                ),
              ),
            ),
            // Placeholder content to demonstrate scrolling
            div(
              cls := "mt-6 space-y-4",
              (1 to 5).map(i =>
                div(
                  key := s"content-$i",
                  cls := "bg-base-200 p-4 rounded-box",
                  h3(cls := "font-medium mb-2", s"Content Section $i"),
                  p(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla facilisi. Maecenas at tellus nec metus sollicitudin commodo.",
                  ),
                ),
              ),
            ),
          ),
        ),
      ),

      // Examples of different sidebar configurations
      h2(
        cls := "text-2xl font-bold mt-12 mb-6",
        "Additional Sidebar Examples",
      ),
      div(
        cls := "grid grid-cols-1 lg:grid-cols-3 gap-6",

        // Example 1: Compact sidebar with icons only
        div(
          cls := "border border-base-300 rounded-box h-[400px] overflow-hidden",
          h3(cls := "p-3 bg-base-200 font-medium border-b", "Compact Icon Sidebar"),
          div(
            cls := "h-[calc(100%-48px)] flex",
            div(
              cls := "h-full",
              Sidebar <> SidebarProps(
                items = sidebarItems,
                variant = "compact",
                size = "sm",
                bgClass = "bg-primary text-primary-content",
                collapsible = false,
                collapsed = true,
                width = "w-16",
              ),
            ),
            div(
              cls := "flex-grow p-4 bg-base-100",
              p("Icon-only sidebar for minimal interfaces"),
            ),
          ),
        ),

        // Example 2: Boxed style sidebar
        div(
          cls := "border border-base-300 rounded-box h-[400px] overflow-hidden",
          h3(cls := "p-3 bg-base-200 font-medium border-b", "Boxed Style Sidebar"),
          div(
            cls := "h-[calc(100%-48px)] flex",
            div(
              cls := "h-full",
              Sidebar <> SidebarProps(
                items = sidebarItems,
                variant = "boxed",
                bordered = true,
                bgClass = "bg-base-200",
                collapsible = false,
                width = "w-56",
                menuClassName = "px-2 py-2",
              ),
            ),
            div(
              cls := "flex-grow p-4 bg-base-100",
              p("Boxed style with distinct sections"),
            ),
          ),
        ),

        // Example 3: Dark themed sidebar
        div(
          cls := "border border-base-300 rounded-box h-[400px] overflow-hidden",
          h3(cls := "p-3 bg-base-200 font-medium border-b", "Themed Sidebar"),
          div(
            cls := "h-[calc(100%-48px)] flex",
            div(
              cls := "h-full",
              Sidebar <> SidebarProps(
                items = sidebarItems,
                variant = "normal",
                size = "md",
                bgClass = "bg-neutral text-neutral-content",
                collapsible = false,
                width = "w-64",
                rounded = true,
              ),
            ),
            div(
              cls := "flex-grow p-4 bg-base-100",
              p("Dark themed sidebar with custom colors"),
            ),
          ),
        ),
      ),
    ),
  )
}
