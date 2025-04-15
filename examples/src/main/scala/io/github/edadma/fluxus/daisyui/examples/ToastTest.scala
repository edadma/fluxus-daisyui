package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def ToastTest: FluxusNode = {
  // Counter to create unique toast messages
  val (counter, setCounter, _) = useState(1)

  // Create a custom toast with more options
  def showCustomToast(): Unit = {
    val customProps = ToastProps(
      title = Some("Custom Notification"),
      message = Some("This is a custom notification with title, message, and custom duration."),
      variant = "info",
      duration = Some(5000), // 5 seconds
      position = "top-center",
    )

    ToastContainer.show(customProps)
  }

  // Create a persistent toast with action
  def showPersistentToast(): Unit = {
    // Define the toast ID upfront to avoid cyclic reference
    var toastId: String = ""

    // Create the toast with a closure that can reference the ID after it's set
    toastId = ToastContainer.show(
      ToastProps(
        title = Some("Important Message"),
        message = Some("This notification will not auto-dismiss. You must close it manually."),
        variant = "warning",
        duration = None, // No auto dismiss
        position = "top-right",
        children = Some(
          div(
            h3(cls := "font-bold", "Important Message"),
            p("This notification will not auto-dismiss and has a custom action button."),
            div(
              cls := "mt-2",
              Button <> ButtonProps(
                text = "Take Action",
                variant = "warning",
                size = "sm",
                onClick = () => {
                  // Now we can safely use toastId
                  ToastContainer.remove(toastId)
                  dom.window.alert("Action taken!")
                },
              ),
            ),
          ),
        ),
      ),
    )
  }

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Toast Component Demo",
      ),

      // Basic Toast Examples
      Card <> CardProps(
        title = Some("Basic Toast Examples"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-2 md:grid-cols-4 gap-4",

          // Success toast
          Button <> ButtonProps(
            text = "Success Toast",
            variant = "success",
            onClick = () => {
              ToastContainer.success(s"Success message #${counter}")
              setCounter(counter + 1)
            },
          ),

          // Error toast
          Button <> ButtonProps(
            text = "Error Toast",
            variant = "error",
            onClick = () => {
              ToastContainer.error(s"Error message #${counter}")
              setCounter(counter + 1)
            },
          ),

          // Info toast
          Button <> ButtonProps(
            text = "Info Toast",
            variant = "info",
            onClick = () => {
              ToastContainer.info(s"Info message #${counter}")
              setCounter(counter + 1)
            },
          ),

          // Warning toast
          Button <> ButtonProps(
            text = "Warning Toast",
            variant = "warning",
            onClick = () => {
              ToastContainer.warning(s"Warning message #${counter}")
              setCounter(counter + 1)
            },
          ),
        ),
      ),

      // Advanced Toast Examples
      Card <> CardProps(
        title = Some("Advanced Toast Examples"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-2 md:grid-cols-3 gap-4",

          // Custom toast
          Button <> ButtonProps(
            text = "Custom Toast",
            variant = "primary",
            onClick = () => showCustomToast(),
          ),

          // Persistent toast
          Button <> ButtonProps(
            text = "Persistent Toast",
            variant = "secondary",
            onClick = () => showPersistentToast(),
          ),

          // Clear all toasts
          Button <> ButtonProps(
            text = "Clear All Toasts",
            variant = "neutral",
            onClick = () => ToastContainer.clear(),
          ),
        ),
      ),

      // Position Examples
      Card <> CardProps(
        title = Some("Position Variants"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-2 md:grid-cols-3 gap-4",

          // Top Left
          Button <> ButtonProps(
            text = "Top Left",
            variant = "ghost",
            onClick = () => {
              ToastContainer.show(
                ToastProps(
                  message = Some(s"Top left toast #${counter}"),
                  position = "top-left",
                ),
              )
              setCounter(counter + 1)
            },
          ),

          // Top Center
          Button <> ButtonProps(
            text = "Top Center",
            variant = "ghost",
            onClick = () => {
              ToastContainer.show(
                ToastProps(
                  message = Some(s"Top center toast #${counter}"),
                  position = "top-center",
                ),
              )
              setCounter(counter + 1)
            },
          ),

          // Top Right
          Button <> ButtonProps(
            text = "Top Right",
            variant = "ghost",
            onClick = () => {
              ToastContainer.show(
                ToastProps(
                  message = Some(s"Top right toast #${counter}"),
                  position = "top-right",
                ),
              )
              setCounter(counter + 1)
            },
          ),

          // Bottom Left
          Button <> ButtonProps(
            text = "Bottom Left",
            variant = "ghost",
            onClick = () => {
              ToastContainer.show(
                ToastProps(
                  message = Some(s"Bottom left toast #${counter}"),
                  position = "bottom-left",
                ),
              )
              setCounter(counter + 1)
            },
          ),

          // Bottom Center
          Button <> ButtonProps(
            text = "Bottom Center",
            variant = "ghost",
            onClick = () => {
              ToastContainer.show(
                ToastProps(
                  message = Some(s"Bottom center toast #${counter}"),
                  position = "bottom-center",
                ),
              )
              setCounter(counter + 1)
            },
          ),

          // Bottom Right
          Button <> ButtonProps(
            text = "Bottom Right",
            variant = "ghost",
            onClick = () => {
              ToastContainer.show(
                ToastProps(
                  message = Some(s"Bottom right toast #${counter}"),
                  position = "bottom-right",
                ),
              )
              setCounter(counter + 1)
            },
          ),
        ),
      ),

      // Explanatory section
      Card <> CardProps(
        title = Some("Implementation Notes"),
        children = div(
          p(cls := "mb-2", "The Toast component consists of two main parts:"),
          ul(
            cls := "list-disc pl-5 mb-3",
            li("The ", code("Toast"), " component itself - renders a single notification"),
            li("The ", code("ToastContainer"), " singleton - manages notifications globally"),
          ),
          p(cls := "mb-2", "To use in your application:"),
          ol(
            cls := "list-decimal pl-5 mb-3",
            li(
              "Add ",
              code("ToastContainer"),
              " components at the positions you want to display toasts (typically in your app root)",
            ),
            li(
              "Call ",
              code("ToastContainer.success()"),
              ", ",
              code("ToastContainer.error()"),
              ", etc. from anywhere in your code",
            ),
          ),
          p(
            "The component handles animations, positioning, and automatic cleanup, while giving you full control over styling and behavior.",
          ),
        ),
      ),
    ),
  )
}

// Sample main app with toast container
def ToastDemoApp: FluxusNode = {
  div(
    cls := "min-h-screen bg-base-100",

    // Main content
    ToastTest,

    // Toast containers at different positions
    ToastContainer("top-left"),
    ToastContainer("top-center"),
    ToastContainer("top-right"),
    ToastContainer("bottom-left"),
    ToastContainer("bottom-center"),
    ToastContainer("bottom-right"),
  )
}
