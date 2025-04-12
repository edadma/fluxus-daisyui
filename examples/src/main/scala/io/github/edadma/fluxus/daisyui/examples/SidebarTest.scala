package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def SidebarTest: FluxusNode = {
  // Icons for demo purposes
  def HomeIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "20",
      height         := "20",
      viewBox        := "0 0 24 24",
      fill           := "none",
      stroke         := "currentColor",
      strokeWidth    := "2",
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      path(
        d := "M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z",
      ),
      polyline(
        points := "9 22 9 12 15 12 15 22",
      ),
    )
  }

  def UsersIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "20",
      height         := "20",
      viewBox        := "0 0 24 24",
      fill           := "none",
      stroke         := "currentColor",
      strokeWidth    := "2",
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      path(
        d := "M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2",
      ),
      circle(
        cx := "9",
        cy := "7",
        r  := "4",
      ),
      path(
        d := "M23 21v-2a4 4 0 0 0-3-3.87",
      ),
      path(
        d := "M16 3.13a4 4 0 0 1 0 7.75",
      ),
    )
  }

  def SettingsIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "20",
      height         := "20",
      viewBox        := "0 0 24 24",
      fill           := "none",
      stroke         := "currentColor",
      strokeWidth    := "2",
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      circle(
        cx := "12",
        cy := "12",
        r  := "3",
      ),
      path(
        d := "M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z",
      ),
    )
  }

  def FilesIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "20",
      height         := "20",
      viewBox        := "0 0 24 24",
      fill           := "none",
      stroke         := "currentColor",
      strokeWidth    := "2",
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      path(
        d := "M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z",
      ),
      polyline(
        points := "13 2 13 9 20 9",
      ),
    )
  }

  def MessagesIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "20",
      height         := "20",
      viewBox        := "0 0 24 24",
      fill           := "none",
      stroke         := "currentColor",
      strokeWidth    := "2",
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      path(
        d := "M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z",
      ),
    )
  }

  // State for the active navigation item
  val (activeNavId, setActiveNavId, _) = useState("dashboard")

  // State for showing the alert
  val (showAlert, setShowAlert, _)       = useState(false)
  val (alertMessage, setAlertMessage, _) = useState("")

  // Handle navigation click
  def handleNavigation(id: String, item: NavItem): Unit = {
    setActiveNavId(id)
    setAlertMessage(s"Navigated to: ${item.title}")
    setShowAlert(true)
    // Hide the alert after 3 seconds
    dom.window.setTimeout(() => setShowAlert(false), 3000)
  }

  // Create navigation items for demo
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
      title = "Users",
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
          id = "user-roles",
          title = "User Roles",
          href = Some("#user-roles"),
          isActive = activeNavId == "users-user-roles",
        ),
      ),
    ),
    NavItem(
      id = "messages",
      title = "Messages",
      icon = Some(MessagesIcon),
      badge = Some("5"),
      badgeVariant = "error",
      href = Some("#messages"),
      isActive = activeNavId == "messages",
    ),
    NavItem(
      id = "files",
      title = "Files",
      icon = Some(FilesIcon),
      items = List(
        NavItem(
          id = "documents",
          title = "Documents",
          href = Some("#documents"),
          isActive = activeNavId == "files-documents",
          items = List(
            NavItem(
              id = "contracts",
              title = "Contracts",
              href = Some("#contracts"),
              isActive = activeNavId == "files-documents-contracts",
            ),
            NavItem(
              id = "reports",
              title = "Reports",
              href = Some("#reports"),
              isActive = activeNavId == "files-documents-reports",
            ),
          ),
        ),
        NavItem(
          id = "images",
          title = "Images",
          href = Some("#images"),
          isActive = activeNavId == "files-images",
        ),
        NavItem(
          id = "videos",
          title = "Videos",
          href = Some("#videos"),
          isActive = activeNavId == "files-videos",
        ),
      ),
    ),
    NavItem(
      id = "settings",
      title = "Settings",
      icon = Some(SettingsIcon),
      href = Some("#settings"),
      isActive = activeNavId == "settings",
    ),
    NavItem(
      id = "disabled-item",
      title = "Disabled Item",
      disabled = true,
      href = Some("#nowhere"),
    ),
  )

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Sidebar Component Demo",
      ),

      // Alert for navigation events
      if (showAlert) {
        div(
          cls := "alert alert-info mb-4",
          div(
            cls := "flex-1",
            svg(
              xmlns   := "http://www.w3.org/2000/svg",
              fill    := "none",
              viewBox := "0 0 24 24",
              stroke  := "currentColor",
              cls     := "w-6 h-6 mx-2",
              path(
                strokeLinecap  := "round",
                strokeLinejoin := "round",
                strokeWidth    := "2",
                d              := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z",
              ),
            ),
            label(alertMessage),
          ),
        )
      } else null,
      div(
        cls := "flex flex-wrap md:flex-nowrap gap-8",

        // Default Sidebar
        div(
          cls := "w-full md:w-auto",
          Card <> CardProps(
            title = Some("Default Sidebar"),
            className = "mb-8",
            children = div(
              Sidebar <> SidebarProps(
                items = sidebarItems,
                expandedSections = List("users"),
                onNavigation = Some(handleNavigation),
                bgClass = "bg-base-100",
                bordered = true,
                width = "w-60",
              ),
            ),
          ),
        ),

        // Compact Sidebar
        div(
          cls := "w-full md:w-auto",
          Card <> CardProps(
            title = Some("Compact Style"),
            className = "mb-8",
            children = div(
              Sidebar <> SidebarProps(
                items = sidebarItems,
                variant = "compact",
                size = "sm",
                onNavigation = Some(handleNavigation),
                bgClass = "bg-base-200",
                width = "w-48",
              ),
            ),
          ),
        ),

        // Collapsible Sidebar
        div(
          cls := "w-full md:w-auto",
          Card <> CardProps(
            title = Some("Collapsible Sidebar"),
            className = "mb-8",
            children = div(
              Sidebar <> SidebarProps(
                items = sidebarItems,
                collapsible = true,
                initialCollapsed = false,
                onNavigation = Some(handleNavigation),
                bgClass = "bg-primary bg-opacity-10",
                bordered = true,
                width = "w-60",
              ),
            ),
          ),
        ),
      ),

      // Additional variants
      div(
        cls := "grid grid-cols-1 md:grid-cols-2 gap-8 mt-8",

        // Boxed style
        Card <> CardProps(
          title = Some("Boxed Style"),
          className = "mb-8",
          children = div(
            Sidebar <> SidebarProps(
              items = sidebarItems,
              variant = "boxed",
              expandedSections = List("files", "files-documents"),
              onNavigation = Some(handleNavigation),
              width = "w-full",
            ),
          ),
        ),

        // Large Size
        Card <> CardProps(
          title = Some("Large Size"),
          className = "mb-8",
          children = div(
            Sidebar <> SidebarProps(
              items = sidebarItems.take(3),
              size = "lg",
              onNavigation = Some(handleNavigation),
              bgClass = "bg-neutral text-neutral-content",
              width = "w-full",
            ),
          ),
        ),
      ),

      // Integration example with content
      Card <> CardProps(
        title = Some("Sidebar with Content Example"),
        className = "mt-8",
        children = div(
          cls := "flex flex-col md:flex-row gap-4",
          div(
            cls := "w-full md:w-64",
            Sidebar <> SidebarProps(
              items = sidebarItems,
              onNavigation = Some(handleNavigation),
              bordered = true,
              bgClass = "bg-base-200",
              width = "w-full md:w-64",
            ),
          ),
          div(
            cls := "flex-1 bg-base-100 p-4 rounded-box border border-base-300",
            h3(
              cls := "text-xl font-bold mb-3",
              "Content Area",
            ),
            p(
              cls := "mb-3",
              "This demonstrates how the sidebar would look integrated with content. Click on sidebar items to see the navigation in action.",
            ),
            p(
              cls := "text-base-content/70",
              s"Active section: $activeNavId",
            ),
          ),
        ),
      ),
    ),
  )
}
