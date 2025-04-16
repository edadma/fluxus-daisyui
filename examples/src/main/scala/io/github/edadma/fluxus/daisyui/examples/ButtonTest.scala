package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def ButtonTest: FluxusNode = {
  // State for demo interactions
  val (isLoading, setIsLoading, _) = useState(false)
  val (count, setCount, _)         = useState(0)

  // Example icons
  def PlusIcon: FluxusNode = {
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
      path(d := "M12 5v14M5 12h14"),
    )
  }

  def MinusIcon: FluxusNode = {
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
      path(d := "M5 12h14"),
    )
  }

  def CheckIcon: FluxusNode = {
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
      path(d := "M20 6L9 17l-5-5"),
    )
  }

  // Simulated loading handler
  def handleLoadingDemo(): Unit = {
    setIsLoading(true)

    // Reset after 2 seconds
    dom.window.setTimeout(
      () => {
        setIsLoading(false)
      },
      2000,
    )
  }

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Button Component Demo",
      ),

      // Basic Button Variants
      Card <> CardProps(
        title = Some("Button Variants"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-2",
          Button <> ButtonProps(text = "Default"),
          Button <> ButtonProps(text = "Primary", variant = "primary"),
          Button <> ButtonProps(text = "Secondary", variant = "secondary"),
          Button <> ButtonProps(text = "Accent", variant = "accent"),
          Button <> ButtonProps(text = "Info", variant = "info"),
          Button <> ButtonProps(text = "Success", variant = "success"),
          Button <> ButtonProps(text = "Warning", variant = "warning"),
          Button <> ButtonProps(text = "Error", variant = "error"),
          Button <> ButtonProps(text = "Ghost", variant = "ghost"),
          Button <> ButtonProps(text = "Link", variant = "link"),
          Button <> ButtonProps(text = "Neutral", variant = "neutral"),
        ),
      ),

      // NEW: Soft Button Variants
      Card <> CardProps(
        title = Some("Soft Button Variants"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",
          p(
            cls := "text-sm text-base-content/70",
            "Soft buttons have a gentler, lower-opacity background color that creates a more subtle appearance.",
          ),
          div(
            cls := "flex flex-wrap gap-2",
            Button <> ButtonProps(text = "Soft Primary", variant = "primary", soft = true),
            Button <> ButtonProps(text = "Soft Secondary", variant = "secondary", soft = true),
            Button <> ButtonProps(text = "Soft Accent", variant = "accent", soft = true),
            Button <> ButtonProps(text = "Soft Info", variant = "info", soft = true),
            Button <> ButtonProps(text = "Soft Success", variant = "success", soft = true),
            Button <> ButtonProps(text = "Soft Warning", variant = "warning", soft = true),
            Button <> ButtonProps(text = "Soft Error", variant = "error", soft = true),
          ),
        ),
      ),

      // NEW: Dash Button Variants
      Card <> CardProps(
        title = Some("Dash Button Variants"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",
          p(
            cls := "text-sm text-base-content/70",
            "Dash buttons feature a dashed border style that provides a unique visual appearance.",
          ),
          div(
            cls := "flex flex-wrap gap-2",
            Button <> ButtonProps(text = "Dash Primary", variant = "primary", dash = true),
            Button <> ButtonProps(text = "Dash Secondary", variant = "secondary", dash = true),
            Button <> ButtonProps(text = "Dash Accent", variant = "accent", dash = true),
            Button <> ButtonProps(text = "Dash Info", variant = "info", dash = true),
            Button <> ButtonProps(text = "Dash Success", variant = "success", dash = true),
            Button <> ButtonProps(text = "Dash Warning", variant = "warning", dash = true),
            Button <> ButtonProps(text = "Dash Error", variant = "error", dash = true),
          ),
        ),
      ),

      // Combined Styles
      Card <> CardProps(
        title = Some("Combined Button Styles"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",
          p(
            cls := "text-sm text-base-content/70",
            "Button style modifiers can be combined for even more styling options.",
          ),
          div(
            cls := "flex flex-wrap gap-2",
            Button <> ButtonProps(text = "Soft Outline", variant = "primary", soft = true, outline = true),
            Button <> ButtonProps(text = "Dash Outline", variant = "secondary", dash = true, outline = true),
            Button <> ButtonProps(
              text = "Soft + Icon",
              variant = "success",
              soft = true,
              startIcon = Some(CheckIcon),
            ),
            Button <> ButtonProps(
              text = "Dash + Wide",
              variant = "warning",
              dash = true,
              wide = true,
            ),
          ),
        ),
      ),

      // Button Sizes
      Card <> CardProps(
        title = Some("Button Sizes"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-2 items-center",
          Button <> ButtonProps(text = "Large", size = "lg", variant = "primary"),
          Button <> ButtonProps(text = "Normal", variant = "primary"),
          Button <> ButtonProps(text = "Small", size = "sm", variant = "primary"),
          Button <> ButtonProps(text = "Tiny", size = "xs", variant = "primary"),
        ),
      ),

      // Button Styles
      Card <> CardProps(
        title = Some("Button Styles"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",
          div(
            h3(cls := "text-lg font-semibold mb-2", "Outline Buttons"),
            div(
              cls := "flex flex-wrap gap-2",
              Button <> ButtonProps(text = "Primary", variant = "primary", outline = true),
              Button <> ButtonProps(text = "Secondary", variant = "secondary", outline = true),
              Button <> ButtonProps(text = "Accent", variant = "accent", outline = true),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Wide & Block Buttons"),
            div(
              cls := "space-y-2",
              Button <> ButtonProps(text = "Wide Button", variant = "primary", wide = true),
              Button <> ButtonProps(text = "Block Button", variant = "secondary", block = true),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Button Shapes"),
            div(
              cls := "flex flex-wrap gap-2 items-center",
              Button <> ButtonProps(
                variant = "primary",
                shape = Some("circle"),
                children = Some(PlusIcon),
              ),
              Button <> ButtonProps(
                variant = "secondary",
                shape = Some("square"),
                children = Some(MinusIcon),
              ),
              Button <> ButtonProps(
                text = "Rounded",
                variant = "accent",
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Glass Button"),
            div(
              cls := "p-8 bg-gradient-to-r from-blue-500 to-purple-500 rounded-box",
              Button <> ButtonProps(text = "Glass Button", glass = true),
            ),
          ),
        ),
      ),

      // Button States
      Card <> CardProps(
        title = Some("Button States"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",
          div(
            h3(cls := "text-lg font-semibold mb-2", "Loading State"),
            div(
              cls := "flex gap-4 items-center",
              Button <> ButtonProps(
                text = if (isLoading) "Loading..." else "Click to Load",
                variant = "primary",
                loading = isLoading,
                onClick = () => handleLoadingDemo(),
              ),
              Button <> ButtonProps(
                text = "Always Loading",
                variant = "secondary",
                loading = true,
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Disabled State"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = "Disabled Button",
                variant = "primary",
                disabled = true,
              ),
              Button <> ButtonProps(
                text = "Disabled Outline",
                variant = "secondary",
                outline = true,
                disabled = true,
              ),
              Button <> ButtonProps(
                text = "Disabled Soft",
                variant = "accent",
                soft = true,
                disabled = true,
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Active State"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = "Active Button",
                variant = "primary",
                active = true,
              ),
              Button <> ButtonProps(
                text = "Normal Button",
                variant = "primary",
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "No Animation"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = "No Animation",
                variant = "primary",
                noAnimation = true,
              ),
              Button <> ButtonProps(
                text = "Default Animation",
                variant = "primary",
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Focus Visible"),
            p(cls  := "text-sm mb-2", "Tab to these buttons to see focus styles:"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = "With Focus Visible",
                variant = "primary",
                focusVisible = true,
              ),
              Button <> ButtonProps(
                text = "Without Focus Visible",
                variant = "primary",
              ),
            ),
          ),
        ),
      ),

      // Buttons with Icons
      Card <> CardProps(
        title = Some("Buttons with Icons"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4",
          Button <> ButtonProps(
            text = "Add Item",
            variant = "primary",
            startIcon = Some(PlusIcon),
          ),
          Button <> ButtonProps(
            text = "Confirm",
            variant = "success",
            endIcon = Some(CheckIcon),
          ),
          Button <> ButtonProps(
            variant = "error",
            shape = Some("circle"),
            children = Some(MinusIcon),
          ),
          Button <> ButtonProps(
            text = "With Both Icons",
            variant = "info",
            startIcon = Some(PlusIcon),
            endIcon = Some(CheckIcon),
          ),
        ),
      ),

      // Interactive Button Example
      Card <> CardProps(
        title = Some("Interactive Example"),
        className = "mb-8",
        children = div(
          cls := "flex flex-col items-center gap-4",
          h3(cls := "text-xl", s"Count: $count"),
          div(
            cls := "flex gap-4",
            Button <> ButtonProps(
              variant = "primary",
              shape = Some("circle"),
              children = Some(MinusIcon),
              onClick = () => setCount(count - 1),
            ),
            Button <> ButtonProps(
              variant = "primary",
              shape = Some("circle"),
              children = Some(PlusIcon),
              onClick = () => setCount(count + 1),
            ),
          ),
        ),
      ),

      // Responsive Button Variants
      Card <> CardProps(
        title = Some("Responsive Buttons"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",
          div(
            h3(cls := "text-lg font-semibold mb-2", "Responsive Variants"),
            p(cls  := "text-sm mb-4", "Resize your browser to see these buttons change:"),
            div(
              cls := "flex flex-wrap gap-2",
              Button <> ButtonProps(
                text = "Responsive Variant",
                variant = "primary",
                mdVariant = Some("secondary"),
                lgVariant = Some("accent"),
              ),
              Button <> ButtonProps(
                text = "Responsive Size",
                variant = "info",
                size = "xs",
                smSize = Some("sm"),
                mdSize = Some("md"),
                lgSize = Some("lg"),
              ),
            ),
          ),
        ),
      ),

      // Button Groups
      Card <> CardProps(
        title = Some("Button Groups"),
        className = "mb-8",
        children = div(
          cls := "space-y-8",
          div(
            h3(cls := "text-lg font-semibold mb-2", "Horizontal Button Group"),
            ButtonGroup <> ButtonGroupProps(
              children = Seq(
                Button <> ButtonProps(text = "Left"),
                Button <> ButtonProps(text = "Middle"),
                Button <> ButtonProps(text = "Right"),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Vertical Button Group"),
            ButtonGroup <> ButtonGroupProps(
              vertical = true,
              children = Seq(
                Button <> ButtonProps(text = "Top"),
                Button <> ButtonProps(text = "Middle"),
                Button <> ButtonProps(text = "Bottom"),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Soft Button Group"),
            ButtonGroup <> ButtonGroupProps(
              variant = Some("primary"),
              children = Seq(
                Button <> ButtonProps(text = "Left", soft = true),
                Button <> ButtonProps(text = "Middle", soft = true),
                Button <> ButtonProps(text = "Right", soft = true),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Dash Button Group"),
            ButtonGroup <> ButtonGroupProps(
              variant = Some("secondary"),
              children = Seq(
                Button <> ButtonProps(text = "Left", dash = true),
                Button <> ButtonProps(text = "Middle", dash = true),
                Button <> ButtonProps(text = "Right", dash = true),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Button Group with Consistent Styling"),
            ButtonGroup <> ButtonGroupProps(
              variant = Some("primary"),
              size = Some("sm"),
              children = Seq(
                Button <> ButtonProps(text = "Small"),
                Button <> ButtonProps(text = "Primary"),
                Button <> ButtonProps(text = "Buttons"),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Button Group with Space Between"),
            ButtonGroup <> ButtonGroupProps(
              joined = false,
              children = Seq(
                Button <> ButtonProps(text = "With", variant = "primary"),
                Button <> ButtonProps(text = "Gap", variant = "primary"),
                Button <> ButtonProps(text = "Between", variant = "primary"),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Responsive Button Group"),
            p(cls  := "text-sm mb-4", "Resize your browser to see this group change orientation:"),
            ButtonGroup <> ButtonGroupProps(
              stacked = Some("md"),
              joined = false,
              children = Seq(
                Button <> ButtonProps(
                  text = "First Item",
                  variant = "primary",
                  block = true,
                ),
                Button <> ButtonProps(
                  text = "Second Item",
                  variant = "secondary",
                  block = true,
                ),
                Button <> ButtonProps(
                  text = "Third Item",
                  variant = "accent",
                  block = true,
                ),
              ),
            ),
          ),
        ),
      ),

      // Practical Examples
      Card <> CardProps(
        title = Some("Practical Examples"),
        children = div(
          cls := "space-y-6",
          div(
            h3(cls := "text-lg font-semibold mb-2", "Form Buttons"),
            div(
              cls := "p-4 border border-base-300 rounded-box",
              div(
                cls := "mb-4",
                label(cls := "block mb-2", "Email"),
                input(
                  typ         := "email",
                  cls         := "input input-bordered w-full",
                  placeholder := "Enter your email",
                ),
              ),
              div(
                cls := "flex justify-end gap-2",
                Button <> ButtonProps(
                  text = "Cancel",
                  variant = "ghost",
                ),
                Button <> ButtonProps(
                  text = "Submit",
                  variant = "primary",
                  soft = true,
                ),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Card Actions"),
            div(
              cls := "card bg-base-100 shadow-xl",
              div(
                cls := "card-body",
                h4(cls := "card-title", "Card Title"),
                p("This card demonstrates soft and dash button usage in a card footer."),
                div(
                  cls := "card-actions justify-end mt-4",
                  Button <> ButtonProps(
                    text = "Cancel",
                    variant = "ghost",
                    size = "sm",
                  ),
                  Button <> ButtonProps(
                    text = "Save Draft",
                    variant = "secondary",
                    dash = true,
                    size = "sm",
                  ),
                  Button <> ButtonProps(
                    text = "Publish",
                    variant = "primary",
                    soft = true,
                    size = "sm",
                  ),
                ),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Pagination"),
            ButtonGroup <> ButtonGroupProps(
              children = Seq(
                Button <> ButtonProps(
                  text = "«",
                  variant = "outline",
                ),
                Button <> ButtonProps(
                  text = "Page 1",
                  variant = "outline",
                  active = true,
                ),
                Button <> ButtonProps(
                  text = "Page 2",
                  variant = "outline",
                ),
                Button <> ButtonProps(
                  text = "Page 3",
                  variant = "outline",
                ),
                Button <> ButtonProps(
                  text = "»",
                  variant = "outline",
                ),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-semibold mb-2", "Social Media Buttons"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = "Facebook",
                soft = true,
              ),
              Button <> ButtonProps(
                text = "Twitter",
                variant = "info",
                soft = true,
              ),
              Button <> ButtonProps(
                text = "Instagram",
                variant = "accent",
                soft = true,
              ),
            ),
          ),
        ),
      ),
    ),
  )
}
