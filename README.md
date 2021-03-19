![logo](docs/header.png)

# Material Motion for Jetpack Compose

<a href="https://jitpack.io/#fornewid/material-motion-compose"><img src="https://jitpack.io/v/fornewid/material-motion-compose.svg"/></a>
<a href="https://github.com/fornewid/material-motion-compose/actions/workflows/Check.yaml"><img src="https://github.com/fornewid/material-motion-compose/workflows/Check/badge.svg"/></a>
<a href="https://opensource.org/licenses/Apache-2.0"><img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
<a href='https://developer.android.com'><img src='http://img.shields.io/badge/platform-android-green.svg'/></a>

Jetpack Compose library for implementing [motion system](https://material.io/develop/android/theming/motion/) in Material Components for Android.

## Usage

This library provides support for [motion patterns](https://material.io/design/motion/the-motion-system.html) defined in the Material spec.

### Shared axis

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
SharedAxis(axis = Axis.X, forward = true, targetState = screen) { newScreen ->
    // composable according to screen
}
```

### Fade through

```kt
val (screen, onScreenChanged) = remember { mutableStateOf(...) }
FadeThrough(targetState = screen) { newScreen ->
    // composable according to screen
}
```

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
