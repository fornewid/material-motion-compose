<img width="550" alt="logo" src="docs/header.png" />

# Material Motion for Jetpack Compose

<a href="https://jitpack.io/#fornewid/material-motion-compose"><img src="https://jitpack.io/v/fornewid/material-motion-compose.svg"/></a>
<a href="https://github.com/fornewid/material-motion-compose/actions/workflows/build.yaml"><img src="https://github.com/fornewid/material-motion-compose/workflows/build/badge.svg"/></a>
<a href="https://opensource.org/licenses/Apache-2.0"><img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
<a href='https://developer.android.com'><img src='http://img.shields.io/badge/platform-android-green.svg'/></a>

Jetpack Compose library for implementing [motion system](https://material.io/develop/android/theming/motion/) in Material Components for Android.

## Usage

This library provides support for [motion patterns](https://material.io/design/motion/the-motion-system.html) defined in the Material spec.

### Shared axis

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
SharedAxis(
    targetState = screen,
    axis = Axis.X,
    forward = true,
    slideDistance = 30.dp // (optional)
) { newScreen ->
    // composable according to screen
}

// or

MaterialMotion(
    targetState = screen,
    motionSpec = sharedAxis(
        axis = Axis.X,
        forward = true,
        slideDistance = 30.dp // (optional)
    )
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
FadeThrough(
    targetState = screen
) { newScreen ->
    // composable according to screen
}

// or

MaterialMotion(
    targetState = screen,
    motionSpec = fadeThrough()
) { newScreen ->
    // composable according to screen
}
```

| Fade Through               |
| -------------------------- |
| <img width="200" src="docs/fade_through.gif" /> |

### Fade

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
Fade(
    targetState = screen
) { newScreen ->
    // composable according to screen
}

// or

MaterialMotion(
    targetState = selectedTab,
    motionSpec = fade()
) { newScreen ->
    // composable according to screen
}
```

| Fade                       |
| -------------------------- |
| <img width="200" src="docs/fade.gif" /> |

### Crossfade

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
MaterialMotion(
    targetState = selectedTab,
    motionSpec = crossfade()
) { newScreen ->
    // composable according to screen
}
```
or use [Crossfade](https://github.com/androidx/androidx/blob/androidx-main/compose/animation/animation/src/commonMain/kotlin/androidx/compose/animation/Crossfade.kt) in compose-animation.

| Crossfade                  |
| -------------------------- |
| <img width="200" src="docs/crossfade.gif" /> |

### If you want to use different motions depending on the state:

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
val motionSpec = when (screen) {
   ... -> fadeThrough()
   ... -> sharedAxisY(forward = true)
   ...
}
MaterialMotion(
    targetState = screen,
    motionSpec = motionSpec
) { newScreen ->
    // composable according to screen
}

// or

MaterialMotion(
    targetState = screen,
    enterMotionSpec = motionSpec,
    exitMotionSpec = motionSpec
) { newScreen ->
    // composable according to screen
}
```

| Demo                       |
| -------------------------- |
| <img width="200" src="docs/demo.gif" /> |

## Installation

Add Jitpack repository to your project's `build.gradle`:
```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

And add the dependency. Depend on the [latest version](https://github.com/fornewid/material-motion-compose/releases) of the library.
```
dependencies {
    implementation 'com.github.fornewid:material-motion-compose:<version>'
}
```

## License
Licensed under the Apache 2.0 license. See [LICENSE](https://github.com/fornewid/material-motion-compose/blob/main/LICENSE) for details.
