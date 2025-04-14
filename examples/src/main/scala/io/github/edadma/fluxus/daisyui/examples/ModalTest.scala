package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def ModalTest: FluxusNode = {
  // State for basic modal
  val (isBasicModalOpen, setBasicModalOpen, _) = useState(false)

  // State for different sized modals
  val (isSmallModalOpen, setSmallModalOpen, _)   = useState(false)
  val (isMediumModalOpen, setMediumModalOpen, _) = useState(false)
  val (isLargeModalOpen, setLargeModalOpen, _)   = useState(false)

  // State for positioned modals
  val (isTopModalOpen, setTopModalOpen, _)       = useState(false)
  val (isBottomModalOpen, setBottomModalOpen, _) = useState(false)

  // State for preset modals
  val (isInfoModalOpen, setInfoModalOpen, _)       = useState(false)
  val (isSuccessModalOpen, setSuccessModalOpen, _) = useState(false)
  val (isWarningModalOpen, setWarningModalOpen, _) = useState(false)
  val (isErrorModalOpen, setErrorModalOpen, _)     = useState(false)
  val (isConfirmModalOpen, setConfirmModalOpen, _) = useState(false)
  val (isDeleteModalOpen, setDeleteModalOpen, _)   = useState(false)

  // State for form modal
  val (isFormModalOpen, setFormModalOpen, _) = useState(false)
  val (formName, setFormName, _)             = useState("")
  val (formEmail, setFormEmail, _)           = useState("")

  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Modal Component Demo",
      ),

      // Basic Modal Section
      Card <> CardProps(
        title = Some("Basic Modal"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "The basic modal provides a simple dialog with customizable content, title, and footer.",
          ),
          Button <> ButtonProps(
            text = "Open Basic Modal",
            variant = "primary",
            onClick = () => setBasicModalOpen(true),
          ),

          // Basic Modal
          Modal <> ModalProps(
            isOpen = isBasicModalOpen,
            onClose = () => setBasicModalOpen(false),
            title = Some("Basic Modal"),
            footer = Some(
              div(
                cls := "flex justify-end",
                Button <> ButtonProps(
                  text = "Close",
                  onClick = () => setBasicModalOpen(false),
                ),
              ),
            ),
            children = div(
              p("This is a basic modal with a title, content, and a custom footer."),
              p(
                cls := "mt-2",
                "Click the close button, the X in the corner, or outside the modal to close it.",
              ),
            ),
          ),
        ),
      ),

      // Modal Sizes Section
      Card <> CardProps(
        title = Some("Modal Sizes"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Modals can be sized according to your content needs with the size prop.",
          ),
          div(
            cls := "flex flex-wrap gap-2",
            Button <> ButtonProps(
              text = "Small Modal",
              variant = "primary",
              onClick = () => setSmallModalOpen(true),
            ),
            Button <> ButtonProps(
              text = "Medium Modal",
              variant = "primary",
              onClick = () => setMediumModalOpen(true),
            ),
            Button <> ButtonProps(
              text = "Large Modal",
              variant = "primary",
              onClick = () => setLargeModalOpen(true),
            ),
          ),

          // Small Modal
          Modal <> ModalProps(
            isOpen = isSmallModalOpen,
            onClose = () => setSmallModalOpen(false),
            title = Some("Small Modal"),
            size = "sm",
            children = p("This is a small modal with compact width."),
          ),

          // Medium Modal
          Modal <> ModalProps(
            isOpen = isMediumModalOpen,
            onClose = () => setMediumModalOpen(false),
            title = Some("Medium Modal"),
            size = "md",
            children = p("This is a medium-sized modal (default size)."),
          ),

          // Large Modal
          Modal <> ModalProps(
            isOpen = isLargeModalOpen,
            onClose = () => setLargeModalOpen(false),
            title = Some("Large Modal"),
            size = "lg",
            children = div(
              p("This is a large modal with extra room for content."),
              p(
                cls := "mt-2",
                "Use this when you need to present more complex information.",
              ),
            ),
          ),
        ),
      ),

      // Modal Positions Section
      Card <> CardProps(
        title = Some("Modal Positions"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Modals can be positioned at the top, center (default), or bottom of the screen.",
          ),
          div(
            cls := "flex flex-wrap gap-2",
            Button <> ButtonProps(
              text = "Top Modal",
              variant = "primary",
              onClick = () => setTopModalOpen(true),
            ),
            Button <> ButtonProps(
              text = "Bottom Modal",
              variant = "primary",
              onClick = () => setBottomModalOpen(true),
            ),
          ),

          // Top Modal
          Modal <> ModalProps(
            isOpen = isTopModalOpen,
            onClose = () => setTopModalOpen(false),
            title = Some("Top Modal"),
            position = "top",
            children = p("This modal appears at the top of the screen."),
          ),

          // Bottom Modal
          Modal <> ModalProps(
            isOpen = isBottomModalOpen,
            onClose = () => setBottomModalOpen(false),
            title = Some("Bottom Modal"),
            position = "bottom",
            children = p("This modal appears at the bottom of the screen."),
          ),
        ),
      ),

      // Preset Modals Section
      Card <> CardProps(
        title = Some("Preset Modals"),
        className = "mb-8",
        children = div(
          p(
            cls := "mb-4",
            "Pre-built modal variants for common use cases like information, confirmation, warnings, or errors.",
          ),
          div(
            cls := "flex flex-wrap gap-2",
            Button <> ButtonProps(
              text = "Info Modal",
              variant = "info",
              onClick = () => setInfoModalOpen(true),
            ),
            Button <> ButtonProps(
              text = "Success Modal",
              variant = "success",
              onClick = () => setSuccessModalOpen(true),
            ),
            Button <> ButtonProps(
              text = "Warning Modal",
              variant = "warning",
              onClick = () => setWarningModalOpen(true),
            ),
            Button <> ButtonProps(
              text = "Error Modal",
              variant = "error",
              onClick = () => setErrorModalOpen(true),
            ),
            Button <> ButtonProps(
              text = "Confirm Modal",
              variant = "primary",
              onClick = () => setConfirmModalOpen(true),
            ),
            Button <> ButtonProps(
              text = "Delete Confirmation",
              variant = "error",
              onClick = () => setDeleteModalOpen(true),
            ),
          ),

          // Info Modal
          ModalPresets.info(
            title = "Information",
            content = "This is some important information you should know about.",
            isOpen = isInfoModalOpen,
            onClose = () => setInfoModalOpen(false),
          ),

          // Success Modal
          ModalPresets.success(
            title = "Success!",
            content = "Your action was completed successfully.",
            isOpen = isSuccessModalOpen,
            onClose = () => setSuccessModalOpen(false),
          ),

          // Warning Modal
          ModalPresets.warning(
            title = "Warning",
            content = "This action might have unexpected consequences.",
            isOpen = isWarningModalOpen,
            onClose = () => setWarningModalOpen(false),
          ),

          // Error Modal
          ModalPresets.error(
            title = "Error",
            content = "Something went wrong. Please try again.",
            isOpen = isErrorModalOpen,
            onClose = () => setErrorModalOpen(false),
          ),

          // Confirm Modal
          ModalPresets.confirm(
            title = "Confirm Action",
            content = "Are you sure you want to proceed with this action?",
            isOpen = isConfirmModalOpen,
            onConfirm = () => {
              // Handle confirmation
              println("Action confirmed!")
            },
            onClose = () => setConfirmModalOpen(false),
          ),

          // Delete Confirmation Modal
          ModalPresets.delete(
            isOpen = isDeleteModalOpen,
            onConfirm = () => {
              // Handle deletion
              println("Item deleted!")
            },
            onClose = () => setDeleteModalOpen(false),
          ),
        ),
      ),

      // Form Modal Example
      Card <> CardProps(
        title = Some("Modal with Form"),
        children = div(
          p(
            cls := "mb-4",
            "Modals are perfect for forms and user input that doesn't require a page navigation.",
          ),
          Button <> ButtonProps(
            text = "Open Form Modal",
            variant = "primary",
            onClick = () => setFormModalOpen(true),
          ),

          // Form Modal
          Modal <> ModalProps(
            isOpen = isFormModalOpen,
            onClose = () => setFormModalOpen(false),
            title = Some("Contact Form"),
            footer = Some(
              div(
                cls := "flex justify-end",
                Button <> ButtonProps(
                  text = "Cancel",
                  variant = "ghost",
                  onClick = () => setFormModalOpen(false),
                ),
                Button <> ButtonProps(
                  text = "Submit",
                  variant = "primary",
                  onClick = () => {
                    // Handle form submission
                    println(s"Form submitted with name: $formName, email: $formEmail")
                    setFormModalOpen(false)
                  },
                ),
              ),
            ),
            children = div(
              cls := "space-y-4",

              // Name field
              div(
                cls := "form-control",
                label(cls := "label", span(cls := "label-text", "Name")),
                Input <> InputProps(
                  value = Some(formName),
                  onChange = setFormName,
                  placeholder = Some("Enter your name"),
                ),
              ),

              // Email field
              div(
                cls := "form-control",
                label(cls := "label", span(cls := "label-text", "Email")),
                Input <> InputProps(
                  typ = "email",
                  value = Some(formEmail),
                  onChange = setFormEmail,
                  placeholder = Some("Enter your email"),
                ),
              ),

              // Message field
              div(
                cls := "form-control",
                label(cls := "label", span(cls := "label-text", "Message")),
                Input <> InputProps(
                  typ = "textarea",
                  rows = Some(4),
                  placeholder = Some("Your message"),
                ),
              ),
            ),
          ),
        ),
      ),
    ),
  )
}
