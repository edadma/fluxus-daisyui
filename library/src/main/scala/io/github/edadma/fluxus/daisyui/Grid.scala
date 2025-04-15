package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Grid component props with comprehensive Tailwind CSS grid styling options
  */
case class GridProps(
    // Core content - now accepts a sequence of nodes
    children: Seq[FluxusNode],

    // Grid columns - must use full Tailwind classes
    columns: String = "grid-cols-1",  // Tailwind grid-cols-* classes (grid-cols-1, grid-cols-2, etc.)
    mdColumns: Option[String] = None, // Tailwind md:grid-cols-* classes (md:grid-cols-1, md:grid-cols-2, etc.)
    lgColumns: Option[String] = None, // Tailwind lg:grid-cols-* classes (lg:grid-cols-1, lg:grid-cols-2, etc.)
    xlColumns: Option[String] = None, // Tailwind xl:grid-cols-* classes (xl:grid-cols-1, xl:grid-cols-2, etc.)

    // Grid rows - must use full Tailwind classes
    rows: String = "",             // Tailwind grid-rows-* classes (grid-rows-1, grid-rows-2, etc.)
    mdRows: Option[String] = None, // Tailwind md:grid-rows-* classes (md:grid-rows-1, md:grid-rows-2, etc.)
    lgRows: Option[String] = None, // Tailwind lg:grid-rows-* classes (lg:grid-rows-1, lg:grid-rows-2, etc.)
    xlRows: Option[String] = None, // Tailwind xl:grid-rows-* classes (xl:grid-rows-1, xl:grid-rows-2, etc.)

    // Grid gaps - must use full Tailwind classes
    gap: String = "gap-4",         // Tailwind gap-* classes (gap-1, gap-2, etc.)
    colGap: Option[String] = None, // Tailwind gap-x-* classes (gap-x-1, gap-x-2, etc.)
    rowGap: Option[String] = None, // Tailwind gap-y-* classes (gap-y-1, gap-y-2, etc.)

    // Grid flow - must use full Tailwind classes
    autoFlow: String = "", // Tailwind grid-flow-* classes (grid-flow-row, grid-flow-col, etc.)

    // Grid alignment - must use full Tailwind classes
    justifyItems: String = "",   // Tailwind justify-items-* (justify-items-start, justify-items-end, etc.)
    alignItems: String = "",     // Tailwind align-items-* (items-start, items-end, etc.)
    justifyContent: String = "", // Tailwind justify-* (justify-start, justify-end, etc.)
    alignContent: String = "",   // Tailwind content-* (content-start, content-end, etc.)

    // Grid layout features
    autoFit: Boolean = false,                                       // Use auto-fit with minmax for responsive grid
    autoFill: Boolean = false,                                      // Use auto-fill with minmax for responsive grid
    minItemWidth: String = "16rem",                                 // Used with autoFit/autoFill
    equalHeight: Boolean = false,                                   // Force all grid items to have equal height
    masonry: Boolean = false,                                       // Enable masonry-like layout (using CSS columns)
    masonryColumns: String = "columns-1 md:columns-2 lg:columns-3", // Column count for masonry

    // Container styling
    bordered: Boolean = false,   // Add border around the grid
    rounded: Boolean = false,    // Add rounded corners
    padding: String = "",        // Tailwind p-* classes (p-1, p-2, etc.)
    bgClass: String = "",        // Background class (bg-base-100, bg-primary, etc.)
    bordered3d: Boolean = false, // 3D effect with border shadows

    // Animation - must use full Tailwind classes
    animate: String = "",       // Animation class (animate-fade-in, animate-bounce, etc.)
    staggered: Boolean = false, // Stagger children animations

    // Debug
    debug: Boolean = false, // Add visible grid lines for debugging

    // Additional styling
    className: String = "",

    // Events
    onItemClick: Option[(dom.Event, Option[String]) => Unit] = None, // Click handler with optional item key
)

/** Grid Item props for positioning within a Grid component
  */
case class GridItemProps(
    // Core content
    children: FluxusNode,

    // Positioning - must use full Tailwind classes
    colSpan: Option[String] = None,  // Tailwind col-span-* classes (col-span-1, col-span-2, etc.)
    rowSpan: Option[String] = None,  // Tailwind row-span-* classes (row-span-1, row-span-2, etc.)
    colStart: Option[String] = None, // Tailwind col-start-* classes (col-start-1, col-start-2, etc.)
    colEnd: Option[String] = None,   // Tailwind col-end-* classes (col-end-1, col-end-2, etc.)
    rowStart: Option[String] = None, // Tailwind row-start-* classes (row-start-1, row-start-2, etc.)
    rowEnd: Option[String] = None,   // Tailwind row-end-* classes (row-end-1, row-end-2, etc.)

    // Responsive positioning - must use full Tailwind classes
    mdColSpan: Option[String] = None, // Medium screen (md:col-span-1, md:col-span-2, etc.)
    lgColSpan: Option[String] = None, // Large screen (lg:col-span-1, lg:col-span-2, etc.)
    xlColSpan: Option[String] = None, // Extra large screen (xl:col-span-1, xl:col-span-2, etc.)

    // Item alignment - must use full Tailwind classes
    justifySelf: Option[String] = None, // Tailwind justify-self-* (justify-self-start, justify-self-end, etc.)
    alignSelf: Option[String] = None,   // Tailwind align-self-* (self-start, self-end, etc.)

    // Styling - must use full Tailwind classes
    bgClass: Option[String] = None, // Background class (bg-base-100, bg-primary, etc.)
    bordered: Boolean = false,      // Add border
    rounded: Boolean = false,       // Add rounded corners
    shadow: Option[String] = None,  // Tailwind shadow-* classes (shadow-sm, shadow, shadow-lg, etc.)
    padding: Option[String] = None, // Tailwind p-* classes (p-1, p-2, etc.)
    margin: Option[String] = None,  // Tailwind m-* classes (m-1, m-2, etc.)
    glass: Boolean = false,         // Glass effect

    // Hover effects
    hoverElevate: Boolean = false,   // Subtle rise effect on hover
    hoverHighlight: Boolean = false, // Highlight effect on hover

    // Animation - must use full Tailwind classes
    animate: Option[String] = None,      // Animation class (animate-fade-in, animate-bounce, etc.)
    animateDelay: Option[String] = None, // Animation delay (delay-100, delay-200, etc.)

    // Interaction
    interactive: Boolean = false,              // Apply pointer and click styling
    key: Option[String] = None,                // Unique key for identifying the item
    onClick: Option[dom.Event => Unit] = None, // Click handler
    disabled: Boolean = false,                 // Disabled state

    // Additional styling
    className: String = "",
)

/** Grid component that provides a flexible grid layout system with comprehensive styling
  *
  * Features:
  *   - Responsive grid layouts
  *   - Built-in support for masonry layout
  *   - Auto-sizing with minmax
  *   - Comprehensive styling options
  *   - Item alignment and positioning
  *   - Animation and hover effects
  */
val Grid = (props: GridProps) => {
  // Build CSS classes for the grid
  val classes = List.newBuilder[String]

  // Base class is always "grid"
  classes += "grid"

  // Conditional classes
  if (props.bordered) classes += "border border-base-300"
  if (props.rounded) classes += "rounded-lg"
  if (props.bordered3d) classes += "shadow-inner border-2 border-base-300"
  if (props.padding.nonEmpty) classes += props.padding
  if (props.bgClass.nonEmpty) classes += props.bgClass

  // Grid columns - handle special autoFit/autoFill cases
  if (props.autoFit || props.autoFill) {
    // We'll handle this with inline style
  } else {
    // Use standard Tailwind classes
    if (props.columns.nonEmpty) classes += props.columns
    props.mdColumns.foreach(cls => classes += cls)
    props.lgColumns.foreach(cls => classes += cls)
    props.xlColumns.foreach(cls => classes += cls)
  }

  // Grid rows
  if (props.rows.nonEmpty) classes += props.rows
  props.mdRows.foreach(cls => classes += cls)
  props.lgRows.foreach(cls => classes += cls)
  props.xlRows.foreach(cls => classes += cls)

  // Grid gaps
  if (props.gap.nonEmpty) classes += props.gap
  props.colGap.foreach(classes += _)
  props.rowGap.foreach(classes += _)

  // Grid flow
  if (props.autoFlow.nonEmpty) classes += props.autoFlow

  // Grid alignment
  if (props.justifyItems.nonEmpty) classes += props.justifyItems
  if (props.alignItems.nonEmpty) classes += props.alignItems
  if (props.justifyContent.nonEmpty) classes += props.justifyContent
  if (props.alignContent.nonEmpty) classes += props.alignContent

  // Force equal height rows if needed
  if (props.equalHeight) classes += "grid-rows-1"

  // Animation
  if (props.animate.nonEmpty) classes += props.animate

  // Debug mode
  if (props.debug)
    classes += "grid-cols-[repeat(auto-fit,minmax(1rem,1fr))] [&>*]:border [&>*]:border-dashed [&>*]:border-base-300 [&>*]:p-1"

  // Masonry layout uses CSS columns instead of grid
  if (props.masonry) {
    classes.clear() // Clear grid classes
    classes += props.masonryColumns
    classes += "gap-4 space-y-4"
  }

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val gridClass = classes.result().mkString(" ")

  // Create inline style for auto-fit/auto-fill
  val gridStyle = if ((props.autoFit || props.autoFill) && !props.masonry) {
    val autoType = if (props.autoFit) "auto-fit" else "auto-fill"
    s"grid-template-columns: repeat($autoType, minmax(${props.minItemWidth}, 1fr));"
  } else {
    ""
  }

  // Apply staggered animation to children if needed
  def processChildren: Seq[FluxusNode] = {
    if (props.staggered && props.animate.nonEmpty) {
      var counter = 0
      props.children.map { child =>
        val delay = counter * 100 // 100ms stagger
        counter += 1

        // For animation delays, use predefined Tailwind classes
        val delayClass = delay match {
          case 0    => "delay-0"
          case 100  => "delay-100"
          case 200  => "delay-200"
          case 300  => "delay-300"
          case 400  => "delay-400"
          case 500  => "delay-500"
          case 600  => "delay-600"
          case 700  => "delay-700"
          case 800  => "delay-800"
          case 900  => "delay-900"
          case 1000 => "delay-1000"
          case _    => "delay-0" // Default for unsupported values
        }

        child match {
          case gItem: ComponentNode if gItem.component.toString.contains("GridItem") =>
            // Extract props and update with animation delay
            val originalProps = gItem.props.asInstanceOf[GridItemProps]
            val newProps = originalProps.copy(
              animateDelay = Some(delayClass),
            )

            GridItem <> newProps

          case other => other // Return unmodified if not a GridItem
        }
      }
    } else {
      // Return children unmodified
      props.children
    }
  }

  // Render the grid component with processed children
  div(
    cls := gridClass,
    if (gridStyle.nonEmpty) style := gridStyle else null,
    processChildren, // Spread the children into the grid container
  )
}

/** GridItem component for positioning items within a Grid
  *
  * Features:
  *   - Control grid column and row placement
  *   - Item-specific alignment
  *   - Styling and interactive states
  *   - Animation and hover effects
  */
val GridItem = (props: GridItemProps) => {
  // Build CSS classes for the grid item
  val classes = List.newBuilder[String]

  // Column span
  props.colSpan.foreach(span => classes += span)
  props.mdColSpan.foreach(span => classes += span) // Already full Tailwind class
  props.lgColSpan.foreach(span => classes += span) // Already full Tailwind class
  props.xlColSpan.foreach(span => classes += span) // Already full Tailwind class

  // Row span
  props.rowSpan.foreach(span => classes += span)

  // Column positioning
  props.colStart.foreach(pos => classes += pos)
  props.colEnd.foreach(pos => classes += pos)

  // Row positioning
  props.rowStart.foreach(pos => classes += pos)
  props.rowEnd.foreach(pos => classes += pos)

  // Self alignment
  props.justifySelf.foreach(align => classes += align)
  props.alignSelf.foreach(align => classes += align)

  // Styling
  props.bgClass.foreach(classes += _)
  if (props.bordered) classes += "border border-base-300"
  if (props.rounded) classes += "rounded-lg"
  props.shadow.foreach(classes += _)
  props.padding.foreach(classes += _)
  props.margin.foreach(classes += _)
  if (props.glass) classes += "glass"

  // Interactive states
  if (props.interactive) {
    classes += "cursor-pointer"
    classes += "transition-all"
    if (!props.disabled) {
      classes += "hover:scale-[1.01]"
      classes += "active:scale-[0.99]"
    } else {
      classes += "opacity-50"
      classes += "cursor-not-allowed"
    }
  }

  // Hover effects
  if (props.hoverElevate) {
    classes += "transition-all"
    classes += "hover:-translate-y-1"
    classes += "hover:shadow-md"
  }

  if (props.hoverHighlight) {
    classes += "transition-all"
    classes += "hover:bg-base-100"
    classes += "hover:shadow-sm"
    classes += "hover:border-primary"
  }

  // Animation
  props.animate.foreach(classes += _)
  props.animateDelay.foreach(classes += _)

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val gridItemClass = classes.result().mkString(" ")

  // Item click handler
  def handleClick(e: dom.Event): Unit = {
    if (!props.disabled) {
      props.onClick.foreach(_(e))
    }
  }

  // Render the grid item
  div(
    cls := gridItemClass,
    key := props.key.getOrElse(""),
    onClick := ((e: dom.Event) => {
      if (props.onClick.isDefined) handleClick(e)
    }),
    aria_disabled := props.disabled.toString,
    props.children,
  )
}

/** GridArea component props for named grid template areas
  */
case class GridAreaProps(
    // Core properties
    name: String,              // Grid area name
    children: Seq[FluxusNode], // Content to render in this area

    // Styling - must use full Tailwind classes
    bgClass: Option[String] = None, // Background color (bg-base-100, bg-primary, etc.)
    bordered: Boolean = false,      // Add border
    rounded: Boolean = false,       // Add rounded corners
    padding: Option[String] = None, // Padding (p-1, p-2, etc.)
    className: String = "",         // Additional CSS classes
)

/** GridArea component for use with grid-template-areas
  */
val GridArea = (props: GridAreaProps) => {
  // Build CSS classes
  val classes = List.newBuilder[String]

  // Positioning
  classes += s"[grid-area:${props.name}]"

  // Styling
  props.bgClass.foreach(classes += _)
  if (props.bordered) classes += "border border-base-300"
  if (props.rounded) classes += "rounded-lg"
  props.padding.foreach(classes += _)

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val areaClass = classes.result().mkString(" ")

  // Render the grid area
  div(
    cls := areaClass,
    props.children,
  )
}

/** Dashboard grid with named template areas
  */
case class DashboardGridProps(
    // Core content - should be GridArea components
    children: Seq[FluxusNode],

    // Template definition
    areas: List[String], // Grid template areas (rows of column names)

    // Styling - must use full Tailwind classes
    gap: String = "gap-4",   // Gap between areas (gap-1, gap-2, etc.)
    padding: String = "p-4", // Padding around the grid (p-1, p-2, etc.)
    bordered: Boolean = false,
    rounded: Boolean = false,
    bgClass: String = "", // Background class (bg-base-100, bg-primary, etc.)
    className: String = "",

    // Responsive changes
    mdAreas: Option[List[String]] = None, // Medium screen layout
    lgAreas: Option[List[String]] = None, // Large screen layout
)

/** DashboardGrid component for dashboard layouts with named areas
  */
val DashboardGrid = (props: DashboardGridProps) => {
  // Process template areas into CSS grid-template-areas
  val template = props.areas.map(row => s"'$row'").mkString(" ")

  // Process responsive templates if provided
  val mdTemplate = props.mdAreas.map(areas =>
    areas.map(row => s"'$row'").mkString(" "),
  )

  val lgTemplate = props.lgAreas.map(areas =>
    areas.map(row => s"'$row'").mkString(" "),
  )

  // Build inline style
  val gridStyle = StringBuilder(s"grid-template-areas: $template;")

  // Add responsive styles if needed
  mdTemplate.foreach(tmpl =>
    gridStyle.append(s" @media (min-width: 768px) { grid-template-areas: $tmpl; }"),
  )

  lgTemplate.foreach(tmpl =>
    gridStyle.append(s" @media (min-width: 1024px) { grid-template-areas: $tmpl; }"),
  )

  // Build CSS classes
  val classes = List.newBuilder[String]

  // Base class
  classes += "grid"
  classes += props.gap
  classes += props.padding

  // Styling
  if (props.bordered) classes += "border border-base-300"
  if (props.rounded) classes += "rounded-lg"
  if (props.bgClass.nonEmpty) classes += props.bgClass

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes
  val gridClass = classes.result().mkString(" ")

  // Render the dashboard grid
  div(
    cls   := gridClass,
    style := gridStyle.toString(),
    props.children,
  )
}

/** AutoGrid component for simple, responsive grids
  */
case class AutoGridProps(
    // Core content
    children: Seq[FluxusNode],

    // Grid configuration
    itemWidth: String = "250px", // Minimum width for items
    gap: String = "gap-4",       // Gap between items (full Tailwind class)

    // Styling
    padding: String = "p-4",     // Padding around the grid (full Tailwind class)
    bordered: Boolean = false,   // Add border around the grid
    rounded: Boolean = false,    // Add rounded corners
    bgClass: String = "",        // Background class (full Tailwind class)
    equalHeight: Boolean = true, // Force equal height rows

    // Additional styling
    className: String = "",
)

/** AutoGrid component for simple responsive grids
  */
val AutoGrid = (props: AutoGridProps) => {
  Grid <> GridProps(
    autoFit = true,
    minItemWidth = props.itemWidth,
    gap = props.gap,
    padding = props.padding,
    bordered = props.bordered,
    rounded = props.rounded,
    bgClass = props.bgClass,
    equalHeight = props.equalHeight,
    className = props.className,
    children = props.children,
  )
}
