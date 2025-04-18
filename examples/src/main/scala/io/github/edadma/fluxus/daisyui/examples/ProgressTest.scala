package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def ProgressTest: FluxusNode = {
  // State for demonstrating interactive progress
  val (progressValue, setProgressValue, _)               = useState(25.0)
  val (indeterminateEnabled, setIndeterminateEnabled, _) = useState(false)

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Progress Component Demo",
      ),

      // Basic Progress Bars Section
      Card <> CardProps(
        title = Some("Basic Progress Bars"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Standard progress bar
          div(
            h3(cls := "text-lg font-medium mb-2", "Standard Progress"),
            Progress <> ProgressProps(
              value = Some(40.0),
              max = 100.0,
            ),
          ),

          // Progress with value display
          div(
            h3(cls := "text-lg font-medium mb-2", "With Value Display"),
            Progress <> ProgressProps(
              value = Some(65.0),
              max = 100.0,
              showValue = true,
            ),
          ),

          // Indeterminate progress
          div(
            h3(cls := "text-lg font-medium mb-2", "Indeterminate Progress"),
            Progress <> ProgressProps(
              indeterminate = true,
            ),
          ),
        ),
      ),

      // Color Variants Section
      Card <> CardProps(
        title = Some("Color Variants"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",

          // Primary variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Primary"),
            Progress <> ProgressProps(
              value = Some(45.0),
              variant = "primary",
            ),
          ),

          // Secondary variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Secondary"),
            Progress <> ProgressProps(
              value = Some(45.0),
              variant = "secondary",
            ),
          ),

          // Accent variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Accent"),
            Progress <> ProgressProps(
              value = Some(45.0),
              variant = "accent",
            ),
          ),

          // Info variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Info"),
            Progress <> ProgressProps(
              value = Some(45.0),
              variant = "info",
            ),
          ),

          // Success variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Success"),
            Progress <> ProgressProps(
              value = Some(45.0),
              variant = "success",
            ),
          ),

          // Warning variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Warning"),
            Progress <> ProgressProps(
              value = Some(45.0),
              variant = "warning",
            ),
          ),

          // Error variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Error"),
            Progress <> ProgressProps(
              value = Some(45.0),
              variant = "error",
            ),
          ),
        ),
      ),

      // Size Variants Section
      Card <> CardProps(
        title = Some("Size Variants"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Extra small
          div(
            h3(cls := "text-lg font-medium mb-2", "Extra Small"),
            Progress <> ProgressProps(
              value = Some(60.0),
              size = "xs",
              variant = "primary",
            ),
          ),

          // Small
          div(
            h3(cls := "text-lg font-medium mb-2", "Small"),
            Progress <> ProgressProps(
              value = Some(60.0),
              size = "sm",
              variant = "primary",
            ),
          ),

          // Medium (default)
          div(
            h3(cls := "text-lg font-medium mb-2", "Medium (Default)"),
            Progress <> ProgressProps(
              value = Some(60.0),
              variant = "primary",
            ),
          ),

          // Large
          div(
            h3(cls := "text-lg font-medium mb-2", "Large"),
            Progress <> ProgressProps(
              value = Some(60.0),
              size = "lg",
              variant = "primary",
            ),
          ),
        ),
      ),

      // Radial Progress Section
      Card <> CardProps(
        title = Some("Radial Progress"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-1 md:grid-cols-3 gap-6",

          // Basic radial progress
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "Basic"),
            RadialProgress <> RadialProgressProps(
              value = 70.0,
            ),
          ),

          // With primary color
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "Primary Color"),
            RadialProgress <> RadialProgressProps(
              value = 70.0,
              variant = "primary",
            ),
          ),

          // With custom content
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "Custom Content"),
            RadialProgress <> RadialProgressProps(
              value = 70.0,
              variant = "secondary",
              children = Some(
                div(
                  cls := "text-secondary-content flex flex-col items-center",
                  svg(
                    xmlns   := "http://www.w3.org/2000/svg",
                    cls     := "h-8 w-8",
                    fill    := "none",
                    viewBox := "0 0 24 24",
                    stroke  := "currentColor",
                    path(
                      strokeLinecap  := "round",
                      strokeLinejoin := "round",
                      strokeWidth    := "2",
                      d              := "M13 10V3L4 14h7v7l9-11h-7z",
                    ),
                  ),
                  span(cls := "text-xs mt-1", "Energy"),
                ),
              ),
            ),
          ),
        ),
      ),

      // Radial Sizes Section
      Card <> CardProps(
        title = Some("Radial Progress Sizes"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-1 md:grid-cols-4 gap-6 justify-items-center",

          // Extra small
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "XS"),
            RadialProgress <> RadialProgressProps(
              value = 75.0,
              size = "xs",
              variant = "primary",
            ),
          ),

          // Small
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "SM"),
            RadialProgress <> RadialProgressProps(
              value = 75.0,
              size = "sm",
              variant = "secondary",
            ),
          ),

          // Medium (default)
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "MD"),
            RadialProgress <> RadialProgressProps(
              value = 75.0,
              variant = "accent",
            ),
          ),

          // Large
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "LG"),
            RadialProgress <> RadialProgressProps(
              value = 75.0,
              size = "lg",
              variant = "success",
            ),
          ),
        ),
      ),

      // Custom Thickness
      Card <> CardProps(
        title = Some("Custom Thickness"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-1 md:grid-cols-3 gap-6 justify-items-center",

          // Thin
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "Thin"),
            RadialProgress <> RadialProgressProps(
              value = 65.0,
              variant = "primary",
              thickness = "border-2",
            ),
          ),

          // Medium (Default)
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "Medium"),
            RadialProgress <> RadialProgressProps(
              value = 65.0,
              variant = "secondary",
              // Default thickness
            ),
          ),

          // Thick
          div(
            cls := "flex flex-col items-center",
            h3(cls := "text-lg font-medium mb-4 text-center", "Thick"),
            RadialProgress <> RadialProgressProps(
              value = 65.0,
              variant = "accent",
              thickness = "border-8",
            ),
          ),
        ),
      ),

      // Interactive Progress Demo
      Card <> CardProps(
        title = Some("Interactive Progress Demo"),
        children = div(
          cls := "space-y-6",

          // Interactive progress control
          div(
            cls := "flex flex-wrap gap-4 items-center mb-4",
            div(
              cls := "flex-none",
              Button <> ButtonProps(
                text = "-10%",
                variant = "primary",
                onClick = () => setProgressValue(Math.max(0, progressValue - 10)),
              ),
            ),
            div(
              cls := "flex-grow",
              Progress <> ProgressProps(
                value = Some(progressValue),
                showValue = true,
                variant = "primary",
              ),
            ),
            div(
              cls := "flex-none",
              Button <> ButtonProps(
                text = "+10%",
                variant = "primary",
                onClick = () => setProgressValue(Math.min(100, progressValue + 10)),
              ),
            ),
          ),

          // Toggle for indeterminate
          div(
            cls := "flex items-center gap-4",
            label(
              cls := "cursor-pointer label",
              span(cls := "label-text", "Indeterminate"),
              input(
                typ      := "checkbox",
                cls      := "toggle toggle-primary",
                checked  := indeterminateEnabled,
                onChange := (() => setIndeterminateEnabled(!indeterminateEnabled)),
              ),
            ),
          ),

          // Indeterminate or value-based progress
          div(
            cls := "mt-4",
            Progress <> ProgressProps(
              value = if (!indeterminateEnabled) Some(progressValue) else None,
              indeterminate = indeterminateEnabled,
              variant = "primary",
            ),
          ),

          // Interactive radial progress
          div(
            cls := "flex justify-center mt-8",
            RadialProgress <> RadialProgressProps(
              value = progressValue,
              variant = "primary",
            ),
          ),
        ),
      ),
    ),
  )
}
