# fluxus-daisyui Component Documentation

This document provides comprehensive documentation for all components in the fluxus-daisyui library.

## Table of Contents

- [Layout Components](#layout-components)
    - [Container](#container)
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
    - [Table](#table)
    - [TableWithPagination](#tablewithpagination)
    - [Pagination](#pagination)
- [Navigation Components](#navigation-components)
    - [Sidebar](#sidebar)
    - [Tabs](#tabs)
- [Feedback Components](#feedback-components)
    - [Alert](#alert)
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

A flexible button component with support for various styling options, icons and states.

#### Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `text` | `String` | `""` | Button text content |
| `children` | `Option[FluxusNode]` | `None` | Alternative to text for complex content |
| `variant` | `String` | `"primary"` | Button variant: primary, secondary, accent, info, success, warning, error, ghost, link, neutral |
| `size` | `String` | `"md"` | Button size: lg, md, sm, xs |
| `shape` | `Option[String]` | `None` | Button shape: circle, square |
| `outline` | `Boolean` | `false` | Whether to use outline style |
| `wide` | `Boolean` | `false` | Whether to use wide style |
| `glass` | `Boolean` | `false` | Whether to use glass effect |
| `block` | `Boolean` | `false` | Whether to make button full width |
| `active` | `Boolean` | `false` | Whether to force active state |
| `loading` | `Boolean` | `false` | Whether to show loading state |
| `disabled` | `Boolean` | `false` | Whether button is disabled |
| `noAnimation` | `Boolean` | `false` | Whether to disable animations |
| `onClick` | `() => Unit` | `() => ()` | Click handler function |
| `onMouseEnter` | `Option[dom.MouseEvent => Unit]` | `None` | Mouse enter handler |
| `onMouseLeave` | `Option[dom.MouseEvent => Unit]` | `None` | Mouse leave handler |
| `startIcon` | `Option[FluxusNode]` | `None` | Icon to display at start of button |
| `endIcon` | `Option[FluxusNode]` | `None` | Icon to display at end of button |
| `className` | `String` | `""` | Additional CSS classes |
| `ariaLabel` | `Option[String]` | `None` | Accessibility label |
| `tabIndex` | `Option[Int]` | `None` | Tab index for focus control |
| `buttonRole` | `Option[String]` | `None` | ARIA role attribute |
| `buttonType` | `String` | `"button"` | HTML button type: button, submit, reset |
| `name` | `Option[String]` | `None` | HTML name attribute |
| `value` | `Option[String]` | `None` | HTML value attribute |

#### Example

```scala
Button <> ButtonProps(
  text = "Click Me",
  variant = "primary",
  size = "md",
  outline = true,
  startIcon = Some(svg(
    // SVG content for icon
  )),
  onClick = () => handleClick(),
  disabled = false,
  className = "mt-4"
)
```

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