package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom
import scala.collection.mutable.ArrayBuffer
import com.raquo.airstream.state.Var
import com.raquo.airstream.core.Transaction

// Internal model for active toast notifications
case class ToastItem(
    id: String,
    variant: String,
    title: Option[String],
    message: Option[String],
    children: Option[FluxusNode],
    duration: Int, // milliseconds
    position: String,
    actions: Option[FluxusNode] = None,
    timestamp: Long = System.currentTimeMillis(),
)

/** Options for configuring toast notifications */
case class ToastOptions(
    title: Option[String] = None,
    duration: Option[Int] = None, // milliseconds
    position: Option[String] = None,
    actions: Option[FluxusNode] = None,
    onClose: Option[() => Unit] = None,
)

/** Global notification manager to create and control toast notifications from anywhere in code */
object notification {
  // Shared state for active toasts
  private val activeToasts = Var[Map[String, ToastItem]](Map())

  // Flag to track if container is mounted
  private var containerMounted = false

  // Counter for generating unique IDs
  private var idCounter = 0

  // Helper to generate unique IDs
  private def generateId(): String = {
    idCounter += 1
    s"toast-$idCounter"
  }

  // Default duration in milliseconds
  private val DEFAULT_DURATION = 4500

  // Default position
  private val DEFAULT_POSITION = "top-end"

  // Initialize the container on demand
  private def ensureContainerMounted(): Unit = {
    if (!containerMounted) {
      val containerId      = "fluxus-notification-container"
      var containerElement = dom.document.getElementById(containerId)

      if (containerElement == null) {
        containerElement = dom.document.createElement("div")
        containerElement.id = containerId
        // Apply styling with class names instead of direct style manipulation
        containerElement.setAttribute("class", "fixed inset-0 pointer-events-none z-[9999]")

        dom.document.body.appendChild(containerElement)
      }

      // Render the ToastContainer into this element
      render(ToastContainer <> (), containerElement)
      containerMounted = true
    }
  }

  // Notification creation methods for standard types
  def success(
      message: String,
      options: ToastOptions = ToastOptions(),
  ): String = show(
    message = Some(message),
    title = options.title,
    variant = "success",
    duration = options.duration.getOrElse(DEFAULT_DURATION),
    position = options.position.getOrElse(DEFAULT_POSITION),
    actions = options.actions,
    onClose = options.onClose,
  )

  def error(
      message: String,
      options: ToastOptions = ToastOptions(),
  ): String = show(
    message = Some(message),
    title = options.title,
    variant = "error",
    duration = options.duration.getOrElse(DEFAULT_DURATION),
    position = options.position.getOrElse(DEFAULT_POSITION),
    actions = options.actions,
    onClose = options.onClose,
  )

  def info(
      message: String,
      options: ToastOptions = ToastOptions(),
  ): String = show(
    message = Some(message),
    title = options.title,
    variant = "info",
    duration = options.duration.getOrElse(DEFAULT_DURATION),
    position = options.position.getOrElse(DEFAULT_POSITION),
    actions = options.actions,
    onClose = options.onClose,
  )

  def warning(
      message: String,
      options: ToastOptions = ToastOptions(),
  ): String = show(
    message = Some(message),
    title = options.title,
    variant = "warning",
    duration = options.duration.getOrElse(DEFAULT_DURATION),
    position = options.position.getOrElse(DEFAULT_POSITION),
    actions = options.actions,
    onClose = options.onClose,
  )

  def neutral(
      message: String,
      options: ToastOptions = ToastOptions(),
  ): String = show(
    message = Some(message),
    title = options.title,
    variant = "neutral",
    duration = options.duration.getOrElse(DEFAULT_DURATION),
    position = options.position.getOrElse(DEFAULT_POSITION),
    actions = options.actions,
    onClose = options.onClose,
  )

  // Core show method that handles all notification types
  def show(
      message: Option[String] = None,
      title: Option[String] = None,
      children: Option[FluxusNode] = None,
      variant: String = "",
      duration: Int = DEFAULT_DURATION,
      position: String = DEFAULT_POSITION,
      actions: Option[FluxusNode] = None,
      onClose: Option[() => Unit] = None,
  ): String = {
    // Make sure container is mounted
    ensureContainerMounted()

    val id = generateId()
    val toast = ToastItem(
      id = id,
      title = title,
      message = message,
      children = children,
      variant = variant,
      duration = duration,
      position = position,
      actions = actions,
    )

    // Add the toast to active toasts
    Transaction { _ =>
      activeToasts.update(_ + (id -> toast))
    }

    // Set up auto-dismiss timer if duration > 0
    if (duration > 0) {
      dom.window.setTimeout(
        () => {
          close(id)
          // Call onClose callback if provided
          onClose.foreach(_())
        },
        duration,
      )
    }

    id
  }

  // Method to manually close a toast by ID
  def close(id: String): Unit = {
    Transaction { _ =>
      activeToasts.update(_.removed(id))
    }
  }

  // Method to close all toasts
  def closeAll(): Unit = {
    Transaction { _ =>
      activeToasts.set(Map())
    }
  }

  // Container component - shouldn't be used directly by users
  private val ToastContainer = () => {
    // Subscribe to active toasts
    val toasts = useSignal(activeToasts)

    // Group toasts by position
    val toastsByPosition = toasts.groupBy(_._2.position)

    // Container for all toast positions
    div(
      // Already have pointer-events-none on the container element

      // For each position, render a toast container with toasts
      toastsByPosition.map { case (position, positionToasts) =>
        // Use DaisyUI's toast and positioning classes
        div(
          key := s"toast-container-$position",
          cls := s"toast ${getToastPositionClass(position)}",

          // Render each toast in this position
          positionToasts.toList.sortBy(_._2.timestamp).map { case (id, toast) =>
            // Individual toast
            div(
              key := id,
              cls := "pointer-events-auto mb-2 w-96", // Enable interactions for the toast itself

              // Alert component inside the toast
              div(
                cls := s"${alertClass(toast.variant)} break-words",

                // Content
                div(
                  cls := "flex-1",
                  toast.title.map(title => div(cls := "font-bold", title)).orNull,
                  toast.message.map(message => div(message)).orNull,
                  toast.children.orNull,
                ),

                // Actions or close button
                div(
                  cls := "flex-none flex items-center gap-2",
                  toast.actions.orNull,
                  button(
                    cls     := "btn btn-sm btn-ghost",
                    onClick := (() => close(id)),
                    "✕",
                  ),
                ),
              ),
            )
          },
        )
      }.toList,
    )
  }

  // Helper to get toast position classes from DaisyUI
  private def getToastPositionClass(position: String): String = {
    position match {
      case "top-start"     => "toast-top toast-start"
      case "top-center"    => "toast-top toast-center"
      case "top-end"       => "toast-top toast-end"
      case "middle"        => "toast-middle"
      case "bottom-start"  => "toast-bottom toast-start"
      case "bottom-center" => "toast-bottom toast-center"
      case "bottom-end"    => "toast-bottom toast-end"
      case _               => "toast-bottom toast-end" // Default
    }
  }

  // Helper to get alert class based on variant
  private def alertClass(variant: String): String = {
    val base = "alert"
    val variantClass = variant match {
      case "info"    => "alert-info"
      case "success" => "alert-success"
      case "warning" => "alert-warning"
      case "error"   => "alert-error"
      case "neutral" => "alert-neutral"
      case _         => ""
    }

    if (variantClass.nonEmpty) s"$base $variantClass" else base
  }
}
