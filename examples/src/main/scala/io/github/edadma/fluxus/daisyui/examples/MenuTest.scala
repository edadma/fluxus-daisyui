package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def MenuTest: FluxusNode = {
  // Example icons
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

  def UserIcon: FluxusNode = {
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
      path(d    := "M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"),
      circle(cx := "12", cy := "7", r := "4"),
    )
  }

  def ChevronRightIcon: FluxusNode = {
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
  }

  // State for active menu item
  val (activeItem, setActiveItem, _) = useState("")

  // Handler for menu clicks
  def handleMenuClick(itemId: String)(): Unit = {
    setActiveItem(itemId)
  }

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Menu Component Demo",
      ),

      // Grid for different menu examples
      div(
        cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6",

        // Basic Menu Example
        Card <> CardProps(
          title = Some("Basic Menu"),
          children = div(
            Menu <> MenuProps(
              size = "md",
              bordered = true,
              rounded = true,
              bgClass = "bg-base-200",
              children = Seq(
                MenuTitle <> MenuTitleProps(
                  title = "Menu Title",
                ),
                MenuItem <> MenuItemProps(
                  title = "Dashboard",
                  icon = Some(HomeIcon),
                  active = activeItem == "dashboard",
                  onClick = Some(handleMenuClick("dashboard")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Settings",
                  icon = Some(SettingsIcon),
                  active = activeItem == "settings",
                  onClick = Some(handleMenuClick("settings")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Profile",
                  icon = Some(UserIcon),
                  active = activeItem == "profile",
                  onClick = Some(handleMenuClick("profile")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Disabled Item",
                  disabled = true,
                ),
              ),
            ),
          ),
        ),

        // Menu with Badges and Icons
        Card <> CardProps(
          title = Some("Menu with Badges"),
          children = div(
            Menu <> MenuProps(
              size = "md",
              children = Seq(
                MenuItem <> MenuItemProps(
                  title = "Inbox",
                  icon = Some(
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
                      path(d          := "M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"),
                      polyline(points := "22,6 12,13 2,6"),
                    ),
                  ),
                  badge = Some("5"),
                  badgeVariant = "primary",
                  active = activeItem == "inbox",
                  onClick = Some(handleMenuClick("inbox")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Notifications",
                  icon = Some(
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
                    ),
                  ),
                  badge = Some("12"),
                  badgeVariant = "error",
                  active = activeItem == "notifications",
                  onClick = Some(handleMenuClick("notifications")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Tasks",
                  icon = Some(
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
                      path(d := "M9 11l3 3L22 4"),
                      path(d := "M21 12v7a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11"),
                    ),
                  ),
                  badge = Some("New"),
                  badgeVariant = "success",
                  active = activeItem == "tasks",
                  onClick = Some(handleMenuClick("tasks")),
                ),
              ),
            ),
          ),
        ),

        // Menu with Submenu
        Card <> CardProps(
          title = Some("Menu with Submenu"),
          children = div(
            Menu <> MenuProps(
              size = "md",
              children = Seq(
                MenuItem <> MenuItemProps(
                  title = "Home",
                  icon = Some(HomeIcon),
                  active = activeItem == "home",
                  onClick = Some(handleMenuClick("home")),
                ),
                MenuSubmenu <> MenuSubmenuProps(
                  title = "User Management",
                  icon = Some(UserIcon),
                  expanded = activeItem.startsWith("user-"),
                  children = Seq(
                    MenuItem <> MenuItemProps(
                      title = "View Users",
                      active = activeItem == "user-view",
                      onClick = Some(handleMenuClick("user-view")),
                    ),
                    MenuItem <> MenuItemProps(
                      title = "Add User",
                      active = activeItem == "user-add",
                      onClick = Some(handleMenuClick("user-add")),
                    ),
                    MenuItem <> MenuItemProps(
                      title = "User Groups",
                      active = activeItem == "user-groups",
                      onClick = Some(handleMenuClick("user-groups")),
                    ),
                  ),
                ),
                MenuSubmenu <> MenuSubmenuProps(
                  title = "Settings",
                  icon = Some(SettingsIcon),
                  expanded = activeItem.startsWith("settings-"),
                  children = Seq(
                    MenuItem <> MenuItemProps(
                      title = "General",
                      active = activeItem == "settings-general",
                      onClick = Some(handleMenuClick("settings-general")),
                    ),
                    MenuItem <> MenuItemProps(
                      title = "Security",
                      active = activeItem == "settings-security",
                      onClick = Some(handleMenuClick("settings-security")),
                    ),
                    MenuItem <> MenuItemProps(
                      title = "Notifications",
                      active = activeItem == "settings-notifications",
                      onClick = Some(handleMenuClick("settings-notifications")),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),

        // Compact Menu Example
        Card <> CardProps(
          title = Some("Compact Menu"),
          children = div(
            Menu <> MenuProps(
              size = "sm",
              variant = "compact",
              bgClass = "bg-base-200",
              shadow = true,
              children = Seq(
                MenuTitle <> MenuTitleProps(
                  title = "Profile",
                ),
                MenuItem <> MenuItemProps(
                  title = "Account",
                  icon = Some(UserIcon),
                  active = activeItem == "account",
                  onClick = Some(handleMenuClick("account")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Security",
                  icon = Some(
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
                      rect(x := "3", y := "11", width := "18", height := "11", rx := "2", ry := "2"),
                      path(d := "M7 11V7a5 5 0 0 1 10 0v4"),
                    ),
                  ),
                  active = activeItem == "security",
                  onClick = Some(handleMenuClick("security")),
                ),
                MenuDivider <> MenuDividerProps(),
                MenuTitle <> MenuTitleProps(
                  title = "System",
                ),
                MenuItem <> MenuItemProps(
                  title = "Logout",
                  icon = Some(
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
                      path(d          := "M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"),
                      polyline(points := "16 17 21 12 16 7"),
                      line(x1         := "21", y1 := "12", x2 := "9", y2 := "12"),
                    ),
                  ),
                  onClick = Some(handleMenuClick("logout")),
                ),
              ),
            ),
          ),
        ),

        // Horizontal Menu Example
        Card <> CardProps(
          title = Some("Horizontal Menu"),
          children = div(
            Menu <> MenuProps(
              variant = "horizontal",
              size = "md",
              bgClass = "bg-base-200",
              rounded = true,
              bordered = true,
              children = Seq(
                MenuItem <> MenuItemProps(
                  title = "Home",
                  active = activeItem == "home-horiz",
                  onClick = Some(handleMenuClick("home-horiz")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Products",
                  active = activeItem == "products",
                  onClick = Some(handleMenuClick("products")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Services",
                  active = activeItem == "services",
                  onClick = Some(handleMenuClick("services")),
                ),
                MenuItem <> MenuItemProps(
                  title = "About",
                  active = activeItem == "about",
                  onClick = Some(handleMenuClick("about")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Contact",
                  active = activeItem == "contact",
                  onClick = Some(handleMenuClick("contact")),
                ),
              ),
            ),
          ),
        ),

        // Menu with End Icons and Links
        Card <> CardProps(
          title = Some("Menu with Links"),
          children = div(
            Menu <> MenuProps(
              size = "md",
              children = Seq(
                MenuTitle <> MenuTitleProps(
                  title = "Documentation",
                ),
                MenuItem <> MenuItemProps(
                  title = "Installation",
                  href = Some("#installation"),
                  endIcon = Some(ChevronRightIcon),
                  active = activeItem == "installation",
                  onClick = Some(handleMenuClick("installation")),
                ),
                MenuItem <> MenuItemProps(
                  title = "Components",
                  href = Some("#components"),
                  endIcon = Some(ChevronRightIcon),
                  active = activeItem == "components",
                  onClick = Some(handleMenuClick("components")),
                ),
                MenuItem <> MenuItemProps(
                  title = "API Reference",
                  href = Some("#api"),
                  endIcon = Some(ChevronRightIcon),
                  active = activeItem == "api",
                  onClick = Some(handleMenuClick("api")),
                ),
                MenuDivider <> MenuDividerProps(),
                MenuItem <> MenuItemProps(
                  title = "External Link",
                  href = Some("https://example.com"),
                  endIcon = Some(
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
                      path(d          := "M18 13v6a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h6"),
                      polyline(points := "15 3 21 3 21 9"),
                      line(x1         := "10", y1 := "14", x2 := "21", y2 := "3"),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),

      // State display
      div(
        cls := "mt-8 p-4 bg-base-200 rounded-box",
        h3(cls := "text-lg font-semibold mb-2", "Active Item"),
        p(
          activeItem.nonEmpty match {
            case true  => s"Selected: '$activeItem'"
            case false => "Nothing selected yet"
          },
        ),
      ),
    ),
  )
}
