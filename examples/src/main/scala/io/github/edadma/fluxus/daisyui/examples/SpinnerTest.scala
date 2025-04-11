package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def SpinnerTest: FluxusNode = {
  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Spinner Component Demo",
      ),

      // Spinner Types Section
      Card <> CardProps(
        title = Some("Spinner Variants"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-8 items-center justify-center",

          // Default spinner
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(),
            p(cls := "mt-4 text-sm", "Default (spinner)"),
          ),

          // Dots spinner
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              variant = "spinner-dots",
            ),
            p(cls := "mt-4 text-sm", "Dots"),
          ),

          // Ring spinner
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              variant = "spinner-ring",
            ),
            p(cls := "mt-4 text-sm", "Ring"),
          ),

          // Ball spinner
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              variant = "spinner-ball",
            ),
            p(cls := "mt-4 text-sm", "Ball"),
          ),

          // Infinity spinner
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              variant = "spinner-infinity",
            ),
            p(cls := "mt-4 text-sm", "Infinity"),
          ),
        ),
      ),

      // Sizes Section
      Card <> CardProps(
        title = Some("Spinner Sizes"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-8 items-end justify-center",

          // Extra small
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              size = "xs",
            ),
            p(cls := "mt-4 text-sm", "XS"),
          ),

          // Small
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              size = "sm",
            ),
            p(cls := "mt-4 text-sm", "SM"),
          ),

          // Medium (default)
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              size = "md",
            ),
            p(cls := "mt-4 text-sm", "MD"),
          ),

          // Large
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              size = "lg",
            ),
            p(cls := "mt-4 text-sm", "LG"),
          ),
        ),
      ),

      // Colors Section
      Card <> CardProps(
        title = Some("Spinner Colors"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-8 items-center justify-center",

          // Primary
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              color = "text-primary",
            ),
            p(cls := "mt-4 text-sm", "Primary"),
          ),

          // Secondary
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              color = "text-secondary",
            ),
            p(cls := "mt-4 text-sm", "Secondary"),
          ),

          // Accent
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              color = "text-accent",
            ),
            p(cls := "mt-4 text-sm", "Accent"),
          ),

          // Info
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              color = "text-info",
            ),
            p(cls := "mt-4 text-sm", "Info"),
          ),

          // Success
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              color = "text-success",
            ),
            p(cls := "mt-4 text-sm", "Success"),
          ),

          // Warning
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              color = "text-warning",
            ),
            p(cls := "mt-4 text-sm", "Warning"),
          ),

          // Error
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              color = "text-error",
            ),
            p(cls := "mt-4 text-sm", "Error"),
          ),
        ),
      ),

      // Animation Section
      Card <> CardProps(
        title = Some("Animation Options"),
        className = "mb-8",
        children = div(
          cls := "flex flex-wrap gap-8 items-center justify-center",

          // Default animation
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(),
            p(cls := "mt-4 text-sm", "Default Animation"),
          ),

          // Pulse animation
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              animation = "animate-pulse",
            ),
            p(cls := "mt-4 text-sm", "Pulse"),
          ),

          // Ping animation
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              animation = "animate-ping",
            ),
            p(cls := "mt-4 text-sm", "Ping"),
          ),

          // Bounce animation
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              animation = "animate-bounce",
            ),
            p(cls := "mt-4 text-sm", "Bounce"),
          ),

          // No animation
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              noAnimation = true,
            ),
            p(cls := "mt-4 text-sm", "No Animation"),
          ),
        ),
      ),

      // Combined Examples Section
      Card <> CardProps(
        title = Some("Combined Examples"),
        children = div(
          cls := "flex flex-wrap gap-8 items-center justify-center",

          // Large primary dot spinner
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              variant = "spinner-dots",
              size = "lg",
              color = "text-primary",
            ),
            p(cls := "mt-4 text-sm", "Large Primary Dots"),
          ),

          // Small secondary ring spinner
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              variant = "spinner-ring",
              size = "sm",
              color = "text-secondary",
            ),
            p(cls := "mt-4 text-sm", "Small Secondary Ring"),
          ),

          // Medium error infinity spinner with pulse animation
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              variant = "spinner-infinity",
              color = "text-error",
              animation = "animate-pulse",
            ),
            p(cls := "mt-4 text-sm", "Error Infinity with Pulse"),
          ),

          // XS success ball spinner with bounce animation
          div(
            cls := "flex flex-col items-center",
            Spinner <> SpinnerProps(
              variant = "spinner-ball",
              size = "xs",
              color = "text-success",
              animation = "animate-bounce",
            ),
            p(cls := "mt-4 text-sm", "XS Success Ball with Bounce"),
          ),
        ),
      ),
    ),
  )
}
