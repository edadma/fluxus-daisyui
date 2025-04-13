# fluxus-daisyui

![Maven Central](https://img.shields.io/maven-central/v/io.github.edadma/fluxus-daisyui_sjs1_3)
![GitHub](https://img.shields.io/github/license/edadma/fluxus-daisyui)
![Scala Version](https://img.shields.io/badge/scala-3.6.4-blue.svg)
![ScalaJS Version](https://img.shields.io/badge/scalajs-1.18.2-blue.svg)

A comprehensive component library for [Fluxus](https://github.com/edadma/fluxus) implementing the [DaisyUI](https://daisyui.com/) component system with full Tailwind CSS integration.

## Features

- **Ready-to-use DaisyUI components** - Buttons, Cards, Avatars, Select inputs, Tables, and more
- **Comprehensive styling options** - Full support for DaisyUI's theming and component variants
- **Type-safe API** - Leverages Scala's type system for component properties
- **Responsive design** - Components adapt to different screen sizes
- **Accessibility support** - Built-in ARIA attributes and keyboard navigation
- **Seamless integration** with existing Fluxus applications

## Installation

Add the dependency to your `build.sbt`:

```scala
libraryDependencies += "io.github.edadma" %%% "fluxus-daisyui" % "0.0.1"
```

Make sure you have Tailwind CSS and DaisyUI set up in your project. You can use the following `package.json` dependencies:

```json
"devDependencies": {
  "tailwindcss": "^4.1.3",
  "daisyui": "^5.0.12"
}
```

## Quick Start

```scala
import io.github.edadma.fluxus._
import io.github.edadma.fluxus.daisyui._

def App: FluxusNode = {
  Container <> ContainerProps(
    className = "py-8",
    children = Card <> CardProps(
      title = Some("Hello DaisyUI"),
      children = div(
        p("This is a simple card using fluxus-daisyui"),
        Button <> ButtonProps(
          text = "Click Me",
          variant = "primary",
          onClick = () => println("Button clicked!")
        )
      )
    )
  )
}

@main def run(): Unit = render(App, "app")
```

## Available Components

The library provides these DaisyUI components with comprehensive styling options:

- **Layout**: Container
- **Input**: Button, Select
- **Data Display**: Avatar, AvatarGroup, Card, Table, TableWithPagination
- **Navigation**: Sidebar
- **Feedback**: Spinner

Each component supports the full range of DaisyUI's styling options through a type-safe props interface.

## Documentation

For detailed documentation on each component's props and usage, see the [component documentation](https://edadma.github.io/fluxus-daisyui/components).

### Example: Button Component

```scala
Button <> ButtonProps(
  text = "Primary Button",
  variant = "primary",        // primary, secondary, accent, etc.
  size = "md",                // xs, sm, md, lg
  outline = true,             // Outlined style
  startIcon = Some(HomeIcon), // Icon at start
  onClick = () => handleClick()
)
```

### Example: Card Component

```scala
Card <> CardProps(
  title = Some("Card Title"),
  bordered = true,
  bgClass = "bg-base-200",
  children = div(
    p("Card content goes here"),
    Button <> ButtonProps(text = "Action")
  ),
  footer = Some(p("Card footer"))
)
```

## Examples

The library includes several example applications demonstrating component usage:

- Basic component examples
- Layout examples
- Form examples
- Data display examples

Run the examples locally with:

```
npm run dev
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the ISC License - see the LICENSE file for details.

## Acknowledgments

- [Fluxus](https://github.com/edadma/fluxus) - The core UI framework
- [DaisyUI](https://daisyui.com/) - Component library for Tailwind CSS
- [Tailwind CSS](https://tailwindcss.com/) - Utility-first CSS framework
