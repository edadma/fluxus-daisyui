package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom
import scala.scalajs.js

/** ThemeChooser component props */
case class ThemeChooserProps(
    // Theme options
    themes: List[String] = List("light", "dark"), // Supported themes
    defaultTheme: String = "light",               // Default if no preference exists
    respectSystemPreference: Boolean = true,      // Use system preference when possible

    // UI options
    variant: String = "toggle", // toggle, dropdown, or button (for modal)
    alignment: String = "end",  // start, center, end (for positioning)
    showLabel: Boolean = false, // Whether to show "Theme" label

    // Additional styling
    className: String = "", // Additional CSS classes

    // Callback when theme changes
    onThemeChange: Option[String => Unit] = None, // Optional callback when theme changes
)

/** ThemeChooser component that detects and respects user color scheme preferences while allowing manual theme selection
  * with persistence.
  */
val ThemeChooser = (props: ThemeChooserProps) => {
  // Current active theme
  val initialTheme = Option(dom.document.documentElement.getAttribute("data-theme"))
    .filter(_.nonEmpty)
    .getOrElse(props.defaultTheme)

  val (currentTheme, setCurrentTheme, _) = useState(initialTheme)

  // Whether we're in auto (system preference) mode
  val (isAutoTheme, setIsAutoTheme, _) = useState(false)

  // State for showing the modal (if using modal variant)
  val (showModal, setShowModal, _) = useState(false)

  // Effect for initialization - runs once when component mounts
  useEffect(
    () => {
      // First check localStorage
      val savedTheme  = Option(dom.window.localStorage.getItem("theme"))
      val savedIsAuto = Option(dom.window.localStorage.getItem("theme-auto")).contains("true")

      if (savedIsAuto && props.respectSystemPreference) {
        // Use auto theme (system preference)
        setIsAutoTheme(true)
        applyThemeBasedOnSystemPreference()
      } else if (savedTheme.isDefined && props.themes.contains(savedTheme.get)) {
        // Use saved theme if it's in our list of supported themes
        setTheme(savedTheme.get)
      } else if (props.respectSystemPreference) {
        // No saved preference, so use system preference
        applyThemeBasedOnSystemPreference()
      } else {
        // Fallback to default theme
        setTheme(props.defaultTheme)
      }

      // Set up media query listener for system preference changes
      val mediaQuery = dom.window.matchMedia("(prefers-color-scheme: dark)")

      // For compatibility with differing browser implementations
      val handleChange = (_: dom.Event) => {
        if (isAutoTheme) {
          setTheme(if (mediaQuery.matches) "dark" else "light")
        }
      }

      // Try to use the standard event model
      try {
        // Need to force type and avoid the missing useCapture parameter
        type SafeAddEventListener = js.Function3[String, js.Function1[dom.Event, ?], Boolean, Unit]
        val addEvent = mediaQuery.asInstanceOf[js.Dynamic].addEventListener.asInstanceOf[SafeAddEventListener]
        addEvent("change", handleChange, false)
      } catch {
        case _: Exception =>
          // Fallback for older browsers
          type MediaQueryAddListener = js.Function1[js.Function1[dom.Event, ?], Unit]
          try {
            val addListener = mediaQuery.asInstanceOf[js.Dynamic].addListener.asInstanceOf[MediaQueryAddListener]
            addListener(handleChange)
          } catch {
            case _: Exception => // Do nothing if not supported
          }
      }

      // Cleanup function
      () => {
        try {
          // Need to force type and avoid the missing useCapture parameter
          type SafeRemoveEventListener = js.Function3[String, js.Function1[dom.Event, ?], Boolean, Unit]
          val removeEvent =
            mediaQuery.asInstanceOf[js.Dynamic].removeEventListener.asInstanceOf[SafeRemoveEventListener]
          removeEvent("change", handleChange, false)
        } catch {
          case _: Exception =>
            // Fallback for older browsers
            type MediaQueryRemoveListener = js.Function1[js.Function1[dom.Event, ?], Unit]
            try {
              val removeListener =
                mediaQuery.asInstanceOf[js.Dynamic].removeListener.asInstanceOf[MediaQueryRemoveListener]
              removeListener(handleChange)
            } catch {
              case _: Exception => // Do nothing if not supported
            }
        }
      }
    },
    Seq(), // Empty deps - only run on mount
  )

  // Helper to set theme
  def setTheme(theme: String): Unit = {
    setCurrentTheme(theme)
    dom.document.documentElement.setAttribute("data-theme", theme)
    dom.window.localStorage.setItem("theme", theme)
    props.onThemeChange.foreach(_(theme))
  }

  // Helper to apply theme based on system preference
  def applyThemeBasedOnSystemPreference(): Unit = {
    val isDarkMode     = dom.window.matchMedia("(prefers-color-scheme: dark)").matches
    val preferredTheme = if (isDarkMode) "dark" else "light"

    // Apply the theme
    setCurrentTheme(preferredTheme)
    dom.document.documentElement.setAttribute("data-theme", preferredTheme)
    props.onThemeChange.foreach(_(preferredTheme))
  }

  // Helper to toggle auto theme
  def toggleAutoTheme(enabled: Boolean): Unit = {
    setIsAutoTheme(enabled)
    dom.window.localStorage.setItem("theme-auto", enabled.toString)

    if (enabled) {
      applyThemeBasedOnSystemPreference()
    }
  }

  // Toggle between light and dark themes (simple toggle variant)
  def toggleLightDark(): Unit = {
    // If in auto mode, exit it
    if (isAutoTheme) {
      toggleAutoTheme(false)
    }

    // Toggle between light and dark
    val newTheme = if (currentTheme == "dark" || currentTheme == "") "light" else "dark"
    setTheme(newTheme)
  }

  // Handle theme selection from dropdown or modal
  def handleThemeChange(theme: String): Unit = {
    if (theme == "auto") {
      toggleAutoTheme(true)
    } else {
      if (isAutoTheme) {
        toggleAutoTheme(false)
      }
      setTheme(theme)
    }

    // Close modal if using modal variant
    setShowModal(false)
  }

  // Render based on the variant
  props.variant match {
    case "toggle" =>
      // Render a simple toggle switch between light/dark
      div(
        cls := s"flex items-center ${props.className}",
        if (props.showLabel) span(cls := "mr-2 text-sm", "Theme") else null,
        label(
          cls := "swap swap-rotate",

          // Hidden checkbox
          input(
            typ      := "checkbox",
            cls      := "theme-controller",
            checked  := currentTheme == "dark",
            onChange := (() => toggleLightDark()),
          ),

          // Sun icon (for light mode)
          svg(
            cls     := "swap-on h-8 w-8 fill-current",
            xmlns   := "http://www.w3.org/2000/svg",
            viewBox := "0 0 24 24",
            path(
              d := "M5.64,17l-.71.71a1,1,0,0,0,0,1.41,1,1,0,0,0,1.41,0l.71-.71A1,1,0,0,0,5.64,17ZM5,12a1,1,0,0,0-1-1H3a1,1,0,0,0,0,2H4A1,1,0,0,0,5,12Zm7-7a1,1,0,0,0,1-1V3a1,1,0,0,0-2,0V4A1,1,0,0,0,12,5ZM5.64,7.05a1,1,0,0,0,.7.29,1,1,0,0,0,.71-.29,1,1,0,0,0,0-1.41l-.71-.71A1,1,0,0,0,4.93,6.34Zm12,.29a1,1,0,0,0,.7-.29l.71-.71a1,1,0,1,0-1.41-1.41L17,5.64a1,1,0,0,0,0,1.41A1,1,0,0,0,17.66,7.34ZM21,11H20a1,1,0,0,0,0,2h1a1,1,0,0,0,0-2Zm-9,8a1,1,0,0,0-1,1v1a1,1,0,0,0,2,0V20A1,1,0,0,0,12,19ZM18.36,17A1,1,0,0,0,17,18.36l.71.71a1,1,0,0,0,1.41,0,1,1,0,0,0,0-1.41ZM12,6.5A5.5,5.5,0,1,0,17.5,12,5.51,5.51,0,0,0,12,6.5Zm0,9A3.5,3.5,0,1,1,15.5,12,3.5,3.5,0,0,1,12,15.5Z",
            ),
          ),

          // Moon icon (for dark mode)
          svg(
            cls     := "swap-off h-8 w-8 fill-current",
            xmlns   := "http://www.w3.org/2000/svg",
            viewBox := "0 0 24 24",
            path(
              d := "M21.64,13a1,1,0,0,0-1.05-.14,8.05,8.05,0,0,1-3.37.73A8.15,8.15,0,0,1,9.08,5.49a8.59,8.59,0,0,1,.25-2A1,1,0,0,0,8,2.36,10.14,10.14,0,1,0,22,14.05,1,1,0,0,0,21.64,13Zm-9.5,6.69A8.14,8.14,0,0,1,7.08,5.22v.27A10.15,10.15,0,0,0,17.22,15.63a9.79,9.79,0,0,0,2.1-.22A8.11,8.11,0,0,1,12.14,19.73Z",
            ),
          ),
        ),
      )

    case "dropdown" =>
      // Render a dropdown with all themes
      val alignmentClass = props.alignment match {
        case "start"  => "dropdown-start"
        case "end"    => "dropdown-end"
        case "top"    => "dropdown-top"
        case "bottom" => "dropdown-bottom"
        case "left"   => "dropdown-left"
        case "right"  => "dropdown-right"
        case _        => "" // Default case if needed
      }

      div(
        cls := s"dropdown $alignmentClass ${props.className}",

        // Dropdown trigger
        label(
          tabIndex := 0,
          cls      := "btn btn-ghost m-1",
          if (props.showLabel) span(cls := "mr-2", "Theme") else null,

          // Current theme indicator
          if (isAutoTheme) {
            span("Auto")
          } else {
            span(currentTheme.capitalize)
          },

          // Dropdown icon
          svg(
            xmlns   := "http://www.w3.org/2000/svg",
            width   := "20",
            height  := "20",
            viewBox := "0 0 24 24",
            fill    := "none",
            stroke  := "currentColor",
            cls     := "ml-1",
            path(
              strokeLinecap  := "round",
              strokeLinejoin := "round",
              strokeWidth    := "2",
              d              := "M19 9l-7 7-7-7",
            ),
          ),
        ),

        // Dropdown menu
        ul(
          tabIndex := 0,
          cls      := "dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52",

          // Auto option if system preference respected
          if (props.respectSystemPreference) {
            li(
              a(
                onClick := (() => handleThemeChange("auto")),
                cls     := (if (isAutoTheme) "active" else ""),
                "Auto (System)",
              ),
            )
          } else null,

          // All themes
          props.themes.map(theme =>
            li(
              key := theme,
              a(
                onClick := (() => handleThemeChange(theme)),
                cls     := (if (!isAutoTheme && currentTheme == theme) "active" else ""),
                theme.capitalize,
              ),
            ),
          ),
        ),
      )

    case "button" =>
      // Render a button that opens a modal with theme preview cards
      div(
        cls := props.className,

        // Button to open modal
        button(
          cls     := "btn btn-ghost",
          onClick := (() => setShowModal(true)),
          if (props.showLabel) span(cls := "mr-2", "Theme") else null,

          // Current theme indicator
          if (isAutoTheme) {
            span("Auto")
          } else {
            span(currentTheme.capitalize)
          },

          // Theme icon
          svg(
            xmlns          := "http://www.w3.org/2000/svg",
            width          := "20",
            height         := "20",
            viewBox        := "0 0 24 24",
            fill           := "none",
            stroke         := "currentColor",
            cls            := "ml-1",
            strokeLinecap  := "round",
            strokeLinejoin := "round",
            strokeWidth    := "2",
            circle(cx := "12", cy := "12", r := "5"),
            path(
              d := "M12 1v2M12 21v2M4.2 4.2l1.4 1.4M18.4 18.4l1.4 1.4M1 12h2M21 12h2M4.2 19.8l1.4-1.4M18.4 5.6l1.4-1.4",
            ),
          ),
        ),

        // Theme selection modal
        if (showModal) {
          div(
            cls     := "fixed inset-0 bg-black bg-opacity-50 z-50 flex items-center justify-center",
            onClick := (() => setShowModal(false)),

            // Modal content - prevent click propagation to close
            div(
              cls     := "bg-base-100 p-4 rounded-box max-w-3xl w-full max-h-[80vh] overflow-auto",
              onClick := ((e: dom.MouseEvent) => e.stopPropagation()),

              // Modal header
              div(
                cls := "flex justify-between items-center mb-4",
                h3(cls := "text-lg font-bold", "Select Theme"),
                button(
                  cls     := "btn btn-sm btn-ghost",
                  onClick := (() => setShowModal(false)),
                  "×",
                ),
              ),

              // Auto theme option
              if (props.respectSystemPreference) {
                div(
                  cls := "mb-4",
                  button(
                    cls     := s"btn btn-outline w-full ${if (isAutoTheme) "btn-primary" else ""}",
                    onClick := (() => handleThemeChange("auto")),
                    "Auto (System Preference)",
                  ),
                )
              } else null,

              // Theme grid
              div(
                cls := "grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4",

                // Theme cards
                props.themes.map(theme =>
                  div(
                    key := theme,
                    cls := s"card bg-base-100 shadow-md cursor-pointer hover:shadow-lg transition-shadow ${
                        if (!isAutoTheme && currentTheme == theme) "ring-2 ring-primary" else ""
                      }",
                    onClick      := (() => handleThemeChange(theme)),
                    "data-theme" := theme, // Apply theme to card for preview

                    div(
                      cls := "card-body p-4",

                      // Theme name
                      h4(cls := "card-title text-base-content", theme.capitalize),

                      // Theme preview elements
                      div(
                        cls := "flex flex-col gap-2 mt-2",

                        // Color previews
                        div(
                          cls := "flex gap-1",
                          div(cls := "bg-primary w-5 h-5 rounded"),
                          div(cls := "bg-secondary w-5 h-5 rounded"),
                          div(cls := "bg-accent w-5 h-5 rounded"),
                          div(cls := "bg-neutral w-5 h-5 rounded"),
                        ),

                        // Button previews
                        div(
                          cls := "flex gap-1",
                          button(cls := "btn btn-primary btn-xs", "Button"),
                          button(cls := "btn btn-secondary btn-xs", "Button"),
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ),
          )
        } else null,
      )

    case _ =>
      // Default to toggle
      div("Unsupported variant")
  }
}
