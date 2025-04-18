package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def StatsTest: FluxusNode = {
  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Stats Component Demo",
      ),

      // Basic Stats Example
      Card <> CardProps(
        title = Some("Basic Stats"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Basic usage of the Stats component with multiple Stat items.",
          ),
          Stats <> StatsProps(
            shadow = true,
            children = div(
              // First stat
              Stat <> StatProps(
                title = "Total Visits",
                value = "89,400",
                description = Some("21% more than last month"),
              ),

              // Second stat
              Stat <> StatProps(
                title = "New Users",
                value = "1,200",
                description = Some("↗︎ 14% increase"),
              ),

              // Third stat
              Stat <> StatProps(
                title = "New Registers",
                value = "700",
                description = Some("↘︎ 9% decrease"),
              ),
            ),
          ),
        ),
      ),

      // Stats with Figures
      Card <> CardProps(
        title = Some("Stats with Icons"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Stats with icons/figures can be added at the start or end position.",
          ),
          Stats <> StatsProps(
            shadow = true,
            children = div(
              // Stat with start icon
              Stat <> StatProps(
                title = "Downloads",
                value = "31K",
                description = Some("Jan 1st - Feb 1st"),
                figure = Some(
                  svg(
                    xmlns   := "http://www.w3.org/2000/svg",
                    fill    := "none",
                    viewBox := "0 0 24 24",
                    cls     := "inline-block w-8 h-8 stroke-current",
                    path(
                      strokeLinecap  := "round",
                      strokeLinejoin := "round",
                      strokeWidth    := "2",
                      d              := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z",
                    ),
                  ),
                ),
              ),

              // Stat with end icon
              Stat <> StatProps(
                title = "Page Views",
                value = "2.6M",
                description = Some("↗︎ 11% increase"),
                figure = Some(
                  svg(
                    xmlns   := "http://www.w3.org/2000/svg",
                    fill    := "none",
                    viewBox := "0 0 24 24",
                    cls     := "inline-block w-8 h-8 stroke-current",
                    path(
                      strokeLinecap  := "round",
                      strokeLinejoin := "round",
                      strokeWidth    := "2",
                      d := "M12 6V4m0 2a2 2 0 100 4m0-4a2 2 0 110 4m-6 8a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4m6 6v10m6-2a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4",
                    ),
                  ),
                ),
                figurePosition = "end",
                figureClass = "text-primary",
              ),
            ),
          ),
        ),
      ),

      // Horizontal Layout
      Card <> CardProps(
        title = Some("Horizontal Layout"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Stats can be displayed horizontally instead of vertically.",
          ),
          Stats <> StatsProps(
            direction = "horizontal",
            shadow = true,
            bordered = true,
            children = div(
              Stat <> StatProps(
                title = "Users",
                value = "4,200",
                description = Some("↗︎ 7% increase"),
              ),
              Stat <> StatProps(
                title = "Sessions",
                value = "1,200",
                description = Some("↘︎ 12% decrease"),
              ),
              Stat <> StatProps(
                title = "Bounce Rate",
                value = "40%",
                description = Some("Lower is better"),
              ),
            ),
          ),
        ),
      ),

      // Stats with Actions
      Card <> CardProps(
        title = Some("Stats with Actions"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Stats can include action buttons below the content.",
          ),
          Stats <> StatsProps(
            shadow = true,
            bgClass = "bg-primary text-primary-content",
            children = div(
              Stat <> StatProps(
                title = "Account Balance",
                value = "$5,600",
                description = Some("Current available balance"),
                actions = Some(
                  div(
                    cls := "flex gap-2",
                    Button <> ButtonProps(
                      text = "Transfer",
                      variant = "ghost",
                      size = "sm",
                    ),
                    Button <> ButtonProps(
                      text = "Deposit",
                      variant = "ghost",
                      size = "sm",
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),

      // Responsive Stats
      Card <> CardProps(
        title = Some("Responsive Stats"),
        children = div(
          p(
            cls := "mb-4",
            "Stats can adapt to screen size, displaying vertically on mobile and horizontally on larger screens.",
          ),
          Stats <> StatsProps(
            direction = "responsive", // vertical on mobile, horizontal on md+
            shadow = true,
            bordered = true,
            children = div(
              Stat <> StatProps(
                title = "Daily Users",
                value = "2,200",
                description = Some("Unique visitors"),
              ),
              Stat <> StatProps(
                title = "Page Views",
                value = "8,700",
                description = Some("Per day average"),
              ),
              Stat <> StatProps(
                title = "Conversion",
                value = "12%",
                description = Some("Visitors to customers"),
              ),
            ),
          ),
        ),
      ),
    ),
  )
}
