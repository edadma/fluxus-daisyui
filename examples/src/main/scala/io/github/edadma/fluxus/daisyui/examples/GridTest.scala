package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def GridTest: FluxusNode = {
  // State for tracking clicked items
  val (clickedItem, setClickedItem, _) = useState("")

  // Handle item click
  def handleItemClick(key: String): Unit = {
    setClickedItem(key)
  }

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Grid Component Demo",
      ),

      // Basic Grid
      Card <> CardProps(
        title = Some("Basic Grid"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "A simple responsive grid with 3 columns on desktop, 2 on tablet, and 1 on mobile.",
          ),
          Grid <> GridProps(
            columns = "grid-cols-1",
            mdColumns = Some("md:grid-cols-2"),
            lgColumns = Some("lg:grid-cols-3"),
            gap = "gap-4",
            children = Seq(
              GridItem <> GridItemProps(
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                bgClass = Some("bg-primary bg-opacity-10"),
                children = div(
                  h3(cls := "text-lg font-bold", "Item 1"),
                  p("This is the first grid item."),
                ),
              ),
              GridItem <> GridItemProps(
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                bgClass = Some("bg-secondary bg-opacity-10"),
                children = div(
                  h3(cls := "text-lg font-bold", "Item 2"),
                  p("This is the second grid item."),
                ),
              ),
              GridItem <> GridItemProps(
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                bgClass = Some("bg-accent bg-opacity-10"),
                children = div(
                  h3(cls := "text-lg font-bold", "Item 3"),
                  p("This is the third grid item."),
                ),
              ),
            ),
          ),
        ),
      ),

      // Auto-fit Grid
      Card <> CardProps(
        title = Some("Auto-fit Responsive Grid"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "A responsive grid that automatically adjusts the number of columns based on available width.",
          ),
          Grid <> GridProps(
            autoFit = true,
            minItemWidth = "200px",
            gap = "gap-4",
            children =
              (1 to 6).map(i =>
                GridItem <> GridItemProps(
                  key = Some(s"item-$i"),
                  bordered = true,
                  rounded = true,
                  padding = Some("p-4"),
                  hoverElevate = true,
                  children = div(
                    h3(cls := "text-lg font-bold", s"Item $i"),
                    p("This item will resize and reflow based on available space."),
                  ),
                ),
              ),
          ),
        ),
      ),

      // Grid with Various Column Spans
      Card <> CardProps(
        title = Some("Grid with Column Spans"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "A grid where items span different numbers of columns.",
          ),
          Grid <> GridProps(
            columns = "grid-cols-6",
            gap = "gap-4",
            children = Seq(
              GridItem <> GridItemProps(
                colSpan = Some("col-span-6"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                bgClass = Some("bg-base-200"),
                children = div(
                  h3(cls := "text-lg font-bold text-center", "Full Width (span 6)"),
                ),
              ),
              GridItem <> GridItemProps(
                colSpan = Some("col-span-3"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                bgClass = Some("bg-primary bg-opacity-10"),
                children = div(
                  h3(cls := "text-lg font-bold", "Half Width (span 3)"),
                  p("This item spans 3 of 6 columns."),
                ),
              ),
              GridItem <> GridItemProps(
                colSpan = Some("col-span-3"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                bgClass = Some("bg-secondary bg-opacity-10"),
                children = div(
                  h3(cls := "text-lg font-bold", "Half Width (span 3)"),
                  p("This item spans 3 of 6 columns."),
                ),
              ),
              GridItem <> GridItemProps(
                colSpan = Some("col-span-2"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                bgClass = Some("bg-info bg-opacity-10"),
                children = div(
                  h3(cls := "text-lg font-bold", "Span 2"),
                  p("This spans 2 columns."),
                ),
              ),
              GridItem <> GridItemProps(
                colSpan = Some("col-span-2"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                bgClass = Some("bg-success bg-opacity-10"),
                children = div(
                  h3(cls := "text-lg font-bold", "Span 2"),
                  p("This spans 2 columns."),
                ),
              ),
              GridItem <> GridItemProps(
                colSpan = Some("col-span-2"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                bgClass = Some("bg-warning bg-opacity-10"),
                children = div(
                  h3(cls := "text-lg font-bold", "Span 2"),
                  p("This spans 2 columns."),
                ),
              ),
            ),
          ),
        ),
      ),

      // Interactive Grid with Events
      Card <> CardProps(
        title = Some("Interactive Grid"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "A grid with interactive items that respond to clicks and hover.",
          ),
          div(
            cls := "mb-4",
            if (clickedItem.nonEmpty) {
              div(
                cls := "alert alert-info",
                s"You clicked on item: $clickedItem",
              )
            } else {
              div(
                cls := "alert alert-info",
                "Click on an item to see an update here.",
              )
            },
          ),
          Grid <> GridProps(
            columns = "grid-cols-2",
            mdColumns = Some("md:grid-cols-3"),
            gap = "gap-4",
            children =
              (1 to 6).map(i =>
                GridItem <> GridItemProps(
                  key = Some(s"interactive-$i"),
                  interactive = true,
                  hoverHighlight = true,
                  bgClass = Some("bg-base-200"),
                  bordered = true,
                  rounded = true,
                  padding = Some("p-4"),
                  shadow = Some("shadow"),
                  onClick = Some(_ => handleItemClick(s"Item $i")),
                  children = div(
                    h3(cls := "text-lg font-bold", s"Item $i"),
                    p("Click me to trigger an event."),
                  ),
                ),
              ),
          ),
        ),
      ),

      // Masonry Layout
      Card <> CardProps(
        title = Some("Masonry Layout"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "A Pinterest-style masonry layout with items of varying heights.",
          ),
          Grid <> GridProps(
            masonry = true,
            masonryColumns = "columns-1 md:columns-2 lg:columns-3",
            gap = "gap-4",
            children =
              (1 to 10).map { i =>
                val randomHeight = (100 + (i * 30) % 200)

                GridItem <> GridItemProps(
                  key = Some(s"masonry-$i"),
                  bordered = true,
                  rounded = true,
                  padding = Some("p-4"),
                  margin = Some("mb-4"), // Important for masonry layout
                  shadow = Some("shadow"),
                  bgClass = Some("bg-base-200"),
                  hoverElevate = true,
                  className = "break-inside-avoid",
                  children = div(
                    h3(cls := "text-lg font-bold", s"Item $i"),
                    p("This is a masonry item with variable height."),
                    div(
                      style := s"height: ${randomHeight}px",
                      cls   := "bg-base-300 mt-2 rounded-lg flex items-center justify-center",
                      s"Height: ${randomHeight}px",
                    ),
                  ),
                )
              },
          ),
        ),
      ),

      // Animated Grid
      Card <> CardProps(
        title = Some("Animated Grid"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "A grid with animated items that fade and slide in with a staggered delay.",
          ),
          Grid <> GridProps(
            columns = "grid-cols-1",
            mdColumns = Some("md:grid-cols-3"),
            gap = "gap-4",
            animate = "animate-in fade-in",
            staggered = true,
            children =
              (1 to 6).map(i =>
                GridItem <> GridItemProps(
                  key = Some(s"animated-$i"),
                  animate = Some("slide-in-from-bottom"),
                  bordered = true,
                  rounded = true,
                  padding = Some("p-4"),
                  bgClass = Some("bg-base-200"),
                  shadow = Some("shadow"),
                  className = "duration-300",
                  children = div(
                    h3(cls := "text-lg font-bold", s"Item $i"),
                    p("This item animates in with a staggered delay."),
                  ),
                ),
              ),
          ),
        ),
      ),

      // Dashboard Grid with Named Areas
      Card <> CardProps(
        title = Some("Dashboard Grid with Named Areas"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "A dashboard layout using named grid areas that changes layout on different screen sizes.",
          ),
          DashboardGrid <> DashboardGridProps(
            areas = List(
              "header header header",
              "sidebar main main",
              "sidebar main main",
              "footer footer footer",
            ),
            mdAreas = Some(List(
              "header header header",
              "sidebar main main",
              "sidebar main main",
              "footer footer footer",
            )),
            lgAreas = Some(List(
              "header header header header",
              "sidebar main main main",
              "sidebar main main main",
              "footer footer footer footer",
            )),
            gap = "gap-4",
            padding = "p-4",
            bordered = true,
            rounded = true,
            bgClass = "bg-base-200",
            className = "min-h-[500px]",
            children = Seq(
              GridArea <> GridAreaProps(
                name = "header",
                bgClass = Some("bg-primary text-primary-content"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                children = Seq(
                  h2(cls := "text-xl font-bold", "Dashboard Header"),
                  p("This is the header area."),
                ),
              ),
              GridArea <> GridAreaProps(
                name = "sidebar",
                bgClass = Some("bg-secondary bg-opacity-20"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                children = Seq(
                  h3(cls := "text-lg font-bold", "Sidebar"),
                  ul(
                    cls := "menu",
                    li(a("Dashboard")),
                    li(a("Analytics")),
                    li(a("Reports")),
                    li(a("Settings")),
                  ),
                ),
              ),
              GridArea <> GridAreaProps(
                name = "main",
                bgClass = Some("bg-base-100"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                children = Seq(
                  h3(cls := "text-lg font-bold", "Main Content"),
                  p("This is the main content area of the dashboard."),
                  p(
                    cls := "mt-2",
                    "This grid layout uses named template areas for easy positioning of dashboard elements.",
                  ),
                  p(
                    cls := "mt-2",
                    "The layout will change on different screen sizes without needing to change the markup.",
                  ),
                ),
              ),
              GridArea <> GridAreaProps(
                name = "footer",
                bgClass = Some("bg-neutral bg-opacity-20"),
                bordered = true,
                rounded = true,
                padding = Some("p-4"),
                children = Seq(
                  p(cls := "text-center", "Dashboard Footer © 2024"),
                ),
              ),
            ),
          ),
        ),
      ),

      // Simplified AutoGrid Component
      Card <> CardProps(
        title = Some("AutoGrid Component"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "A simplified AutoGrid component for quick responsive layouts.",
          ),
          AutoGrid <> AutoGridProps(
            itemWidth = "180px",
            gap = "gap-6",
            padding = "p-4",
            bordered = true,
            rounded = true,
            bgClass = "bg-base-200",
            children =
              (1 to 8).map(i =>
                div(
                  key := s"auto-$i",
                  cls := "bg-base-100 p-4 rounded-lg shadow",
                  h3(cls := "text-lg font-bold", s"Card $i"),
                  p("Simple card in an auto-grid layout."),
                ),
              ),
          ),
        ),
      ),

      // 3D Effect Grid
      Card <> CardProps(
        title = Some("3D Effect Grid"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "A grid with 3D effect and perspective transforms on hover.",
          ),
          Grid <> GridProps(
            columns = "grid-cols-1",
            mdColumns = Some("md:grid-cols-2"),
            lgColumns = Some("lg:grid-cols-3"),
            gap = "gap-6",
            padding = "p-6",
            bordered3d = true,
            rounded = true,
            bgClass = "bg-base-200",
            children =
              (1 to 6).map(i =>
                GridItem <> GridItemProps(
                  key = Some(s"3d-$i"),
                  className =
                    "transform perspective-[1000px] transition-all duration-300 hover:rotate-y-2 hover:rotate-x-2 hover:shadow-xl",
                  bordered = true,
                  rounded = true,
                  padding = Some("p-4"),
                  shadow = Some("shadow"),
                  bgClass = Some("bg-base-100"),
                  children = div(
                    h3(cls := "text-lg font-bold", s"3D Card $i"),
                    p("This card has a subtle 3D effect on hover."),
                    div(
                      cls := "mt-4 flex justify-end",
                      Button <> ButtonProps(
                        text = "View Details",
                        size = "sm",
                        variant = "primary",
                      ),
                    ),
                  ),
                ),
              ),
          ),
        ),
      ),

      // Debug Grid
      Card <> CardProps(
        title = Some("Debug Grid"),
        children = div(
          p(
            cls := "mb-4",
            "A grid with debug mode enabled to show grid lines and cells.",
          ),
          Grid <> GridProps(
            columns = "grid-cols-4",
            rows = "grid-rows-3",
            gap = "gap-2",
            padding = "p-4",
            bordered = true,
            rounded = true,
            debug = true,
            children =
              (1 to 12).map(i =>
                div(
                  key := s"debug-$i",
                  cls := "bg-base-200 p-2 text-center",
                  s"Cell $i",
                ),
              ),
          ),
        ),
      ),
    ),
  )
}
