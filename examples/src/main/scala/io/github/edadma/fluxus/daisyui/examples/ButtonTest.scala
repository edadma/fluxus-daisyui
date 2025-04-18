package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def ButtonTest: FluxusNode = {
  // State for interactive demos
  val (clickCount, setClickCount, _)           = useState(0)
  val (isLoading, setIsLoading, _)             = useState(false)
  val (selectedVariant, setSelectedVariant, _) = useState("primary")
  val (notification, setNotification, _)       = useState("")

  // Icon examples
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
    setNotification("Loading started...")

    // Reset after 2 seconds
    dom.window.setTimeout(
      () => {
        setIsLoading(false)
        setNotification("Loading completed!")
      },
      2000,
    )
  }

  // Show notification for button clicks
  def showVariantNotification(variant: String): Unit = {
    setSelectedVariant(variant)
    setNotification(s"Selected $variant variant")

    // Clear notification after 2 seconds
    dom.window.setTimeout(
      () => {
        if (notification == s"Selected $variant variant") {
          setNotification("")
        }
      },
      2000,
    )
  }

  Container <> ContainerProps(
    className = "p-8 max-w-4xl mx-auto",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "DaisyUI Button Components",
      ),

      // Notification area
      div(
        cls := "mb-8 min-h-16",
        if (notification.nonEmpty) {
          div(
            cls := "alert alert-info shadow-lg",
            div(
              svg(
                xmlns   := "http://www.w3.org/2000/svg",
                cls     := "stroke-current flex-shrink-0 h-6 w-6",
                fill    := "none",
                viewBox := "0 0 24 24",
                path(
                  strokeLinecap  := "round",
                  strokeLinejoin := "round",
                  strokeWidth    := "2",
                  d              := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z",
                ),
              ),
              span(notification),
            ),
          )
        } else null,
      ),

      // Basic Variants
      Card <> CardProps(
        title = Some("Button Variants"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-2",
          Button <> ButtonProps(
            text = "Default",
            onClick = () => showVariantNotification("default"),
          ),
          Button <> ButtonProps(
            text = "Primary",
            variant = "primary",
            onClick = () => showVariantNotification("primary"),
          ),
          Button <> ButtonProps(
            text = "Secondary",
            variant = "secondary",
            onClick = () => showVariantNotification("secondary"),
          ),
          Button <> ButtonProps(
            text = "Accent",
            variant = "accent",
            onClick = () => showVariantNotification("accent"),
          ),
          Button <> ButtonProps(
            text = "Info",
            variant = "info",
            onClick = () => showVariantNotification("info"),
          ),
          Button <> ButtonProps(
            text = "Success",
            variant = "success",
            onClick = () => showVariantNotification("success"),
          ),
          Button <> ButtonProps(
            text = "Warning",
            variant = "warning",
            onClick = () => showVariantNotification("warning"),
          ),
          Button <> ButtonProps(
            text = "Error",
            variant = "error",
            onClick = () => showVariantNotification("error"),
          ),
        ),
      ),

      // Special Variants
      Card <> CardProps(
        title = Some("Special Variants"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4",
          div(
            h3(cls := "text-lg font-medium mb-2", "Outline Style"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = "Outline Primary",
                variant = "primary",
                outline = true,
                onClick = () => showVariantNotification("primary outline"),
              ),
              Button <> ButtonProps(
                text = "Outline Secondary",
                variant = "secondary",
                outline = true,
                onClick = () => showVariantNotification("secondary outline"),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-medium mb-2", "Soft Style"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = "Soft Primary",
                variant = "primary",
                soft = true,
                onClick = () => showVariantNotification("primary soft"),
              ),
              Button <> ButtonProps(
                text = "Soft Secondary",
                variant = "secondary",
                soft = true,
                onClick = () => showVariantNotification("secondary soft"),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-medium mb-2", "Ghost & Link"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = "Ghost Button",
                variant = "ghost",
                onClick = () => showVariantNotification("ghost"),
              ),
              Button <> ButtonProps(
                text = "Link Button",
                variant = "link",
                href = Some("https://www.ap.org/"),
              ),
            ),
          ),
        ),
      ),

      // Sizes
      Card <> CardProps(
        title = Some("Button Sizes"),
        className = "mb-8",
        children = div(
          cls := "flex items-center gap-2 flex-wrap",
          Button <> ButtonProps(
            text = "Large",
            variant = "primary",
            size = "lg",
            onClick = () => setNotification("Large button clicked"),
          ),
          Button <> ButtonProps(
            text = "Normal",
            variant = "primary",
            onClick = () => setNotification("Normal button clicked"),
          ),
          Button <> ButtonProps(
            text = "Small",
            variant = "primary",
            size = "sm",
            onClick = () => setNotification("Small button clicked"),
          ),
          Button <> ButtonProps(
            text = "Tiny",
            variant = "primary",
            size = "xs",
            onClick = () => setNotification("Tiny button clicked"),
          ),
        ),
      ),

      // Shapes and Styles
      Card <> CardProps(
        title = Some("Shapes and Styles"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4",
          div(
            h3(cls := "text-lg font-medium mb-2", "Shape Variants"),
            div(
              cls := "flex gap-2 items-center",
              Button <> ButtonProps(
                variant = "primary",
                shape = Some("circle"),
                children = Some(PlusIcon),
                onClick = () => setNotification("Circle button clicked"),
              ),
              Button <> ButtonProps(
                variant = "secondary",
                shape = Some("square"),
                children = Some(CheckIcon),
                onClick = () => setNotification("Square button clicked"),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-medium mb-2", "Width Variants"),
            div(
              cls := "space-y-2",
              Button <> ButtonProps(
                text = "Wide Button",
                variant = "primary",
                wide = true,
                onClick = () => setNotification("Wide button clicked"),
              ),
              Button <> ButtonProps(
                text = "Block Button",
                variant = "secondary",
                block = true,
                onClick = () => setNotification("Block button clicked"),
              ),
            ),
          ),
        ),
      ),

      // States
      Card <> CardProps(
        title = Some("Button States"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4",
          div(
            h3(cls := "text-lg font-medium mb-2", "Loading State"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = if (isLoading) "Loading..." else "Click to Load",
                variant = "primary",
                loading = isLoading,
                onClick = () => handleLoadingDemo(),
              ),
            ),
          ),
          div(
            h3(cls := "text-lg font-medium mb-2", "Disabled State"),
            div(
              cls := "flex gap-2",
              Button <> ButtonProps(
                text = "Disabled Button",
                variant = "primary",
                disabled = true,
                onClick = () => setNotification("This should not appear"),
              ),
            ),
          ),
        ),
      ),

      // Interactive Counter
      Card <> CardProps(
        title = Some("Interactive Counter"),
        className = "mb-8",
        children = div(
          cls := "flex flex-col items-center gap-4",
          h3(
            cls := "text-2xl font-bold",
            s"Count: $clickCount",
          ),
          div(
            cls := "flex gap-4",
            Button <> ButtonProps(
              variant = "primary",
              outline = true,
              children = Some(
                div(
                  cls := "text-xl font-bold",
                  "−",
                ),
              ),
              onClick = () => setClickCount(clickCount - 1),
            ),
            Button <> ButtonProps(
              variant = "primary",
              children = Some(
                div(
                  cls := "text-xl font-bold",
                  "+",
                ),
              ),
              onClick = () => setClickCount(clickCount + 1),
            ),
          ),
          div(
            cls := "mt-4",
            Button <> ButtonProps(
              text = "Reset Counter",
              variant = "ghost",
              onClick = () => setClickCount(0),
            ),
          ),
        ),
      ),

      // Buttons with Icons
      Card <> CardProps(
        title = Some("Buttons with Icons"),
        children = div(
          cls := "flex flex-wrap gap-4",
          Button <> ButtonProps(
            text = "Add Item",
            variant = "primary",
            startIcon = Some(PlusIcon),
            onClick = () => setNotification("Add item clicked"),
          ),
          Button <> ButtonProps(
            text = "Confirm",
            variant = "success",
            endIcon = Some(CheckIcon),
            onClick = () => setNotification("Confirm clicked"),
          ),
          Button <> ButtonProps(
            text = "Both Icons",
            variant = "info",
            startIcon = Some(PlusIcon),
            endIcon = Some(CheckIcon),
            onClick = () => setNotification("Button with both icons clicked"),
          ),
        ),
      ),
    ),
  )
}
