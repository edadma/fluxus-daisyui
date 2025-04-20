package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

case class LanguageSwitcherProps(
    // Core props
    currentLang: String,                     // Current language code
    supportedLanguages: Map[String, String], // Map of language code -> name
    onLanguageChange: String => Unit,        // Callback when language changes

    // Display options
    displayMode: String = "code", // "code", "name", "both"
    showFlags: Boolean = false,   // Whether to show language flags

    // Styling options
    variant: String = "ghost",         // Button variant (primary, ghost, etc.)
    size: String = "md",               // Size (xs, sm, md, lg)
    buttonShape: String = "circle",    // Button shape (circle, square, default)
    dropdownAlignment: String = "end", // Dropdown alignment (start, end)
    bgClass: String = "bg-base-200",   // Background class for dropdown

    // Additional styling
    className: String = "",        // Additional class for container
    buttonClassName: String = "",  // Additional class for button
    dropdownClassName: String = "", // Additional class for dropdown
)

val LanguageSwitcher = (props: LanguageSwitcherProps) => {
  // Handle button variant
  val buttonVariant = props.variant match {
    case "primary"   => "btn-primary"
    case "secondary" => "btn-secondary"
    case "accent"    => "btn-accent"
    case "ghost"     => "btn-ghost"
    case "link"      => "btn-link"
    case _           => ""
  }

  // Handle size
  val sizeClass = props.size match {
    case "xs" => "btn-xs"
    case "sm" => "btn-sm"
    case "lg" => "btn-lg"
    case _    => "btn-md"
  }

  // Handle shape
  val shapeClass = props.buttonShape match {
    case "circle" => "btn-circle"
    case "square" => "btn-square"
    case _        => ""
  }

  // Handle dropdown alignment
  val alignmentClass = props.dropdownAlignment match {
    case "start"  => "dropdown-start"
    case "end"    => "dropdown-end"
    case "top"    => "dropdown-top"
    case "bottom" => "dropdown-bottom"
    case _        => "dropdown-end"
  }

  // Current language display based on mode
  val currentLangDisplay = props.displayMode match {
    case "name" => props.supportedLanguages.getOrElse(props.currentLang, props.currentLang)
    case "both" => s"${props.currentLang}: ${props.supportedLanguages.getOrElse(props.currentLang, "")}"
    case _      => props.currentLang.toUpperCase() // Default to code
  }

  div(
    cls := s"dropdown $alignmentClass ${props.className}",

    // Trigger button
    label(
      tabIndex := 0,
      cls      := s"btn $buttonVariant $sizeClass $shapeClass ${props.buttonClassName}",

      // Language display
      if (props.showFlags) {
        span(
          cls := "mr-1",
          // Here you would add code to display flag based on language code
          // This could be an SVG or a span with a country code class
        )
      } else null,
      span(
        cls := "font-medium",
        currentLangDisplay,
      ),
    ),

    // Dropdown menu
    ul(
      tabIndex := 0,
      cls := s"dropdown-content z-20 menu p-2 shadow ${props.bgClass} rounded-box mt-1 min-w-40 ${props.dropdownClassName}",
      props.supportedLanguages.map { case (code, name) =>
        li(
          key := code,
          a(
            cls     := (if (code == props.currentLang) "active" else ""),
            onClick := (() => props.onLanguageChange(code)),

            // Show flag if enabled
            if (props.showFlags) {
              span(
                cls := "mr-2",
                // Flag code would go here
              )
            } else null,

            // Language display in dropdown
            props.displayMode match {
              case "code" => code.toUpperCase()
              case "both" => s"${code.toUpperCase()} - $name"
              case _      => name
            },
          ),
        )
      },
    ),
  )
}
