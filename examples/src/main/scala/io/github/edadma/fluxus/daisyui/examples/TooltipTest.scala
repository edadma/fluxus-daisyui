package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.daisyui.*
import org.scalajs.dom

def TooltipTest: FluxusNode = {
  Container <> ContainerProps(
    className = "py-8",
    children = div(
      h1(
        cls := "text-3xl font-bold mb-8 text-center",
        "Tooltip Component Demo",
      ),
      div(
        cls := "flex flex-wrap justify-center gap-8",

        // Tooltip on a primary button, positioned at the top.
        Tooltip <> TooltipProps(
          tip = "Click this button to perform an action",
          position = Some("top"),
          color = Some("primary"),
          children = button(cls := "btn btn-primary", "Hover over me"),
        ),

        // Tooltip on a link, positioned at the bottom.
        Tooltip <> TooltipProps(
          tip = "Visit our homepage",
          position = Some("bottom"),
          color = Some("info"),
          children = a(href := "#", cls := "link", "Hover over this link"),
        ),

        // Tooltip on an informational icon, positioned at the right.
        Tooltip <> TooltipProps(
          tip = "More information available here",
          position = Some("right"),
          color = Some("secondary"),
          children = span(cls := "text-xl", "ℹ"),
        ),

        // Tooltip with default color styling, positioned at the left.
        Tooltip <> TooltipProps(
          tip = "Default tooltip styling",
          position = Some("left"),
          children = button(cls := "btn", "Hover default"),
        ),
      ),
    ),
  )
}
