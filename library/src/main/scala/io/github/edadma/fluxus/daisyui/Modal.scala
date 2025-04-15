package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom

/** Props for Modal component with DaisyUI styling options
  */
case class ModalProps(
    // Content
    title: Option[String] = None,
    children: FluxusNode,
    footer: Option[FluxusNode] = None,

    // State
    isOpen: Boolean = false,
    onClose: () => Unit = () => (),

    // Styling
    size: String = "md",           // xs, sm, md, lg, xl
    position: String = "middle",   // middle, top, bottom
    responsive: Boolean = true,    // Adjust for responsive layouts
    backdrop: Boolean = true,      // Show backdrop overlay
    backdropClose: Boolean = true, // Close on backdrop click
    closeOnEscKey: Boolean = true, // Close on Escape key
    animation: Boolean = true,     // Whether to animate the modal
    closeButton: Boolean = true,   // Show X close button in top-right

    // Additional styling
    className: String = "",
    headerClassName: String = "",
    bodyClassName: String = "",
    footerClassName: String = "",

    // Accessibility
    ariaLabelledby: Option[String] = None,
    ariaDescribedby: Option[String] = None,
)

/** Modal component using DaisyUI styling
  *
  * Features:
  *   - Configurable size and position
  *   - Optional backdrop with click-to-close
  *   - Optional close button
  *   - Customizable header, body, and footer
  *   - Keyboard support (escape to close)
  *   - Accessibility attributes
  */
val Modal: ModalProps => FluxusNode = (props: ModalProps) => {
  // Trap focus within modal when open
  val modalRef = useRef[dom.html.Div]()

  // Generate unique ID for accessibility
  val modalId = useRef[String]()
  if (modalId.current == null) {
    modalId.current = s"modal-${scala.util.Random.alphanumeric.take(8).mkString}"
  }

  // Effect for keyboard navigation and scroll lock
  useEffect(
    () => {
      if (props.isOpen) {
        // Handle ESC key press
        if (props.closeOnEscKey) {
          val handleKeyDown = (e: dom.KeyboardEvent) => {
            if (e.key == "Escape") {
              props.onClose()
            }
          }

          dom.document.addEventListener("keydown", handleKeyDown)

          // Return cleanup function
          () => dom.document.removeEventListener("keydown", handleKeyDown)
        } else {
          () => ()
        }
      } else {
        () => ()
      }
    },
    Seq(props.isOpen, props.closeOnEscKey),
  )

  // Handle backdrop click
  def handleBackdropClick(e: dom.MouseEvent): Unit = {
    if (props.backdropClose && e.target == e.currentTarget) {
      props.onClose()
    }
  }

  // If modal is not open, render nothing
  if (!props.isOpen) {
    null
  } else {
    // Build modal classes
    val modalClasses = List.newBuilder[String]

    // Base class is always present
    modalClasses += "modal"

    // Open state
    modalClasses += "modal-open"

    // Position class
    val positionClass = props.position match {
      case "top"    => "modal-top"
      case "bottom" => "modal-bottom"
      case _        => "" // Default is middle
    }
    if (positionClass.nonEmpty) modalClasses += positionClass

    // Add any custom classes
    if (props.className.nonEmpty) {
      props.className.split(" ").foreach(modalClasses += _)
    }

    // Modal content classes
    val modalBoxClasses = List.newBuilder[String]

    // Base class
    modalBoxClasses += "modal-box"

    // Size class - map to appropriate Tailwind classes
    val sizeClass = props.size match {
      case "xs" => "max-w-xs"
      case "sm" => "max-w-sm"
      case "lg" => "max-w-3xl"
      case "xl" => "max-w-5xl"
      case _    => "max-w-md" // Default is medium
    }
    modalBoxClasses += sizeClass

    // Animation class
    if (!props.animation) modalBoxClasses += "no-animation"

    // Build CSS classes
    val modalClass    = modalClasses.result().mkString(" ")
    val modalBoxClass = modalBoxClasses.result().mkString(" ")

    // Render the modal
    div(
      ref          := modalRef,
      role         := "dialog",
      cls          := modalClass,
      "aria_modal" := "true",
      props.ariaLabelledby.map(id => aria_labelledby := id).getOrElse(aria_labelledby := s"${modalId.current}-title"),
      props.ariaDescribedby.map(id => aria_describedby := id).orNull,
      onClick := ((e: dom.MouseEvent) => handleBackdropClick(e)),

      // Modal backdrop (shown when props.backdrop is true)
      if (props.backdrop) div(cls := "modal-backdrop") else null,

      // Modal content box
      div(
        cls := modalBoxClass,

        // Close button (if enabled)
        if (props.closeButton) {
          button(
            cls        := "btn btn-sm btn-circle absolute right-2 top-2",
            onClick    := (() => props.onClose()),
            aria_label := "Close modal",

            // X icon
            svg(
              xmlns          := "http://www.w3.org/2000/svg",
              cls            := "h-4 w-4",
              fill           := "none",
              viewBox        := "0 0 24 24",
              stroke         := "currentColor",
              strokeWidth    := "2",
              strokeLinecap  := "round",
              strokeLinejoin := "round",
              path(d := "M6 18L18 6M6 6l12 12"),
            ),
          )
        } else null,

        // Title (if provided)
        props.title.map(title =>
          h3(
            id  := s"${modalId.current}-title",
            cls := s"font-bold text-lg ${props.headerClassName}",
            title,
          ),
        ).orNull,

        // Modal body
        div(
          cls := s"mt-4 ${props.bodyClassName}",
          props.children,
        ),

        // Footer (if provided)
        props.footer.map(footer =>
          div(
            cls := s"modal-action ${props.footerClassName}",
            footer,
          ),
        ).orNull,
      ),
    )
  }
}

/** Confirmation dialog props
  */
case class ConfirmModalProps(
    title: String,
    content: FluxusNode,
    isOpen: Boolean,
    onClose: () => Unit,
    onConfirm: () => Unit,
    onCancel: () => Unit = () => (),
    confirmText: String = "Confirm",
    cancelText: String = "Cancel",
    confirmButtonProps: ButtonProps = ButtonProps(),
    cancelButtonProps: ButtonProps = ButtonProps(variant = "ghost"),
    icon: FluxusNode = null,
    variant: String = "info", // info, success, warning, error
)

/** Confirmation dialog component
  *
  * Pre-built modal component with confirm/cancel actions
  */
val ConfirmModal = (props: ConfirmModalProps) => {
  // Default icons based on variant
  def getDefaultIcon(variant: String): FluxusNode = {
    variant match {
      case "success" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "h-12 w-12 mx-auto mb-4 text-success",
          fill           := "none",
          viewBox        := "0 0 24 24",
          stroke         := "currentColor",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
      case "warning" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "h-12 w-12 mx-auto mb-4 text-warning",
          fill           := "none",
          viewBox        := "0 0 24 24",
          stroke         := "currentColor",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(
            d := "M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z",
          ),
        )
      case "error" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "h-12 w-12 mx-auto mb-4 text-error",
          fill           := "none",
          viewBox        := "0 0 24 24",
          stroke         := "currentColor",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
      case _ => // info or default
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "h-12 w-12 mx-auto mb-4 text-info",
          fill           := "none",
          viewBox        := "0 0 24 24",
          stroke         := "currentColor",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
    }
  }

  // Footer with action buttons
  val footer = div(
    cls := "flex gap-2 justify-end",

    // Cancel button
    Button <> props.cancelButtonProps.copy(
      text = props.cancelText,
      onClick = () => {
        props.onCancel()
        props.onClose()
      },
    ),

    // Confirm button
    Button <> props.confirmButtonProps.copy(
      text = props.confirmText,
      onClick = () => {
        props.onConfirm()
        props.onClose()
      },
    ),
  )

  // Determine which icon to use
  val icon = if (props.icon != null) props.icon else getDefaultIcon(props.variant)

  // The modal component
  Modal <> ModalProps(
    isOpen = props.isOpen,
    title = Some(props.title),
    onClose = props.onClose,
    footer = Some(footer),
    children = div(
      cls := "text-center",
      icon,
      div(props.content),
    ),
  )
}

/** Alert modal props
  */
case class AlertModalProps(
    title: String,
    content: FluxusNode,
    isOpen: Boolean,
    onClose: () => Unit,
    buttonText: String = "OK",
    buttonProps: ButtonProps = ButtonProps(),
    icon: FluxusNode = null,
    variant: String = "info", // info, success, warning, error
)

/** Alert modal component
  *
  * Pre-built modal component with a single action button
  */
val AlertModal = (props: AlertModalProps) => {
  // Default icons based on variant (reused from ConfirmModal)
  def getDefaultIcon(variant: String): FluxusNode = {
    variant match {
      case "success" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "h-12 w-12 mx-auto mb-4 text-success",
          fill           := "none",
          viewBox        := "0 0 24 24",
          stroke         := "currentColor",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
      case "warning" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "h-12 w-12 mx-auto mb-4 text-warning",
          fill           := "none",
          viewBox        := "0 0 24 24",
          stroke         := "currentColor",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(
            d := "M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z",
          ),
        )
      case "error" =>
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "h-12 w-12 mx-auto mb-4 text-error",
          fill           := "none",
          viewBox        := "0 0 24 24",
          stroke         := "currentColor",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
      case _ => // info or default
        svg(
          xmlns          := "http://www.w3.org/2000/svg",
          cls            := "h-12 w-12 mx-auto mb-4 text-info",
          fill           := "none",
          viewBox        := "0 0 24 24",
          stroke         := "currentColor",
          strokeWidth    := "2",
          strokeLinecap  := "round",
          strokeLinejoin := "round",
          path(d := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"),
        )
    }
  }

  // Footer with action button
  val footer = div(
    cls := "flex justify-center",

    // OK button
    Button <> props.buttonProps.copy(
      text = props.buttonText,
      onClick = () => props.onClose(),
    ),
  )

  // Determine which icon to use
  val icon = if (props.icon != null) props.icon else getDefaultIcon(props.variant)

  // The modal component
  Modal <> ModalProps(
    isOpen = props.isOpen,
    title = Some(props.title),
    onClose = props.onClose,
    footer = Some(footer),
    children = div(
      cls := "text-center",
      icon,
      div(props.content),
    ),
  )
}

// Pre-built modal variants
object ModalPresets {
  // Info modal - convenience function
  def info(
      title: String,
      content: FluxusNode,
      isOpen: Boolean,
      onClose: () => Unit,
      buttonText: String = "OK",
  ): FluxusNode = {
    AlertModal <> AlertModalProps(
      title = title,
      content = content,
      isOpen = isOpen,
      onClose = onClose,
      buttonText = buttonText,
      buttonProps = ButtonProps(variant = "info"),
    )
  }

  // Success modal - convenience function
  def success(
      title: String,
      content: FluxusNode,
      isOpen: Boolean,
      onClose: () => Unit,
      buttonText: String = "OK",
  ): FluxusNode = {
    AlertModal <> AlertModalProps(
      title = title,
      content = content,
      isOpen = isOpen,
      onClose = onClose,
      buttonText = buttonText,
      buttonProps = ButtonProps(variant = "success"),
      variant = "success",
    )
  }

  // Warning modal - convenience function
  def warning(
      title: String,
      content: FluxusNode,
      isOpen: Boolean,
      onClose: () => Unit,
      buttonText: String = "OK",
  ): FluxusNode = {
    AlertModal <> AlertModalProps(
      title = title,
      content = content,
      isOpen = isOpen,
      onClose = onClose,
      buttonText = buttonText,
      buttonProps = ButtonProps(variant = "warning"),
      variant = "warning",
    )
  }

  // Error modal - convenience function
  def error(
      title: String,
      content: FluxusNode,
      isOpen: Boolean,
      onClose: () => Unit,
      buttonText: String = "OK",
  ): FluxusNode = {
    AlertModal <> AlertModalProps(
      title = title,
      content = content,
      isOpen = isOpen,
      onClose = onClose,
      buttonText = buttonText,
      buttonProps = ButtonProps(variant = "error"),
      variant = "error",
    )
  }

  // Confirm modal - convenience function
  def confirm(
      title: String,
      content: FluxusNode,
      isOpen: Boolean,
      onConfirm: () => Unit,
      onClose: () => Unit,
      onCancel: () => Unit = () => (),
      confirmText: String = "Confirm",
      cancelText: String = "Cancel",
  ): FluxusNode = {
    ConfirmModal <> ConfirmModalProps(
      title = title,
      content = content,
      isOpen = isOpen,
      onClose = onClose,
      onConfirm = onConfirm,
      onCancel = onCancel,
      confirmText = confirmText,
      cancelText = cancelText,
      variant = "warning",
    )
  }

  // Delete confirmation modal - convenience function
  def delete(
      title: String = "Confirm Deletion",
      content: FluxusNode = "Are you sure you want to delete this item? This action cannot be undone.",
      isOpen: Boolean,
      onConfirm: () => Unit,
      onClose: () => Unit,
      onCancel: () => Unit = () => (),
      confirmText: String = "Delete",
      cancelText: String = "Cancel",
  ): FluxusNode = {
    ConfirmModal <> ConfirmModalProps(
      title = title,
      content = content,
      isOpen = isOpen,
      onClose = onClose,
      onConfirm = onConfirm,
      onCancel = onCancel,
      confirmText = confirmText,
      cancelText = cancelText,
      confirmButtonProps = ButtonProps(variant = "error"),
      variant = "error",
    )
  }
}
