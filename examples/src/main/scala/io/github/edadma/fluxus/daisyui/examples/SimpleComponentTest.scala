package io.github.edadma.fluxus.daisyui.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def SimpleComponentTest: FluxusNode = {
  val (clickCount, setClickCount, _) = useState(0)

  Container <> ContainerProps(
    className = "flex items-center justify-center min-h-screen",
    children = Card <> CardProps(
      title = Some("Simple Component Test"),
      className = "w-full max-w-md",
      children = div(
        p(
          cls := "mb-6",
          "This is a simple test of the Container, Card and Button components.",
        ),
        div(
          cls := "flex flex-col items-center",
          Button <> ButtonProps(
            text = "Click Me",
            onClick = () => setClickCount(clickCount + 1),
          ),
          p(
            cls := "mt-4",
            s"Button clicked: $clickCount times",
          ),
        ),
      ),
    ),
  )
}
