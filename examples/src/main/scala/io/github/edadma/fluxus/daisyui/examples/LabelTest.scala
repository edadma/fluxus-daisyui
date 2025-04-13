package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._
import org.scalajs.dom

def LabelTest: FluxusNode = {
  // State for form inputs (to demonstrate interactions)
  val (username, setUsername, _)               = useState("")
  val (password, setPassword, _)               = useState("")
  val (email, setEmail, _)                     = useState("")
  val (bio, setBio, _)                         = useState("")
  val (validationError, setValidationError, _) = useState(Option.empty[String])

  // Validate email on change
  useEffect(
    () => {
      if (email.nonEmpty && !email.contains("@")) {
        setValidationError(Some("Please enter a valid email address"))
      } else {
        setValidationError(None)
      }
      ()
    },
    Seq(email),
  )

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Label Component Demo",
      ),

      // Basic Label Examples
      Card <> CardProps(
        title = Some("Basic Labels"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-1 md:grid-cols-2 gap-6",

          // Simple text label
          div(
            h3(cls := "text-lg font-semibold mb-2", "Simple Text Label"),
            div(
              cls := "p-4 border rounded-box",
              Label <> LabelProps(
                text = "Username",
                className = "mb-2",
              ),
              input(
                cls         := "input input-bordered w-full",
                placeholder := "Enter username",
              ),
            ),
          ),

          // Label with required indicator
          div(
            h3(cls := "text-lg font-semibold mb-2", "Required Field Indicator"),
            div(
              cls := "p-4 border rounded-box",
              Label <> LabelProps(
                text = "Password",
                required = true,
                className = "mb-2",
              ),
              input(
                cls         := "input input-bordered w-full",
                typ         := "password",
                placeholder := "Enter password",
              ),
            ),
          ),

          // Label with alt text (hint)
          div(
            h3(cls := "text-lg font-semibold mb-2", "Label with Hint Text"),
            div(
              cls := "p-4 border rounded-box",
              Label <> LabelProps(
                text = "Email Address",
                altText = Some("We'll never share your email"),
                className = "mb-2",
              ),
              input(
                cls         := "input input-bordered w-full",
                typ         := "email",
                placeholder := "Enter email",
              ),
            ),
          ),

          // Label with error text
          div(
            h3(cls := "text-lg font-semibold mb-2", "Label with Error Text"),
            div(
              cls := "p-4 border rounded-box",
              Label <> LabelProps(
                text = "Email Address",
                altText = Some("Invalid email format"),
                altVariant = "error",
                className = "mb-2",
              ),
              input(
                cls         := "input input-bordered input-error w-full",
                typ         := "email",
                placeholder := "Enter email",
              ),
            ),
          ),
        ),
      ),

      // Label Positioning
      Card <> CardProps(
        title = Some("Label Positioning"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-1 md:grid-cols-2 gap-6",

          // Default position
          div(
            h3(cls := "text-lg font-semibold mb-2", "Default Position"),
            div(
              cls := "p-4 border rounded-box",
              div(
                cls := "form-control",
                Label <> LabelProps(
                  text = "Username",
                  position = "default",
                ),
                input(
                  cls         := "input input-bordered w-full",
                  placeholder := "Enter username",
                ),
              ),
            ),
          ),

          // Top position
          div(
            h3(cls := "text-lg font-semibold mb-2", "Top Position"),
            div(
              cls := "p-4 border rounded-box",
              div(
                cls := "form-control",
                Label <> LabelProps(
                  text = "Password",
                  position = "top",
                  required = true,
                ),
                input(
                  cls         := "input input-bordered w-full",
                  typ         := "password",
                  placeholder := "Enter password",
                ),
              ),
            ),
          ),

          // Left position (using grid)
          div(
            h3(cls := "text-lg font-semibold mb-2", "Side-by-side with Grid"),
            div(
              cls := "p-4 border rounded-box",
              div(
                cls := "grid grid-cols-4 items-center gap-2",
                Label <> LabelProps(
                  text = "Email",
                  position = "left",
                  className = "col-span-1",
                ),
                div(
                  cls := "col-span-3",
                  input(
                    cls         := "input input-bordered w-full",
                    typ         := "email",
                    placeholder := "Enter email",
                  ),
                ),
              ),
            ),
          ),

          // With swapped positions
          div(
            h3(cls := "text-lg font-semibold mb-2", "Swapped Labels"),
            div(
              cls := "p-4 border rounded-box",
              div(
                cls := "form-control",
                Label <> LabelProps(
                  text = "Bio",
                  altText = Some("Share a little about yourself"),
                  position = "top",
                  swap = true,
                ),
                textarea(
                  cls         := "textarea textarea-bordered w-full",
                  placeholder := "Write something about yourself",
                  rows        := "3",
                ),
              ),
            ),
          ),
        ),
      ),

      // Styling Variations
      Card <> CardProps(
        title = Some("Label Styling Variations"),
        className = "mb-8",
        children = div(
          cls := "grid grid-cols-1 md:grid-cols-2 gap-6",

          // Size variations
          div(
            h3(cls := "text-lg font-semibold mb-2", "Size Variations"),
            div(
              cls := "p-4 border rounded-box space-y-4",
              div(
                Label <> LabelProps(
                  text = "Extra Small Label",
                  size = "xs",
                ),
              ),
              div(
                Label <> LabelProps(
                  text = "Small Label",
                  size = "sm",
                ),
              ),
              div(
                Label <> LabelProps(
                  text = "Medium Label (Default)",
                  size = "md",
                ),
              ),
              div(
                Label <> LabelProps(
                  text = "Large Label",
                  size = "lg",
                ),
              ),
            ),
          ),

          // Color variants
          div(
            h3(cls := "text-lg font-semibold mb-2", "Color Variants"),
            div(
              cls := "p-4 border rounded-box space-y-4",
              div(
                Label <> LabelProps(
                  text = "Primary Color",
                  variant = "primary",
                ),
              ),
              div(
                Label <> LabelProps(
                  text = "Secondary Color",
                  variant = "secondary",
                ),
              ),
              div(
                Label <> LabelProps(
                  text = "Accent Color",
                  variant = "accent",
                ),
              ),
              div(
                Label <> LabelProps(
                  text = "Success Color",
                  variant = "success",
                ),
              ),
              div(
                Label <> LabelProps(
                  text = "Warning Color",
                  variant = "warning",
                ),
              ),
              div(
                Label <> LabelProps(
                  text = "Error Color",
                  variant = "error",
                ),
              ),
            ),
          ),
        ),
      ),

      // Interactive Form Example
      Card <> CardProps(
        title = Some("Interactive Form Example"),
        className = "mb-8",
        children = div(
          cls := "p-4 border rounded-box",
          form(
            cls := "space-y-4",
            onSubmit := ((e: dom.Event) => {
              e.preventDefault()
              dom.window.alert("Form submitted!")
            }),

            // Username field
            div(
              cls := "form-control",
              Label <> LabelProps(
                text = "Username",
                position = "top",
                required = true,
              ),
              input(
                cls   := "input input-bordered w-full",
                value := username,
                onInput := ((e: dom.Event) =>
                  setUsername(e.target.asInstanceOf[dom.html.Input].value)
                ),
              ),
            ),

            // Email field with validation
            div(
              cls := "form-control",
              Label <> LabelProps(
                text = "Email Address",
                position = "top",
                required = true,
                altText = validationError,
                altVariant = if (validationError.isDefined) "error" else "",
              ),
              input(
                cls   := s"input input-bordered w-full ${if (validationError.isDefined) "input-error" else ""}",
                value := email,
                onInput := ((e: dom.Event) =>
                  setEmail(e.target.asInstanceOf[dom.html.Input].value)
                ),
              ),
            ),

            // Password field
            div(
              cls := "form-control",
              Label <> LabelProps(
                text = "Password",
                position = "top",
                required = true,
                altText = Some("Must be at least 8 characters"),
                altVariant = "info",
              ),
              input(
                cls   := "input input-bordered w-full",
                typ   := "password",
                value := password,
                onInput := ((e: dom.Event) =>
                  setPassword(e.target.asInstanceOf[dom.html.Input].value)
                ),
              ),
            ),

            // Bio field
            div(
              cls := "form-control",
              Label <> LabelProps(
                text = "Bio",
                position = "top",
                altText = Some("Optional"),
              ),
              textarea(
                cls         := "textarea textarea-bordered w-full",
                placeholder := "Tell us about yourself",
                rows        := "3",
                value       := bio,
                onInput := ((e: dom.Event) =>
                  setBio(e.target.asInstanceOf[dom.html.TextArea].value)
                ),
              ),
            ),

            // Submit button
            div(
              cls := "pt-2",
              Button <> ButtonProps(
                text = "Submit Form",
                buttonType = "submit",
                disabled = email.nonEmpty && validationError.isDefined,
              ),
            ),
          ),
        ),
      ),

      // Floating Label Example
      Card <> CardProps(
        title = Some("Floating Label Example"),
        children = div(
          cls := "p-4 border rounded-box",
          div(
            cls := "form-control",
            div(
              cls := "relative",
              input(
                id          := "floating-input",
                cls         := "input input-bordered w-full pt-5",
                placeholder := " ",
              ),
              Label <> LabelProps(
                text = "Floating Label",
                htmlFor = Some("floating-input"),
                position = "floating",
                className =
                  "absolute text-sm text-gray-500 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] left-4 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-4",
              ),
            ),
            p(
              cls := "text-xs text-base-content/60 mt-1 ml-1",
              "Note: Floating labels require additional CSS classes for transitions",
            ),
          ),
        ),
      ),
    ),
  )
}
