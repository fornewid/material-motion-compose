# Material Motion Core

<a href="https://jitpack.io/#fornewid/material-motion-compose"><img src="https://jitpack.io/v/fornewid/material-motion-compose.svg"/></a>

Material Motion Core is a Jetpack Compose library for implementing [motion system](https://material.io/develop/android/theming/motion/) in Material Components for Android.

## Installation

```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.fornewid.material-motion-compose:core:<version>"
}
```

Depend on the [latest version](https://github.com/fornewid/material-motion-compose/releases) of the library.

## Usage

This library provides support for [motion patterns](https://material.io/design/motion/the-motion-system.html) defined in the Material spec.

### Shared axis

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
MaterialSharedAxisX(
    targetState = screen,
    forward = true,
    slideDistance = 30.dp // (optional)
) { newScreen ->
    // composable according to screen
}

// or

MaterialMotion(
    targetState = screen,
    motionSpec = {
        materialSharedAxisX(slideDistance = 30.dp)
    },
    pop = forward.not()
) { newScreen ->
    // composable according to screen
}
```

| Axis X                     | Axis Y                     | Axis Z                     |
| -------------------------- | -------------------------- | -------------------------- |
| <img width="200" src="docs/shared_axis_x.gif" /> | <img width="200" src="docs/shared_axis_y.gif" /> | <img width="200" src="docs/shared_axis_z.gif" /> |

### Fade through

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
MaterialFadeThrough(
    targetState = screen
) { newScreen ->
    // composable according to screen
}

// or

MaterialMotion(
    targetState = screen,
    motionSpec = { materialFadeThrough() }
) { newScreen ->
    // composable according to screen
}
```

| Fade Through               |
| -------------------------- |
| <img width="200" src="docs/fade_through.gif" /> |

### Fade

```kt
val (visible, onVisibleChanged) = remember { mutableStateOf(...) }
MaterialFade(
    visible = visible
) {
    // composable to show
}
```

| Fade                       |
| -------------------------- |
| <img width="200" src="docs/fade.gif" /> |

### Elevation scale

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
MaterialMotion(
    targetState = screen,
    motionSpec = { materialElevationScaleIn() with ... }
    // or
    motionSpec = { ... with materialElevationScaleOut() }
) { newScreen ->
    // composable according to screen
}
```

| ElevationScale             |
| -------------------------- |
| <img width="200" src="docs/elevation_scale.gif" /> |

### Hold

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
MaterialMotion(
    targetState = screen,
    motionSpec = { holdIn() with ... }
    // or
    motionSpec = { ... with holdOut() }
) { newScreen ->
    // composable according to screen
}
```

| Hold                       |
| -------------------------- |
| <img width="200" src="docs/hold.gif" /> |

### If you want to use different motions depending on the state:

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
val motionSpec = when (screen) {
   ... -> materialSharedAxisY()
   ... -> materialFadeThroughIn() with materialFadeThroughOut()
   ...
}
MaterialMotion(
    targetState = screen,
    motionSpec = { motionSpec },
    pop = false // whether motion contents are rendered in reverse order.
) { newScreen ->
    // composable according to screen
}
```

| Demo                       |
| -------------------------- |
| <img width="200" src="docs/demo.gif" /> |

### If you want to change motion durations:

Just pass a duration(=ms) directly when creating material motion.

For example:
```kt
val motionSpec = materialFadeThrough(durationMillis = 300)
```
