package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._
import org.scalajs.dom
import scala.concurrent.duration._

/** Props for Toast notification component */
case class ToastProps(
    // Content options
    title: Option[String] = None,
    message: Option[String] = None,
    children: Option[FluxusNode] = None,

    // Display options
    variant: String = "info",          // info, success, warning, error, neutral
    icon: Option[FluxusNode] = None,   // Custom icon (if not using default)
    showIcon: Boolean = true,          // Whether to show icon
    position: String = "bottom-right", // top-right, top-left, bottom-right, bottom-left, top-center, bottom-center

    // Behavior options
    duration: Option[Int] = Some(3000), // Duration in ms (None for persistent)
    onClose: Option[() => Unit] = None, // Close callback
    closable: Boolean = true,           // Whether to show close button

    // Animation options
    animationIn: String = "animate-in slide-in-from-right fade-in",
    animationOut: String = "animate-out slide-out-to-right fade-out",

    // Styling options
    className: String = "",
    maxWidth: String = "max-w-sm", // Max width of toast
    shadow: String = "shadow-lg",  // Shadow style
)

/** Toast notification component using DaisyUI styling
  *
  * Features:
  *   - Multiple variants (info, success, warning, error, neutral)
  *   - Custom or automatic positioning
  *   - Auto-dismiss with configurable duration
  *   - Smooth animations
  *   - Close button
  *   - Default icons based on variant
  */
val Toast = (props: ToastProps) => {
  // State for controlling visibility during animation
  val (isVisible, setIsVisible, _) = useState(true)
  val (isLeaving, setIsLeaving, _) = useState(false)

  // Timeout for auto-dismiss
  val timerRef = useRef[Option[Int]](None)

  // Effect for handling auto-dismiss timer
  useEffect(
    () => {
      // Set up auto-dismiss timer if duration is provided
      if (props.duration.isDefined && props.duration.get > 0) {
        val timerId = dom.window.setTimeout(
          () => {
            handleClose()
          },
          props.duration.get,
        )

        timerRef.current = Some(timerId)
      }

      // Cleanup function to clear timer on unmount
      () => {
        timerRef.current.foreach(dom.window.clearTimeout)
      }
    },
    Seq(), // Empty deps - only run on mount
  )

  // Handle manual close
  def handleClose(): Unit = {
    // Start exit animation
    setIsLeaving(true)

    // Actually remove after animation completes
    dom.window.setTimeout(
      () => {
        setIsVisible(false)
        props.onClose.foreach(_())
      },
      300,
    ) // Match animation duration
  }

  // Get default icon based on variant
  def getDefaultIcon(): FluxusNode = {
    props.variant match {
      case "info" =>
        svg(
          xmlns   := "http://www.w3.org/2000/svg",
          cls     := "h-6 w-6 stroke-current flex-shrink-0",
          fill    := "none",
          viewBox := "0 0 24 24",
          path(
            strokeLinecap  := "round",
            strokeLinejoin := "round",
            strokeWidth    := "2",
            d              := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z",
          ),
        )
      case "success" =>
        svg(
          xmlns   := "http://www.w3.org/2000/svg",
          cls     := "h-6 w-6 stroke-current flex-shrink-0",
          fill    := "none",
          viewBox := "0 0 24 24",
          path(
            strokeLinecap  := "round",
            strokeLinejoin := "round",
            strokeWidth    := "2",
            d              := "M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z",
          ),
        )
      case "warning" =>
        svg(
          xmlns   := "http://www.w3.org/2000/svg",
          cls     := "h-6 w-6 stroke-current flex-shrink-0",
          fill    := "none",
          viewBox := "0 0 24 24",
          path(
            strokeLinecap  := "round",
            strokeLinejoin := "round",
            strokeWidth    := "2",
            d := "M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z",
          ),
        )
      case "error" =>
        svg(
          xmlns   := "http://www.w3.org/2000/svg",
          cls     := "h-6 w-6 stroke-current flex-shrink-0",
          fill    := "none",
          viewBox := "0 0 24 24",
          path(
            strokeLinecap  := "round",
            strokeLinejoin := "round",
            strokeWidth    := "2",
            d              := "M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z",
          ),
        )
      case _ =>
        svg(
          xmlns   := "http://www.w3.org/2000/svg",
          cls     := "h-6 w-6 stroke-current flex-shrink-0",
          fill    := "none",
          viewBox := "0 0 24 24",
          path(
            strokeLinecap  := "round",
            strokeLinejoin := "round",
            strokeWidth    := "2",
            d              := "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z",
          ),
        )
    }
  }

  // If toast is not visible, don't render anything
  if (!isVisible) {
    null
  } else {
    // Calculate alert classes (using DaisyUI alert component as base)
    val alertClasses = List.newBuilder[String]

    // Base class is always present
    alertClasses += "alert"

    // Add variant class
    val variantClass = props.variant match {
      case "info"    => "alert-info"
      case "success" => "alert-success"
      case "warning" => "alert-warning"
      case "error"   => "alert-error"
      case "neutral" => "alert-neutral"
      case _         => "alert-info" // Default to info
    }
    alertClasses += variantClass

    // Add animation classes
    if (isLeaving) {
      alertClasses += props.animationOut
    } else {
      alertClasses += props.animationIn
    }

    // Add styling classes
    alertClasses += props.shadow
    alertClasses += props.maxWidth

    // Add any custom classes
    if (props.className.nonEmpty) {
      props.className.split(" ").foreach(cls => alertClasses += cls)
    }

    // Join classes
    val alertClass = alertClasses.result().mkString(" ")

    // Determine content
    val content =
      if (props.children.isDefined) {
        // Use custom children content
        props.children.get
      } else {
        // Use title and/or message
        div(
          props.title.map(titleText =>
            h3(cls := "font-bold", titleText),
          ).orNull,
          props.message.map(messageText =>
            div(cls := (if (props.title.isDefined) "mt-1" else ""), messageText),
          ).orNull,
        )
      }

    // Main component render
    div(
      cls  := alertClass,
      role := "alert",

      // Left side: Icon (if enabled)
      if (props.showIcon) {
        props.icon.getOrElse(getDefaultIcon())
      } else null,

      // Middle: Content
      div(
        cls := "flex-1",
        content,
      ),

      // Right side: Close button (if enabled)
      if (props.closable) {
        button(
          cls     := "btn btn-sm btn-ghost",
          onClick := (() => handleClose()),
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
    )
  }
}

/** Container for all toast notifications This component should be mounted once at the application root
  */
object ToastContainer {
  // Singleton module to manage toast notifications
  // Create and store a unique ID for each toast
  private var toastCounter  = 0
  private val toastRegistry = scala.collection.mutable.Map[String, ToastEntry]()

  // Class to hold toast data
  private case class ToastEntry(
      id: String,
      props: ToastProps,
      createdAt: Long = System.currentTimeMillis(),
  )

  // Functions to create notifications programmatically
  def success(message: String, options: ToastProps = ToastProps()): String = {
    show(options.copy(
      message = Some(message),
      variant = "success",
    ))
  }

  def error(message: String, options: ToastProps = ToastProps()): String = {
    show(options.copy(
      message = Some(message),
      variant = "error",
    ))
  }

  def info(message: String, options: ToastProps = ToastProps()): String = {
    show(options.copy(
      message = Some(message),
      variant = "info",
    ))
  }

  def warning(message: String, options: ToastProps = ToastProps()): String = {
    show(options.copy(
      message = Some(message),
      variant = "warning",
    ))
  }

  // Method to add toast to the registry
  def show(props: ToastProps): String = {
    val id = s"toast-${toastCounter}"
    toastCounter += 1

    // Configure close handler to remove toast
    val newProps = props.copy(
      onClose = Some(() => {
        // Chain the original onClose if it exists
        props.onClose.foreach(_())
        // Remove from registry
        toastRegistry.remove(id)
        // Force update of all containers
        notifyContainers()
      }),
    )

    // Add to registry
    toastRegistry.put(id, ToastEntry(id, newProps))

    // Notify all containers to re-render
    notifyContainers()

    // Return the ID for later reference
    id
  }

  // Method to remove a specific toast
  def remove(id: String): Unit = {
    toastRegistry.remove(id)
    notifyContainers()
  }

  // Method to clear all toasts
  def clear(): Unit = {
    toastRegistry.clear()
    notifyContainers()
  }

  // Private state for tracking container components
  private val containerUpdateCallbacks = scala.collection.mutable.Set[() => Unit]()

  // Register container update callback
  private def registerContainer(updateCallback: () => Unit): () => Unit = {
    containerUpdateCallbacks.add(updateCallback)
    // Return unregister function
    () => containerUpdateCallbacks.remove(updateCallback)
  }

  // Notify all containers to update
  private def notifyContainers(): Unit = {
    containerUpdateCallbacks.foreach(_())
  }

  // Component for rendering toasts at a specific position
  def apply(position: String = "bottom-right"): FluxusNode = {
    val ToastContainerComponent = () => {
      // Force re-render when toasts change
      val (_, _, forceUpdate) = useState(0)

      // Register this container for updates
      useEffect(
        () => {
          val unregister = registerContainer(() => {
            forceUpdate(_ + 1)
          })

          // Cleanup function
          unregister
        },
        Seq(), // Empty deps - only run once on mount
      )

      // Get toasts for this position and sort by creation time
      val toasts = toastRegistry.values
        .filter(_.props.position == position)
        .toList
        .sortBy(_.createdAt)

      // Calculate the position classes
      val positionClass = position match {
        case "top-right"     => "toast toast-top toast-end"
        case "top-left"      => "toast toast-top toast-start"
        case "top-center"    => "toast toast-top toast-center"
        case "bottom-right"  => "toast toast-bottom toast-end"
        case "bottom-left"   => "toast toast-bottom toast-start"
        case "bottom-center" => "toast toast-bottom toast-center"
        case _               => "toast toast-bottom toast-end" // Default
      }

      // Render the container and toasts
      div(
        cls := positionClass,
        toasts.map(entry =>
          div(
            key := entry.id,
            Toast <> entry.props,
          ),
        ),
      )
    }

    ToastContainerComponent <> ()
  }
}
