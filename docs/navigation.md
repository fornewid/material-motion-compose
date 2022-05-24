# Material Motion Navigation

[![Maven Central](https://img.shields.io/maven-central/v/io.github.fornewid/material-motion-compose-navigation)](https://search.maven.org/search?q=g:io.github.fornewid)

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

## Download

[![Maven Central](https://img.shields.io/maven-central/v/io.github.fornewid/material-motion-compose-navigation)](https://search.maven.org/search?q=g:io.github.fornewid)

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
