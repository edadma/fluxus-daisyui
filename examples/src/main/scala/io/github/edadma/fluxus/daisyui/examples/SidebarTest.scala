package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def SidebarTest: FluxusNode = {
  // Length & Distance icon
  def LengthIcon: FluxusNode = {
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
      createElement("rect", "x1" := "2", "y1" := "12", "x2" := "22", "y2" := "12"),
      polyline(points            := "18 8 22 12 18 16"),
      polyline(points            := "6 8 2 12 6 16"),
    )
  }

  // Weight & Mass icon
  def WeightIcon: FluxusNode = {
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
        d := "M8 2h8a2 2 0 0 1 2 2v2.95a5 5 0 0 1 1.86 1.48A5 5 0 0 1 21 12a5 5 0 0 1-1.14 3.17A5 5 0 0 1 18 16.33V20a2 2 0 0 1-2 2H8a2 2 0 0 1-2-2v-3.73a5 5 0 0 1-1.86-1.43A5 5 0 0 1 3 12a5 5 0 0 1 1.14-3.12A5 5 0 0 1 6 7.45V4a2 2 0 0 1 2-2z",
      ),
      path(d := "M12 12m-3 0a3 3 0 1 0 6 0a3 3 0 1 0 -6 0"),
    )
  }

  // Temperature icon
  def TemperatureIcon: FluxusNode = {
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
      path(d := "M14 4v10.54a4 4 0 1 1-4 0V4a2 2 0 0 1 4 0Z"),
    )
  }

  // Area icon
  def AreaIcon: FluxusNode = {
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
      rect("x" := "3", "y" := "3", width := "18", height := "18", "rx" := "2", "ry" := "2"),
    )
  }

  // State for the active conversion
  val (activeConversion, setActiveConversion, _) = useState("meters-to-feet")

  // Handle navigation
  def handleNavigation(id: String, item: NavItem): Unit = {
    setActiveConversion(id)
  }

  // Conversion categories
  val conversionItems = List(
    NavItem(
      id = "length",
      title = "Length & Distance",
      icon = Some(LengthIcon),
      items = List(
        NavItem(
          id = "meters-to-feet",
          title = "Meters to Feet",
          isActive = activeConversion == "meters-to-feet",
        ),
        NavItem(
          id = "feet-to-meters",
          title = "Feet to Meters",
          isActive = activeConversion == "feet-to-meters",
        ),
        NavItem(
          id = "inches-to-cm",
          title = "Inches to Centimeters",
          isActive = activeConversion == "inches-to-cm",
        ),
        NavItem(
          id = "cm-to-inches",
          title = "Centimeters to Inches",
          isActive = activeConversion == "cm-to-inches",
        ),
      ),
    ),
    NavItem(
      id = "weight",
      title = "Weight & Mass",
      icon = Some(WeightIcon),
      items = List(
        NavItem(
          id = "kg-to-pounds",
          title = "Kilograms to Pounds",
          isActive = activeConversion == "kg-to-pounds",
        ),
        NavItem(
          id = "pounds-to-kg",
          title = "Pounds to Kilograms",
          isActive = activeConversion == "pounds-to-kg",
        ),
        NavItem(
          id = "grams-to-ounces",
          title = "Grams to Ounces",
          isActive = activeConversion == "grams-to-ounces",
        ),
        NavItem(
          id = "ounces-to-grams",
          title = "Ounces to Grams",
          isActive = activeConversion == "ounces-to-grams",
        ),
      ),
    ),
    NavItem(
      id = "temperature",
      title = "Temperature",
      icon = Some(TemperatureIcon),
      items = List(
        NavItem(
          id = "celsius-to-fahrenheit",
          title = "Celsius to Fahrenheit",
          isActive = activeConversion == "celsius-to-fahrenheit",
        ),
        NavItem(
          id = "fahrenheit-to-celsius",
          title = "Fahrenheit to Celsius",
          isActive = activeConversion == "fahrenheit-to-celsius",
        ),
        NavItem(
          id = "celsius-to-kelvin",
          title = "Celsius to Kelvin",
          isActive = activeConversion == "celsius-to-kelvin",
        ),
        NavItem(
          id = "kelvin-to-celsius",
          title = "Kelvin to Celsius",
          isActive = activeConversion == "kelvin-to-celsius",
        ),
      ),
    ),
    NavItem(
      id = "area",
      title = "Area",
      icon = Some(AreaIcon),
      items = List(
        NavItem(
          id = "sqmeters-to-sqfeet",
          title = "Square Meters to Square Feet",
          isActive = activeConversion == "sqmeters-to-sqfeet",
        ),
        NavItem(
          id = "sqfeet-to-sqmeters",
          title = "Square Feet to Square Meters",
          isActive = activeConversion == "sqfeet-to-sqmeters",
        ),
        NavItem(
          id = "acres-to-hectares",
          title = "Acres to Hectares",
          isActive = activeConversion == "acres-to-hectares",
        ),
        NavItem(
          id = "hectares-to-acres",
          title = "Hectares to Acres",
          isActive = activeConversion == "hectares-to-acres",
        ),
      ),
    ),
  )

  // State for sidebar collapse
  val (isSidebarCollapsed, setIsSidebarCollapsed, _) = useState(false)

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Styled Category Sidebar Demo",
      ),
      div(
        cls := "flex flex-col md:flex-row gap-6",

        // Light theme sidebar (default style)
        div(
          cls := "w-full md:w-1/2",
          Card <> CardProps(
            title = Some("Light Theme Converter Sidebar"),
            className = "mb-6",
            children = div(
              cls := "bg-base-100 p-2 rounded-box",
              div(
                cls := "mb-3 text-center",
                button(
                  cls     := "btn btn-sm btn-primary",
                  onClick := (() => setIsSidebarCollapsed(!isSidebarCollapsed)),
                  if (isSidebarCollapsed) "Expand Sidebar" else "Collapse Sidebar",
                ),
              ),
              div(
                cls := "border border-base-300 rounded-box overflow-hidden",
                Sidebar <> SidebarProps(
                  items = conversionItems,
                  expandedSections = List("length", "weight"),
                  onNavigation = Some(handleNavigation),
                  collapsible = true,
                  collapsed = isSidebarCollapsed,
                  onCollapseChange = Some(setIsSidebarCollapsed),
                  showToggleIcons = true,
                  width = "w-64",
                  collapsedWidth = "w-16",

                  // The key styling that makes category headers distinct
                  categoryClass = "font-medium",
                  categoryTitleClass = "text-primary",
                  categoryIconClass = "text-primary",
                  itemClass = "text-base-content",
                  itemTitleClass = "text-base-content/90 text-sm",
                  itemIconClass = "",
                ),
              ),
            ),
          ),
        ),

        // Dark theme sidebar
        div(
          cls := "w-full md:w-1/2",
          Card <> CardProps(
            title = Some("Dark Theme Converter Sidebar"),
            className = "mb-6",
            children = div(
              cls := "bg-neutral text-neutral-content p-2 rounded-box",
              Sidebar <> SidebarProps(
                items = conversionItems,
                expandedSections = List("length"),
                onNavigation = Some(handleNavigation),

                // Dark theme base styling
                bgClass = "bg-neutral",
                textClass = "text-neutral-content",

                // Category and item distinction
                categoryClass = "font-medium",
                categoryTitleClass = "text-primary-content text-blue-300",
                categoryIconClass = "text-blue-300",
                itemClass = "",
                itemTitleClass = "opacity-80 text-sm",
                itemIconClass = "opacity-70",
              ),
            ),
          ),
        ),
      ),

      // Conversion display area
      Card <> CardProps(
        title = Some("Selected Conversion"),
        className = "mt-6",
        children = div(
          cls := "p-4 bg-base-200 rounded-box",
          h3(
            cls := "text-xl font-bold",
            activeConversion.split("-").map(part => part.capitalize).mkString(" to "),
          ),
          p(
            cls := "mt-2",
            "This area would show the conversion interface. Click on different conversions in the sidebar to see this update.",
          ),
        ),
      ),
    ),
  )
}
