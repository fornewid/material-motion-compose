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

## Migration

### From 'accompanist-navigation-animation' library

The migration is simple.

Please check differences in the following codes.

```diff
- import com.google.accompanist.navigation.animation.AnimatedNavHost
- import com.google.accompanist.navigation.animation.composable
- import com.google.accompanist.navigation.animation.navigation
- import com.google.accompanist.navigation.animation.rememberAnimatedNavController
+ import soup.compose.material.motion.navigation.MaterialMotionNavHost
+ import soup.compose.material.motion.navigation.composable
+ import soup.compose.material.motion.navigation.navigation
+ import soup.compose.material.motion.navigation.rememberMaterialMotionNavController

- val navController = rememberAnimatedNavController()
+ val navController = rememberMaterialMotionNavController()
- AnimatedNavHost(
+ MaterialMotionNavHost(
      ...,
  ) {
      composable(...) { ... }
      navigation(...) {
          composable(...) { ... }
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
