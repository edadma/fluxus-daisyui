package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def TableExample: FluxusNode = {
  // State for pagination
  val (currentPage, setCurrentPage, _) = useState(1)

  // Sample data
  val users = List(
    Map("id" -> 1, "name" -> "John Doe", "email"   -> "john@example.com", "role" -> "Admin", "status" -> "Active"),
    Map("id" -> 2, "name" -> "Jane Smith", "email" -> "jane@example.com", "role" -> "User", "status"  -> "Active"),
    Map(
      "id"     -> 3,
      "name"   -> "Alice Johnson",
      "email"  -> "alice@example.com",
      "role"   -> "Editor",
      "status" -> "Inactive",
    ),
    Map("id" -> 4, "name" -> "Bob Williams", "email" -> "bob@example.com", "role" -> "Admin", "status" -> "Active"),
    Map("id" -> 5, "name" -> "Charlie Brown", "email" -> "charlie@example.com", "role" -> "User", "status" -> "Pending"),
  )

  // Column definitions
  val columns = List(
    TableColumnDef(
      key = "id",
      title = "ID",
      width = Some("60px"),
      align = "center",
      className = "font-mono",
    ),
    TableColumnDef(
      key = "name",
      title = "Name",
      minWidth = Some("150px"),
    ),
    TableColumnDef(
      key = "email",
      title = "Email Address",
      minWidth = Some("200px"),
      render = Some((value, _, _) =>
        a(
          href := s"mailto:${value}",
          cls  := "link link-primary",
          value.toString,
        ),
      ),
    ),
    TableColumnDef(
      key = "role",
      title = "Role",
      align = "center",
      render = Some((value, _, _) =>
        span(
          cls := {
            value match {
              case "Admin"  => "badge badge-primary"
              case "Editor" => "badge badge-secondary"
              case _        => "badge badge-ghost"
            }
          },
          value.toString,
        ),
      ),
    ),
    TableColumnDef(
      key = "status",
      title = "Status",
      align = "center",
      render = Some((value, _, _) =>
        span(
          cls := {
            value match {
              case "Active"   => "badge badge-success"
              case "Inactive" => "badge badge-error"
              case _          => "badge badge-warning"
            }
          },
          value.toString,
        ),
      ),
    ),
    TableColumnDef(
      key = "actions",
      title = "Actions",
      align = "center",
      width = Some("120px"),
      render = Some((_, row, _) =>
        div(
          cls := "flex justify-center gap-1",
          button(
            cls := "btn btn-xs btn-ghost text-info",
            onClick := ((e: dom.MouseEvent) => {
              e.stopPropagation() // Prevent row click
              dom.window.alert(s"View user ${row("id")}")
            }),
            "View",
          ),
          button(
            cls := "btn btn-xs btn-ghost text-warning",
            onClick := ((e: dom.MouseEvent) => {
              e.stopPropagation() // Prevent row click
              dom.window.alert(s"Edit user ${row("id")}")
            }),
            "Edit",
          ),
          button(
            cls := "btn btn-xs btn-ghost text-error",
            onClick := ((e: dom.MouseEvent) => {
              e.stopPropagation() // Prevent row click
              dom.window.alert(s"Delete user ${row("id")}")
            }),
            "Delete",
          ),
        ),
      ),
    ),
  )

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Table Component Demo",
      ),

      // Basic Table Example
      Card <> CardProps(
        title = Some("Basic Table"),
        className = "mb-8",
        children = div(
          Table <> TableProps(
            data = users,
            columns = columns.filterNot(_.key == "actions"),
            bordered = true,
            hover = true,
            caption = Some("User information table"),
            headerBgClass = "bg-base-200",
            onRowClick = Some((row, _) => dom.window.alert(s"Row clicked: ${row("name")}")),
          ),
        ),
      ),

      // Styled Table Example
      Card <> CardProps(
        title = Some("Styled Table with Variants"),
        className = "mb-8",
        children = div(
          Table <> TableProps(
            data = users,
            columns = columns,
            bordered = true,
            striped = true,
            hover = true,
            headerBgClass = "bg-primary text-primary-content",
            headerTextClass = "text-lg font-bold",
            size = "sm",
            onRowClick = Some((row, _) => dom.window.alert(s"Selected user: ${row("name")}")),
          ),
        ),
      ),

      // Table with Pagination Example
      Card <> CardProps(
        title = Some("Table with Pagination"),
        className = "mb-8",
        children = div(
          TableWithPagination <> TableWithPaginationProps(
            data = users,
            columns = columns,
            pageSize = 3,
            currentPage = currentPage,
            onPageChange = setCurrentPage,
            bordered = true,
            hover = true,
            headerBgClass = "bg-base-300",
            onRowClick = Some((row, _) => dom.window.alert(s"Selected user: ${row("name")}")),
          ),
        ),
      ),

      // Empty Table Example
      Card <> CardProps(
        title = Some("Empty Table"),
        className = "mb-8",
        children = div(
          Table <> TableProps(
            data = List(),
            columns = columns,
            bordered = true,
            emptyText = "No users found",
          ),
        ),
      ),

      // Responsive Table Example
      Card <> CardProps(
        title = Some("Responsive Table (resize window to see effect)"),
        children = div(
          // Add many columns to demonstrate horizontal scrolling
          Table <> TableProps(
            data = users,
            columns = List(
              TableColumnDef(key = "id", title = "ID", width = Some("80px")),
              TableColumnDef(key = "name", title = "Name", minWidth = Some("150px")),
              TableColumnDef(key = "email", title = "Email", minWidth = Some("200px")),
              TableColumnDef(key = "role", title = "Role", minWidth = Some("120px")),
              TableColumnDef(key = "status", title = "Status", minWidth = Some("120px")),
              TableColumnDef(
                key = "created",
                title = "Created Date",
                minWidth = Some("200px"),
                render = Some((_, _, _) => span("2023-04-11")),
              ),
              TableColumnDef(
                key = "updated",
                title = "Last Updated",
                minWidth = Some("200px"),
                render = Some((_, _, _) => span("2024-01-28")),
              ),
              TableColumnDef(
                key = "department",
                title = "Department",
                minWidth = Some("150px"),
                render = Some((_, _, _) => span("Engineering")),
              ),
              TableColumnDef(
                key = "location",
                title = "Location",
                minWidth = Some("150px"),
                render = Some((_, _, _) => span("New York")),
              ),
              TableColumnDef(
                key = "manager",
                title = "Manager",
                minWidth = Some("150px"),
                render = Some((_, _, _) => span("Jane Smith")),
              ),
            ),
            bordered = true,
            striped = true,
            hover = true,
            size = "sm",
            headerBgClass = "bg-base-200 sticky top-0",
          ),
        ),
      ),
    ),
  )
}
