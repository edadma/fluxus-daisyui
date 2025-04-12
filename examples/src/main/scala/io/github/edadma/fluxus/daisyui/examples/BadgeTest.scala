package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def BadgeTest: FluxusNode = {
  // Simple SVG icon for demo purposes
  def StarIcon: FluxusNode = {
    svg(
      xmlns   := "http://www.w3.org/2000/svg",
      width   := "12",
      height  := "12",
      viewBox := "0 0 24 24",
      fill    := "currentColor",
      path(
        d := "M12 2l3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z",
      ),
    )
  }

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Badge Component Demo",
      ),

      // Basic Badges Section
      Card <> CardProps(
        title = Some("Basic Badges"),
        className = "mb-8",
        children = div(
          h3(cls := "text-lg font-medium mb-4", "Variants"),
          div(
            cls := "flex flex-wrap gap-2 mb-6",
            Badge <> BadgeProps(text = "Default", variant = ""),
            Badge <> BadgeProps(text = "Primary", variant = "primary"),
            Badge <> BadgeProps(text = "Secondary", variant = "secondary"),
            Badge <> BadgeProps(text = "Accent", variant = "accent"),
            Badge <> BadgeProps(text = "Info", variant = "info"),
            Badge <> BadgeProps(text = "Success", variant = "success"),
            Badge <> BadgeProps(text = "Warning", variant = "warning"),
            Badge <> BadgeProps(text = "Error", variant = "error"),
            Badge <> BadgeProps(text = "Ghost", variant = "ghost"),
            Badge <> BadgeProps(text = "Neutral", variant = "neutral"),
          ),
          h3(cls := "text-lg font-medium mb-4", "Sizes"),
          div(
            cls := "flex flex-wrap items-center gap-2 mb-6",
            Badge <> BadgeProps(text = "Extra Small", size = "xs", variant = "primary"),
            Badge <> BadgeProps(text = "Small", size = "sm", variant = "primary"),
            Badge <> BadgeProps(text = "Medium (Default)", variant = "primary"),
            Badge <> BadgeProps(text = "Large", size = "lg", variant = "primary"),
          ),
          h3(cls := "text-lg font-medium mb-4", "Outline Style"),
          div(
            cls := "flex flex-wrap gap-2 mb-6",
            Badge <> BadgeProps(text = "Primary", variant = "primary", outline = true),
            Badge <> BadgeProps(text = "Secondary", variant = "secondary", outline = true),
            Badge <> BadgeProps(text = "Accent", variant = "accent", outline = true),
            Badge <> BadgeProps(text = "Success", variant = "success", outline = true),
            Badge <> BadgeProps(text = "Warning", variant = "warning", outline = true),
            Badge <> BadgeProps(text = "Error", variant = "error", outline = true),
          ),
        ),
      ),

      // Use Cases Section
      Card <> CardProps(
        title = Some("Badge/Tag Use Cases"),
        className = "mb-8",
        children = div(
          h3(cls := "text-lg font-medium mb-4", "Status Indicators"),
          div(
            cls := "flex flex-wrap gap-2 mb-6",
            div(
              cls := "flex items-center gap-2",
              Badge <> BadgeProps(empty = true, variant = "success", className = "mr-1"),
              span("Active"),
            ),
            div(
              cls := "flex items-center gap-2",
              Badge <> BadgeProps(empty = true, variant = "error", className = "mr-1"),
              span("Offline"),
            ),
            div(
              cls := "flex items-center gap-2",
              Badge <> BadgeProps(empty = true, variant = "warning", className = "mr-1"),
              span("Away"),
            ),
            Badge <> BadgeProps(text = "Published", variant = "success"),
            Badge <> BadgeProps(text = "Pending Review", variant = "warning"),
            Badge <> BadgeProps(text = "Rejected", variant = "error"),
          ),
          h3(cls := "text-lg font-medium mb-4", "Count Badges"),
          div(
            cls := "flex flex-wrap gap-4 mb-6",
            div(
              cls := "relative inline-flex",
              Button <> ButtonProps(text = "Inbox"),
              div(
                cls := "absolute -top-2 -right-2",
                Badge <> BadgeProps(text = "3", variant = "secondary", size = "sm"),
              ),
            ),
            div(
              cls := "relative inline-flex",
              Button <> ButtonProps(text = "Notifications", variant = "primary"),
              div(
                cls := "absolute -top-2 -right-2",
                Badge <> BadgeProps(text = "99+", variant = "error", size = "sm"),
              ),
            ),
          ),
          h3(cls := "text-lg font-medium mb-4", "Category Tags"),
          div(
            cls := "flex flex-wrap gap-2 mb-6",
            Badge <> BadgeProps(text = "JavaScript", variant = "ghost"),
            Badge <> BadgeProps(text = "TypeScript", variant = "ghost"),
            Badge <> BadgeProps(text = "Scala", variant = "ghost"),
            Badge <> BadgeProps(text = "HTML", variant = "ghost"),
            Badge <> BadgeProps(text = "CSS", variant = "ghost"),
          ),
        ),
      ),

      // Advanced Features Section
      Card <> CardProps(
        title = Some("Advanced Features"),
        className = "mb-8",
        children = div(
          h3(cls := "text-lg font-medium mb-4", "Badges as Links"),
          div(
            cls := "flex flex-wrap gap-2 mb-6",
            Badge <> BadgeProps(
              text = "GitHub",
              variant = "neutral",
              href = Some("https://github.com"),
              target = Some("_blank"),
            ),
            Badge <> BadgeProps(
              text = "Documentation",
              variant = "primary",
              href = Some("#"),
            ),
            Badge <> BadgeProps(
              text = "Twitter",
              variant = "info",
              href = Some("https://twitter.com"),
              target = Some("_blank"),
              outline = true,
            ),
          ),
          h3(cls := "text-lg font-medium mb-4", "Badges with Custom Content"),
          div(
            cls := "flex flex-wrap gap-2 mb-6",
            Badge <> BadgeProps(
              variant = "success",
              children = Some(
                div(
                  cls := "flex items-center gap-1",
                  StarIcon,
                  span("Featured"),
                ),
              ),
            ),
            Badge <> BadgeProps(
              variant = "info",
              children = Some(
                div(
                  cls := "flex items-center gap-1",
                  span("New"),
                  svg(
                    xmlns          := "http://www.w3.org/2000/svg",
                    width          := "12",
                    height         := "12",
                    viewBox        := "0 0 24 24",
                    fill           := "none",
                    stroke         := "currentColor",
                    strokeWidth    := "2",
                    strokeLinecap  := "round",
                    strokeLinejoin := "round",
                    circle(cx := "12", cy := "12", r  := "10"),
                    line(x1   := "12", y1 := "8", x2  := "12", y2 := "16"),
                    line(x1   := "8", y1  := "12", x2 := "16", y2 := "12"),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),

      // Practical Examples Section
      Card <> CardProps(
        title = Some("Practical Examples"),
        children = div(
          // Product card with badges
          div(
            cls := "mb-6 border border-base-300 rounded-lg p-4",
            div(
              cls := "flex justify-between items-start mb-2",
              h4(cls := "text-lg font-medium", "Product Title"),
              Badge <> BadgeProps(text = "Sale", variant = "error", size = "sm"),
            ),
            p(
              cls := "text-sm text-base-content/70 mb-4",
              "Product description goes here. This is a sample product card showing how badges can be integrated.",
            ),
            div(
              cls := "flex flex-wrap gap-1 mb-3",
              Badge <> BadgeProps(text = "Electronics", variant = "ghost", size = "sm"),
              Badge <> BadgeProps(text = "Wireless", variant = "ghost", size = "sm"),
              Badge <> BadgeProps(text = "Bluetooth", variant = "ghost", size = "sm"),
            ),
            div(
              cls := "flex justify-between items-center",
              div(
                span(cls := "text-lg font-bold", "$99.99 "),
                span(cls := "text-sm line-through text-base-content/50", "$129.99"),
              ),
              Badge <> BadgeProps(text = "In Stock", variant = "success", size = "sm"),
            ),
          ),

          // User profile with badges
          div(
            cls := "flex items-center gap-4 border border-base-300 rounded-lg p-4",
            Avatar <> AvatarProps(
              text = Some("JD"),
              size = "md",
            ),
            div(
              cls := "flex-1",
              h4(cls := "text-lg font-medium", "John Doe"),
              p(cls  := "text-sm text-base-content/70", "Full Stack Developer"),
              div(
                cls := "flex flex-wrap gap-1 mt-2",
                Badge <> BadgeProps(text = "Admin", variant = "primary", size = "sm"),
                Badge <> BadgeProps(text = "Pro User", variant = "secondary", size = "sm", outline = true),
              ),
            ),
          ),
        ),
      ),
    ),
  )
}
