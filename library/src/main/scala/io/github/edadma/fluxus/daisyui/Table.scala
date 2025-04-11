package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Table component props with comprehensive styling options
 */
case class TableProps(
                       // Data & display options
                       data: List[Map[String, Any]] = List(),           // Raw data to display
                       columns: List[TableColumnDef] = List(),          // Column definitions
                       keyField: String = "id",                         // Field to use as unique key for rows
                       emptyText: String = "No data available",         // Text to show when data is empty

                       // Style variants
                       bordered: Boolean = true,                        // Add borders to table
                       striped: Boolean = false,                        // Alternate row colors
                       hover: Boolean = true,                           // Highlight rows on hover
                       compact: Boolean = false,                        // Reduce cell padding

                       // Layout options
                       size: String = "md",                             // sm, md, lg
                       responsive: Boolean = true,                      // Add horizontal scrolling on small screens
                       fullWidth: Boolean = true,                       // Make table take full width of container

                       // Visual styling
                       headerBgClass: String = "",                      // Background class for header (e.g. "bg-base-200")
                       headerTextClass: String = "font-bold",           // Text styling for header
                       rowBgClass: String = "",                         // Background class for rows

                       // Event handlers
                       onRowClick: Option[(Map[String, Any], Int) => Unit] = None, // Triggered when row is clicked

                       // Custom elements
                       caption: Option[String] = None,                  // Table caption text
                       footer: Option[FluxusNode] = None,               // Custom footer content

                       // Accessibility
                       ariaLabel: Option[String] = None,                // Accessible label for table

                       // Additional styling
                       className: String = "",                          // Additional classes for table
                       headerClassName: String = "",                    // Additional classes for header
                       bodyClassName: String = "",                      // Additional classes for body
                       footerClassName: String = "",                    // Additional classes for footer
                     )

/** Column definition for Table component
 */
case class TableColumnDef(
                           key: String,                                     // Data key in the row map
                           title: String,                                   // Display title for column header

                           // Display options
                           width: Option[String] = None,                    // Column width (CSS value)
                           minWidth: Option[String] = None,                 // Minimum width (CSS value)
                           maxWidth: Option[String] = None,                 // Maximum width (CSS value)
                           align: String = "left",                          // Text alignment: "left", "center", "right"
                           hidden: Boolean = false,                         // Hide this column
                           truncate: Boolean = false,                       // Truncate text with ellipsis

                           // Custom rendering
                           render: Option[(Any, Map[String, Any], Int) => FluxusNode] = None, // Custom cell renderer (value, row, index)
                           renderHeader: Option[FluxusNode] = None,         // Custom header content

                           // Additional styling
                           className: String = "",                          // Additional classes for cells
                           headerClassName: String = "",                    // Additional classes for header cell
                         )

/** Table component using Tailwind styling
 *
 * Features:
 * - Data-driven table generation
 * - Custom cell rendering
 * - Comprehensive styling options
 * - Responsive design
 * - Accessibility support
 */
val Table = (props: TableProps) => {
  // Build table classes
  val tableClasses = List.newBuilder[String]

  // Base class is always present
  tableClasses += "table"

  // Add table variant classes
  if (props.bordered) tableClasses += "border border-base-300"
  if (props.striped) tableClasses += "table-zebra"
  if (props.hover) tableClasses += "table-hover"
  if (props.compact) tableClasses += "table-compact"

  // Size classes
  val sizeClass = props.size match {
    case "sm" => "table-sm"
    case "lg" => "table-lg"
    case _    => "" // Default (md) has no specific class
  }
  if (sizeClass.nonEmpty) tableClasses += sizeClass

  // Full width setting
  if (props.fullWidth) tableClasses += "w-full"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => tableClasses += cls)
  }

  // Join all classes with spaces
  val tableClass = tableClasses.result().mkString(" ")

  // Get visible columns
  val visibleColumns = props.columns.filterNot(_.hidden)

  // Determine if we have data to show
  val hasData = props.data.nonEmpty

  // Table content
  val tableContent =
    if (!hasData) {
      // Empty state
      div(
        cls := "text-center py-8 text-base-content/70",
        props.emptyText
      )
    } else {
      table(
        cls := tableClass,
        props.ariaLabel.map(al => aria_label := al).orNull,

        // Optional caption
        props.caption.map(captionText =>
          caption(
            cls := "caption-top text-sm text-base-content/70 py-2",
            captionText
          )
        ).orNull,

        // Table header
        thead(
          cls := {
            val classes = List.newBuilder[String]
            if (props.headerClassName.nonEmpty) classes += props.headerClassName
            if (props.headerBgClass.nonEmpty) classes += props.headerBgClass
            if (props.headerTextClass.nonEmpty) classes += props.headerTextClass
            classes.result().mkString(" ")
          },
          tr(
            // Column headers
            visibleColumns.map(column => {
              val headerClasses = List.newBuilder[String]

              // Add alignment classes
              val alignClass = column.align match {
                case "center" => "text-center"
                case "right" => "text-right"
                case _ => "text-left"
              }
              headerClasses += alignClass

              // Add custom header class
              if (column.headerClassName.nonEmpty) {
                column.headerClassName.split(" ").foreach(cls => headerClasses += cls)
              }

              // Build style attribute
              val styles = List.newBuilder[String]
              column.width.foreach(w => styles += s"width: $w")
              column.minWidth.foreach(w => styles += s"min-width: $w")
              column.maxWidth.foreach(w => styles += s"max-width: $w")
              val styleAttr = styles.result().mkString("; ")

              th(
                key := s"header-${column.key}",
                cls := headerClasses.result().mkString(" "),
                if (styleAttr.nonEmpty) style := styleAttr else null,

                // Use custom header content if provided, otherwise show title
                column.renderHeader.getOrElse(span(column.title))
              )
            })
          )
        ),

        // Table body
        tbody(
          cls := props.bodyClassName,
          props.data.zipWithIndex.map { case (row, rowIndex) =>
            tr(
              key := s"row-${row.getOrElse(props.keyField, rowIndex)}",
              cls := props.rowBgClass,
              if (props.onRowClick.isDefined)
                onClick := ((e: dom.MouseEvent) => props.onRowClick.foreach(_(row, rowIndex)))
              else null,

              // Render cells for each visible column
              visibleColumns.map(column => {
                val value = row.getOrElse(column.key, "")

                val cellClasses = List.newBuilder[String]

                // Add alignment classes
                val alignClass = column.align match {
                  case "center" => "text-center"
                  case "right" => "text-right"
                  case _ => "text-left"
                }
                cellClasses += alignClass

                // Add truncation if specified
                if (column.truncate) {
                  cellClasses += "truncate"
                  cellClasses += "max-w-xs"
                }

                // Add custom cell class
                if (column.className.nonEmpty) {
                  column.className.split(" ").foreach(cls => cellClasses += cls)
                }

                td(
                  key := s"cell-${row.getOrElse(props.keyField, rowIndex)}-${column.key}",
                  cls := cellClasses.result().mkString(" "),

                  // Use custom render function if provided, otherwise show raw value
                  column.render match {
                    case Some(renderFn) => renderFn(value, row, rowIndex)
                    case None => span(value.toString)
                  }
                )
              })
            )
          })
      )

      // Table footer if provided
      props.footer.map(footerContent =>
        tfoot(
          cls := props.footerClassName,
          tr(
            td(
              colSpan := visibleColumns.length.toString,
              footerContent
            )
          )
        )
      ).orNull
      )
    }

  // Render the table with optional responsive wrapper
  if (props.responsive) {
    div(
      cls := "overflow-x-auto w-full",
      tableContent
    )
  } else {
    tableContent
  }
}

/** Pagination props for use with Table component
 */
case class PaginationProps(
                            currentPage: Int = 1,                   // Current active page
                            totalPages: Int = 1,                    // Total number of pages
                            onPageChange: Int => Unit = _ => (),    // Page change handler
                            size: String = "md",                    // sm, md, lg
                            showFirstLast: Boolean = true,          // Show first/last page buttons
                            showPageNumbers: Boolean = true,        // Show numbered page buttons
                            maxDisplayedPages: Int = 5,             // Maximum number of page buttons to show
                            className: String = "",                 // Additional classes
                          )

/** Pagination component for use with Table
 *
 * Can be used independently or in a table footer
 */
val Pagination = (props: PaginationProps) => {
  val classes = List.newBuilder[String]

  // Base class
  classes += "flex items-center justify-center gap-1"

  // Size classes - use btn size classes since pagination uses buttons
  val sizeClass = props.size match {
    case "sm" => "btn-sm"
    case "lg" => "btn-lg"
    case _    => "btn-md" // Default size
  }

  // Add custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  val paginationClass = classes.result().mkString(" ")

  // Calculate which page numbers to show
  def getPageNumbers(): List[Int] = {
    if (!props.showPageNumbers || props.totalPages <= 1) {
      return List()
    }

    if (props.totalPages <= props.maxDisplayedPages) {
      return (1 to props.totalPages).toList
    }

    // Complex pagination logic with ellipsis
    val halfDisplayed = props.maxDisplayedPages / 2

    if (props.currentPage <= halfDisplayed + 1) {
      // Near start
      return (1 to props.maxDisplayedPages - 1).toList :+ props.totalPages
    } else if (props.currentPage >= props.totalPages - halfDisplayed) {
      // Near end
      return 1 +: (props.totalPages - props.maxDisplayedPages + 2 to props.totalPages).toList
    } else {
      // Middle
      val start = props.currentPage - halfDisplayed
      val end = props.currentPage + halfDisplayed
      return 1 +: (start to end).toList :+ props.totalPages
    }
  }

  // Generate page buttons
  val pageNumbers = getPageNumbers()
  val showEllipsisStart = props.showPageNumbers && pageNumbers.contains(1) && !pageNumbers.contains(2) && props.totalPages > 1
  val showEllipsisEnd = props.showPageNumbers && pageNumbers.contains(props.totalPages) && !pageNumbers.contains(props.totalPages - 1) && props.totalPages > 1

  div(
    cls := paginationClass,

    // First page button
    if (props.showFirstLast && props.totalPages > 1)
      button(
        typ := "button",
        cls := s"btn $sizeClass btn-ghost",
        disabled := props.currentPage == 1,
        onClick := (() => props.onPageChange(1)),
        "«"
      )
    else null,

    // Previous page button
    button(
      typ := "button",
      cls := s"btn $sizeClass btn-ghost",
      disabled := props.currentPage == 1,
      onClick := (() => props.onPageChange(props.currentPage - 1)),
      "‹"
    ),

    // Page number buttons
    if (props.showPageNumbers) {
      pageNumbers.zipWithIndex.map { case (pageNum, idx) =>
        if (idx > 0 && pageNum - pageNumbers(idx - 1) > 1) {
          // Show ellipsis for gaps
          span(
            key := s"ellipsis-$idx",
            cls := s"btn $sizeClass btn-disabled opacity-50 no-animation",
            "…"
          )
        } else {
          button(
            key := s"page-$pageNum",
            typ := "button",
            cls := s"btn $sizeClass ${if (pageNum == props.currentPage) "btn-active" else "btn-ghost"}",
            onClick := (() => props.onPageChange(pageNum)),
            pageNum.toString
          )
        }
      }
    } else null,

    // Next page button
    button(
      typ := "button",
      cls := s"btn $sizeClass btn-ghost",
      disabled := props.currentPage == props.totalPages || props.totalPages == 0,
      onClick := (() => props.onPageChange(props.currentPage + 1)),
      "›"
    ),

    // Last page button
    if (props.showFirstLast && props.totalPages > 1)
      button(
        typ := "button",
        cls := s"btn $sizeClass btn-ghost",
        disabled := props.currentPage == props.totalPages || props.totalPages == 0,
        onClick := (() => props.onPageChange(props.totalPages)),
        "»"
      )
    else null
  )
}

/** TableWithPagination props combining Table and Pagination
 */
case class TableWithPaginationProps(
                                     // Core data props
                                     data: List[Map[String, Any]] = List(),        // All table data
                                     columns: List[TableColumnDef] = List(),       // Column definitions

                                     // Pagination props
                                     pageSize: Int = 10,                           // Records per page
                                     currentPage: Int = 1,                         // Current page (controlled)
                                     onPageChange: Int => Unit = _ => (),          // Page change handler
                                     paginationSize: String = "sm",                // Pagination button size

                                     // Table styling (pass-through to Table component)
                                     bordered: Boolean = true,
                                     striped: Boolean = false,
                                     hover: Boolean = true,
                                     compact: Boolean = false,
                                     size: String = "md",
                                     responsive: Boolean = true,
                                     headerBgClass: String = "",
                                     className: String = "",

                                     // Other table features
                                     onRowClick: Option[(Map[String, Any], Int) => Unit] = None,
                                     emptyText: String = "No data available",
                                     keyField: String = "id",
                                     caption: Option[String] = None,
                                   )

/** TableWithPagination component
 *
 * Combines Table and Pagination components for a complete solution
 */
val TableWithPagination = (props: TableWithPaginationProps) => {
  // Calculate total pages
  val totalPages = Math.ceil(props.data.size.toDouble / props.pageSize.toDouble).toInt

  // Calculate current page data subset
  val startIndex = (props.currentPage - 1) * props.pageSize
  val pageData = props.data.slice(startIndex, startIndex + props.pageSize)

  // Create pagination footer node
  val paginationNode =
    Pagination <> PaginationProps(
      currentPage = props.currentPage,
      totalPages = totalPages,
      onPageChange = props.onPageChange,
      size = props.paginationSize
    )

  // Render the table with pagination in footer
  div(
    cls := "space-y-4",

    Table <> TableProps(
      data = pageData,
      columns = props.columns,
      keyField = props.keyField,
      bordered = props.bordered,
      striped = props.striped,
      hover = props.hover,
      compact = props.compact,
      size = props.size,
      responsive = props.responsive,
      headerBgClass = props.headerBgClass,
      className = props.className,
      onRowClick = props.onRowClick,
      emptyText = props.emptyText,
      caption = props.caption,
      footer = Some(div(
        cls := "pt-2 pb-1",
        paginationNode
      ))
    )
  )
}