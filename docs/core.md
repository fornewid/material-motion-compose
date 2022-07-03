# Material Motion Core

[![Maven Central](https://img.shields.io/maven-central/v/io.github.fornewid/material-motion-compose-core)](https://search.maven.org/search?q=g:io.github.fornewid)

Material Motion Core is a Jetpack Compose library for implementing [motion system](https://material.io/develop/android/theming/motion/) in Material Components for Android.

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

val slideDistance = rememberSlideDistance(slideDistance = 30.dp)
MaterialMotion(
    targetState = screen,
    motionSpec = {
        materialSharedAxisX(forward = forward, slideDistance = slideDistance)
    },
    pop = forward.not()
) { newScreen ->
    // composable according to screen
}
```

| Axis X                     | Axis Y                     | Axis Z                     |
| -------------------------- | -------------------------- | -------------------------- |
| <img width="200" src="shared_axis_x.gif" /> | <img width="200" src="shared_axis_y.gif" /> | <img width="200" src="shared_axis_z.gif" /> |

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
| <img width="200" src="fade_through.gif" /> |

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
| <img width="200" src="fade.gif" /> |

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
| <img width="200" src="elevation_scale.gif" /> |

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
| <img width="200" src="hold.gif" /> |

### If you want to use different motions depending on the state:

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
val slideDistance = rememberSlideDistance(slideDistance = 30.dp)
MaterialMotion(
    targetState = screen,
    motionSpec = {
        when (targetState) {
           ... -> materialSharedAxisY(forward = true, slideDistance = slideDistance)
           ... -> materialFadeThroughIn() with materialFadeThroughOut()
           ...
        }
    },
    pop = false // whether motion contents are rendered in reverse order.
) { newScreen ->
    // composable according to screen
}
```

| Demo                       |
| -------------------------- |
| <img width="200" src="demo.gif" /> |

### If you want to change motion durations:

Just pass a duration(=ms) directly when creating material motion.

For example:
```kt
val motionSpec = materialFadeThrough(durationMillis = 300)
```

## Download

[![Maven Central](https://img.shields.io/maven-central/v/io.github.fornewid/material-motion-compose-core)](https://search.maven.org/search?q=g:io.github.fornewid)

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation "io.github.fornewid:material-motion-compose-core:<version>"
}
```
