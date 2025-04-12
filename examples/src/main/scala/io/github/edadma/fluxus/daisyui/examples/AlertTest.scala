package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def AlertTest: FluxusNode = {
  // State for showing or hiding the dismissible alerts
  val (showDismissibleInfo, setShowDismissibleInfo, _) = useState(true)
  val (showCustomAlert, setShowCustomAlert, _)         = useState(true)

  // Custom bell icon for notification example
  def BellIcon: FluxusNode = {
    svg(
      xmlns          := "http://www.w3.org/2000/svg",
      cls            := "stroke-current shrink-0 h-6 w-6",
      fill           := "none",
      viewBox        := "0 0 24 24",
      strokeWidth    := "2",
      strokeLinecap  := "round",
      strokeLinejoin := "round",
      path(d := "M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"),
      path(d := "M13.73 21a2 2 0 0 1-3.46 0"),
    )
  }

  // Example action buttons
  def ActionButtons(variant: String): FluxusNode = {
    div(
      cls := "flex gap-2",
      Button <> ButtonProps(
        text = "Cancel",
        variant = "ghost",
        size = "sm",
      ),
      Button <> ButtonProps(
        text = "Accept",
        variant = variant,
        size = "sm",
      ),
    )
  }

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Alert Component Demo",
      ),

      // Basic Alerts Section
      Card <> CardProps(
        title = Some("Basic Alerts"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",

          // Info alert
          Alert <> AlertProps(
            variant = "info",
            title = Some("Information"),
            message = Some("This is an informational alert to notify you about something."),
          ),

          // Success alert
          Alert <> AlertProps(
            variant = "success",
            title = Some("Success"),
            message = Some("Your action was completed successfully!"),
          ),

          // Warning alert
          Alert <> AlertProps(
            variant = "warning",
            title = Some("Warning"),
            message = Some("Please be careful with this action."),
          ),

          // Error alert
          Alert <> AlertProps(
            variant = "error",
            title = Some("Error"),
            message = Some("Something went wrong. Please try again."),
          ),

          // Neutral alert
          Alert <> AlertProps(
            variant = "neutral",
            title = Some("Neutral"),
            message = Some("This is a neutral alert with general information."),
          ),
        ),
      ),

      // Alerts with Different Layouts Section
      Card <> CardProps(
        title = Some("Alert Variations"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",

          // Message only (no title)
          Alert <> AlertProps(
            variant = "info",
            message = Some("This alert has a message without a title."),
          ),

          // Without icon
          Alert <> AlertProps(
            variant = "success",
            title = Some("No Icon"),
            message = Some("This alert doesn't show an icon."),
            showIcon = false,
          ),

          // Custom icon
          Alert <> AlertProps(
            variant = "warning",
            title = Some("Custom Icon"),
            message = Some("This alert uses a custom icon."),
            icon = Some(BellIcon),
          ),

          // Compact alert
          Alert <> AlertProps(
            variant = "error",
            title = Some("Compact Alert"),
            message = Some("This is a compact alert with less padding."),
            compact = true,
          ),
        ),
      ),

      // Dismissible Alerts Section
      Card <> CardProps(
        title = Some("Dismissible Alerts"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",

          // Dismissible info alert
          if (showDismissibleInfo) {
            Alert <> AlertProps(
              variant = "info",
              title = Some("Dismissible Alert"),
              message = Some("Click the X button to dismiss this alert."),
              onClose = Some(() => setShowDismissibleInfo(false)),
            )
          } else {
            div(
              Button <> ButtonProps(
                text = "Show Alert Again",
                variant = "primary",
                onClick = () => setShowDismissibleInfo(true),
              ),
            )
          },

          // Custom dismissible alert with more complex content
          if (showCustomAlert) {
            Alert <> AlertProps(
              variant = "warning",
              onClose = Some(() => setShowCustomAlert(false)),
              children = Some(
                div(
                  h3(cls := "font-bold", "Your subscription is about to expire!"),
                  div(
                    cls := "text-sm mt-1",
                    "Your premium plan will end in 3 days. Renew now to avoid service interruption.",
                  ),
                  div(
                    cls := "mt-3",
                    Button <> ButtonProps(
                      text = "Renew Subscription",
                      variant = "warning",
                      size = "sm",
                    ),
                  ),
                ),
              ),
            )
          } else {
            div(
              Button <> ButtonProps(
                text = "Show Subscription Alert",
                variant = "warning",
                onClick = () => setShowCustomAlert(true),
              ),
            )
          },
        ),
      ),

      // Alerts with Actions Section
      Card <> CardProps(
        title = Some("Alerts with Actions"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",

          // Info alert with actions
          Alert <> AlertProps(
            variant = "info",
            title = Some("Update Available"),
            message = Some("A new software update is available. Would you like to update now?"),
            actions = Some(ActionButtons("info")),
          ),

          // Success alert with actions
          Alert <> AlertProps(
            variant = "success",
            title = Some("Invitation Sent"),
            message = Some("Your invitation has been sent successfully. Would you like to send more?"),
            actions = Some(ActionButtons("success")),
          ),

          // Warning alert with actions
          Alert <> AlertProps(
            variant = "warning",
            title = Some("Delete Account?"),
            message = Some("You're about to delete your account. This action cannot be undone."),
            actions = Some(ActionButtons("warning")),
          ),

          // Error alert with actions and close button
          Alert <> AlertProps(
            variant = "error",
            title = Some("Connection Lost"),
            message = Some("The connection to the server was lost. Would you like to reconnect?"),
            actions = Some(ActionButtons("error")),
            onClose = Some(() => dom.window.alert("Alert dismissed")),
          ),
        ),
      ),

      // Practical Usage Examples Section
      Card <> CardProps(
        title = Some("Practical Examples"),
        children = div(
          cls := "space-y-6",

          // Form validation example
          div(
            h3(cls := "text-lg font-medium mb-2", "Form Validation"),
            div(
              cls := "border border-base-300 rounded-box p-4",
              div(
                cls := "form-control mb-4",
                label(cls := "label", span(cls := "label-text", "Email")),
                input(
                  typ         := "email",
                  cls         := "input input-bordered w-full",
                  placeholder := "Your email",
                ),
              ),
              Alert <> AlertProps(
                variant = "error",
                compact = true,
                showIcon = true,
                message = Some("Please enter a valid email address"),
              ),
              div(
                cls := "mt-4",
                Button <> ButtonProps(
                  text = "Submit",
                  variant = "primary",
                ),
              ),
            ),
          ),

          // Cookie consent example
          div(
            h3(cls := "text-lg font-medium mb-2", "Cookie Consent Banner"),
            Alert <> AlertProps(
              variant = "neutral",
              children = Some(
                div(
                  div(cls := "font-medium", "We use cookies"),
                  p(
                    cls := "text-sm mt-1",
                    "This website uses cookies to ensure you get the best experience on our website.",
                  ),
                  div(
                    cls := "flex gap-2 mt-3",
                    Button <> ButtonProps(
                      text = "Accept All",
                      variant = "primary",
                      size = "sm",
                    ),
                    Button <> ButtonProps(
                      text = "Reject Non-Essential",
                      variant = "ghost",
                      size = "sm",
                    ),
                    Button <> ButtonProps(
                      text = "Cookie Settings",
                      variant = "link",
                      size = "sm",
                    ),
                  ),
                ),
              ),
            ),
          ),

          // Maintenance notice example
          div(
            h3(cls := "text-lg font-medium mb-2", "Maintenance Notice"),
            Alert <> AlertProps(
              variant = "warning",
              icon = Some(
                svg(
                  xmlns       := "http://www.w3.org/2000/svg",
                  cls         := "stroke-current shrink-0 h-6 w-6",
                  fill        := "none",
                  viewBox     := "0 0 24 24",
                  strokeWidth := "2",
                  path(
                    d := "M19.428 15.428a2 2 0 00-1.022-.547l-2.387-.477a6 6 0 00-3.86.517l-.318.158a6 6 0 01-3.86.517L6.05 15.21a2 2 0 00-1.806.547M8 4h8l-1 1v5.172a2 2 0 00.586 1.414l5 5c1.26 1.26.367 3.414-1.415 3.414H4.828c-1.782 0-2.674-2.154-1.414-3.414l5-5A2 2 0 009 10.172V5L8 4z",
                  ),
                ),
              ),
              children = Some(
                div(
                  h3(cls := "font-bold", "Scheduled Maintenance"),
                  div(
                    cls := "text-sm",
                    p(
                      "Our system will be unavailable on Sunday, April 21 from 2:00 AM to 4:00 AM UTC for scheduled maintenance.",
                    ),
                    p(cls := "mt-2", "We apologize for any inconvenience this may cause."),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),
    ),
  )
}
