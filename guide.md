# fluxus-daisyui Component Documentation

This document provides comprehensive documentation for all components in the fluxus-daisyui library.

## Table of Contents

- [Layout Components](#layout-components)
    - [Container](#container)
    - [Grid](#grid)
- [Input Components](#input-components)
    - [Button](#button)
    - [Input](#input)
    - [Label](#label)
    - [Select](#select)
- [Data Display Components](#data-display-components)
    - [Avatar](#avatar)
    - [AvatarGroup](#avatargroup)
    - [Badge](#badge)
    - [Card](#card)
    - [Pagination](#pagination)
    - [Table](#table)
    - [TableWithPagination](#tablewithpagination)
    - [Tooltip](#tooltip)
- [Navigation Components](#navigation-components)
    - [Sidebar](#sidebar)
    - [Tabs](#tabs)
- [Feedback Components](#feedback-components)
    - [Alert](#alert)
    - [Modal](#modal)
    - [Notification System](#notification-system)
    - [Spinner](#spinner)

## Layout Components

### Container

A wrapper component that provides consistent padding and container styling.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `children` | `FluxusNode` | _required_ | Child elements to render inside the container |
| `padding` | `Boolean` | `true` | Whether to apply padding (px-4) |
| `center` | `Boolean` | `true` | Centers content using container and mx-auto |
| `className` | `String` | `""` | Additional CSS classes |

#### Example

```scala
Container <> ContainerProps(
  padding = true,
  center = true,
  className = "min-h-screen py-8",
  children = div(
    h1("Hello World")
  )
)
```

### Grid

The `Grid` component provides a responsive, flexible grid layout powered by Tailwind CSS. It supports advanced features like auto-fit, masonry layouts, animations, debug overlays, and item alignment.

#### Props

All props map to Tailwind class names and layout features.

- `children: Seq[FluxusNode]` — grid items.
- `columns: String` — default column class (e.g., `"grid-cols-1"`).
- `mdColumns / lgColumns / xlColumns: Option[String]` — responsive column classes.
- `rows: String` — row configuration (e.g., `"grid-rows-3"`).
- `mdRows / lgRows / xlRows: Option[String]` — responsive row configurations.
- `gap: String` — overall gap class (e.g., `"gap-4"`).
- `colGap / rowGap: Option[String]` — independent horizontal and vertical gap classes.
- `autoFlow: String` — grid flow class (e.g., `"grid-flow-row"`).
- `justifyItems / alignItems / justifyContent / alignContent: String` — alignment classes.
- `autoFit / autoFill: Boolean` — enable responsive column layout using CSS `minmax(...)`.
- `minItemWidth: String` — used with `autoFit`/`autoFill` (e.g., `"200px"`).
- `equalHeight: Boolean` — forces uniform row heights.
- `masonry: Boolean` — enables column-based masonry layout.
- `masonryColumns: String` — Tailwind column classes (e.g., `"columns-1 md:columns-2"`).
- `bordered / rounded: Boolean` — adds border and/or rounded corners.
- `bordered3d: Boolean` — adds a 3D border style.
- `padding: String` — Tailwind padding classes.
- `bgClass: String` — Tailwind background classes.
- `animate: String` — animation classes (e.g., `"animate-in fade-in"`).
- `staggered: Boolean` — applies staggered delay animation to `GridItem`s.
- `debug: Boolean` — enables grid debugging UI (visible cell outlines, etc.).
- `className: String` — additional custom classes.
- `onItemClick: Option[(dom.Event, Option[String]) => Unit]` — optional click handler.

When `autoFit` or `autoFill` is true, an inline `grid-template-columns: repeat(auto-fit|auto-fill, minmax(...))` is used.

When `masonry` is true, the grid becomes a column layout with `break-inside-avoid` applied to children.

---

### GridItem

`GridItem` is a styled child of `Grid`. It supports responsive spans, alignment, animations, and interaction.

#### Props

- `children: FluxusNode` — content.
- `colSpan / rowSpan / colStart / colEnd / rowStart / rowEnd: Option[String]` — grid positioning.
- `mdColSpan / lgColSpan / xlColSpan: Option[String]` — responsive span overrides.
- `justifySelf / alignSelf: Option[String]` — self-alignment classes.
- `bgClass / shadow / padding / margin: Option[String]` — Tailwind styling.
- `bordered / rounded / glass: Boolean` — extra visuals.
- `hoverElevate / hoverHighlight: Boolean` — predefined hover effects.
- `animate / animateDelay: Option[String]` — Tailwind animation classes.
- `interactive: Boolean` — enables pointer and scale interaction.
- `key: Option[String]` — used for tracking animation and interaction.
- `onClick: Option[dom.Event => Unit]` — click handler.
- `disabled: Boolean` — disables interactivity.
- `className: String` — additional classes.

---

### AutoGrid

A simplified wrapper around `Grid` for quick layouts with auto-fit behavior.

#### Props (AutoGridProps)

- `children: Seq[FluxusNode]` — content.
- `itemWidth: String` — value for `minItemWidth` (e.g., `"250px"`).
- `gap / padding / bgClass: String` — layout and styling classes.
- `bordered / rounded / equalHeight: Boolean` — layout features.
- `className: String` — additional styling.

Internally, `AutoGrid` maps directly to `Grid` with `autoFit = true`.

---

### DashboardGrid

A grid container that uses named CSS `grid-template-areas` for dashboards.

#### Props (DashboardGridProps)

- `children: Seq[FluxusNode]` — must include `GridArea` children with matching names.
- `areas: List[String]` — row-wise strings of space-separated area names (e.g., `"header header header"`).
- `mdAreas / lgAreas: Option[List[String]]` — responsive versions.
- `gap / padding / bgClass: String` — layout and style.
- `bordered / rounded: Boolean` — enable visual styling.
- `className: String` — additional custom classes.

---

### GridArea

Defines a named region inside a `DashboardGrid`.

#### Props (GridAreaProps)

- `name: String` — matches a template area in the parent `DashboardGrid`.
- `children: Seq[FluxusNode]` — content.
- `bgClass / padding / margin / shadow: Option[String]` — styling.
- `bordered / rounded / glass: Boolean` — visual styling.
- `className: String` — custom classes.

A `GridArea` is positioned by setting `style := s"grid-area: $name"` and inherits styling like a `GridItem`.

---

## Input Components

### Label Component

A versatile form label component with support for various styling options, positions, and helper text.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `text` | `String` | `""` | Text content of the label |
| `children` | `Option[FluxusNode]` | `None` | Alternative to text for complex content |
| `altText` | `Option[String]` | `None` | Additional text (hint, error message) |
| `htmlFor` | `Option[String]` | `None` | Associates label with form control via HTML for attribute |
| `required` | `Boolean` | `false` | Whether to show required indicator (asterisk) |
| `requiredIndicator` | `String` | `"*"` | Character to use for required indicator |
| `position` | `String` | `"default"` | Positioning style: default, top, left, right, floating |
| `swap` | `Boolean` | `false` | Whether to swap label and alt text positions |
| `size` | `String` | `"md"` | Label size: xs, sm, md, lg |
| `variant` | `String` | `""` | Color variant: primary, secondary, accent, etc. |
| `altVariant` | `String` | `""` | Color variant for alt text (e.g., error) |
| `textClass` | `String` | `""` | Additional CSS class for main label text |
| `altTextClass` | `String` | `""` | Additional CSS class for alt text |
| `className` | `String` | `""` | Additional CSS classes for the label container |

#### Examples

##### Basic Label

```scala
Label <> LabelProps(
  text = "Username",
  htmlFor = Some("username-input")
)
```

##### Required Field with Helper Text

```scala
Label <> LabelProps(
  text = "Email Address",
  htmlFor = Some("email-input"),
  required = true,
  altText = Some("We'll never share your email with anyone")
)
```

##### Label with Error Message

```scala
Label <> LabelProps(
  text = "Password",
  htmlFor = Some("password-input"),
  altText = Some("Password must be at least 8 characters"),
  altVariant = "error"
)
```

##### Floating Label (for custom inputs)

```scala
div(
  cls := "relative",
  input(
    id := "floating-input",
    cls := "input input-bordered w-full pt-5",
    placeholder := " "
  ),
  Label <> LabelProps(
    text = "Floating Label",
    htmlFor = Some("floating-input"),
    position = "floating",
    className = "absolute text-sm duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] left-4"
  )
)
```

##### Form Control Integration

```scala
div(
  cls := "form-control",
  Label <> LabelProps(
    text = "Birth Date",
    position = "top",
    required = true
  ),
  input(
    typ := "date",
    cls := "input input-bordered w-full"
  )
)
```

##### Label with Custom Styling

```scala
Label <> LabelProps(
  text = "Priority",
  variant = "primary",
  size = "lg",
  textClass = "font-bold"
)
```

#### Usage Tips

- **Form Integration**: Use the `htmlFor` prop to associate the label with a form control by its ID
- **Validation States**: Use `altVariant = "error"` to display error messages in red
- **Floating Labels**: To create floating labels, set `position = "floating"` and position the label absolutely
- **Responsive Design**: Labels work well with DaisyUI's form-control class for consistent spacing and layout

### Button

A versatile button component with comprehensive styling options, including all DaisyUI button variants.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `text` | `String` | `""` | Button text content |
| `children` | `Option[FluxusNode]` | `None` | Alternative to text for complex content |
| `variant` | `String` | `"primary"` | Button variant: primary, secondary, accent, info, success, warning, error, ghost, link, neutral |
| `size` | `String` | `"md"` | Button size: lg, md, sm, xs |
| `shape` | `Option[String]` | `None` | Button shape: circle, square |
| `soft` | `Boolean` | `false` | Whether to use soft style (lower-opacity background) |
| `dash` | `Boolean` | `false` | Whether to use dashed border style |
| `outline` | `Boolean` | `false` | Whether to use outline style |
| `wide` | `Boolean` | `false` | Whether to use wide style |
| `glass` | `Boolean` | `false` | Whether to use glass effect |
| `block` | `Boolean` | `false` | Whether to make button full width |
| `active` | `Boolean` | `false` | Whether to force active state |
| `focusVisible` | `Boolean` | `false` | Whether to show focus styles only with keyboard navigation |
| `loading` | `Boolean` | `false` | Whether to show loading state |
| `disabled` | `Boolean` | `false` | Whether button is disabled |
| `noAnimation` | `Boolean` | `false` | Whether to disable animations |
| `onClick` | `() => Unit` | `() => ()` | Click handler function |
| `startIcon` | `Option[FluxusNode]` | `None` | Icon to display at start of button |
| `endIcon` | `Option[FluxusNode]` | `None` | Icon to display at end of button |
| `className` | `String` | `""` | Additional CSS classes |

#### Examples

##### Basic Variants

```scala
Button <> ButtonProps(text = "Default")
Button <> ButtonProps(text = "Primary", variant = "primary")
Button <> ButtonProps(text = "Secondary", variant = "secondary")
Button <> ButtonProps(text = "Accent", variant = "accent")
Button <> ButtonProps(text = "Info", variant = "info")
Button <> ButtonProps(text = "Success", variant = "success")
Button <> ButtonProps(text = "Warning", variant = "warning")
Button <> ButtonProps(text = "Error", variant = "error")
```

##### Soft and Dash Variants

```scala
// Soft buttons (lower-opacity background)
Button <> ButtonProps(text = "Soft Primary", variant = "primary", soft = true)
Button <> ButtonProps(text = "Soft Secondary", variant = "secondary", soft = true)

// Dash buttons (dashed border)
Button <> ButtonProps(text = "Dash Primary", variant = "primary", dash = true)
Button <> ButtonProps(text = "Dash Secondary", variant = "secondary", dash = true)
```

##### Sizes

```scala
Button <> ButtonProps(text = "Large", size = "lg", variant = "primary")
Button <> ButtonProps(text = "Normal", variant = "primary") // Default is "md"
Button <> ButtonProps(text = "Small", size = "sm", variant = "primary")
Button <> ButtonProps(text = "Tiny", size = "xs", variant = "primary")
```

##### Styles

```scala
// Outline
Button <> ButtonProps(text = "Outline", variant = "primary", outline = true)

// Wide (medium width)
Button <> ButtonProps(text = "Wide", variant = "primary", wide = true)

// Block (full width)
Button <> ButtonProps(text = "Block", variant = "primary", block = true)

// Glass effect
Button <> ButtonProps(text = "Glass", glass = true)

// Shapes
Button <> ButtonProps(variant = "primary", shape = Some("circle"), children = Some(PlusIcon))
Button <> ButtonProps(variant = "primary", shape = Some("square"), children = Some(PlusIcon))
```

##### States

```scala
// Loading
Button <> ButtonProps(text = "Loading", variant = "primary", loading = true)

// Disabled
Button <> ButtonProps(text = "Disabled", variant = "primary", disabled = true)

// Active
Button <> ButtonProps(text = "Active", variant = "primary", active = true)

// No animation
Button <> ButtonProps(text = "No Animation", variant = "primary", noAnimation = true)
```

##### With Icons

```scala
// Start icon
Button <> ButtonProps(
  text = "Add Item",
  variant = "primary",
  startIcon = Some(PlusIcon)
)

// End icon
Button <> ButtonProps(
  text = "Next Page",
  variant = "primary",
  endIcon = Some(ArrowIcon)
)

// Icon-only button
Button <> ButtonProps(
  variant = "primary",
  shape = Some("circle"),
  children = Some(PlusIcon)
)
```

#### Combining Styles

```scala
// Combining multiple style modifiers
Button <> ButtonProps(
  text = "Soft Outline",
  variant = "primary",
  soft = true,
  outline = true
)

Button <> ButtonProps(
  text = "Wide Dash",
  variant = "secondary",
  dash = true,
  wide = true
)
```

### RadioGroup

A component for managing a group of radio buttons with various styling options and layouts.

#### RadioOption Model

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `value` | `String` | _required_ | The option value (used internally) |
| `label` | `String` | _required_ | The display text shown to users |
| `disabled` | `Boolean` | `false` | Whether the option is disabled |

#### RadioButtonProps

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `value` | `String` | _required_ | Value for this radio button |
| `name` | `String` | _required_ | Group name (for HTML form submission) |
| `label` | `Option[String]` | `None` | Optional label text |
| `checked` | `Boolean` | `false` | Whether this radio is checked |
| `disabled` | `Boolean` | `false` | Whether this radio is disabled |
| `variant` | `String` | `""` | Color variant: primary, secondary, accent, info, success, warning, error |
| `size` | `String` | `"md"` | Size: xs, sm, md, lg |
| `onChange` | `Option[String => Unit]` | `None` | Change handler for the radio value |
| `className` | `String` | `""` | Additional CSS classes for the radio input |
| `labelClassName` | `String` | `""` | Additional CSS classes for the label |

#### RadioGroupProps

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `name` | `String` | _required_ | Group name for all radio buttons |
| `value` | `Option[String]` | `None` | Currently selected value |
| `onChange` | `String => Unit` | `_ => ()` | Change handler |
| `options` | `List[RadioOption]` | `List()` | List of options to display |
| `variant` | `String` | `""` | Color variant: primary, secondary, accent, info, success, warning, error |
| `size` | `String` | `"md"` | Size: xs, sm, md, lg |
| `direction` | `String` | `"vertical"` | Layout direction: vertical, horizontal |
| `className` | `String` | `""` | Additional CSS classes |

#### Examples

##### Basic Radio Group

```scala
val (selectedFruit, setSelectedFruit, _) = useState[Option[String]](None)

RadioGroup <> RadioGroupProps(
  name = "fruit",
  value = selectedFruit,
  onChange = (value) => setSelectedFruit(Some(value)),
  options = List(
    RadioOption("apple", "Apple"),
    RadioOption("banana", "Banana"),
    RadioOption("cherry", "Cherry"),
    RadioOption("durian", "Durian", disabled = true)
  )
)

div(
  cls := "mt-4 p-2 bg-base-200 rounded",
  s"Selected fruit: ${selectedFruit.getOrElse("None")}"
)
```

##### Color Variants

```scala
// Primary radio buttons
RadioGroup <> RadioGroupProps(
  name = "color",
  value = selectedColor,
  onChange = setSelectedColor,
  options = colorOptions,
  variant = "primary"
)

// Success radio buttons
RadioGroup <> RadioGroupProps(
  name = "status",
  value = selectedStatus,
  onChange = setSelectedStatus,
  options = statusOptions,
  variant = "success"
)
```

##### Horizontal Layout

```scala
RadioGroup <> RadioGroupProps(
  name = "theme",
  value = selectedTheme,
  onChange = (value) => setSelectedTheme(Some(value)),
  options = List(
    RadioOption("light", "Light"),
    RadioOption("dark", "Dark"),
    RadioOption("system", "System")
  ),
  direction = "horizontal"
)
```

##### Sizes

```scala
// Small radio buttons
RadioGroup <> RadioGroupProps(
  name = "size-demo",
  value = selectedSize,
  onChange = setSelectedSize,
  options = sizeOptions,
  size = "sm"
)

// Large radio buttons
RadioGroup <> RadioGroupProps(
  name = "size-demo-large",
  value = selectedSize,
  onChange = setSelectedSize,
  options = sizeOptions,
  size = "lg"
)
```

##### Individual Radio Buttons

If you need more control over placement or styling, you can use `RadioButton` directly:

```scala
div(
  cls := "space-y-2",
  RadioButton <> RadioButtonProps(
    name = "agreement",
    value = "agree",
    label = Some("I agree to the terms and conditions"),
    checked = isAgreed,
    onChange = Some((_) => setIsAgreed(true)),
    variant = "primary"
  ),
  RadioButton <> RadioButtonProps(
    name = "agreement",
    value = "disagree",
    label = Some("I do not agree"),
    checked = !isAgreed,
    onChange = Some((_) => setIsAgreed(false)),
    variant = "primary"
  )
)
```

#### Usage Tips

- **Form Integration**: Use the same `name` for all radio buttons in a group for proper HTML form submission
- **Controlled vs Uncontrolled**:
  - For controlled behavior, provide `value` and `onChange` props
  - Update the `value` state in your component when `onChange` is called
- **Horizontal vs Vertical**: Use the `direction` prop to control layout orientation
- **Validation**: Combine with the `Label` component for form validation messages
- **Styling**: Use the `variant` prop to match your application's color scheme
- **Accessibility**: RadioGroup adds proper ARIA roles for improved screen reader support

### Select

A custom select dropdown component with styling options and clear functionality.

#### SelectOption Model

| Prop | Type | Description |
|------|------|-------------|
| `value` | `String` | The option's value (used internally) |
| `label` | `String` | The display text shown to users |

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `options` | `List[SelectOption]` | `List()` | List of options to display |
| `value` | `Option[String]` | `None` | Currently selected value (None for no selection) |
| `onChange` | `Option[String] => Unit` | `_ => ()` | Callback when selection changes |
| `placeholder` | `String` | `"Select an option"` | Text to display when nothing is selected |
| `allowClear` | `Boolean` | `false` | Whether to show clear button |
| `variant` | `String` | `""` | Color variant: primary, secondary, accent, info, success, warning, error |
| `width` | `Option[String]` | `None` | Custom width (CSS value) |
| `bordered` | `Boolean` | `true` | Whether to show border |
| `size` | `String` | `"md"` | Component size: sm, md, lg |
| `disabled` | `Boolean` | `false` | Whether control is disabled |
| `loading` | `Boolean` | `false` | Whether to show loading state |
| `className` | `String` | `""` | Additional CSS classes |

#### Example

```scala
val (selectedValue, setSelectedValue, _) = useState[Option[String]](None)

val options = List(
  SelectOption(value = "1", label = "Option 1"),
  SelectOption(value = "2", label = "Option 2"),
  SelectOption(value = "3", label = "Option 3")
)

Select <> SelectProps(
  options = options,
  value = selectedValue,
  onChange = setSelectedValue,
  placeholder = "Choose an option",
  allowClear = true,
  variant = "primary",
  size = "md",
  loading = false
)
```

### Input

A versatile input component that supports various input types, styling options, and features like icons, helper text, and validation.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `value` | `Option[String]` | `None` | Controlled input value |
| `defaultValue` | `Option[String]` | `None` | Default value (uncontrolled) |
| `onChange` | `String => Unit` | `_ => ()` | Value change handler |
| `typ` | `String` | `"text"` | Input type: text, number, password, textarea, etc. |
| `name` | `Option[String]` | `None` | Input name attribute |
| `placeholder` | `Option[String]` | `None` | Placeholder text |
| `disabled` | `Boolean` | `false` | Whether input is disabled |
| `readOnly` | `Boolean` | `false` | Whether input is read-only |
| `required` | `Boolean` | `false` | Whether input is required |
| `autoFocus` | `Boolean` | `false` | Whether input should auto-focus |
| `maxLength` | `Option[Int]` | `None` | Maximum character length |
| `min` | `Option[String]` | `None` | Minimum value (for number inputs) |
| `max` | `Option[String]` | `None` | Maximum value (for number inputs) |
| `step` | `Option[String]` | `None` | Step value (for number inputs) |
| `size` | `String` | `"md"` | Input size: xs, sm, md, lg |
| `variant` | `String` | `""` | Input variant: primary, secondary, accent, info, success, warning, error |
| `bordered` | `Boolean` | `true` | Whether to show input border |
| `ghost` | `Boolean` | `false` | Whether to use ghost style |
| `fullWidth` | `Boolean` | `true` | Whether input should take full width |
| `loading` | `Boolean` | `false` | Whether to show loading state |
| `error` | `Option[String]` | `None` | Error message to display |
| `label` | `Option[String]` | `None` | Input label text |
| `labelFloat` | `Boolean` | `false` | Whether to use floating label style |
| `helperText` | `Option[String]` | `None` | Helper text displayed below input |
| `leadingIcon` | `Option[FluxusNode]` | `None` | Icon displayed at start of input |
| `trailingIcon` | `Option[FluxusNode]` | `None` | Icon displayed at end of input |
| `prefix` | `Option[FluxusNode]` | `None` | Content displayed at start (inside input) |
| `suffix` | `Option[FluxusNode]` | `None` | Content displayed at end (inside input) |
| `showPasswordToggle` | `Boolean` | `false` | Whether to show password visibility toggle |
| `showClearButton` | `Boolean` | `false` | Whether to show button to clear input |
| `showCharCount` | `Boolean` | `false` | Whether to show character count for maxLength |
| `className` | `String` | `""` | Additional CSS classes for form control |
| `inputClassName` | `String` | `""` | Additional CSS classes for input element |
| `labelClassName` | `String` | `""` | Additional CSS classes for label |
| `helperTextClassName` | `String` | `""` | Additional CSS classes for helper text |
| `rows` | `Option[Int]` | `None` | Number of rows for textarea |
| `resize` | `Boolean` | `true` | Whether textarea is resizable |
| `onFocus` | `Option[dom.FocusEvent => Unit]` | `None` | Focus event handler |
| `onBlur` | `Option[dom.FocusEvent => Unit]` | `None` | Blur event handler |
| `onKeyDown` | `Option[dom.KeyboardEvent => Unit]` | `None` | Key down event handler |
| `onKeyUp` | `Option[dom.KeyboardEvent => Unit]` | `None` | Key up event handler |
| `ariaLabel` | `Option[String]` | `None` | Accessibility label |
| `ariaDescribedBy` | `Option[String]` | `None` | ID of element that describes input |
| `ariaInvalid` | `Option[Boolean]` | `None` | Whether input is invalid for accessibility |

#### Examples

##### Basic Text Input

```scala
val (inputValue, setInputValue, _) = useState("")

Input <> InputProps(
  label = Some("Full Name"),
  placeholder = Some("Enter your full name"),
  value = Some(inputValue),
  onChange = setInputValue,
  helperText = Some("As it appears on your ID card")
)
```

##### Password Input with Toggle

```scala
val (password, setPassword, _) = useState("")

Input <> InputProps(
  label = Some("Password"),
  typ = "password",
  placeholder = Some("Enter your password"),
  value = Some(password),
  onChange = setPassword,
  showPasswordToggle = true
)
```

##### Input with Validation Error

```scala
Input <> InputProps(
  label = Some("Email Address"),
  typ = "email",
  placeholder = Some("your@email.com"),
  error = Some("Please enter a valid email address"),
  variant = "error"
)
```

##### Input with Icons

```scala
Input <> InputProps(
  placeholder = Some("Search..."),
  leadingIcon = Some(
    svg(
      xmlns := "http://www.w3.org/2000/svg",
      cls := "h-5 w-5 text-base-content/50",
      fill := "none",
      viewBox := "0 0 24 24",
      stroke := "currentColor",
      path(
        strokeLinecap := "round",
        strokeLinejoin := "round",
        strokeWidth := "2",
        d := "M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
      )
    )
  ),
  showClearButton = true
)
```

##### Textarea Input

```scala
val (notes, setNotes, _) = useState("")

Input <> InputProps(
  label = Some("Notes"),
  typ = "textarea",
  rows = Some(4),
  placeholder = Some("Enter additional information..."),
  value = Some(notes),
  onChange = setNotes,
  showCharCount = true,
  maxLength = Some(500)
)
```

##### Floating Label

```scala
Input <> InputProps(
  label = Some("Username"),
  placeholder = Some(" "), // Space required for proper floating
  labelFloat = true
)
```

##### Number Input with Min/Max/Step

```scala
Input <> InputProps(
  label = Some("Quantity"),
  typ = "number",
  min = Some("1"),
  max = Some("100"),
  step = Some("1"),
  defaultValue = Some("1")
)
```

#### Usage Tips

- **Input Types**: Set the `typ` prop to change the input type (e.g., "text", "number", "password", "textarea")
- **Validation**: Display validation errors using the `error` prop and "error" variant
- **Password Fields**: Use `showPasswordToggle = true` to add a visibility toggle button
- **Controlled vs Uncontrolled**:
    - For controlled inputs, provide both `value` and `onChange` props
    - For uncontrolled inputs, use `defaultValue` without `value`
- **Textarea**: Set `typ = "textarea"` to render a textarea element instead of an input
- **Character Count**: Enable `showCharCount = true` with `maxLength` to display a character counter
- **Accessibility**: Use `ariaLabel`, `ariaDescribedBy`, and other aria props for better accessibility

---

## Data Display Components

### Avatar

A component for displaying user avatars with various shapes, sizes, and states.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `src` | `Option[String]` | `None` | Image source URL |
| `alt` | `String` | `"avatar"` | Alt text for image |
| `text` | `Option[String]` | `None` | Text to display (e.g., initials) |
| `icon` | `Option[FluxusNode]` | `None` | Icon to display |
| `size` | `String` | `"md"` | Avatar size: xs, sm, md, lg |
| `shape` | `String` | `"rounded"` | Avatar shape: rounded, square, hexagon, triangle, squircle |
| `border` | `Boolean` | `false` | Whether to add border |
| `borderColor` | `String` | `""` | Border color class (e.g., border-primary) |
| `borderWidth` | `String` | `""` | Border width class (e.g., border-2) |
| `outline` | `Boolean` | `false` | Whether to add outline |
| `outlineColor` | `String` | `""` | Outline color class |
| `outlineOffset` | `String` | `""` | Outline offset class |
| `ring` | `Boolean` | `false` | Whether to add ring effect |
| `ringColor` | `String` | `""` | Ring color class |
| `ringOffset` | `String` | `""` | Ring offset class |
| `online` | `Boolean` | `false` | Show online status indicator |
| `offline` | `Boolean` | `false` | Show offline status indicator |
| `busy` | `Boolean` | `false` | Show busy status indicator |
| `away` | `Boolean` | `false` | Show away status indicator |
| `placeholder` | `Boolean` | `false` | Force placeholder styling |
| `bgClass` | `String` | `""` | Background CSS class |
| `textColorClass` | `String` | `""` | Text color CSS class |
| `className` | `String` | `""` | Additional CSS classes |

#### Example

```scala
// Image avatar
Avatar <> AvatarProps(
  src = Some("https://example.com/avatar.jpg"),
  alt = "User avatar",
  size = "md",
  shape = "rounded"
)

// Text avatar with status
Avatar <> AvatarProps(
  text = Some("JD"),
  bgClass = "bg-primary",
  textColorClass = "text-primary-content",
  size = "lg",
  online = true
)

// Icon avatar
Avatar <> AvatarProps(
  icon = Some(svg(
    // SVG icon content
  )),
  bgClass = "bg-secondary",
  size = "md",
  shape = "hexagon"
)
```

### AvatarGroup

A component for displaying multiple avatars grouped together with overlap.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `children` | `List[FluxusNode]` | _required_ | List of Avatar components |
| `size` | `String` | `"md"` | Size for all avatars: xs, sm, md, lg |
| `offset` | `String` | `"end"` | Alignment: start, center, end |
| `className` | `String` | `""` | Additional CSS classes |

#### Example

```scala
AvatarGroup <> AvatarGroupProps(
  offset = "center",
  children = List(
    Avatar <> AvatarProps(text = Some("A"), bgClass = "bg-primary"),
    Avatar <> AvatarProps(text = Some("B"), bgClass = "bg-secondary"),
    Avatar <> AvatarProps(text = Some("C"), bgClass = "bg-accent"),
    Avatar <> AvatarProps(text = Some("+5"))
  )
)
```

### Badge

A small tag/badge component for displaying status indicators, counts, or labels.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `text` | `String` | `""` | Badge text content |
| `children` | `Option[FluxusNode]` | `None` | Alternative to text for complex content |
| `variant` | `String` | `"primary"` | Badge style: primary, secondary, accent, info, success, warning, error, ghost, neutral |
| `size` | `String` | `"md"` | Badge size: xs, sm, md, lg |
| `outline` | `Boolean` | `false` | Whether to use outline style |
| `empty` | `Boolean` | `false` | Render an empty badge (useful for indicators) |
| `fullWidth` | `Boolean` | `false` | Make the badge full width |
| `href` | `Option[String]` | `None` | Make the badge a link |
| `target` | `Option[String]` | `None` | Target for link (_blank, _self, etc.) |
| `ariaLabel` | `Option[String]` | `None` | Accessibility label |
| `className` | `String` | `""` | Additional CSS classes |

#### Examples

##### Basic Badge Variants

```scala
// Primary badge
Badge <> BadgeProps(text = "Primary", variant = "primary")

// Secondary badge
Badge <> BadgeProps(text = "Secondary", variant = "secondary")

// Success badge
Badge <> BadgeProps(text = "Success", variant = "success")

// Warning badge
Badge <> BadgeProps(text = "Warning", variant = "warning")

// Error badge
Badge <> BadgeProps(text = "Error", variant = "error")

// Ghost badge
Badge <> BadgeProps(text = "Tag", variant = "ghost")
```

##### Badge Sizes

```scala
Badge <> BadgeProps(text = "Extra Small", size = "xs", variant = "primary")
Badge <> BadgeProps(text = "Small", size = "sm", variant = "primary")
Badge <> BadgeProps(text = "Medium", size = "md", variant = "primary")
Badge <> BadgeProps(text = "Large", size = "lg", variant = "primary")
```

##### Outline Style

```scala
Badge <> BadgeProps(text = "Outline", variant = "primary", outline = true)
```

##### Status Indicators

```scala
div(
  cls := "flex items-center gap-2",
  Badge <> BadgeProps(empty = true, variant = "success", className = "mr-1"),
  span("Online")
)
```

##### Badge as Link

```scala
Badge <> BadgeProps(
  text = "Documentation",
  variant = "primary",
  href = Some("https://example.com/docs"),
  target = Some("_blank")
)
```

##### Badge with Custom Content

```scala
Badge <> BadgeProps(
  variant = "info",
  children = Some(
    div(
      cls := "flex items-center gap-1",
      svg(
        xmlns := "http://www.w3.org/2000/svg",
        width := "12",
        height := "12",
        viewBox := "0 0 24 24",
        fill := "currentColor",
        path(d := "M12 2l3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z")
      ),
      span("New")
    )
  )
)
```

##### Count Badges

```scala
div(
  cls := "relative inline-flex",
  Button <> ButtonProps(text = "Notifications", variant = "primary"),
  div(
    cls := "absolute -top-2 -right-2",
    Badge <> BadgeProps(text = "99+", variant = "error", size = "sm")
  )
)
```

#### Usage Tips

- **Status Indicators**: Use empty badges with color variants to show status (e.g., online/offline)
- **Tag Groups**: Use ghost variant for tag groupings
- **Notification Counts**: Place small badges on corners of buttons or icons for notification counts
- **Category Labels**: Use badges to categorize items (e.g., post categories, product tags)
- **Accessibility**: Use `ariaLabel` prop for better screen reader support
- **Positioning**: Badges don't have positioning built in - wrap them in positioned containers when needed

### Card

A versatile card component for displaying content in a contained container.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `title` | `Option[String]` | `None` | Card title |
| `children` | `FluxusNode` | _required_ | Card content |
| `imageSrc` | `Option[String]` | `None` | Top image URL |
| `imageAlt` | `String` | `""` | Alt text for image |
| `variant` | `String` | `"normal"` | Card variant: normal, compact, side |
| `bordered` | `Boolean` | `true` | Whether to show border |
| `bgClass` | `String` | `"bg-base-200"` | Background CSS class |
| `textClass` | `String` | `""` | Text color CSS class |
| `shadowClass` | `String` | `"shadow"` | Shadow CSS class |
| `glass` | `Boolean` | `false` | Whether to use glass effect |
| `hover` | `Boolean` | `false` | Whether to add hover effects |
| `footer` | `Option[FluxusNode]` | `None` | Footer content |
| `actions` | `Option[FluxusNode]` | `None` | Action buttons in card body |
| `headerActions` | `Option[FluxusNode]` | `None` | Action buttons in header |
| `padding` | `String` | `"normal"` | Padding size: normal, compact, none |
| `className` | `String` | `""` | Additional CSS classes |

#### Example

```scala
Card <> CardProps(
  title = Some("Card Title"),
  imageSrc = Some("https://example.com/image.jpg"),
  imageAlt = "Sample image",
  variant = "normal",
  bordered = true,
  bgClass = "bg-base-200",
  hover = true,
  actions = Some(
    div(
      Button <> ButtonProps(text = "Action")
    )
  ),
  children = div(
    p("Card content goes here")
  ),
  footer = Some(
    p("Card footer")
  )
)
```

### Pagination

A component for page navigation in paginated interfaces.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `currentPage` | `Int` | `1` | Current active page |
| `totalPages` | `Int` | `1` | Total number of pages |
| `onPageChange` | `Int => Unit` | `_ => ()` | Page change handler |
| `size` | `String` | `"md"` | Button size: sm, md, lg |
| `showFirstLast` | `Boolean` | `true` | Show first/last page buttons |
| `showPageNumbers` | `Boolean` | `true` | Show numbered page buttons |
| `maxDisplayedPages` | `Int` | `5` | Maximum number of page buttons |
| `className` | `String` | `""` | Additional CSS classes |

#### Example

```scala
val (currentPage, setCurrentPage, _) = useState(1)

Pagination <> PaginationProps(
  currentPage = currentPage,
  totalPages = 10,
  onPageChange = setCurrentPage,
  size = "sm",
  showFirstLast = true
)
```

### Progress

A progress bar component with linear and radial options for showing completion status or loading state.

#### ProgressProps

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `value` | `Option[Double]` | `None` | Current progress value (within min-max range) |
| `min` | `Double` | `0` | Minimum value |
| `max` | `Double` | `100` | Maximum value |
| `variant` | `String` | `""` | Color variant: primary, secondary, accent, info, success, warning, error |
| `size` | `String` | `"md"` | Size variations: xs, sm, md, lg |
| `indeterminate` | `Boolean` | `false` | Whether to show indeterminate animation |
| `showValue` | `Boolean` | `false` | Whether to show progress value as text |
| `valuePrefix` | `String` | `""` | Prefix for displayed value (e.g., "$") |
| `valueSuffix` | `String` | `"%"` | Suffix for displayed value (e.g., "%") |
| `valueFormat` | `Double => String` | `(v: Double) => v.toInt.toString` | Function to format value |
| `className` | `String` | `""` | Additional CSS classes |
| `ariaLabel` | `Option[String]` | `None` | Accessibility label for screen readers |
| `ariaValuenow` | `Option[String]` | `None` | Current value for ARIA (falls back to value) |
| `ariaValuemin` | `Option[String]` | `None` | Min value for ARIA (falls back to min) |
| `ariaValuemax` | `Option[String]` | `None` | Max value for ARIA (falls back to max) |

#### RadialProgressProps

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `value` | `Double` | `0` | Current progress value (within min-max range) |
| `min` | `Double` | `0` | Minimum value |
| `max` | `Double` | `100` | Maximum value |
| `variant` | `String` | `""` | Color variant: primary, secondary, accent, etc. |
| `size` | `String` | `"md"` | Size variations: xs, sm, md, lg |
| `thickness` | `String` | `""` | Thickness of progress ring |
| `showValue` | `Boolean` | `true` | Whether to show value in center |
| `valuePrefix` | `String` | `""` | Prefix for displayed value |
| `valueSuffix` | `String` | `"%"` | Suffix for displayed value |
| `valueFormat` | `Double => String` | `(v: Double) => v.toInt.toString` | Function to format value |
| `children` | `Option[FluxusNode]` | `None` | Custom content to show inside (overrides showValue) |
| `className` | `String` | `""` | Additional CSS classes |
| `ariaLabel` | `Option[String]` | `None` | Accessibility label |

#### Examples

##### Basic Progress Bar

```scala
// Standard progress bar at 40%
Progress <> ProgressProps(
  value = Some(40.0),
  max = 100.0
)

// Progress with value display
Progress <> ProgressProps(
  value = Some(65.0),
  max = 100.0,
  showValue = true
)

// Indeterminate progress for loading states
Progress <> ProgressProps(
  indeterminate = true
)
```

##### Progress Variants

```scala
// Primary color
Progress <> ProgressProps(
  value = Some(45.0),
  variant = "primary"
)

// Success indicator
Progress <> ProgressProps(
  value = Some(100.0),
  variant = "success",
  showValue = true
)

// Warning at 30%
Progress <> ProgressProps(
  value = Some(30.0),
  variant = "warning",
  showValue = true
)
```

##### Progress Sizes

```scala
// Extra small
Progress <> ProgressProps(
  value = Some(60.0),
  size = "xs",
  variant = "primary"
)

// Small
Progress <> ProgressProps(
  value = Some(60.0),
  size = "sm",
  variant = "primary"
)

// Default size (medium)
Progress <> ProgressProps(
  value = Some(60.0),
  variant = "primary"
)

// Large
Progress <> ProgressProps(
  value = Some(60.0),
  size = "lg",
  variant = "primary"
)
```

##### Radial Progress

```scala
// Basic radial progress
RadialProgress <> RadialProgressProps(
  value = 70.0
)

// With color
RadialProgress <> RadialProgressProps(
  value = 70.0,
  variant = "primary"
)

// Custom size and content
RadialProgress <> RadialProgressProps(
  value = 70.0,
  variant = "secondary",
  size = "lg",
  children = Some(
    div(
      cls := "text-secondary-content flex flex-col items-center",
      svg(
        // SVG icon content
      ),
      span(cls := "text-xs mt-1", "Energy")
    )
  )
)
```

##### Custom Thickness and Styling

```scala
// Thin radial progress
RadialProgress <> RadialProgressProps(
  value = 65.0,
  variant = "primary",
  thickness = "border-2"
)

// Thick radial progress
RadialProgress <> RadialProgressProps(
  value = 65.0,
  variant = "accent",
  thickness = "border-8"
)
```

##### Interactive Progress Example

```scala
val (progressValue, setProgressValue, _) = useState(25.0)
val (isIndeterminate, setIsIndeterminate, _) = useState(false)

div(
  cls := "space-y-4",
  
  // Controls
  div(
    cls := "flex gap-4 items-center",
    Button <> ButtonProps(
      text = "-10%",
      onClick = () => setProgressValue(Math.max(0, progressValue - 10))
    ),
    Button <> ButtonProps(
      text = "+10%",
      onClick = () => setProgressValue(Math.min(100, progressValue + 10))
    ),
    label(
      cls := "flex items-center gap-2 cursor-pointer",
      "Indeterminate",
      input(
        typ := "checkbox",
        cls := "toggle toggle-primary",
        checked := isIndeterminate,
        onChange := (() => setIsIndeterminate(!isIndeterminate))
      )
    )
  ),
  
  // Progress bar
  Progress <> ProgressProps(
    value = if (!isIndeterminate) Some(progressValue) else None,
    indeterminate = isIndeterminate,
    variant = "primary",
    showValue = !isIndeterminate
  ),
  
  // Radial progress
  div(
    cls := "flex justify-center mt-4",
    RadialProgress <> RadialProgressProps(
      value = progressValue,
      variant = "primary"
    )
  )
)
```

#### Usage Tips

- **Indeterminate Progress**: Use `indeterminate = true` when loading data and the completion percentage is unknown
- **Accessibility**: Provide proper ARIA attributes for screen readers with `ariaLabel`, `ariaValuenow`, etc.
- **Custom Formatting**: Use the `valueFormat` function to format values (e.g., for currency or large numbers)
- **Radial vs Linear**: Use `RadialProgress` for circular indicators and `Progress` for traditional horizontal bars
- **Custom Content**: For radial progress, use the `children` prop to place custom content within the circle
- **Interactive Progress**: Combine with state management for dynamic progress indicators
- **Multiple Indicators**: For complex processes, use multiple progress bars with different variants

### Table

A powerful table component for displaying data with sorting, pagination, and custom rendering.

#### TableColumnDef

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `key` | `String` | _required_ | Data field key from row data |
| `title` | `String` | _required_ | Column header text |
| `width` | `Option[String]` | `None` | Fixed column width |
| `minWidth` | `Option[String]` | `None` | Minimum column width |
| `maxWidth` | `Option[String]` | `None` | Maximum column width |
| `align` | `String` | `"left"` | Text alignment: left, center, right |
| `hidden` | `Boolean` | `false` | Whether to hide column |
| `truncate` | `Boolean` | `false` | Whether to truncate long text |
| `render` | `Option[(Any, Map[String, Any], Int) => FluxusNode]` | `None` | Custom cell renderer (value, row, index) |
| `renderHeader` | `Option[FluxusNode]` | `None` | Custom header content |
| `className` | `String` | `""` | Additional cell CSS classes |
| `headerClassName` | `String` | `""` | Additional header CSS classes |

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `data` | `List[Map[String, Any]]` | `List()` | Table data as list of maps |
| `columns` | `List[TableColumnDef]` | `List()` | Column definitions |
| `keyField` | `String` | `"id"` | Field to use as unique row key |
| `emptyText` | `String` | `"No data available"` | Text shown when data is empty |
| `bordered` | `Boolean` | `true` | Whether to show borders |
| `striped` | `Boolean` | `false` | Whether to use zebra striping |
| `hover` | `Boolean` | `true` | Whether to highlight rows on hover |
| `compact` | `Boolean` | `false` | Whether to use compact styling |
| `size` | `String` | `"md"` | Table size: sm, md, lg |
| `responsive` | `Boolean` | `true` | Whether to enable horizontal scrolling |
| `fullWidth` | `Boolean` | `true` | Whether table takes full width |
| `headerBgClass` | `String` | `""` | Header background CSS class |
| `headerTextClass` | `String` | `"font-bold"` | Header text CSS class |
| `rowBgClass` | `String` | `""` | Row background CSS class |
| `onRowClick` | `Option[(Map[String, Any], Int) => Unit]` | `None` | Row click handler |
| `caption` | `Option[String]` | `None` | Table caption |
| `footer` | `Option[FluxusNode]` | `None` | Custom footer content |
| `ariaLabel` | `Option[String]` | `None` | Accessibility label |
| `className` | `String` | `""` | Additional table CSS classes |
| `headerClassName` | `String` | `""` | Additional header CSS classes |
| `bodyClassName` | `String` | `""` | Additional body CSS classes |
| `footerClassName` | `String` | `""` | Additional footer CSS classes |

#### Example

```scala
// Sample data
val users = List(
  Map("id" -> 1, "name" -> "John Doe", "email" -> "john@example.com", "role" -> "Admin"),
  Map("id" -> 2, "name" -> "Jane Smith", "email" -> "jane@example.com", "role" -> "User")
)

// Column definitions
val columns = List(
  TableColumnDef(
    key = "id",
    title = "ID",
    width = Some("60px"),
    align = "center"
  ),
  TableColumnDef(
    key = "name",
    title = "Name",
    minWidth = Some("150px")
  ),
  TableColumnDef(
    key = "email",
    title = "Email",
    render = Some((value, _, _) => 
      a(
        href := s"mailto:${value}",
        cls := "link link-primary",
        value.toString
      )
    )
  ),
  TableColumnDef(
    key = "role",
    title = "Role",
    align = "center",
    render = Some((value, _, _) =>
      span(
        cls := {
          value match {
            case "Admin" => "badge badge-primary"
            case _ => "badge badge-ghost"
          }
        },
        value.toString
      )
    )
  )
)

// Table component
Table <> TableProps(
  data = users,
  columns = columns,
  bordered = true,
  striped = true,
  hover = true,
  headerBgClass = "bg-base-200",
  onRowClick = Some((row, _) => handleRowClick(row))
)
```

### TableWithPagination

A component that combines Table and Pagination for a complete paginated data solution.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `data` | `List[Map[String, Any]]` | `List()` | All table data |
| `columns` | `List[TableColumnDef]` | `List()` | Column definitions |
| `pageSize` | `Int` | `10` | Records per page |
| `currentPage` | `Int` | `1` | Current page |
| `onPageChange` | `Int => Unit` | `_ => ()` | Page change handler |
| `paginationSize` | `String` | `"sm"` | Pagination button size |
| `bordered` | `Boolean` | `true` | Whether to show table borders |
| `striped` | `Boolean` | `false` | Whether to use zebra striping |
| `hover` | `Boolean` | `true` | Whether to highlight rows on hover |
| `compact` | `Boolean` | `false` | Whether to use compact table |
| `size` | `String` | `"md"` | Table size: sm, md, lg |
| `responsive` | `Boolean` | `true` | Whether to enable horizontal scrolling |
| `headerBgClass` | `String` | `""` | Header background CSS class |
| `className` | `String` | `""` | Additional CSS classes |
| `onRowClick` | `Option[(Map[String, Any], Int) => Unit]` | `None` | Row click handler |
| `emptyText` | `String` | `"No data available"` | Text shown when data is empty |
| `keyField` | `String` | `"id"` | Field to use as unique row key |
| `caption` | `Option[String]` | `None` | Table caption |

#### Example

```scala
val (currentPage, setCurrentPage, _) = useState(1)

// Using the same users and columns as in the Table example
TableWithPagination <> TableWithPaginationProps(
  data = users,
  columns = columns,
  pageSize = 5,
  currentPage = currentPage,
  onPageChange = setCurrentPage,
  bordered = true,
  hover = true,
  headerBgClass = "bg-base-300"
)
```

### Tooltip

A tooltip component that displays informational text when hovering over an element.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `tip` | `String` | _required_ | Text content of the tooltip |
| `position` | `Option[String]` | `None` | Position: top, bottom, left, right |
| `color` | `Option[String]` | `None` | Color variant: primary, secondary, accent, info, success, warning, error |
| `className` | `Option[String]` | `None` | Additional CSS classes |
| `children` | `FluxusNode` | _required_ | Element to attach the tooltip to |

#### Example

```scala
// Basic tooltip
Tooltip <> TooltipProps(
  tip = "This is a helpful tooltip",
  children = button(cls := "btn", "Hover me")
)

// Positioned tooltip with color
Tooltip <> TooltipProps(
  tip = "Click to save changes",
  position = Some("bottom"),
  color = Some("primary"),
  children = button(cls := "btn btn-primary", "Save")
)

// Tooltip on an icon
Tooltip <> TooltipProps(
  tip = "More information",
  position = Some("right"),
  color = Some("info"),
  children = span(cls := "text-lg", "ℹ️")
)
```

#### Usage Tips

- Use tooltips to provide additional context for UI elements without cluttering the interface
- Keep tooltip text concise and clear - typically one line works best
- Position the tooltip based on available space around the element
- Use appropriate color variants to match the tooltip's purpose
- Accessibility note: tooltips should supplement, not replace, clear labeling

---

## Navigation Components

### Sidebar

A navigation sidebar component with collapsible sections, icons, and badges.

#### NavItem

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `id` | `String` | _required_ | Unique identifier |
| `title` | `String` | _required_ | Menu item text |
| `icon` | `Option[FluxusNode]` | `None` | Icon for the item |
| `href` | `Option[String]` | `None` | Link URL |
| `onClick` | `Option[() => Unit]` | `None` | Click handler |
| `badge` | `Option[String]` | `None` | Badge text |
| `badgeVariant` | `String` | `"primary"` | Badge color variant |
| `items` | `List[NavItem]` | `List()` | Child menu items |
| `isActive` | `Boolean` | `false` | Whether item is active |
| `disabled` | `Boolean` | `false` | Whether item is disabled |

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `items` | `List[NavItem]` | `List()` | Navigation items |
| `variant` | `String` | `"normal"` | Sidebar style: normal, compact, boxed |
| `size` | `String` | `"md"` | Menu size: xs, sm, md, lg |
| `bordered` | `Boolean` | `false` | Whether to show border |
| `rounded` | `Boolean` | `true` | Whether to use rounded corners |
| `bgClass` | `String` | `""` | Background CSS class |
| `textClass` | `String` | `""` | Text color CSS class |
| `width` | `String` | `"w-64"` | Width when expanded |
| `collapsedWidth` | `String` | `"w-16"` | Width when collapsed |
| `collapsible` | `Boolean` | `false` | Whether sidebar can collapse |
| `collapsed` | `Boolean` | `false` | Whether sidebar is collapsed |
| `onCollapseChange` | `Option[Boolean => Unit]` | `None` | Collapse state change handler |
| `expandedSections` | `List[String]` | `List()` | IDs of initially expanded sections |
| `onNavigation` | `Option[(String, NavItem) => Unit]` | `None` | Navigation handler |
| `collapseButtonPosition` | `String` | `"top"` | Position: top, bottom |
| `showToggleIcons` | `Boolean` | `true` | Whether to show section toggle icons |
| `className` | `String` | `""` | Additional sidebar CSS classes |
| `menuClassName` | `String` | `""` | Additional menu CSS classes |

#### Example

```scala
// State for the active navigation item
val (activeNavId, setActiveNavId, _) = useState("dashboard")

// Create navigation items
val sidebarItems = List(
  NavItem(
    id = "dashboard",
    title = "Dashboard",
    icon = Some(HomeIcon),
    href = Some("#dashboard"),
    isActive = activeNavId == "dashboard"
  ),
  NavItem(
    id = "users",
    title = "Users",
    icon = Some(UsersIcon),
    items = List(
      NavItem(
        id = "all-users",
        title = "All Users",
        href = Some("#all-users"),
        isActive = activeNavId == "users-all-users"
      ),
      NavItem(
        id = "add-user",
        title = "Add User",
        href = Some("#add-user"),
        isActive = activeNavId == "users-add-user",
        badge = Some("New"),
        badgeVariant = "success"
      )
    )
  ),
  NavItem(
    id = "settings",
    title = "Settings",
    icon = Some(SettingsIcon),
    href = Some("#settings"),
    isActive = activeNavId == "settings"
  )
)

// Handle navigation click
def handleNavigation(id: String, item: NavItem): Unit = {
  setActiveNavId(id)
  // Additional navigation logic
}

// Sidebar component
Sidebar <> SidebarProps(
  items = sidebarItems,
  variant = "normal",
  size = "md",
  bordered = true,
  bgClass = "bg-base-200",
  expandedSections = List("users"),
  onNavigation = Some(handleNavigation)
)
```

### Tabs

A tabbed interface component for organizing content into multiple sections.

#### TabPanelProps

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `id` | `String` | _required_ | Unique identifier for the tab |
| `title` | `String` | _required_ | Tab label text |
| `icon` | `Option[FluxusNode]` | `None` | Icon to display with tab title |
| `badge` | `Option[String]` | `None` | Badge text to display on tab |
| `badgeVariant` | `String` | `"primary"` | Badge color variant |
| `disabled` | `Boolean` | `false` | Whether tab is disabled |
| `children` | `FluxusNode` | _required_ | Tab panel content |

#### TabsProps

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `children` | `List[TabPanelProps]` | `List()` | List of tab panel configurations |
| `activeTab` | `String` | `""` | ID of the active tab (for controlled usage) |
| `onTabChange` | `String => Unit` | `_ => ()` | Tab change handler |
| `variant` | `String` | `"bordered"` | Tab style: bordered, lifted, boxed |
| `size` | `String` | `"md"` | Tab size: xs, sm, md, lg |
| `alignment` | `String` | `"start"` | Tab alignment: start, center, end |
| `fullWidth` | `Boolean` | `false` | Whether tabs should take full width |
| `tabsClassName` | `String` | `""` | Additional CSS classes for tabs container |
| `contentClassName` | `String` | `""` | Additional CSS classes for content area |
| `contentContainerClassName` | `String` | `""` | Additional CSS classes for overall container |

#### Examples

##### Basic Tabs

```scala
val (activeTab, setActiveTab, _) = useState("tab1")

Tabs <> TabsProps(
  activeTab = activeTab,
  onTabChange = setActiveTab,
  children = List(
    TabPanelProps(
      id = "tab1",
      title = "Tab 1",
      children = div(
        h3(cls := "text-lg font-medium mb-2", "Tab 1 Content"),
        p("This is the content for Tab 1.")
      )
    ),
    TabPanelProps(
      id = "tab2",
      title = "Tab 2",
      children = div(
        h3(cls := "text-lg font-medium mb-2", "Tab 2 Content"),
        p("This is the content for Tab 2.")
      )
    ),
    TabPanelProps(
      id = "tab3",
      title = "Tab 3",
      children = div(
        h3(cls := "text-lg font-medium mb-2", "Tab 3 Content"),
        p("This is the content for Tab 3.")
      )
    )
  )
)
```

##### Tab Variants

```scala
// Lifted tabs
Tabs <> TabsProps(
  variant = "lifted",
  children = List(
    TabPanelProps(
      id = "lifted1",
      title = "Profile",
      children = div(
        p("Lifted tabs have a subtle curved appearance, giving a 3D-like effect.")
      )
    ),
    TabPanelProps(
      id = "lifted2",
      title = "Settings",
      children = div(
        p("Settings content for the lifted tab style example.")
      )
    )
  )
)

// Boxed tabs
Tabs <> TabsProps(
  variant = "boxed",
  children = List(
    TabPanelProps(
      id = "boxed1",
      title = "About",
      children = div(
        p("Boxed tabs have a contained appearance with background color.")
      )
    ),
    TabPanelProps(
      id = "boxed2",
      title = "Features",
      children = div(
        p("Features content for the boxed tab style.")
      )
    )
  )
)
```

##### Tabs with Icons and Badges

```scala
Tabs <> TabsProps(
  children = List(
    TabPanelProps(
      id = "home",
      title = "Home",
      icon = Some(
        // Home icon SVG
        svg(
          xmlns := "http://www.w3.org/2000/svg",
          width := "20",
          height := "20",
          viewBox := "0 0 24 24",
          fill := "none",
          stroke := "currentColor",
          strokeWidth := "2",
          strokeLinecap := "round",
          strokeLinejoin := "round",
          path(d := "M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"),
          polyline(points := "9 22 9 12 15 12 15 22")
        )
      ),
      children = div(
        h3(cls := "text-lg font-medium mb-2", "Home Dashboard"),
        p("Welcome to your dashboard overview.")
      )
    ),
    TabPanelProps(
      id = "notifications",
      title = "Notifications",
      badge = Some("3"),
      badgeVariant = "error",
      children = div(
        h3(cls := "text-lg font-medium mb-2", "Notifications"),
        p("You have 3 unread notifications.")
      )
    )
  )
)
```

##### Tab Alignments

```scala
// Center Aligned
Tabs <> TabsProps(
  variant = "boxed",
  alignment = "center",
  children = List(
    TabPanelProps(id = "tab1", title = "Tab 1", children = div("Content 1")),
    TabPanelProps(id = "tab2", title = "Tab 2", children = div("Content 2")),
    TabPanelProps(id = "tab3", title = "Tab 3", children = div("Content 3"))
  )
)

// End Aligned
Tabs <> TabsProps(
  variant = "lifted",
  alignment = "end",
  children = List(
    TabPanelProps(id = "tab1", title = "Tab 1", children = div("Content 1")),
    TabPanelProps(id = "tab2", title = "Tab 2", children = div("Content 2")),
    TabPanelProps(id = "tab3", title = "Tab 3", children = div("Content 3"))
  )
)

// Full Width
Tabs <> TabsProps(
  variant = "bordered",
  fullWidth = true,
  children = List(
    TabPanelProps(id = "tab1", title = "Tab 1", children = div("Content 1")),
    TabPanelProps(id = "tab2", title = "Tab 2", children = div("Content 2")),
    TabPanelProps(id = "tab3", title = "Tab 3", children = div("Content 3"))
  )
)
```

##### Disabled Tabs

```scala
Tabs <> TabsProps(
  children = List(
    TabPanelProps(id = "tab1", title = "Active Tab", children = div("Content 1")),
    TabPanelProps(
      id = "tab2", 
      title = "Disabled Tab", 
      disabled = true,
      children = div("This content is not accessible")
    ),
    TabPanelProps(id = "tab3", title = "Another Tab", children = div("Content 3"))
  )
)
```

#### Usage Tips

- **Controlled vs Uncontrolled**:
    - For controlled tabs, provide both `activeTab` and `onTabChange` props
    - For uncontrolled tabs, omit `activeTab` and the component will manage state internally
- **Accessibility**: Tabs are implemented with proper ARIA roles for keyboard navigation and screen readers
- **Tab Variants**:
    - `bordered`: Default style with underline for active tab
    - `lifted`: Tabs with curved corners that appear to lift the active tab
    - `boxed`: Contained tabs with background colors
- **Content Area**: The tab content area maintains a consistent height and handles transitions automatically
- **Responsive Design**: For small screens, consider using fewer tabs or shorter tab labels

## Feedback Components

### Alert

A flexible alert/notification component for displaying information, success messages, warnings, and errors.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `title` | `Option[String]` | `None` | Alert title text |
| `message` | `Option[String]` | `None` | Alert message text |
| `children` | `Option[FluxusNode]` | `None` | Custom alert content (overrides title/message) |
| `variant` | `String` | `"info"` | Alert style: info, success, warning, error, neutral |
| `icon` | `Option[FluxusNode]` | `None` | Custom icon (overrides default) |
| `showIcon` | `Boolean` | `true` | Whether to show the default icon |
| `hideIcon` | `Boolean` | `false` | Alternative way to hide icon (takes precedence) |
| `actions` | `Option[FluxusNode]` | `None` | Action buttons to display |
| `onClose` | `Option[() => Unit]` | `None` | Close handler (shows close button when provided) |
| `className` | `String` | `""` | Additional CSS classes |
| `compact` | `Boolean` | `false` | Whether to use compact style with less padding |

#### Examples

##### Basic Alert Types

```scala
// Info alert
Alert <> AlertProps(
  variant = "info",
  title = Some("Information"),
  message = Some("This is an informational alert to notify you about something.")
)

// Success alert
Alert <> AlertProps(
  variant = "success",
  title = Some("Success"),
  message = Some("Your action was completed successfully!")
)

// Warning alert
Alert <> AlertProps(
  variant = "warning",
  title = Some("Warning"),
  message = Some("Please be careful with this action.")
)

// Error alert
Alert <> AlertProps(
  variant = "error",
  title = Some("Error"),
  message = Some("Something went wrong. Please try again.")
)
```

##### Alert with Custom Icon

```scala
Alert <> AlertProps(
  variant = "warning",
  title = Some("Custom Icon"),
  message = Some("This alert uses a custom icon."),
  icon = Some(
    svg(
      xmlns := "http://www.w3.org/2000/svg",
      cls := "stroke-current shrink-0 h-6 w-6",
      fill := "none",
      viewBox := "0 0 24 24",
      stroke := "currentColor",
      path(
        d := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
      )
    )
  )
)
```

##### Dismissible Alert

```scala
val (showAlert, setShowAlert, _) = useState(true)

if (showAlert) {
  Alert <> AlertProps(
    variant = "info",
    title = Some("Dismissible Alert"),
    message = Some("Click the X button to dismiss this alert."),
    onClose = Some(() => setShowAlert(false))
  )
}
```

##### Alert with Actions

```scala
Alert <> AlertProps(
  variant = "warning",
  title = Some("Delete Account?"),
  message = Some("You're about to delete your account. This action cannot be undone."),
  actions = Some(
    div(
      cls := "flex gap-2",
      Button <> ButtonProps(
        text = "Cancel",
        variant = "ghost",
        size = "sm"
      ),
      Button <> ButtonProps(
        text = "Delete",
        variant = "warning",
        size = "sm"
      )
    )
  )
)
```

##### Custom Alert Content

```scala
Alert <> AlertProps(
  variant = "info",
  children = Some(
    div(
      h3(cls := "font-bold", "Cookie Policy"),
      div(
        cls := "text-sm mt-1",
        "This website uses cookies to ensure you get the best experience on our website."
      ),
      div(
        cls := "mt-3",
        button(
          cls := "btn btn-sm btn-primary mr-2",
          "Accept"
        ),
        button(
          cls := "btn btn-sm btn-ghost",
          "Decline"
        )
      )
    )
  )
)
```

#### Usage Tips

- **Alert Types**: Choose the appropriate variant (info, success, warning, error) based on the nature of the notification
- **Accessibility**: Alerts are automatically marked with `role="alert"` for screen readers
- **Responsive Design**: Alerts expand to fill their container width
- **Compact Style**: Use `compact={true}` for alerts with less padding in dense UIs
- **Flexible Content**: Use either the `title`/`message` props for simple alerts or `children` for custom content
- **Dismissible Alerts**: Provide an `onClose` handler to make the alert dismissible with a close button

### Modal

A flexible dialog component for displaying content that requires user attention or interaction.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `title` | `Option[String]` | `None` | Modal title text |
| `children` | `FluxusNode` | _required_ | Modal content |
| `footer` | `Option[FluxusNode]` | `None` | Modal footer content (typically action buttons) |
| `isOpen` | `Boolean` | `false` | Whether the modal is visible |
| `onClose` | `() => Unit` | `() => ()` | Handler called when modal is closed |
| `size` | `String` | `"md"` | Modal size: xs, sm, md, lg, xl |
| `position` | `String` | `"middle"` | Modal position: middle, top, bottom |
| `responsive` | `Boolean` | `true` | Enable responsive behavior |
| `backdrop` | `Boolean` | `true` | Show backdrop overlay |
| `backdropClose` | `Boolean` | `true` | Close when backdrop is clicked |
| `closeOnEscKey` | `Boolean` | `true` | Close on Escape key press |
| `animation` | `Boolean` | `true` | Enable animation effects |
| `closeButton` | `Boolean` | `true` | Show close button in top-right corner |
| `className` | `String` | `""` | Additional CSS classes for modal |
| `headerClassName` | `String` | `""` | Additional CSS classes for header |
| `bodyClassName` | `String` | `""` | Additional CSS classes for body |
| `footerClassName` | `String` | `""` | Additional CSS classes for footer |
| `ariaLabelledby` | `Option[String]` | `None` | ID of element that labels the modal |
| `ariaDescribedby` | `Option[String]` | `None` | ID of element that describes the modal |

#### Examples

##### Basic Modal

```scala
val (isOpen, setIsOpen, _) = useState(false)

// Trigger button
Button <> ButtonProps(
  text = "Open Modal",
  onClick = () => setIsOpen(true)
)

// Modal component
Modal <> ModalProps(
  isOpen = isOpen,
  onClose = () => setIsOpen(false),
  title = Some("Basic Modal"),
  footer = Some(
    div(
      cls := "flex justify-end",
      Button <> ButtonProps(
        text = "Close",
        onClick = () => setIsOpen(false)
      )
    )
  ),
  children = div(
    p("This is a basic modal with a title, content, and footer."),
    p("Click the close button, the X in the corner, or outside the modal to close it.")
  )
)
```

##### Different Sizes and Positions

```scala
// Small modal
Modal <> ModalProps(
  isOpen = isSmallModalOpen,
  onClose = () => setSmallModalOpen(false),
  title = Some("Small Modal"),
  size = "sm",
  children = p("This is a small modal with compact width.")
)

// Top positioned modal
Modal <> ModalProps(
  isOpen = isTopModalOpen,
  onClose = () => setTopModalOpen(false),
  title = Some("Top Modal"),
  position = "top",
  children = p("This modal appears at the top of the screen.")
)

// Bottom positioned modal
Modal <> ModalProps(
  isOpen = isBottomModalOpen,
  onClose = () => setBottomModalOpen(false),
  title = Some("Bottom Modal"),
  position = "bottom",
  children = p("This modal appears at the bottom of the screen.")
)
```

##### Form Modal

```scala
Modal <> ModalProps(
  isOpen = isFormModalOpen,
  onClose = () => setFormModalOpen(false),
  title = Some("Contact Form"),
  footer = Some(
    div(
      cls := "flex justify-end gap-2",
      Button <> ButtonProps(
        text = "Cancel",
        variant = "ghost",
        onClick = () => setFormModalOpen(false)
      ),
      Button <> ButtonProps(
        text = "Submit",
        variant = "primary",
        onClick = () => {
          // Handle form submission
          setFormModalOpen(false)
        }
      )
    )
  ),
  children = div(
    cls := "space-y-4",
    // Form fields
    div(
      cls := "form-control",
      label(cls := "label", span(cls := "label-text", "Name")),
      Input <> InputProps(
        value = Some(formName),
        onChange = setFormName,
        placeholder = Some("Enter your name")
      )
    ),
    div(
      cls := "form-control",
      label(cls := "label", span(cls := "label-text", "Email")),
      Input <> InputProps(
        typ = "email",
        value = Some(formEmail),
        onChange = setFormEmail,
        placeholder = Some("Enter your email")
      )
    )
  )
)
```

#### Modal Presets

Fluxus-DaisyUI includes several pre-built modal variants for common use cases:

##### Info Modal

```scala
ModalPresets.info(
  title = "Information",
  content = "This is some important information you should know about.",
  isOpen = isInfoModalOpen,
  onClose = () => setInfoModalOpen(false)
)
```

##### Success Modal

```scala
ModalPresets.success(
  title = "Success!",
  content = "Your action was completed successfully.",
  isOpen = isSuccessModalOpen,
  onClose = () => setSuccessModalOpen(false)
)
```

##### Warning Modal

```scala
ModalPresets.warning(
  title = "Warning",
  content = "This action might have unexpected consequences.",
  isOpen = isWarningModalOpen,
  onClose = () => setWarningModalOpen(false)
)
```

##### Error Modal

```scala
ModalPresets.error(
  title = "Error",
  content = "Something went wrong. Please try again.",
  isOpen = isErrorModalOpen,
  onClose = () => setErrorModalOpen(false)
)
```

##### Confirmation Dialog

```scala
ModalPresets.confirm(
  title = "Confirm Action",
  content = "Are you sure you want to proceed with this action?",
  isOpen = isConfirmModalOpen,
  onConfirm = () => {
    // Handle confirmation
    console.log("Action confirmed!")
  },
  onClose = () => setConfirmModalOpen(false)
)
```

##### Delete Confirmation Dialog

```scala
ModalPresets.delete(
  isOpen = isDeleteModalOpen,
  onConfirm = () => {
    // Handle deletion
    console.log("Item deleted!")
  },
  onClose = () => setDeleteModalOpen(false)
)
```

#### Usage Tips

- **Modal State Management**: Use the `isOpen` prop to control visibility and `onClose` handler for closing the modal
- **Accessibility**: Modals trap focus when open and support keyboard navigation (Escape to close)
- **Composition**: Use the `footer` prop for action buttons and the `title` prop for consistent header styling
- **Responsiveness**: Modals automatically adapt to screen size but can be configured with different sizes
- **Presets**: Use the `ModalPresets` utility for common dialog types (confirmation, error, etc.)
- **Customization**: Use the `className` props to add custom styling to different parts of the modal

### Notification System

A global notification/toast system that allows you to display temporary informational messages anywhere in your application.

#### API Overview

The `notification` object provides a set of methods to show different types of notifications:

| Method | Description |
|--------|-------------|
| `notification.success(message, options)` | Shows a success notification |
| `notification.error(message, options)` | Shows an error notification |
| `notification.info(message, options)` | Shows an info notification |
| `notification.warning(message, options)` | Shows a warning notification |
| `notification.neutral(message, options)` | Shows a neutral notification |
| `notification.show(...)` | Core method with all configuration options |
| `notification.close(id)` | Closes a specific notification by ID |
| `notification.closeAll()` | Closes all active notifications |

#### ToastOptions

The configuration options for notifications:

```scala
case class ToastOptions(
    title: Option[String] = None,               // Optional title
    duration: Option[Int] = None,               // Duration in milliseconds before auto-dismiss
    position: Option[String] = None,            // Position on screen
    actions: Option[FluxusNode] = None,         // Action buttons to display
    onClose: Option[() => Unit] = None,         // Callback when notification closes
)
```

#### Basic Usage

```scala
// Simple success notification
notification.success("Operation completed successfully!")

// Error notification with title
notification.error(
  "Failed to save changes to the document",
  ToastOptions(title = Some("Save Failed"))
)

// Info notification with custom duration
notification.info(
  "This notification will stay longer",
  ToastOptions(duration = Some(8000)) // 8 seconds
)

// Warning notification with custom position
notification.warning(
  "Please check your input values",
  ToastOptions(position = Some("bottom-end"))
)
```

#### Positions

The notification system supports different positions on screen:

- `"top-start"` - Top left
- `"top-center"` - Top center
- `"top-end"` - Top right (default)
- `"middle"` - Center of screen
- `"bottom-start"` - Bottom left
- `"bottom-center"` - Bottom center
- `"bottom-end"` - Bottom right

```scala
// Show notification at the top-center
notification.info(
  "New updates available",
  ToastOptions(position = Some("top-center"))
)

// Show notification at the bottom-center
notification.success(
  "Your changes have been saved",
  ToastOptions(position = Some("bottom-center"))
)
```

#### Duration

Control how long notifications stay visible:

```scala
// Short notification (2 seconds)
notification.info(
  "This notification will disappear quickly",
  ToastOptions(duration = Some(2000))
)

// Long notification (10 seconds)
notification.info(
  "This notification will stay longer",
  ToastOptions(duration = Some(10000))
)

// Persistent notification (won't auto-dismiss)
notification.warning(
  "This notification won't auto-dismiss. Click the X to close it.",
  ToastOptions(duration = Some(0))  // 0 means no auto-dismiss
)
```

#### Advanced Usage

You can use the core `show` method for more control:

```scala
// Initialize variable for the notification ID
var notificationId = ""

// Show a notification with actions that reference its own ID
notificationId = notification.show(
  message = Some("A new version is available for download."),
  title = Some("Update Available"),
  variant = "info",
  duration = 0,  // Won't auto-dismiss
  position = "top-end",
  actions = Some(
    div(
      cls := "flex flex-col gap-2 mt-2",
      Button <> ButtonProps(
        text = "Update Now",
        variant = "primary",
        size = "xs",
        className = "w-full",
        onClick = () => {
          // Close this notification
          notification.close(notificationId)
          
          // Show success notification
          notification.success("Update started successfully")
        }
      ),
      Button <> ButtonProps(
        text = "Later",
        variant = "ghost",
        size = "xs",
        className = "w-full",
        onClick = () => notification.close(notificationId)
      )
    )
  )
)
```

#### Notification Stacking

Notifications automatically stack when multiple are shown:

```scala
// Show multiple notifications
Button <> ButtonProps(
  text = "Show Multiple Notifications",
  variant = "primary",
  onClick = () => {
    // Create 3 notifications
    notification.info("First notification")
    notification.success("Second notification")
    notification.warning("Third notification")
  }
)
```

#### Clearing Notifications

```scala
// Close all notifications
Button <> ButtonProps(
  text = "Clear All Notifications",
  variant = "error",
  onClick = () => notification.closeAll()
)
```

#### Implementation Notes

- Notifications are automatically rendered into a specially created container in the DOM
- You don't need to add any container component to your app
- Notifications use DaisyUI's alert component internally with appropriate styling
- The notification system uses an internal Var to track active toast notifications
- Notifications are automatically removed from this state when closed or auto-dismissed

#### Usage Tips

- Use short, concise messages that communicate the essential information
- Choose the appropriate notification type based on the context:
  - `success` for successful operations
  - `error` for failures and errors
  - `warning` for potential issues or cautions
  - `info` for neutral information
- For important messages, use a longer duration or set `duration = 0` to prevent auto-dismissal
- For interactive notifications, add action buttons with the `actions` property
- Consider the position based on the importance and context of the notification:
  - Important alerts often work better at the top of the screen
  - Less critical information can appear at the bottom

### Spinner

A loading spinner component with various styles and animations.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `variant` | `String` | `"spinner"` | Spinner type: spinner (border), spinner-dots, spinner-ring, spinner-ball, spinner-infinity |
| `size` | `String` | `"md"` | Spinner size: xs, sm, md, lg |
| `color` | `String` | `""` | Color class: text-primary, text-secondary, etc. |
| `animation` | `String` | `""` | Animation class: animate-spin, animate-pulse, animate-ping, animate-bounce |
| `noAnimation` | `Boolean` | `false` | Whether to disable animation |
| `className` | `String` | `""` | Additional CSS classes |

#### Example

```scala
// Default spinner
Spinner <> SpinnerProps()

// Customized spinner
Spinner <> SpinnerProps(
  variant = "spinner-dots",
  size = "lg",
  color = "text-primary",
  animation = "animate-bounce",
  className = "my-4"
)
```

---

### ThemeChooser

A theme selection component that allows users to switch between light, dark, and custom themes with automatic system preference detection.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `themes` | `List[String]` | `List("light", "dark")` | List of supported theme names |
| `defaultTheme` | `String` | `"light"` | Default theme if no preference exists |
| `respectSystemPreference` | `Boolean` | `true` | Whether to use system color scheme preference |
| `variant` | `String` | `"toggle"` | UI style: toggle, dropdown, or button (modal) |
| `alignment` | `String` | `"end"` | Dropdown alignment: start, center, end |
| `showLabel` | `Boolean` | `false` | Whether to show "Theme" label |
| `className` | `String` | `""` | Additional CSS classes |
| `onThemeChange` | `Option[String => Unit]` | `None` | Callback when theme changes |

#### Examples

##### Toggle Variant (Light/Dark Switch)

```scala
// Simple light/dark toggle
ThemeChooser <> ThemeChooserProps(
  variant = "toggle",
  themes = List("light", "dark")
)

// Toggle with label and theme change callback
ThemeChooser <> ThemeChooserProps(
  variant = "toggle",
  showLabel = true,
  onThemeChange = Some(theme => println(s"Theme changed to: $theme"))
)
```

##### Dropdown Variant (Multiple Themes)

```scala
// Dropdown with multiple themes
ThemeChooser <> ThemeChooserProps(
  variant = "dropdown",
  themes = List("light", "dark", "cupcake", "forest", "synthwave"),
  alignment = "start",
  showLabel = true
)

// Dropdown without system preference detection
ThemeChooser <> ThemeChooserProps(
  variant = "dropdown",
  themes = List("light", "dark", "corporate", "luxury"),
  respectSystemPreference = false,
  defaultTheme = "corporate"
)
```

##### Button/Modal Variant (Theme Preview Cards)

```scala
// Button that opens theme selection modal
ThemeChooser <> ThemeChooserProps(
  variant = "button",
  themes = List(
    "light", "dark", "cupcake", "forest", 
    "aqua", "lofi", "pastel", "fantasy"
  ),
  onThemeChange = Some(newTheme => {
    println(s"Theme switched to: $newTheme")
  })
)
```

#### How It Works

The ThemeChooser component:

1. Sets the `data-theme` attribute on the `html` element to apply DaisyUI themes
2. Stores preferences in localStorage for persistence
3. Can detect and respect system color scheme preference
4. Supports multiple UI variants for different levels of theme control

#### Implementation Details

- **Theme Persistence**: Selected themes are saved to `localStorage` as "theme"
- **System Preference**: When enabled, detects `prefers-color-scheme` media query
- **Auto Mode**: When in auto mode (system preference), a "theme-auto" flag is saved
- **Media Query Listener**: Automatically updates theme if system preference changes while in auto mode

#### Usage Tips

- **Toggle Variant**: Best for simple light/dark switching
- **Dropdown Variant**: Good for applications with multiple theme options
- **Button/Modal Variant**: Provides visual preview of themes before selection
- **System Preference**: Keep `respectSystemPreference` enabled for best user experience
- **Theme List**: Include only themes you've configured in your Tailwind/DaisyUI setup
- **Callbacks**: Use `onThemeChange` to sync theme state with your application

#### Integration with Theme Configuration

To ensure all themes work properly, configure your DaisyUI themes in your Tailwind config:

```js
// tailwind.config.js
module.exports = {
  plugins: [require("daisyui")],
  daisyui: {
    themes: ["light", "dark", "cupcake", "forest", "synthwave"]
  }
}
```

Or in a CSS file using the `@plugin` directive:

```css
@plugin "daisyui" {
  themes: light --default, dark --prefersdark, cupcake, aqua, night, forest;
}
```

## Usage Tips

### Combining Components

Components can be nested to create complex UIs:

```scala
Container <> ContainerProps(
  children = div(
    Card <> CardProps(
      title = Some("User Profile"),
      children = div(
        div(
          cls := "flex items-center gap-4 mb-4",
          Avatar <> AvatarProps(
            text = Some("JD"),
            size = "lg",
            bgClass = "bg-primary"
          ),
          div(
            h3(cls := "text-lg font-bold", "John Doe"),
            p(cls := "text-sm opacity-70", "Administrator")
          )
        ),
        
        div(
          cls := "space-y-4",
          p("Account settings and preferences"),
          
          Button <> ButtonProps(
            text = "Update Profile",
            variant = "primary"
          )
        )
      )
    )
  )
)
```

### Working with Icons

For icons, you can use Lucide React components, SVG elements, or any other icon library:

```scala
// Simple SVG icon example
def HomeIcon: FluxusNode = {
  svg(
    xmlns := "http://www.w3.org/2000/svg",
    width := "20",
    height := "20",
    viewBox := "0 0 24 24",
    fill := "none",
    stroke := "currentColor",
    strokeWidth := "2",
    strokeLinecap := "round",
    strokeLinejoin := "round",
    path(
      d := "M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"
    ),
    polyline(
      points := "9 22 9 12 15 12 15 22"
    )
  )
}

// Using with button
Button <> ButtonProps(
  text = "Home",
  startIcon = Some(HomeIcon)
)
```

### Form Handling

When working with form components like Select, use state hooks to manage values:

```scala
val (formValues, setFormValues, updateFormValues) = useState(Map[String, Option[String]]())

def handleSelectChange(field: String)(value: Option[String]): Unit = {
  updateFormValues(_ + (field -> value))
}

div(
  cls := "space-y-4",
  
  // Form fields
  Select <> SelectProps(
    options = genderOptions,
    value = formValues.getOrElse("gender", None),
    onChange = handleSelectChange("gender"),
    placeholder = "Select gender"
  ),
  
  // Submit button
  Button <> ButtonProps(
    text = "Submit",
    variant = "primary",
    onClick = () => submitForm(formValues)
  )
)
```

### Responsive Design

Components like Table have built-in responsive behavior. You can enhance this with Tailwind's responsive utilities:

```scala
div(
  cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4", 
  
  // A card that's full width on mobile, half on tablet, third on desktop
  Card <> CardProps(
    title = Some("Important Information"),
    children = div(
      p("This card adjusts its width based on screen size.")
    )
  )
)
```

### Theme Integration

The components inherit colors from DaisyUI's theme system. Make sure your app has `data-theme` set on the HTML or a parent element:

```html
<html lang="en" data-theme="light">
  <!-- Your app content -->
</html>
```

You can also toggle themes dynamically:

```scala
val (theme, setTheme, _) = useState("light")

div(
  cls := s"min-h-screen bg-base-100",
  data_theme := theme,
  
  // Theme toggle
  Button <> ButtonProps(
    text = if (theme == "light") "Dark Mode" else "Light Mode",
    onClick = () => setTheme(if (theme == "light") "dark" else "light")
  )
)
```

