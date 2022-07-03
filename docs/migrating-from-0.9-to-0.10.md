# Migrating from `0.9.x` to `0.10.x`

In `0.10.0`, `MotionSpec` is deprecated and use `ContentTransform` instead.

So, if you were already using this library, you should migrate your codes.

The deprecated APIs may be removed in the next release.

Please check differences in the following codes.


## Migrating 'core' library

If you are using 'core' library, migration is not difficult except for MaterialSharedAxisX/Y.


### MaterialMotion

**Migration steps:**
1. Renames `motionSpec` parameter to `transitionSpec`.
2. Change packages from `soup.compose.material.motion.*` to`soup.compose.material.motion.animation.*`.
3. Change import from `soup.compose.material.motion.with` to `androidx.compose.animation.with`.

```diff
- import soup.compose.material.motion.*
+ import soup.compose.material.motion.animation.*
- import soup.compose.material.motion.with
+ import androidx.compose.animation.with

  MaterialMotion(
-     motionSpec = { ... with ... },
+     transitionSpec = { ... with ... },
  ) { ... }
```

### MaterialSharedAxisX

- (1) SharedAxis(X,Y)에서 SlideDistance 변경 필요함

Change packages from `soup.compose.material.motion.*` to`soup.compose.material.motion.animation.*`.

```diff
- import soup.compose.material.motion.materialSharedAxisX
- import soup.compose.material.motion.materialSharedAxisXIn
- import soup.compose.material.motion.materialSharedAxisXOut
+ import soup.compose.material.motion.animation.materialSharedAxisX
+ import soup.compose.material.motion.animation.materialSharedAxisXIn
+ import soup.compose.material.motion.animation.materialSharedAxisXOut

- materialSharedAxisX()
+ val slideDistance = rememberSlideDistance()
+ materialSharedAxisX(forward = forward, slideDistance = slideDistance)

- materialSharedAxisXIn()
+ val slideDistance = rememberSlideDistance()
+ materialSharedAxisXIn(forward = forward, slideDistance = slideDistance)

- materialSharedAxisXOut()
+ val slideDistance = rememberSlideDistance()
+ materialSharedAxisXOut(forward = forward, slideDistance = slideDistance)
```

### MaterialSharedAxisY

Change packages from `soup.compose.material.motion.*` to`soup.compose.material.motion.animation.*`.

```diff
- import soup.compose.material.motion.materialSharedAxisY
- import soup.compose.material.motion.materialSharedAxisYIn
- import soup.compose.material.motion.materialSharedAxisYOut
+ import soup.compose.material.motion.animation.materialSharedAxisY
+ import soup.compose.material.motion.animation.materialSharedAxisYIn
+ import soup.compose.material.motion.animation.materialSharedAxisYOut

- materialSharedAxisY()
+ val slideDistance = rememberSlideDistance()
+ materialSharedAxisY(forward = forward, slideDistance = slideDistance)

- materialSharedAxisYIn()
+ val slideDistance = rememberSlideDistance()
+ materialSharedAxisYIn(forward = forward, slideDistance = slideDistance)

- materialSharedAxisYOut()
+ val slideDistance = rememberSlideDistance()
+ materialSharedAxisYOut(forward = forward, slideDistance = slideDistance)
```

### MaterialSharedAxisZ

Change packages from `soup.compose.material.motion.*` to`soup.compose.material.motion.animation.*`.

```diff
- import soup.compose.material.motion.materialSharedAxisZ
- import soup.compose.material.motion.materialSharedAxisZIn
- import soup.compose.material.motion.materialSharedAxisZOut
+ import soup.compose.material.motion.animation.materialSharedAxisZ
+ import soup.compose.material.motion.animation.materialSharedAxisZIn
+ import soup.compose.material.motion.animation.materialSharedAxisZOut
```

### MaterialFadeThrough

Change packages from `soup.compose.material.motion.*` to`soup.compose.material.motion.animation.*`.

```diff
- import soup.compose.material.motion.materialFadeThrough
- import soup.compose.material.motion.materialFadeThroughIn
- import soup.compose.material.motion.materialFadeThroughOut
+ import soup.compose.material.motion.animation.materialFadeThrough
+ import soup.compose.material.motion.animation.materialFadeThroughIn
+ import soup.compose.material.motion.animation.materialFadeThroughOut
```

### MaterialElevationScale

Change packages from `soup.compose.material.motion.*` to`soup.compose.material.motion.animation.*`.

```diff
- import soup.compose.material.motion.materialElevationScaleIn
- import soup.compose.material.motion.materialElevationScaleOut
+ import soup.compose.material.motion.animation.materialElevationScaleIn
+ import soup.compose.material.motion.animation.materialElevationScaleOut
```

### Hold

Change packages from `soup.compose.material.motion.*` to`soup.compose.material.motion.animation.*`.

```diff
- import soup.compose.material.motion.holdIn
- import soup.compose.material.motion.holdOut
+ import soup.compose.material.motion.animation.holdIn
+ import soup.compose.material.motion.animation.holdOut
```

### Translate

Change packages from `soup.compose.material.motion.*` to`soup.compose.material.motion.animation.*`.

```diff
- import soup.compose.material.motion.translateXIn
- import soup.compose.material.motion.translateXOut
- import soup.compose.material.motion.translateYIn
- import soup.compose.material.motion.translateYOut
+ import soup.compose.material.motion.animation.translateXIn
+ import soup.compose.material.motion.animation.translateXOut
+ import soup.compose.material.motion.animation.translateYIn
+ import soup.compose.material.motion.animation.translateYOut
```



## Migrating 'core' library

If you are using 'navigation' library, migration is simple.

**Migration steps:**
1. Rename all `*MotionSpec` parameters to `*Transition`.
2. Change all material motion references from `soup.compose.material.motion.*` package to`soup.compose.material.motion.animation.*` package.
   For more information, see **Migrating 'core' library** section.

With this changes, you can easily migrate to this library from [accompanist-navigation-animation](https://github.com/google/accompanist/tree/main/navigation-animation).


### MaterialMotionNavHost

```diff
- import soup.compose.material.motion.*
+ import soup.compose.material.motion.animation.*

  MaterialMotionNavHost(
      ...,
-     enterMotionSpec = { ... },
-     exitMotionSpec = { ... },
-     popEnterMotionSpec = { ... },
-     popExitMotionSpec = { ... }
+     enterTransition = { ... },
+     exitTransition = { ... },
+     popEnterTransition = { ... },
+     popExitTransition = { ... }
  ) { ... }
```

### `composable()`

```diff
- import soup.compose.material.motion.*
+ import soup.compose.material.motion.animation.*

  MaterialMotionNavHost(...) {
      composable(
          ...,
-         enterMotionSpec = { ... },
-         exitMotionSpec = { ... },
-         popEnterMotionSpec = { ... },
-         popExitMotionSpec = { ... }
+         enterTransition = { ... },
+         exitTransition = { ... },
+         popEnterTransition = { ... },
+         popExitTransition = { ... }
      ) { ... }
  }
```

### `navigation()`

```diff
- import soup.compose.material.motion.*
+ import soup.compose.material.motion.animation.*

  MaterialMotionNavHost(...) {
      navigation(
          ...,
-         enterMotionSpec = { ... },
-         exitMotionSpec = { ... },
-         popEnterMotionSpec = { ... },
-         popExitMotionSpec = { ... }
+         enterTransition = { ... },
+         exitTransition = { ... },
+         popEnterTransition = { ... },
+         popExitTransition = { ... }
      ) {
          composable(...) { ... }
      }
  }
```

### If you are using default motionSpecs:

In `0.10.0`, default transitions are changed:

- Enter: `materialSharedAxisZIn()` -> `fadeIn(animationSpec = tween(DefaultMotionDuration))`
- Exit: `materialSharedAxisZOut()` -> `fadeOut(animationSpec = tween(DefaultMotionDuration))`

So if you want to keep the previous transitions, you need to define `*Transition` parameters.

Please add `*Transition` parameters like the following codes:

```diff
  MaterialMotionNavHost(
      ...,
+     enterTransition = { materialSharedAxisZIn(forward = true) },
+     exitTransition = { materialSharedAxisZOut(forward = true) },
+     popEnterTransition = { materialSharedAxisZIn(forward = false) },
+     popExitTransition = { materialSharedAxisZOut(forward = false) },
  ) { ... }
```
