package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom
import scala.scalajs.js.timers.setTimeout

def ToastTest: FluxusNode = {
  val (customTitle, setCustomTitle, _)       = useState("Custom Notification")
  val (customMessage, setCustomMessage, _)   = useState("This is a custom notification message.")
  val (customDuration, setCustomDuration, _) = useState("4500")
  val (customPosition, setCustomPosition, _) = useState("top-end")

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      // No need to include the ToastContainer anymore!

      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Notification System Demo",
      ),

      // Basic notification examples
      Card <> CardProps(
        title = Some("Basic Notifications"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-4",
          Button <> ButtonProps(
            text = "Success Notification",
            variant = "success",
            onClick = () => notification.success("Operation completed successfully!"),
          ),
          Button <> ButtonProps(
            text = "Error Notification",
            variant = "error",
            onClick = () => notification.error("An error occurred during the operation."),
          ),
          Button <> ButtonProps(
            text = "Info Notification",
            variant = "info",
            onClick = () => notification.info("This is an informational message."),
          ),
          Button <> ButtonProps(
            text = "Warning Notification",
            variant = "warning",
            onClick = () => notification.warning("Warning: This action might cause issues."),
          ),
          Button <> ButtonProps(
            text = "Neutral Notification",
            variant = "neutral",
            onClick = () => notification.neutral("A neutral notification"),
          ),
        ),
      ),

      // Advanced notification examples
      Card <> CardProps(
        title = Some("Advanced Notifications"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",

          // Notifications with titles
          div(
            h3(cls := "text-lg font-medium mb-2", "With Titles"),
            div(
              cls := "flex flex-wrap gap-2",
              Button <> ButtonProps(
                text = "Success with Title",
                variant = "success",
                onClick = () =>
                  notification.success(
                    "Files were successfully uploaded",
                    ToastOptions(title = Some("Upload Complete")),
                  ),
              ),
              Button <> ButtonProps(
                text = "Error with Title",
                variant = "error",
                onClick = () =>
                  notification.error(
                    "Failed to save changes to the document",
                    ToastOptions(title = Some("Save Failed")),
                  ),
              ),
            ),
          ),

          // Notifications with different durations
          div(
            h3(cls := "text-lg font-medium mb-2", "Different Durations"),
            div(
              cls := "flex flex-wrap gap-2",
              Button <> ButtonProps(
                text = "Short (2s)",
                variant = "primary",
                onClick = () =>
                  notification.info(
                    "This notification will disappear quickly",
                    ToastOptions(duration = Some(2000)),
                  ),
              ),
              Button <> ButtonProps(
                text = "Long (8s)",
                variant = "primary",
                onClick = () =>
                  notification.info(
                    "This notification will stay longer",
                    ToastOptions(duration = Some(8000)),
                  ),
              ),
              Button <> ButtonProps(
                text = "Persistent",
                variant = "primary",
                onClick = () => {
                  val id = notification.info(
                    "This notification won't auto-dismiss. Click the X to close it.",
                    ToastOptions(duration = Some(0)), // 0 means no auto-dismiss
                  )
                  // We could store this ID to close it programmatically later
                  println(s"Created persistent toast with ID: $id")
                },
              ),
            ),
          ),

          // Notifications at different positions
          div(
            h3(cls := "text-lg font-medium mb-2", "Different Positions"),
            div(
              cls := "grid grid-cols-3 gap-2",

              // Top row
              Button <> ButtonProps(
                text = "Top-Start",
                variant = "secondary",
                onClick = () =>
                  notification.info(
                    "Top-start position",
                    ToastOptions(position = Some("top-start")),
                  ),
              ),
              Button <> ButtonProps(
                text = "Top-Center",
                variant = "secondary",
                onClick = () =>
                  notification.info(
                    "Top-center position",
                    ToastOptions(position = Some("top-center")),
                  ),
              ),
              Button <> ButtonProps(
                text = "Top-End",
                variant = "secondary",
                onClick = () =>
                  notification.info(
                    "Top-end position",
                    ToastOptions(position = Some("top-end")),
                  ),
              ),

              // Middle
              div(),
              Button <> ButtonProps(
                text = "Middle",
                variant = "secondary",
                onClick = () =>
                  notification.info(
                    "Middle position",
                    ToastOptions(position = Some("middle")),
                  ),
              ),
              div(),

              // Bottom row
              Button <> ButtonProps(
                text = "Bottom-Start",
                variant = "secondary",
                onClick = () =>
                  notification.info(
                    "Bottom-start position",
                    ToastOptions(position = Some("bottom-start")),
                  ),
              ),
              Button <> ButtonProps(
                text = "Bottom-Center",
                variant = "secondary",
                onClick = () =>
                  notification.info(
                    "Bottom-center position",
                    ToastOptions(position = Some("bottom-center")),
                  ),
              ),
              Button <> ButtonProps(
                text = "Bottom-End",
                variant = "secondary",
                onClick = () =>
                  notification.info(
                    "Bottom-end position",
                    ToastOptions(position = Some("bottom-end")),
                  ),
              ),
            ),
          ),

          // Notifications with actions
          div(
            h3(cls := "text-lg font-medium mb-2", "With Actions"),
            div(
              cls := "flex flex-wrap gap-2",
              Button <> ButtonProps(
                text = "With Action Buttons",
                variant = "primary",
                onClick = () =>
                  notification.info(
                    "A new version is available for download.",
                    ToastOptions(
                      title = Some("Update Available"),
                      duration = Some(0), // Stay until user interacts
                      actions = Some(
                        div(
                          cls := "flex gap-2",
                          Button <> ButtonProps(
                            text = "Update",
                            variant = "primary",
                            size = "sm",
                            onClick = () => {
                              // Simulate update process
                              notification.success("Update started")
                              // Close all existing notifications
//                              notification.closeAll()
                            },
                          ),
                          Button <> ButtonProps(
                            text = "Later",
                            variant = "ghost",
                            size = "sm",
                            onClick = () => notification.closeAll(),
                          ),
                        ),
                      ),
                    ),
                  ),
              ),
            ),
          ),
        ),
      ),

      // Demo for stacking notifications
      Card <> CardProps(
        title = Some("Notification Stacking Demo"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",
          div(
            Button <> ButtonProps(
              text = "Stack 5 Notifications",
              variant = "primary",
              onClick = () => {
                // Create 5 notifications with slight delay between them
                for (i <- 1 to 5) {
                  setTimeout(i * 300) {
                    notification.info(
                      s"This is notification #$i in the stack",
                      ToastOptions(position = Some("bottom-end")),
                    )
                  }
                }
              },
            ),
          ),
          div(
            Button <> ButtonProps(
              text = "Mixed Position Stack",
              variant = "primary",
              onClick = () => {
                // Create notifications at different positions
                val positions = List(
                  "top-start",
                  "top-end",
                  "bottom-start",
                  "bottom-end",
                )

                positions.zipWithIndex.foreach { case (position, i) =>
                  setTimeout(i * 300) {
                    notification.info(
                      s"Notification at $position",
                      ToastOptions(position = Some(position)),
                    )
                  }
                }
              },
            ),
          ),
          div(
            Button <> ButtonProps(
              text = "Clear All Notifications",
              variant = "error",
              onClick = () => notification.closeAll(),
            ),
          ),
        ),
      ),

      // Custom notification creator
      Card <> CardProps(
        title = Some("Custom Notification Creator"),
        children = div(
          cls := "space-y-4",
          div(
            cls := "form-control",
            label(cls := "label", span(cls := "label-text", "Title")),
            Input <> InputProps(
              placeholder = Some("Enter notification title"),
              value = Some(customTitle),
              onChange = setCustomTitle,
            ),
          ),
          div(
            cls := "form-control",
            label(cls := "label", span(cls := "label-text", "Message")),
            Input <> InputProps(
              typ = "textarea",
              placeholder = Some("Enter notification message"),
              value = Some(customMessage),
              onChange = setCustomMessage,
              rows = Some(2),
            ),
          ),
          div(
            cls := "grid grid-cols-1 md:grid-cols-2 gap-4",
            div(
              cls := "form-control",
              label(cls := "label", span(cls := "label-text", "Duration (ms)")),
              Input <> InputProps(
                typ = "number",
                placeholder = Some("Enter duration in milliseconds"),
                value = Some(customDuration),
                onChange = setCustomDuration,
              ),
            ),
            div(
              cls := "form-control",
              label(cls := "label", span(cls := "label-text", "Position")),
              Select <> SelectProps(
                options = List(
                  SelectOption("top-start", "Top-Start"),
                  SelectOption("top-center", "Top-Center"),
                  SelectOption("top-end", "Top-End"),
                  SelectOption("middle", "Middle"),
                  SelectOption("bottom-start", "Bottom-Start"),
                  SelectOption("bottom-center", "Bottom-Center"),
                  SelectOption("bottom-end", "Bottom-End"),
                ),
                value = Some(customPosition),
                onChange = pos => setCustomPosition(pos.getOrElse("top-end")),
              ),
            ),
          ),
          div(
            cls := "flex flex-wrap gap-2 mt-4",
            Button <> ButtonProps(
              text = "Show as Success",
              variant = "success",
              onClick = () =>
                notification.success(
                  customMessage,
                  ToastOptions(
                    title = Some(customTitle),
                    duration = Some(customDuration.toIntOption.getOrElse(4500)),
                    position = Some(customPosition),
                  ),
                ),
            ),
            Button <> ButtonProps(
              text = "Show as Error",
              variant = "error",
              onClick = () =>
                notification.error(
                  customMessage,
                  ToastOptions(
                    title = Some(customTitle),
                    duration = Some(customDuration.toIntOption.getOrElse(4500)),
                    position = Some(customPosition),
                  ),
                ),
            ),
            Button <> ButtonProps(
              text = "Show as Info",
              variant = "info",
              onClick = () =>
                notification.info(
                  customMessage,
                  ToastOptions(
                    title = Some(customTitle),
                    duration = Some(customDuration.toIntOption.getOrElse(4500)),
                    position = Some(customPosition),
                  ),
                ),
            ),
            Button <> ButtonProps(
              text = "Show as Warning",
              variant = "warning",
              onClick = () =>
                notification.warning(
                  customMessage,
                  ToastOptions(
                    title = Some(customTitle),
                    duration = Some(customDuration.toIntOption.getOrElse(4500)),
                    position = Some(customPosition),
                  ),
                ),
            ),
          ),
        ),
      ),
    ),
  )
}
