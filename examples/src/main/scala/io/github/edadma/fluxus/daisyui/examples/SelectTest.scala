package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def SelectTest: FluxusNode = {
  // State for selected values
  val (selectedValue1, setSelectedValue1, _) = useState[Option[String]](None)
  val (selectedValue2, setSelectedValue2, _) = useState[Option[String]](Some("2")) // Pre-selected

  // Sample options
  val options = List(
    SelectOption(value = "1", label = "Option 1"),
    SelectOption(value = "2", label = "Option 2"),
    SelectOption(value = "3", label = "Option 3"),
    SelectOption(value = "4", label = "Option 4"),
    SelectOption(value = "5", label = "Option 5"),
    SelectOption(value = "6", label = "A really long option name to test overflow"),
  )

  // More options for testing scrolling
  val manyOptions = (1 to 20).map(i => SelectOption(i.toString, s"Option $i")).toList

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Select Component Demo",
      ),

      // Basic usage
      Card <> CardProps(
        title = Some("Basic Usage"),
        className = "mb-8",
        children = div(
          div(
            cls := "mb-4",
            label(
              cls := "block text-sm font-medium mb-2",
              "Basic Select",
            ),
            Select <> SelectProps(
              options = options,
              value = selectedValue1,
              onChange = setSelectedValue1,
              placeholder = "Choose an option",
            ),
          ),
          div(
            cls := "text-sm mt-2",
            s"Selected value: ${selectedValue1.map(v => s"'${options.find(_.value == v).map(_.label).getOrElse(v)}'").getOrElse("Nothing selected")}",
          ),
        ),
      ),

      // With clear button
      Card <> CardProps(
        title = Some("With Clear Button"),
        className = "mb-8",
        children = div(
          div(
            cls := "mb-4",
            label(
              cls := "block text-sm font-medium mb-2",
              "Select with Clear Button",
            ),
            Select <> SelectProps(
              options = options,
              value = selectedValue2,
              onChange = setSelectedValue2,
              placeholder = "Choose an option",
              allowClear = true,
            ),
          ),
          div(
            cls := "text-sm mt-2",
            s"Selected value: ${selectedValue2.map(v => s"'${options.find(_.value == v).map(_.label).getOrElse(v)}'").getOrElse("Nothing selected")}",
          ),
          div(
            cls := "mt-4",
            Button <> ButtonProps(
              text = "Reset Selection",
              variant = "secondary",
              size = "sm",
              onClick = () => setSelectedValue2(None),
            ),
          ),
        ),
      ),

      // Variants showcase
      Card <> CardProps(
        title = Some("Select Variants"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",

          // Small size
          div(
            label(
              cls := "block text-sm font-medium mb-2",
              "Small Size",
            ),
            Select <> SelectProps(
              options = options,
              size = "sm",
              placeholder = "Small select",
            ),
          ),

          // Large size
          div(
            label(
              cls := "block text-sm font-medium mb-2",
              "Large Size",
            ),
            Select <> SelectProps(
              options = options,
              size = "lg",
              placeholder = "Large select",
            ),
          ),

          // Custom width
          div(
            label(
              cls := "block text-sm font-medium mb-2",
              "Custom Width",
            ),
            Select <> SelectProps(
              options = options,
              width = Some("300px"),
              placeholder = "Fixed width 300px",
            ),
          ),

          // Borderless
          div(
            label(
              cls := "block text-sm font-medium mb-2",
              "Borderless",
            ),
            Select <> SelectProps(
              options = options,
              bordered = false,
              placeholder = "Borderless select",
            ),
          ),

          // Loading state
          div(
            label(
              cls := "block text-sm font-medium mb-2",
              "Loading State",
            ),
            Select <> SelectProps(
              options = options,
              loading = true,
              placeholder = "Loading...",
            ),
          ),

          // Disabled
          div(
            label(
              cls := "block text-sm font-medium mb-2",
              "Disabled",
            ),
            Select <> SelectProps(
              options = options,
              disabled = true,
              placeholder = "Disabled select",
            ),
          ),
        ),
      ),

      // Scrollable options
      Card <> CardProps(
        title = Some("Scrollable Options"),
        children = div(
          label(
            cls := "block text-sm font-medium mb-2",
            "Many Options (Scrollable)",
          ),
          Select <> SelectProps(
            options = manyOptions,
            placeholder = "Select from many options",
            allowClear = true,
          ),
        ),
      ),
    ),
  )
}
