package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def InputTest: FluxusNode = {
  // State for the different input examples
  val (basicValue, setBasicValue, _)       = useState("")
  val (numberValue, setNumberValue, _)     = useState("0")
  val (passwordValue, setPasswordValue, _) = useState("")
  val (emailValue, setEmailValue, _)       = useState("")
  val (textareaValue, setTextareaValue, _) = useState("")

  // Special state for validation example
  val (username, setUsername, _)           = useState("")
  val (usernameError, setUsernameError, _) = useState(Option.empty[String])

  // Validate username on change
  def validateUsername(value: String): Unit = {
    setUsername(value)

    if (value.isEmpty) {
      setUsernameError(Some("Username is required"))
    } else if (value.length < 3) {
      setUsernameError(Some("Username must be at least 3 characters"))
    } else if (!value.matches("^[a-zA-Z0-9_]+$")) {
      setUsernameError(Some("Username can only contain letters, numbers, and underscores"))
    } else {
      setUsernameError(None)
    }
  }

  // Function to create simple icons for the examples
  def SearchIcon: FluxusNode = {
    svg(
      xmlns   := "http://www.w3.org/2000/svg",
      cls     := "h-5 w-5 text-base-content/50",
      fill    := "none",
      viewBox := "0 0 24 24",
      stroke  := "currentColor",
      path(
        strokeLinecap  := "round",
        strokeLinejoin := "round",
        strokeWidth    := "2",
        d              := "M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z",
      ),
    )
  }

  def CalendarIcon: FluxusNode = {
    svg(
      xmlns   := "http://www.w3.org/2000/svg",
      cls     := "h-5 w-5 text-base-content/50",
      fill    := "none",
      viewBox := "0 0 24 24",
      stroke  := "currentColor",
      path(
        strokeLinecap  := "round",
        strokeLinejoin := "round",
        strokeWidth    := "2",
        d              := "M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z",
      ),
    )
  }

  def DollarIcon: FluxusNode = {
    svg(
      xmlns   := "http://www.w3.org/2000/svg",
      cls     := "h-5 w-5 text-base-content/50",
      fill    := "none",
      viewBox := "0 0 24 24",
      stroke  := "currentColor",
      path(
        strokeLinecap  := "round",
        strokeLinejoin := "round",
        strokeWidth    := "2",
        d := "M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z",
      ),
    )
  }

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Input Component Demo",
      ),

      // Basic Inputs Section
      Card <> CardProps(
        title = Some("Basic Inputs"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",

          // Simple text input
          div(
            h3(cls := "text-lg font-medium mb-2", "Text Input"),
            Input <> InputProps(
              label = Some("Name"),
              placeholder = Some("Enter your name"),
              value = Some(basicValue),
              onChange = setBasicValue,
              helperText = Some("Your full name as it appears on your ID"),
            ),
          ),

          // Number input
          div(
            h3(cls := "text-lg font-medium mb-2", "Number Input"),
            Input <> InputProps(
              label = Some("Quantity"),
              typ = "number",
              value = Some(numberValue),
              onChange = setNumberValue,
              min = Some("0"),
              max = Some("100"),
              step = Some("1"),
            ),
          ),

          // Email input
          div(
            h3(cls := "text-lg font-medium mb-2", "Email Input"),
            Input <> InputProps(
              label = Some("Email Address"),
              typ = "email",
              placeholder = Some("you@example.com"),
              value = Some(emailValue),
              onChange = setEmailValue,
              helperText = Some("We'll never share your email with anyone else"),
            ),
          ),

          // Password input with toggle
          div(
            h3(cls := "text-lg font-medium mb-2", "Password Input"),
            Input <> InputProps(
              label = Some("Password"),
              typ = "password",
              placeholder = Some("Enter your password"),
              value = Some(passwordValue),
              onChange = setPasswordValue,
              showPasswordToggle = true,
            ),
          ),

          // Textarea input
          div(
            h3(cls := "text-lg font-medium mb-2", "Textarea"),
            Input <> InputProps(
              label = Some("Message"),
              typ = "textarea",
              placeholder = Some("Enter your message"),
              value = Some(textareaValue),
              onChange = setTextareaValue,
              rows = Some(4),
            ),
          ),
        ),
      ),

      // Input Variants Section
      Card <> CardProps(
        title = Some("Input Variants"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-1 md:grid-cols-2 gap-6",

          // Primary variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Primary"),
            Input <> InputProps(
              placeholder = Some("Primary input"),
              variant = "primary",
            ),
          ),

          // Secondary variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Secondary"),
            Input <> InputProps(
              placeholder = Some("Secondary input"),
              variant = "secondary",
            ),
          ),

          // Accent variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Accent"),
            Input <> InputProps(
              placeholder = Some("Accent input"),
              variant = "accent",
            ),
          ),

          // Info variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Info"),
            Input <> InputProps(
              placeholder = Some("Info input"),
              variant = "info",
            ),
          ),

          // Success variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Success"),
            Input <> InputProps(
              placeholder = Some("Success input"),
              variant = "success",
            ),
          ),

          // Warning variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Warning"),
            Input <> InputProps(
              placeholder = Some("Warning input"),
              variant = "warning",
            ),
          ),

          // Error variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Error"),
            Input <> InputProps(
              placeholder = Some("Error input"),
              variant = "error",
            ),
          ),

          // Ghost variant
          div(
            h3(cls := "text-lg font-medium mb-2", "Ghost"),
            Input <> InputProps(
              placeholder = Some("Ghost input"),
              ghost = true,
            ),
          ),
        ),
      ),

      // Input Sizes Section
      Card <> CardProps(
        title = Some("Input Sizes"),
        className = "mb-8",
        children = div(
          cls := "space-y-4",

          // Extra small size
          div(
            h3(cls := "text-lg font-medium mb-2", "Extra Small"),
            Input <> InputProps(
              placeholder = Some("Extra small input"),
              size = "xs",
            ),
          ),

          // Small size
          div(
            h3(cls := "text-lg font-medium mb-2", "Small"),
            Input <> InputProps(
              placeholder = Some("Small input"),
              size = "sm",
            ),
          ),

          // Medium size (default)
          div(
            h3(cls := "text-lg font-medium mb-2", "Medium (Default)"),
            Input <> InputProps(
              placeholder = Some("Medium input"),
              size = "md",
            ),
          ),

          // Large size
          div(
            h3(cls := "text-lg font-medium mb-2", "Large"),
            Input <> InputProps(
              placeholder = Some("Large input"),
              size = "lg",
            ),
          ),
        ),
      ),

      // Input States Section
      Card <> CardProps(
        title = Some("Input States"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Disabled state
          div(
            h3(cls := "text-lg font-medium mb-2", "Disabled Input"),
            Input <> InputProps(
              label = Some("Disabled Input"),
              placeholder = Some("This input is disabled"),
              disabled = true,
            ),
          ),

          // ReadOnly state
          div(
            h3(cls := "text-lg font-medium mb-2", "Read-Only Input"),
            Input <> InputProps(
              label = Some("Read-Only Input"),
              value = Some("This input is read-only"),
              readOnly = true,
            ),
          ),

          // Error state
          div(
            h3(cls := "text-lg font-medium mb-2", "Input with Error"),
            Input <> InputProps(
              label = Some("Email"),
              placeholder = Some("Enter your email"),
              error = Some("Please enter a valid email address"),
            ),
          ),

          // Loading state
          div(
            h3(cls := "text-lg font-medium mb-2", "Loading Input"),
            Input <> InputProps(
              label = Some("Loading Input"),
              placeholder = Some("Loading..."),
              loading = true,
            ),
          ),
        ),
      ),

      // Input with Icons and Add-ons Section
      Card <> CardProps(
        title = Some("Inputs with Icons and Add-ons"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Input with leading icon
          div(
            h3(cls := "text-lg font-medium mb-2", "Leading Icon"),
            Input <> InputProps(
              placeholder = Some("Search..."),
              leadingIcon = Some(SearchIcon),
            ),
          ),

          // Input with trailing icon
          div(
            h3(cls := "text-lg font-medium mb-2", "Trailing Icon"),
            Input <> InputProps(
              placeholder = Some("Select a date..."),
              trailingIcon = Some(CalendarIcon),
            ),
          ),

          // Input with prefix
          div(
            h3(cls := "text-lg font-medium mb-2", "Prefix Add-on"),
            Input <> InputProps(
              placeholder = Some("0.00"),
              prefix = Some(span("$")),
            ),
          ),

          // Input with leading icon and clear button
          div(
            h3(cls := "text-lg font-medium mb-2", "Leading Icon with Clear Button"),
            Input <> InputProps(
              placeholder = Some("Enter an amount..."),
              leadingIcon = Some(DollarIcon),
              showClearButton = true,
            ),
          ),
        ),
      ),

      // Floating Label Section
      Card <> CardProps(
        title = Some("Floating Label Inputs"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Basic floating label
          div(
            h3(cls := "text-lg font-medium mb-2", "Floating Label"),
            Input <> InputProps(
              label = Some("Username"),
              placeholder = Some(" "), // Placeholder is required for proper floating
              labelFloat = true,
            ),
          ),

          // Floating label with icon
          div(
            h3(cls := "text-lg font-medium mb-2", "Floating Label with Icon"),
            Input <> InputProps(
              label = Some("Search"),
              placeholder = Some(" "),
              labelFloat = true,
              leadingIcon = Some(SearchIcon),
            ),
          ),
        ),
      ),

      // Special Features Section
      Card <> CardProps(
        title = Some("Special Features"),
        className = "mb-8",
        children = div(
          cls := "space-y-6",

          // Input with clear button
          div(
            h3(cls := "text-lg font-medium mb-2", "Clear Button"),
            Input <> InputProps(
              label = Some("Clearable Input"),
              placeholder = Some("Type something..."),
              showClearButton = true,
            ),
          ),

          // Input with character count
          div(
            h3(cls := "text-lg font-medium mb-2", "Character Count"),
            Input <> InputProps(
              label = Some("Tweet"),
              typ = "textarea",
              placeholder = Some("What's happening?"),
              maxLength = Some(280),
              showCharCount = true,
              rows = Some(3),
            ),
          ),

          // Password with toggle
          div(
            h3(cls := "text-lg font-medium mb-2", "Password Toggle"),
            Input <> InputProps(
              label = Some("Password with Toggle"),
              typ = "password",
              placeholder = Some("Enter your password"),
              showPasswordToggle = true,
            ),
          ),
        ),
      ),

      // Live Validation Example
      Card <> CardProps(
        title = Some("Live Validation Example"),
        children = div(
          h3(cls := "text-lg font-medium mb-4", "Username Validation"),
          p(
            cls := "mb-4 text-base-content/70",
            "Type in the field below to see live validation. Username must be at least 3 characters and can only contain letters, numbers, and underscores.",
          ),
          Input <> InputProps(
            label = Some("Username"),
            placeholder = Some("Enter a username"),
            value = Some(username),
            onChange = validateUsername,
            error = usernameError,
            showClearButton = true,
          ),
          div(
            cls := "mt-6 flex justify-end",
            Button <> ButtonProps(
              text = "Submit",
              variant = "primary",
              disabled = usernameError.isDefined || username.isEmpty,
            ),
          ),
        ),
      ),
    ),
  )
}
