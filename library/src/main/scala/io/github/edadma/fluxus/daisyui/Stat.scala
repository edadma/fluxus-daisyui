package io.github.edadma.fluxus.daisyui

import io.github.edadma.fluxus._

/** Props for individual Stat component */
case class StatProps(
    // Core content
    title: String,                      // Title/label of the stat
    value: String,                      // The primary stat value (number or text)
    description: Option[String] = None, // Optional description text

    // Icons/figures
    figure: Option[FluxusNode] = None,      // Optional icon or figure
    figurePosition: String = "start",       // "start" or "end"
    figureClass: String = "text-secondary", // Class for figure container

    // Actions
    actions: Option[FluxusNode] = None, // Optional action buttons

    // Styling
    titleClass: String = "",       // Additional classes for title
    valueClass: String = "",       // Additional classes for value
    descriptionClass: String = "", // Additional classes for description
    className: String = "",        // Additional classes for the stat item
)

/** Props for the Stats container component */
case class StatsProps(
    // Core content
    children: FluxusNode, // Child stat components

    // Layout
    direction: String = "vertical", // "vertical", "horizontal", or "responsive"
    compact: Boolean = false,       // Smaller padding

    // Styling
    shadow: Boolean = false,   // Whether to add shadow
    bordered: Boolean = false, // Whether to add borders
    bgClass: String = "",      // Background class
    className: String = "",    // Additional classes
)

/** Stat component for displaying individual statistic */
val Stat = (props: StatProps) => {
  val classes = List.newBuilder[String]

  // Base class
  classes += "stat"

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val statClass = classes.result().mkString(" ")

  div(
    cls := statClass,

    // Figure at start position if specified
    if (props.figure.isDefined && props.figurePosition == "start") {
      div(
        cls := s"stat-figure ${props.figureClass}",
        props.figure.get,
      )
    } else null,

    // Title
    div(
      cls := s"stat-title ${props.titleClass}",
      props.title,
    ),

    // Value (primary stat)
    div(
      cls := s"stat-value ${props.valueClass}",
      props.value,
    ),

    // Description if provided
    props.description.map(desc =>
      div(
        cls := s"stat-desc ${props.descriptionClass}",
        desc,
      ),
    ).orNull,

    // Figure at end position if specified
    if (props.figure.isDefined && props.figurePosition == "end") {
      div(
        cls := s"stat-figure ${props.figureClass}",
        props.figure.get,
      )
    } else null,

    // Actions if provided
    props.actions.map(actionContent =>
      div(
        cls := "stat-actions",
        actionContent,
      ),
    ).orNull,
  )
}

/** Stats component for displaying multiple statistics in a container */
val Stats = (props: StatsProps) => {
  val classes = List.newBuilder[String]

  // Base class
  classes += "stats"

  // Layout direction
  if (props.direction == "horizontal") {
    classes += "stats-horizontal"
  } else if (props.direction == "vertical") {
    classes += "stats-vertical"
  } else if (props.direction == "responsive") {
    // On small screens vertical, on medium and up horizontal
    classes += "stats-vertical md:stats-horizontal"
  }

  // Add compact style if specified
  if (props.compact) {
    classes += "stats-compact"
  }

  // Add shadow if specified
  if (props.shadow) {
    classes += "shadow"
  }

  // Add border if specified
  if (props.bordered) {
    classes += "border border-base-300"
  }

  // Add background class if provided
  if (props.bgClass.nonEmpty) {
    classes += props.bgClass // Should be a complete class like "bg-base-200"
  }

  // Add any custom classes
  if (props.className.nonEmpty) {
    props.className.split(" ").foreach(cls => classes += cls)
  }

  // Join all classes with spaces
  val statsClass = classes.result().mkString(" ")

  div(
    cls := statsClass,
    props.children,
  )
}
