package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def CheckboxTest: FluxusNode = {
  // State for controlled checkboxes
  val (isChecked1, setIsChecked1, _)           = useState(false)
  val (isChecked2, setIsChecked2, _)           = useState(true)
  val (isIndeterminate, setIsIndeterminate, _) = useState(true)

  // State for form checkbox group
  val (selectedItems, setSelectedItems, updateSelectedItems) = useState(Set[String]())

  // Toggle items in the set
  def handleItemToggle(item: String)(checked: Boolean): Unit = {
    if (checked) {
      updateSelectedItems(_ + item)
    } else {
      updateSelectedItems(_ - item)
    }
  }

  // Toggle indeterminate demo
  def toggleIndeterminate(): Unit = {
    setIsIndeterminate(!isIndeterminate)
  }

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Checkbox Component Demo",
      ),

      // Basic Checkboxes Section
      Card <> CardProps(
        title = Some("Basic Checkboxes"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Simple Checkbox
          div(
            h3(cls := "text-lg font-medium mb-2", "Simple Checkbox"),
            div(
              Checkbox <> CheckboxProps(
                label = Some("Check me"),
                onChange = Some(checked => println(s"Checkbox changed: $checked")),
              ),
            ),
          ),

          // Controlled Checkbox
          div(
            h3(cls := "text-lg font-medium mb-2", "Controlled Checkbox"),
            div(
              cls := "flex items-center gap-4",
              Checkbox <> CheckboxProps(
                checked = isChecked1,
                label = Some("Toggle me"),
                onChange = Some(checked => setIsChecked1(checked)),
              ),
              Button <> ButtonProps(
                text = if (isChecked1) "Uncheck" else "Check",
                variant = "primary",
                size = "sm",
                onClick = () => setIsChecked1(!isChecked1),
              ),
            ),
            div(
              cls := "mt-2 text-sm",
              s"State: ${if (isChecked1) "Checked" else "Unchecked"}",
            ),
          ),

          // Indeterminate State
          div(
            h3(cls := "text-lg font-medium mb-2", "Indeterminate State"),
            div(
              cls := "flex items-center gap-4",
              Checkbox <> CheckboxProps(
                checked = isChecked2,
                label = Some("Parent checkbox"),
                indeterminate = isIndeterminate,
                onChange = Some(checked => {
                  setIsChecked2(checked)
                  if (checked) {
                    setIsIndeterminate(false)
                  }
                }),
              ),
              Button <> ButtonProps(
                text = "Toggle Indeterminate",
                variant = "secondary",
                size = "sm",
                onClick = () => toggleIndeterminate(),
              ),
            ),
            div(
              cls := "ml-6 mt-2 space-y-1",
              Checkbox <> CheckboxProps(
                label = Some("Child option 1"),
                disabled = isChecked2 && !isIndeterminate,
              ),
              Checkbox <> CheckboxProps(
                label = Some("Child option 2"),
                disabled = isChecked2 && !isIndeterminate,
              ),
            ),
          ),
        ),
      ),

      // Checkbox Variants Section
      Card <> CardProps(
        title = Some("Checkbox Variants"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-1 md:grid-cols-2 gap-6",

          // Primary variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Primary"),
            Checkbox <> CheckboxProps(
              label = Some("Primary checkbox"),
              variant = "primary",
              defaultChecked = Some(true),
            ),
          ),

          // Secondary variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Secondary"),
            Checkbox <> CheckboxProps(
              label = Some("Secondary checkbox"),
              variant = "secondary",
              defaultChecked = Some(true),
            ),
          ),

          // Accent variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Accent"),
            Checkbox <> CheckboxProps(
              label = Some("Accent checkbox"),
              variant = "accent",
              defaultChecked = Some(true),
            ),
          ),

          // Success variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Success"),
            Checkbox <> CheckboxProps(
              label = Some("Success checkbox"),
              variant = "success",
              defaultChecked = Some(true),
            ),
          ),

          // Warning variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Warning"),
            Checkbox <> CheckboxProps(
              label = Some("Warning checkbox"),
              variant = "warning",
              defaultChecked = Some(true),
            ),
          ),

          // Error variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Error"),
            Checkbox <> CheckboxProps(
              label = Some("Error checkbox"),
              variant = "error",
              defaultChecked = Some(true),
            ),
          ),
        ),
      ),

      // Checkbox Sizes Section
      Card <> CardProps(
        title = Some("Checkbox Sizes"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Extra Small
          div(
            h3(cls := "text-lg font-medium mb-2", "Extra Small"),
            Checkbox <> CheckboxProps(
              label = Some("Extra small checkbox"),
              size = "xs",
              defaultChecked = Some(true),
            ),
          ),

          // Small
          div(
            h3(cls := "text-lg font-medium mb-2", "Small"),
            Checkbox <> CheckboxProps(
              label = Some("Small checkbox"),
              size = "sm",
              defaultChecked = Some(true),
            ),
          ),

          // Medium (default)
          div(
            h3(cls := "text-lg font-medium mb-2", "Medium (Default)"),
            Checkbox <> CheckboxProps(
              label = Some("Medium checkbox"),
              defaultChecked = Some(true),
            ),
          ),

          // Large
          div(
            h3(cls := "text-lg font-medium mb-2", "Large"),
            Checkbox <> CheckboxProps(
              label = Some("Large checkbox"),
              size = "lg",
              defaultChecked = Some(true),
            ),
          ),
        ),
      ),

      // Label Positions Section
      Card <> CardProps(
        title = Some("Label Positions"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Right label (default)
          div(
            h3(cls := "text-lg font-medium mb-2", "Right Label (Default)"),
            Checkbox <> CheckboxProps(
              label = Some("Label on the right"),
              labelPosition = "right",
              defaultChecked = Some(true),
            ),
          ),

          // Left label
          div(
            h3(cls := "text-lg font-medium mb-2", "Left Label"),
            Checkbox <> CheckboxProps(
              label = Some("Label on the left"),
              labelPosition = "left",
              defaultChecked = Some(true),
            ),
          ),
        ),
      ),

      // Disabled State Section
      Card <> CardProps(
        title = Some("Disabled State"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Disabled unchecked
          div(
            h3(cls := "text-lg font-medium mb-2", "Disabled Unchecked"),
            Checkbox <> CheckboxProps(
              label = Some("Disabled checkbox"),
              disabled = true,
            ),
          ),

          // Disabled checked
          div(
            h3(cls := "text-lg font-medium mb-2", "Disabled Checked"),
            Checkbox <> CheckboxProps(
              label = Some("Disabled checked checkbox"),
              disabled = true,
              defaultChecked = Some(true),
            ),
          ),
        ),
      ),

      // Practical Example Section
      Card <> CardProps(
        title = Some("Practical Example: Item Selection"),
        children = div(
          h3(cls := "text-lg font-medium mb-3", "Select Toppings"),
          div(
            cls := "p-4 border border-base-300 rounded-box",
            div(
              cls := "space-y-2",

              // Item options
              Checkbox <> CheckboxProps(
                label = Some("Pepperoni"),
                checked = selectedItems.contains("pepperoni"),
                onChange = Some(handleItemToggle("pepperoni")),
                variant = "primary",
              ),
              Checkbox <> CheckboxProps(
                label = Some("Mushrooms"),
                checked = selectedItems.contains("mushrooms"),
                onChange = Some(handleItemToggle("mushrooms")),
                variant = "primary",
              ),
              Checkbox <> CheckboxProps(
                label = Some("Onions"),
                checked = selectedItems.contains("onions"),
                onChange = Some(handleItemToggle("onions")),
                variant = "primary",
              ),
              Checkbox <> CheckboxProps(
                label = Some("Olives"),
                checked = selectedItems.contains("olives"),
                onChange = Some(handleItemToggle("olives")),
                variant = "primary",
              ),
              Checkbox <> CheckboxProps(
                label = Some("Extra Cheese"),
                checked = selectedItems.contains("cheese"),
                onChange = Some(handleItemToggle("cheese")),
                variant = "primary",
              ),
            ),
            div(
              cls := "mt-4 p-3 bg-base-200 rounded",
              h4(cls := "font-medium mb-1", "Selected Toppings:"),
              if (selectedItems.isEmpty) {
                p("No toppings selected")
              } else {
                ul(
                  cls := "list-disc list-inside",
                  selectedItems.toList.sorted.map(item =>
                    li(key := item, item.capitalize),
                  ),
                )
              },
            ),
            div(
              cls := "flex justify-end mt-4",
              Button <> ButtonProps(
                text = "Apply Selection",
                variant = "primary",
                disabled = selectedItems.isEmpty,
              ),
            ),
          ),
        ),
      ),
    ),
  )
}
