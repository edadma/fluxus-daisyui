package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def TabsTest: FluxusNode = {
  // State for controlled tabs examples
  val (activeBasicTab, setActiveBasicTab, _) = useState("tab1")
  val (activeIconTab, setActiveIconTab, _)   = useState("home")

  // Icons for the tabs with icons example
  def HomeIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "18",
      height         := "18",
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

  def UserIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "18",
      height         := "18",
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

  def SettingsIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "18",
      height         := "18",
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

  def BellIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "18",
      height         := "18",
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

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Tabs Component Demo",
      ),

      // Basic Tabs Example
      Card <> CardProps(
        title = Some("Basic Tabs (Controlled)"),
        className = "mb-8",
        children = div(
          p(cls := "mb-4 text-base-content/70", "This example shows the default bordered tabs with controlled state."),
          Tabs <> TabsProps(
            activeTab = activeBasicTab,
            onTabChange = setActiveBasicTab,
            children = List(
              TabPanelProps(
                id = "tab1",
                title = "Tab 1",
                children = div(
                  h3(cls := "text-lg font-medium mb-2", "Tab 1 Content"),
                  p("This is the content for Tab 1. The tabs in this example are controlled by React state."),
                  div(
                    cls := "mt-4",
                    Button <> ButtonProps(
                      text = "Next Tab",
                      onClick = () => setActiveBasicTab("tab2"),
                      variant = "primary",
                    ),
                  ),
                ),
              ),
              TabPanelProps(
                id = "tab2",
                title = "Tab 2",
                badge = Some("New"),
                children = div(
                  h3(cls := "text-lg font-medium mb-2", "Tab 2 Content"),
                  p("This is the content for Tab 2. Notice the badge on the tab indicating it's new."),
                  div(
                    cls := "mt-4",
                    Button <> ButtonProps(
                      text = "Next Tab",
                      onClick = () => setActiveBasicTab("tab3"),
                      variant = "primary",
                    ),
                  ),
                ),
              ),
              TabPanelProps(
                id = "tab3",
                title = "Tab 3",
                children = div(
                  h3(cls := "text-lg font-medium mb-2", "Tab 3 Content"),
                  p("This is the content for Tab 3. You can navigate back to the first tab."),
                  div(
                    cls := "mt-4",
                    Button <> ButtonProps(
                      text = "Back to First Tab",
                      onClick = () => setActiveBasicTab("tab1"),
                      variant = "primary",
                    ),
                  ),
                ),
              ),
              TabPanelProps(
                id = "tab4",
                title = "Disabled Tab",
                disabled = true,
                children = div(
                  "This content won't be shown because the tab is disabled.",
                ),
              ),
            ),
          ),
        ),
      ),

      // Tabs with Different Styles
      Card <> CardProps(
        title = Some("Tab Style Variants"),
        className = "mb-8",
        children = div(
          cls := "space-y-8",

          // Lifted Tabs
          div(
            h3(cls := "text-lg font-medium mb-2", "Lifted Style"),
            Tabs <> TabsProps(
              variant = "lifted",
              children = List(
                TabPanelProps(
                  id = "lifted1",
                  title = "Profile",
                  children = div(
                    p("Lifted tabs have a subtle curved appearance, giving a 3D-like effect."),
                    p(cls := "mt-2", "This style works well for content that should appear to float above the page."),
                  ),
                ),
                TabPanelProps(
                  id = "lifted2",
                  title = "Settings",
                  children = div(
                    p("This is the settings content for the lifted tab style example."),
                    div(
                      cls := "mt-4 p-3 bg-base-200 rounded",
                      "Settings options would go here...",
                    ),
                  ),
                ),
                TabPanelProps(
                  id = "lifted3",
                  title = "Messages",
                  badge = Some("5"),
                  badgeVariant = "error",
                  children = div(
                    p("This tab has a badge showing unread message count."),
                    ul(
                      cls := "mt-4 space-y-2",
                      li(cls := "p-2 rounded bg-base-200", "Message 1"),
                      li(cls := "p-2 rounded bg-base-200", "Message 2"),
                      li(cls := "p-2 rounded bg-base-200", "Message 3"),
                      li(cls := "p-2 rounded bg-base-200", "Message 4"),
                      li(cls := "p-2 rounded bg-base-200", "Message 5"),
                    ),
                  ),
                ),
              ),
            ),
          ),

          // Boxed Tabs
          div(
            h3(cls := "text-lg font-medium mb-2", "Boxed Style"),
            Tabs <> TabsProps(
              variant = "boxed",
              children = List(
                TabPanelProps(
                  id = "boxed1",
                  title = "About",
                  children = div(
                    p("Boxed tabs have a contained appearance, with a background color filling the entire tab area."),
                    p(cls := "mt-2", "This style is useful for tabs that should stand out more distinctly."),
                  ),
                ),
                TabPanelProps(
                  id = "boxed2",
                  title = "Features",
                  children = div(
                    p("Features content for the boxed tab style."),
                    ul(
                      cls := "mt-4 list-disc list-inside space-y-1",
                      li("Feature 1: Lorem ipsum dolor sit amet"),
                      li("Feature 2: Consectetur adipiscing elit"),
                      li("Feature 3: Sed do eiusmod tempor incididunt"),
                    ),
                  ),
                ),
                TabPanelProps(
                  id = "boxed3",
                  title = "Pricing",
                  children = div(
                    p("Pricing content for the boxed tab style."),
                    div(
                      cls := "grid grid-cols-1 md:grid-cols-3 gap-4 mt-4",
                      div(
                        cls := "p-4 border rounded",
                        h4(cls := "font-bold", "Basic"),
                        p(cls  := "font-mono text-lg", "$10/mo"),
                        p(cls  := "text-sm mt-2", "For individual users"),
                      ),
                      div(
                        cls := "p-4 border rounded bg-primary text-primary-content",
                        h4(cls := "font-bold", "Pro"),
                        p(cls  := "font-mono text-lg", "$29/mo"),
                        p(cls  := "text-sm mt-2", "For small teams"),
                      ),
                      div(
                        cls := "p-4 border rounded",
                        h4(cls := "font-bold", "Enterprise"),
                        p(cls  := "font-mono text-lg", "$99/mo"),
                        p(cls  := "text-sm mt-2", "For large organizations"),
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),

      // Tabs with Icons
      Card <> CardProps(
        title = Some("Tabs with Icons"),
        className = "mb-8",
        children = div(
          p(cls := "mb-4 text-base-content/70", "Tabs can include icons alongside text for better visual recognition."),
          Tabs <> TabsProps(
            activeTab = activeIconTab,
            onTabChange = setActiveIconTab,
            variant = "bordered",
            children = List(
              TabPanelProps(
                id = "home",
                title = "Home",
                icon = Some(HomeIcon),
                children = div(
                  h3(cls := "text-lg font-medium mb-2", "Home Dashboard"),
                  p("Welcome to your dashboard overview."),
                  div(
                    cls := "grid grid-cols-1 md:grid-cols-2 gap-4 mt-4",
                    div(
                      cls := "bg-base-200 p-4 rounded-box",
                      h4(cls := "font-bold", "Quick Stats"),
                      p("Your account is in good standing."),
                    ),
                    div(
                      cls := "bg-base-200 p-4 rounded-box",
                      h4(cls := "font-bold", "Recent Activity"),
                      p("No recent activity to show."),
                    ),
                  ),
                ),
              ),
              TabPanelProps(
                id = "profile",
                title = "Profile",
                icon = Some(UserIcon),
                children = div(
                  h3(cls := "text-lg font-medium mb-2", "User Profile"),
                  div(
                    cls := "flex flex-col md:flex-row gap-4",
                    div(
                      cls := "flex-none",
                      Avatar <> AvatarProps(
                        text = Some("JD"),
                        size = "lg",
                        bgClass = "bg-primary",
                        textColorClass = "text-primary-content",
                      ),
                    ),
                    div(
                      cls := "flex-1",
                      p(cls := "font-bold text-lg", "John Doe"),
                      p(cls := "text-base-content/70", "john.doe@example.com"),
                      p(cls := "mt-2", "Member since April 2023"),
                    ),
                  ),
                ),
              ),
              TabPanelProps(
                id = "notifications",
                title = "Notifications",
                icon = Some(BellIcon),
                badge = Some("3"),
                badgeVariant = "error",
                children = div(
                  h3(cls := "text-lg font-medium mb-2", "Notifications"),
                  div(
                    cls := "space-y-3 mt-2",
                    Alert <> AlertProps(
                      variant = "info",
                      compact = true,
                      title = Some("System Update"),
                      message = Some("A new system update is available."),
                    ),
                    Alert <> AlertProps(
                      variant = "success",
                      compact = true,
                      title = Some("Payment Received"),
                      message = Some("Your payment was processed successfully."),
                    ),
                    Alert <> AlertProps(
                      variant = "warning",
                      compact = true,
                      title = Some("Storage Limit"),
                      message = Some("You are approaching your storage limit."),
                    ),
                  ),
                ),
              ),
              TabPanelProps(
                id = "settings",
                title = "Settings",
                icon = Some(SettingsIcon),
                children = div(
                  h3(cls := "text-lg font-medium mb-2", "Settings"),
                  div(
                    cls := "space-y-4",
                    div(
                      h4(cls := "font-medium", "Account Settings"),
                      div(
                        cls := "form-control",
                        label(
                          cls := "label cursor-pointer justify-start gap-4",
                          span(cls  := "label-text", "Enable two-factor authentication"),
                          input(typ := "checkbox", cls := "checkbox"),
                        ),
                      ),
                      div(
                        cls := "form-control",
                        label(
                          cls := "label cursor-pointer justify-start gap-4",
                          span(cls  := "label-text", "Email notifications"),
                          input(typ := "checkbox", cls := "checkbox", checked := true),
                        ),
                      ),
                    ),
                    div(
                      h4(cls := "font-medium", "Privacy Settings"),
                      div(
                        cls := "form-control",
                        label(
                          cls := "label cursor-pointer justify-start gap-4",
                          span(cls  := "label-text", "Make profile public"),
                          input(typ := "checkbox", cls := "checkbox"),
                        ),
                      ),
                      div(
                        cls := "form-control",
                        label(
                          cls := "label cursor-pointer justify-start gap-4",
                          span(cls  := "label-text", "Show online status"),
                          input(typ := "checkbox", cls := "checkbox", checked := true),
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),

      // Tabs with Different Alignments
      Card <> CardProps(
        title = Some("Tab Alignments"),
        className = "mb-8",
        children = div(
          cls := "space-y-8",

          // Center aligned tabs
          div(
            h3(cls := "text-lg font-medium mb-2", "Center Aligned"),
            Tabs <> TabsProps(
              variant = "boxed",
              alignment = "center",
              children = List(
                TabPanelProps(
                  id = "center1",
                  title = "Tab 1",
                  children = div("Content for center-aligned Tab 1"),
                ),
                TabPanelProps(
                  id = "center2",
                  title = "Tab 2",
                  children = div("Content for center-aligned Tab 2"),
                ),
                TabPanelProps(
                  id = "center3",
                  title = "Tab 3",
                  children = div("Content for center-aligned Tab 3"),
                ),
              ),
            ),
          ),

          // End aligned tabs
          div(
            h3(cls := "text-lg font-medium mb-2", "End Aligned"),
            Tabs <> TabsProps(
              variant = "lifted",
              alignment = "end",
              children = List(
                TabPanelProps(
                  id = "end1",
                  title = "Tab 1",
                  children = div("Content for end-aligned Tab 1"),
                ),
                TabPanelProps(
                  id = "end2",
                  title = "Tab 2",
                  children = div("Content for end-aligned Tab 2"),
                ),
                TabPanelProps(
                  id = "end3",
                  title = "Tab 3",
                  children = div("Content for end-aligned Tab 3"),
                ),
              ),
            ),
          ),

          // Full width tabs
          div(
            h3(cls := "text-lg font-medium mb-2", "Full Width Tabs"),
            Tabs <> TabsProps(
              variant = "bordered",
              fullWidth = true,
              children = List(
                TabPanelProps(
                  id = "full1",
                  title = "First Tab",
                  children = div("Content for full-width Tab 1"),
                ),
                TabPanelProps(
                  id = "full2",
                  title = "Second Tab",
                  children = div("Content for full-width Tab 2"),
                ),
                TabPanelProps(
                  id = "full3",
                  title = "Third Tab",
                  children = div("Content for full-width Tab 3"),
                ),
              ),
            ),
          ),
        ),
      ),

      // Practical Example - Forms in Tabs
      Card <> CardProps(
        title = Some("Practical Example - Registration Form"),
        children = div(
          p(
            cls := "mb-4 text-base-content/70",
            "This example shows how tabs can be used to break a complex form into manageable sections.",
          ),
          Tabs <> TabsProps(
            variant = "lifted",
            children = List(
              TabPanelProps(
                id = "account",
                title = "Account",
                children = div(
                  cls := "p-2",
                  h3(cls := "text-lg font-medium mb-4", "Account Information"),
                  div(
                    cls := "space-y-4",
                    div(
                      cls := "form-control",
                      label(cls := "label", span(cls := "label-text", "Email")),
                      input(typ := "email", placeholder := "email@example.com", cls := "input input-bordered w-full"),
                    ),
                    div(
                      cls := "form-control",
                      label(cls := "label", span(cls := "label-text", "Password")),
                      input(typ := "password", placeholder := "••••••••", cls := "input input-bordered w-full"),
                    ),
                    div(
                      cls := "form-control",
                      label(cls := "label", span(cls := "label-text", "Confirm Password")),
                      input(typ := "password", placeholder := "••••••••", cls := "input input-bordered w-full"),
                    ),
                  ),
                  div(
                    cls := "mt-6 flex justify-end",
                    Button <> ButtonProps(
                      text = "Next: Personal Info",
                      variant = "primary",
                    ),
                  ),
                ),
              ),
              TabPanelProps(
                id = "personal",
                title = "Personal Info",
                children = div(
                  cls := "p-2",
                  h3(cls := "text-lg font-medium mb-4", "Personal Information"),
                  div(
                    cls := "space-y-4",
                    div(
                      cls := "grid grid-cols-1 md:grid-cols-2 gap-4",
                      div(
                        cls := "form-control",
                        label(cls := "label", span(cls := "label-text", "First Name")),
                        input(typ := "text", placeholder := "John", cls := "input input-bordered w-full"),
                      ),
                      div(
                        cls := "form-control",
                        label(cls := "label", span(cls := "label-text", "Last Name")),
                        input(typ := "text", placeholder := "Doe", cls := "input input-bordered w-full"),
                      ),
                    ),
                    div(
                      cls := "form-control",
                      label(cls := "label", span(cls := "label-text", "Date of Birth")),
                      input(typ := "date", cls := "input input-bordered w-full"),
                    ),
                    div(
                      cls := "form-control",
                      label(cls := "label", span(cls := "label-text", "Phone Number")),
                      input(typ := "tel", placeholder := "(123) 456-7890", cls := "input input-bordered w-full"),
                    ),
                  ),
                  div(
                    cls := "mt-6 flex justify-between",
                    Button <> ButtonProps(
                      text = "Back",
                      variant = "ghost",
                    ),
                    Button <> ButtonProps(
                      text = "Next: Preferences",
                      variant = "primary",
                    ),
                  ),
                ),
              ),
              TabPanelProps(
                id = "preferences",
                title = "Preferences",
                children = div(
                  cls := "p-2",
                  h3(cls := "text-lg font-medium mb-4", "User Preferences"),
                  div(
                    cls := "space-y-4",
                    div(
                      cls := "form-control",
                      label(cls := "label", span(cls := "label-text", "Language")),
                      select(
                        cls := "select select-bordered w-full",
                        option("English"),
                        option("Spanish"),
                        option("French"),
                        option("German"),
                      ),
                    ),
                    div(
                      cls := "form-control",
                      label(cls := "label", span(cls := "label-text", "Timezone")),
                      select(
                        cls := "select select-bordered w-full",
                        option("(GMT-08:00) Pacific Time"),
                        option("(GMT-05:00) Eastern Time"),
                        option("(GMT+00:00) UTC"),
                        option("(GMT+01:00) Central European Time"),
                        option("(GMT+09:00) Japan Standard Time"),
                      ),
                    ),
                    div(
                      cls := "form-control mt-4",
                      label(
                        cls := "cursor-pointer label justify-start gap-4",
                        input(typ := "checkbox", cls := "checkbox"),
                        span(cls  := "label-text", "Subscribe to newsletter"),
                      ),
                    ),
                    div(
                      cls := "form-control",
                      label(
                        cls := "cursor-pointer label justify-start gap-4",
                        input(typ := "checkbox", cls := "checkbox"),
                        span(cls  := "label-text", "Enable email notifications"),
                      ),
                    ),
                  ),
                  div(
                    cls := "mt-6 flex justify-between",
                    Button <> ButtonProps(
                      text = "Back",
                      variant = "ghost",
                    ),
                    Button <> ButtonProps(
                      text = "Create Account",
                      variant = "success",
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),
    ),
  )
}
