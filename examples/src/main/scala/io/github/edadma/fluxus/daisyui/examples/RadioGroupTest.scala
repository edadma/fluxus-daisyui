package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def RadioGroupTest: FluxusNode = {
  // State for basic radio buttons
  val (selectedFruit, setSelectedFruit, _) = useState[Option[String]](None)

  // State for color theme selection
  val (selectedTheme, setSelectedTheme, _) = useState[Option[String]](Some("light"))

  // State for size selection
  val (selectedSize, setSelectedSize, _) = useState[Option[String]](None)

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Radio Button Component Demo"
      ),

      // Basic Radio Group Example
      Card <> CardProps(
        title = Some("Basic Radio Group"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "This example shows a vertical radio group with default styling.",
          ),

          RadioGroup <> RadioGroupProps(
            name = "fruit",
            value = selectedFruit,
            onChange = (value) => setSelectedFruit(Some(value)),
            options = List(
              RadioOption("apple", "Apple"),
              RadioOption("banana", "Banana"),
              RadioOption("cherry", "Cherry"),
              RadioOption("durian", "Durian (disabled)", disabled = true)
            )
          ),

          div(
            cls := "mt-4 p-2 bg-base-200 rounded",
            s"Selected fruit: ${selectedFruit.getOrElse("None")}"
          )
        )
      ),

      // Color Variants
      Card <> CardProps(
        title = Some("Color Variants"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Radio buttons support DaisyUI's color variants.",
          ),

          div(
            cls := "grid grid-cols-1 md:grid-cols-2 gap-6",

            // Primary variant
            div(
              h3(cls := "text-lg font-medium mb-2", "Primary"),
              RadioButton <> RadioButtonProps(
                name = "color-demo-primary",
                value = "primary",
                label = Some("Primary Radio"),
                variant = "primary",
                checked = true
              )
            ),

            // Secondary variant
            div(
              h3(cls := "text-lg font-medium mb-2", "Secondary"),
              RadioButton <> RadioButtonProps(
                name = "color-demo-secondary",
                value = "secondary",
                label = Some("Secondary Radio"),
                variant = "secondary",
                checked = true
              )
            ),

            // Accent variant
            div(
              h3(cls := "text-lg font-medium mb-2", "Accent"),
              RadioButton <> RadioButtonProps(
                name = "color-demo-accent",
                value = "accent",
                label = Some("Accent Radio"),
                variant = "accent",
                checked = true
              )
            ),

            // Info variant
            div(
              h3(cls := "text-lg font-medium mb-2", "Info"),
              RadioButton <> RadioButtonProps(
                name = "color-demo-info",
                value = "info",
                label = Some("Info Radio"),
                variant = "info",
                checked = true
              )
            ),

            // Success variant
            div(
              h3(cls := "text-lg font-medium mb-2", "Success"),
              RadioButton <> RadioButtonProps(
                name = "color-demo-success",
                value = "success",
                label = Some("Success Radio"),
                variant = "success",
                checked = true
              )
            ),

            // Warning variant
            div(
              h3(cls := "text-lg font-medium mb-2", "Warning"),
              RadioButton <> RadioButtonProps(
                name = "color-demo-warning",
                value = "warning",
                label = Some("Warning Radio"),
                variant = "warning",
                checked = true
              )
            ),

            // Error variant
            div(
              h3(cls := "text-lg font-medium mb-2", "Error"),
              RadioButton <> RadioButtonProps(
                name = "color-demo-error",
                value = "error",
                label = Some("Error Radio"),
                variant = "error",
                checked = true
              )
            )
          )
        )
      ),

      // Size Options
      Card <> CardProps(
        title = Some("Size Options"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Radio buttons come in multiple sizes.",
          ),

          RadioGroup <> RadioGroupProps(
            name = "sizes",
            value = selectedSize,
            onChange = (value) => setSelectedSize(Some(value)),
            options = List(
              RadioOption("xs", "Extra Small"),
              RadioOption("sm", "Small"),
              RadioOption("md", "Medium (Default)"),
              RadioOption("lg", "Large")
            ),
            variant = "primary",
            size = "md"
          ),

          div(
            cls := "mt-6 flex items-end gap-6",

            // xs size
            RadioButton <> RadioButtonProps(
              name = "size-demo",
              value = "xs",
              label = Some("XS"),
              variant = "primary",
              size = "xs",
              checked = true
            ),

            // sm size
            RadioButton <> RadioButtonProps(
              name = "size-demo",
              value = "sm",
              label = Some("SM"),
              variant = "primary",
              size = "sm",
              checked = true
            ),

            // md size (default)
            RadioButton <> RadioButtonProps(
              name = "size-demo",
              value = "md",
              label = Some("MD"),
              variant = "primary",
              size = "md",
              checked = true
            ),

            // lg size
            RadioButton <> RadioButtonProps(
              name = "size-demo",
              value = "lg",
              label = Some("LG"),
              variant = "primary",
              size = "lg",
              checked = true
            )
          )
        )
      ),

      // Horizontal Layout
      Card <> CardProps(
        title = Some("Horizontal Layout"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Radio groups can be displayed horizontally.",
          ),

          RadioGroup <> RadioGroupProps(
            name = "theme",
            value = selectedTheme,
            onChange = (value) => setSelectedTheme(Some(value)),
            options = List(
              RadioOption("light", "Light"),
              RadioOption("dark", "Dark"),
              RadioOption("cupcake", "Cupcake"),
              RadioOption("forest", "Forest"),
              RadioOption("aqua", "Aqua")
            ),
            variant = "primary",
            direction = "horizontal"
          ),

          div(
            cls := "mt-4 p-2 bg-base-200 rounded",
            s"Selected theme: ${selectedTheme.getOrElse("None")}"
          )
        )
      ),

      // Practical Example
      Card <> CardProps(
        title = Some("Practical Example: User Preferences"),
        children = div(
          h3(cls := "text-lg font-medium mb-3", "Notification Settings"),

          div(
            cls := "p-4 border border-base-300 rounded-lg",

            div(
              cls := "space-y-3",

              h4(cls := "font-medium", "Receive notifications by:"),

              RadioGroup <> RadioGroupProps(
                name = "notifications",
                value = Some("email"),
                options = List(
                  RadioOption("email", "Email", false),
                  RadioOption("sms", "SMS", false),
                  RadioOption("push", "Push Notifications", false),
                  RadioOption("none", "Do not notify me", false)
                ),
                variant = "secondary",
                size = "sm"
              ),

              div(
                cls := "pt-4 mt-4 border-t border-base-300",
                h4(cls := "font-medium mb-3", "Notification frequency:"),

                RadioGroup <> RadioGroupProps(
                  name = "frequency",
                  value = Some("daily"),
                  options = List(
                    RadioOption("realtime", "Immediately", false),
                    RadioOption("daily", "Daily digest", false),
                    RadioOption("weekly", "Weekly digest", false),
                    RadioOption("never", "Never", false)
                  ),
                  variant = "secondary",
                  size = "sm"
                )
              )
            ),

            div(
              cls := "flex justify-end mt-6",
              Button <> ButtonProps(
                text = "Save Preferences",
                variant = "primary",
                size = "sm"
              )
            )
          )
        )
      )
    )
  )
}