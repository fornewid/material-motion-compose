# Material Motion Navigation

[![Maven Central](https://img.shields.io/maven-central/v/io.github.fornewid/material-motion-compose-navigation)](https://search.maven.org/search?q=g:io.github.fornewid)

## Installation

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation "io.github.fornewid:material-motion-compose-navigation:<version>"
    
    // If you want to change motion, use with core library.
    implementation "io.github.fornewid:material-motion-compose-core:<version>"
}
```

Depend on the [latest version](https://github.com/fornewid/material-motion-compose/releases) of the library.

## Usage

```kotlin
val navController = rememberMaterialMotionNavController()

MaterialMotionNavHost(navController, startDestination = "first") {
  composable("first",
    enterMotionSpec = { holdIn() },
    exitMotionSpec = { holdOut() },
  ) {
    // first composable
  }
  
  composable("second",
    enterMotionSpec = { materialSharedAxisXIn() },
    exitMotionSpec = { materialSharedAxisXOut() },
  ) {
    // second composable
  }
}
```
