package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*
import org.scalajs.dom

def AvatarTest: FluxusNode = {
  // Simple SVG icon for demo purposes
  def UserIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "24",
      height         := "24",
      viewBox        := "0 0 24 24",
      fill           := "none",
      stroke         := "currentColor",
      strokeWidth    := "2",
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      path(
        d := "M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2",
      ),
      circle(
        cx := "12",
        cy := "7",
        r  := "4",
      ),
    )
  }

  // Simple SVG icon for demo purposes
  def StarIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "24",
      height         := "24",
      viewBox        := "0 0 24 24",
      fill           := "none",
      stroke         := "currentColor",
      strokeWidth    := "2",
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      polygon(
        points := "12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2",
      ),
    )
  }

  // Simple SVG icon for demo purposes
  def CodeIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      width          := "24",
      height         := "24",
      viewBox        := "0 0 24 24",
      fill           := "none",
      stroke         := "currentColor",
      strokeWidth    := "2",
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      polyline(
        points := "16 18 22 12 16 6",
      ),
      polyline(
        points := "8 6 2 12 8 18",
      ),
    )
  }

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Avatar Component Demo",
      ),

      // Basic Avatars Section
      Card <> CardProps(
        title = Some("Basic Avatars"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4 items-center",

          // Image avatar
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              src = Some("/api/placeholder/100/100"), // Placeholder image
              alt = "User avatar",
            ),
            p(cls := "mt-2 text-sm", "Image"),
          ),

          // Text avatar
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("AB"),
            ),
            p(cls := "mt-2 text-sm", "Text"),
          ),

          // Icon avatar
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              icon = Some(UserIcon),
            ),
            p(cls := "mt-2 text-sm", "Icon"),
          ),

          // Placeholder avatar
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              placeholder = true,
            ),
            p(cls := "mt-2 text-sm", "Placeholder"),
          ),
        ),
      ),

      // Sizes Section
      Card <> CardProps(
        title = Some("Avatar Sizes"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4 items-end",

          // Extra small
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("XS"),
              size = "xs",
            ),
            p(cls := "mt-2 text-sm", "XS"),
          ),

          // Small
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("SM"),
              size = "sm",
            ),
            p(cls := "mt-2 text-sm", "SM"),
          ),

          // Medium (default)
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("MD"),
              size = "md",
            ),
            p(cls := "mt-2 text-sm", "MD"),
          ),

          // Large
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("LG"),
              size = "lg",
            ),
            p(cls := "mt-2 text-sm", "LG"),
          ),
        ),
      ),

      // Shapes Section
      Card <> CardProps(
        title = Some("Avatar Shapes"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4 items-center",

          // Rounded (default)
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("R"),
              shape = "rounded",
            ),
            p(cls := "mt-2 text-sm", "Rounded"),
          ),

          // Square
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("S"),
              shape = "square",
            ),
            p(cls := "mt-2 text-sm", "Square"),
          ),

          // Hexagon
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("H"),
              shape = "hexagon",
            ),
            p(cls := "mt-2 text-sm", "Hexagon"),
          ),

          // Triangle
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("T"),
              shape = "triangle",
            ),
            p(cls := "mt-2 text-sm", "Triangle"),
          ),

          // Squircle
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("SQ"),
              shape = "squircle",
            ),
            p(cls := "mt-2 text-sm", "Squircle"),
          ),
        ),
      ),

      // Styling Section
      Card <> CardProps(
        title = Some("Avatar Styling"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4 items-center",

          // With border
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("B"),
              border = true,
              borderWidth = "border-4",
              borderColor = "border-primary",
            ),
            p(cls := "mt-2 text-sm", "Border"),
          ),

          // With ring
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("R"),
              ring = true,
              ringColor = "ring-secondary",
              ringOffset = "ring-offset-2",
            ),
            p(cls := "mt-2 text-sm", "Ring"),
          ),

          // With custom colors
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("C"),
              bgClass = "bg-accent",
              textColorClass = "text-accent-content",
            ),
            p(cls := "mt-2 text-sm", "Custom Colors"),
          ),

          // With outline
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("O"),
              outline = true,
              outlineColor = "outline-error",
              outlineOffset = "outline-offset-2",
            ),
            p(cls := "mt-2 text-sm", "Outline"),
          ),
        ),
      ),

      // Status Indicators Section
      Card <> CardProps(
        title = Some("Status Indicators"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4 items-center",

          // Online
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("ON"),
              online = true,
            ),
            p(cls := "mt-2 text-sm", "Online"),
          ),

          // Offline
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("OFF"),
              offline = true,
            ),
            p(cls := "mt-2 text-sm", "Offline"),
          ),

          // Busy
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("BZ"),
              busy = true,
            ),
            p(cls := "mt-2 text-sm", "Busy"),
          ),

          // Away
          div(
            cls := "flex flex-col items-center",
            Avatar <> AvatarProps(
              text = Some("AW"),
              away = true,
            ),
            p(cls := "mt-2 text-sm", "Away"),
          ),
        ),
      ),

      // Avatar Group Section
      Card <> CardProps(
        title = Some("Avatar Groups"),
        className = "mb-8",
        children = div(
          cls := "space-y-8",

          // Default (end alignment)
          div(
            h3(cls := "text-lg mb-2", "End Alignment (Default)"),
            AvatarGroup <> AvatarGroupProps(
              children = List(
                Avatar <> AvatarProps(icon = Some(UserIcon), ring = true, ringColor = "ring-primary"),
                Avatar <> AvatarProps(text = Some("B"), bgClass = "bg-secondary"),
                Avatar <> AvatarProps(text = Some("C"), bgClass = "bg-accent"),
                Avatar <> AvatarProps(icon = Some(StarIcon), bgClass = "bg-info"),
                Avatar <> AvatarProps(text = Some("+5")),
              ),
            ),
          ),

          // Center alignment
          div(
            h3(cls := "text-lg mb-2", "Center Alignment"),
            AvatarGroup <> AvatarGroupProps(
              offset = "center",
              children = List(
                Avatar <> AvatarProps(text = Some("1"), bgClass = "bg-primary"),
                Avatar <> AvatarProps(text = Some("2"), bgClass = "bg-secondary"),
                Avatar <> AvatarProps(text = Some("3"), bgClass = "bg-accent"),
                Avatar <> AvatarProps(text = Some("4"), bgClass = "bg-info"),
                Avatar <> AvatarProps(text = Some("5"), bgClass = "bg-success"),
              ),
            ),
          ),

          // Start alignment
          div(
            h3(cls := "text-lg mb-2", "Start Alignment"),
            AvatarGroup <> AvatarGroupProps(
              offset = "start",
              children = List(
                Avatar <> AvatarProps(icon = Some(UserIcon), bgClass = "bg-primary"),
                Avatar <> AvatarProps(icon = Some(StarIcon), bgClass = "bg-secondary"),
                Avatar <> AvatarProps(icon = Some(CodeIcon), bgClass = "bg-accent"),
              ),
            ),
          ),
        ),
      ),

      // Interactive Avatar Example (using wrapper div)
      Card <> CardProps(
        title = Some("Interactive Avatars (using wrapper elements)"),
        children = div(
          cls := "flex flex-wrap gap-4 items-center",

          // Clickable wrapper
          div(
            cls := "flex flex-col items-center",
            button(
              onClick := (() => dom.window.alert("Avatar clicked!")),
              cls     := "cursor-pointer focus:outline-none",
              Avatar <> AvatarProps(
                text = Some("CL"),
                bgClass = "bg-primary",
              ),
            ),
            p(cls := "mt-2 text-sm", "Wrapped in Button"),
          ),

          // Hover effects wrapper
          div(
            cls := "flex flex-col items-center",
            div(
              cls := "transition-transform hover:scale-110 cursor-pointer",
              Avatar <> AvatarProps(
                text = Some("HV"),
                bgClass = "bg-secondary",
              ),
            ),
            p(cls := "mt-2 text-sm", "Wrapped with Hover Effect"),
          ),
        ),
      ),
    ),
  )
}
