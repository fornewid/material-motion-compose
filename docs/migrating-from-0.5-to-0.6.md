# Migrating from `0.5.x` to `0.6.x`

In `0.6.0`, `MaterialMotion` has been completely rewritten based on [`AnimatedContent`](https://developer.android.com/jetpack/compose/animation#animatedcontent).

So, if you were already using this library, you should migrate your codes.

Please check the before and after in the following codes.


## [Shared axis](https://material.io/design/motion/the-motion-system.html#shared-axis)

In `0.6.1`, both `Axis` and `MaterialSharedAxis` composable were deprecated.

Use `MaterialSharedAxis*` composables or `materialSharedAxis*` motion specs for each axis instead.

- `MaterialSharedAxis*` composables:

| Axis | Composable              |
| ---- | ----------------------- |
| X    | `MaterialSharedAxisX()` |
| Y    | `MaterialSharedAxisY()` |
| Z    | `MaterialSharedAxisZ()` |

- `materialSharedAxis*` motion specs:

| Axis | MotionSpec              | EnterMotionSpec           | ExitMotionSpec             |
| ---- | ----------------------- | ------------------------- | -------------------------- |
| X    | `materialSharedAxisX()` | `materialSharedAxisXIn()` | `materialSharedAxisXOut()` |
| Y    | `materialSharedAxisY()` | `materialSharedAxisYIn()` | `materialSharedAxisYOut()` |
| Z    | `materialSharedAxisZ()` | `materialSharedAxisZIn()` | `materialSharedAxisZOut()` |

You need to set the slide distance on the X/Y axis.

If wants, you can set the slide distance as `Dp` value using `rememberSlideDistance()`.

```kt
val motionSpec: MotionSpec = materialSharedAxisX(
    forward = true or false,
    slideDistance = rememberSlideDistance(30.dp)
)
```

----

In `0.5.x`:
```kt
MaterialSharedAxis(
    targetState = screen,
    axis = Axis.X,
    forward = true,
    slideDistance = 30.dp // (optional)
) { newScreen ->
    // composable according to screen
}
```

In `0.6.x`:
```kt
MaterialSharedAxisX(
    targetState = screen,
    forward = true,
    slideDistance = rememberSlideDistance(30.dp)
) { newScreen ->
    // composable according to screen
}
```

### With `MaterialMotion`

In `0.5.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = materialSharedAxis(
        axis = Axis.X,
        forward = true,
        slideDistance = 30.dp // (optional)
    )
) { newScreen ->
    // composable according to screen
}
```

In `0.6.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = materialSharedAxisX(
        forward = true,
        slideDistance = rememberSlideDistance(30.dp)
    )
) { newScreen ->
    // composable according to screen
}
```

## [Fade through](https://material.io/design/motion/the-motion-system.html#fade-through)

Usage has not changed between `0.5.x` and `0.6.x`.

| MotionSpec              | EnterMotionSpec           | ExitMotionSpec             |
| ----------------------- | ------------------------- | -------------------------- |
| `materialFadeThrough()` | `materialFadeThroughIn()` | `materialFadeThroughOut()` |

----

In `0.5.x`:
```kt
MaterialFadeThrough(
    targetState = screen
) { newScreen ->
    // composable according to screen
}
```

In `0.6.x`:
```kt
MaterialFadeThrough(
    targetState = screen
) { newScreen ->
    // composable according to screen
}
```

### With `MaterialMotion`

In `0.5.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = materialFadeThrough()
) { newScreen ->
    // composable according to screen
}
```

In `0.6.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = materialFadeThrough()
) { newScreen ->
    // composable according to screen
}
```

## [Fade](https://material.io/design/motion/the-motion-system.html#fade)

Unlike `0.5.x`, `0.6.x` does NOT support `MotionSpec`.
Use `MaterialFade` composable instead.

In `0.5.x`:
```kt
MaterialFade(
    targetState = visible
) { visible ->
    if (visible) {
        // composable
    }
}
```

In `0.6.x`:
```kt
MaterialFade(
    visible = visible
) {
    // composable
}
```

## Elevation scale

| MotionSpec                 | EnterMotionSpec              | ExitMotionSpec                |
| -------------------------- | ---------------------------- | ----------------------------- |
| -                          | `materialElevationScaleIn()` | `materialElevationScaleOut()` |

-----

In `0.5.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = materialElevationScale(growing = false)
) { newScreen ->
    // composable according to screen
}
```

In `0.6.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = materialElevationScaleIn() with materialElevationScaleOut()
) { newScreen ->
    // composable according to screen
}
```

### With other `MaterialMotion`

In `0.5.x`:
```kt
MaterialMotion(
    targetState = screen,
    enterMotionSpec = materialElevationScale(growing = false)
    exitMotionSpec = ...
) { newScreen ->
    // composable according to screen
}
// or
MaterialMotion(
    targetState = screen,
    enterMotionSpec = ...
    exitMotionSpec = materialElevationScale(growing = true)
) { newScreen ->
    // composable according to screen
}
```

In `0.6.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = materialElevationScaleIn() with ...
) { newScreen ->
    // composable according to screen
}
// or
MaterialMotion(
    targetState = screen,
    motionSpec = ... with materialElevationScaleOut()
) { newScreen ->
    // composable according to screen
}
```

## Hold

| MotionSpec | EnterMotionSpec | ExitMotionSpec |
| ---------- | --------------- | -------------- |
| -          | `holdIn()`      | `holdOut()`    |

-----

In `0.5.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = hold()
) { newScreen ->
    // composable according to screen
}
```

In `0.6.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = holdIn() with holdOut()
) { newScreen ->
    // composable according to screen
}
```

### With other `MaterialMotion`

In `0.5.x`:
```kt
MaterialMotion(
    targetState = screen,
    enterMotionSpec = hold()
    exitMotionSpec = ...
) { newScreen ->
    // composable according to screen
}
// or
MaterialMotion(
    targetState = screen,
    enterMotionSpec = ...
    exitMotionSpec = hold()
) { newScreen ->
    // composable according to screen
}
```

In `0.6.x`:
```kt
MaterialMotion(
    targetState = screen,
    motionSpec = holdIn() with ...
) { newScreen ->
    // composable according to screen
}
// or
MaterialMotion(
    targetState = screen,
    motionSpec = ... with holdOut()
) { newScreen ->
    // composable according to screen
}
```
