# Material Motion Navigation

<a href="https://jitpack.io/#fornewid/material-motion-compose"><img src="https://jitpack.io/v/fornewid/material-motion-compose.svg"/></a>

## Installation

```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.fornewid.material-motion-compose:navigation:<version>"
    
    // If you want to change motion, use with core library.
    implementation "com.github.fornewid.material-motion-compose:core:<version>"
}
```

Depend on the [latest version](https://github.com/fornewid/material-motion-compose/releases) of the library.

## Usage

```kotlin
val navController = rememberMaterialMotionNavController()

MaterialMotionNavHost(navController, startDestination = "first") {
  composable("first",
    enterMotionSpec = { _, _ -> holdIn() },
    exitMotionSpec = { _, _ -> holdOut() },
  ) {
    // first composable
  }
  
  composable("second",
    enterMotionSpec = { _, _ -> materialSharedAxisXIn(rememberSlideDistance()) },
    exitMotionSpec = { _, _ -> materialSharedAxisXOut(rememberSlideDistance()) },
  ) {
    // second composable
  }
}
```
