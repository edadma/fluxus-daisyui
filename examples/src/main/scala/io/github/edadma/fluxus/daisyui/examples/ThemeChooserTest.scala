package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def ThemeChooserTest: FluxusNode = {
  // State to track theme changes for demonstration
  val (lastThemeChange, setLastThemeChange, _) = useState("")

  // Define a few sample DaisyUI themes to showcase
  val minimalThemes = List("light", "dark")
  val popularThemes = List("light", "dark", "cupcake", "forest", "aqua", "synthwave")

  // Common handler for theme changes to display when themes change
  def handleThemeChange(theme: String): Unit = {
    setLastThemeChange(s"Theme changed to: $theme")
  }

  div(
    cls := "min-h-screen bg-base-100 text-base-content p-4",

    // Header
    div(
      cls := "text-center mb-8",
      h1(cls := "text-3xl font-bold", "ThemeChooser Component Demo"),
      p(cls  := "text-base-content/70 mt-2", "Try different variants and configurations of the ThemeChooser component"),
    ),

    // Last theme change notification
    div(
      cls := "alert alert-info mb-8 max-w-3xl mx-auto",
      svg(
        xmlns       := "http://www.w3.org/2000/svg",
        fill        := "none",
        viewBox     := "0 0 24 24",
        strokeWidth := "2",
        stroke      := "currentColor",
        cls         := "w-6 h-6",
        path(
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          d              := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z",
        ),
      ),
      span(
        if (lastThemeChange.nonEmpty) lastThemeChange
        else "Change a theme to see this update",
      ),
    ),

    // Demo cards grid
    div(
      cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 max-w-6xl mx-auto",

      // Card 1: Toggle variant
      div(
        cls := "card bg-base-200 shadow-xl",
        div(
          cls := "card-body",
          h2(cls := "card-title", "Toggle Variant"),
          p("Simple light/dark toggle with sun/moon icons"),
          div(
            cls := "flex justify-center my-4",
            ThemeChooser <> ThemeChooserProps(
              variant = "toggle",
              themes = minimalThemes,
              onThemeChange = Some(handleThemeChange),
            ),
          ),

          // Sample UI that responds to theme changes
          div(
            cls := "flex justify-center gap-2 mt-4",
            button(cls := "btn btn-primary", "Primary"),
            button(cls := "btn btn-secondary", "Secondary"),
          ),
        ),
      ),

      // Card 2: Dropdown variant
      div(
        cls := "card bg-base-200 shadow-xl",
        div(
          cls := "card-body",
          h2(cls := "card-title", "Dropdown Variant"),
          p("Select from multiple themes via dropdown"),
          div(
            cls := "flex justify-center my-4",
            ThemeChooser <> ThemeChooserProps(
              variant = "dropdown",
              themes = popularThemes,
              showLabel = true,
              onThemeChange = Some(handleThemeChange),
            ),
          ),

          // Sample UI elements
          div(
            cls := "flex flex-wrap justify-center gap-2 mt-4",
            div(cls := "badge badge-primary", "Badge"),
            div(cls := "badge badge-secondary", "Badge"),
            div(cls := "badge badge-accent", "Badge"),
          ),
        ),
      ),

      // Card 3: Button/Modal variant
      div(
        cls := "card bg-base-200 shadow-xl",
        div(
          cls := "card-body",
          h2(cls := "card-title", "Modal Variant"),
          p("Opens a modal with theme preview cards"),
          div(
            cls := "flex justify-center my-4",
            ThemeChooser <> ThemeChooserProps(
              variant = "button",
              themes = popularThemes,
              onThemeChange = Some(handleThemeChange),
            ),
          ),

          // Progress bars to show theme colors
          div(
            cls := "w-full space-y-2 mt-4",
            div(cls      := "text-xs", "Primary"),
            progress(cls := "progress progress-primary w-full", value   := "40", max := "100"),
            div(cls      := "text-xs mt-2", "Secondary"),
            progress(cls := "progress progress-secondary w-full", value := "70", max := "100"),
          ),
        ),
      ),

      // Card 4: No system preference
      div(
        cls := "card bg-base-200 shadow-xl",
        div(
          cls := "card-body",
          h2(cls := "card-title", "Without System Preference"),
          p("Ignores system dark/light mode"),
          div(
            cls := "flex justify-center my-4",
            ThemeChooser <> ThemeChooserProps(
              variant = "dropdown",
              themes = List("light", "dark", "cupcake"),
              respectSystemPreference = false,
              defaultTheme = "cupcake",
              onThemeChange = Some(handleThemeChange),
            ),
          ),
          div(
            cls := "flex justify-center gap-2 mt-4",
            div(cls  := "avatar placeholder"),
            div(cls  := "bg-neutral-focus text-neutral-content rounded-full w-12"),
            span(cls := "text-xl", "A"),
          ),
        ),
      ),

      // Card 5: Different dropdown alignment
      div(
        cls := "card bg-base-200 shadow-xl",
        div(
          cls := "card-body",
          h2(cls := "card-title", "Dropdown Alignment"),
          p("Control dropdown menu positioning"),
          div(
            cls := "flex justify-between my-4",
            ThemeChooser <> ThemeChooserProps(
              variant = "dropdown",
              themes = minimalThemes,
              alignment = "start",
              className = "mb-2",
            ),
            ThemeChooser <> ThemeChooserProps(
              variant = "dropdown",
              themes = minimalThemes,
              alignment = "end",
              className = "mb-2",
            ),
          ),
          div(
            cls := "alert alert-success mt-4",
            span("Notice how dropdowns open in different directions"),
          ),
        ),
      ),

      // Card 6: Combined with labels
      div(
        cls := "card bg-base-200 shadow-xl",
        div(
          cls := "card-body",
          h2(cls := "card-title", "With Labels"),
          p("Adding labels for better context"),
          div(
            cls := "flex flex-col items-center gap-4 my-4",
            ThemeChooser <> ThemeChooserProps(
              variant = "toggle",
              showLabel = true,
              onThemeChange = Some(handleThemeChange),
            ),
            ThemeChooser <> ThemeChooserProps(
              variant = "dropdown",
              themes = minimalThemes,
              showLabel = true,
              onThemeChange = Some(handleThemeChange),
            ),
          ),

          // Card to show base colors
          div(
            cls := "grid grid-cols-4 gap-1 mt-4",
            div(cls := "p-2 bg-base-100 text-center text-xs", "100"),
            div(cls := "p-2 bg-base-200 text-center text-xs", "200"),
            div(cls := "p-2 bg-base-300 text-center text-xs", "300"),
            div(cls := "p-2 bg-neutral text-neutral-content text-center text-xs", "N"),
          ),
        ),
      ),
    ),

    // Footer
    div(
      cls := "text-center mt-16 text-base-content/60",
      p("Try changing themes and observe how UI elements update"),
      p(cls := "text-sm mt-2", "Check localStorage in devtools to see saved preferences"),
    ),
  )
}
