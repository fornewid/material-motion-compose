# Using with compose-animation

If you would like to apply material motion with compose-animation, you can use the following features.

This library provides implementations of ContentTransform or EnterTransition or ExitTransition.

Available in the `soup.compose.material.motion.experimental` package.

🧪 Currently these are experimental features. 🧪

### Using with `AnimatedContent`

```kt
AnimatedContent(
    targetState = ...,
    modifier = Modifier,
    transitionSpec = {
        // ContentTransform
        // or
        // EnterTransition with ExitTransition
    }
) { targetState ->
    // content
}
```

#### [Shared axis](https://material.io/design/motion/the-motion-system.html#shared-axis)

| ContentTransform | EnterTransition | ExitTransition |
| ---------------- | --------------- | -------------- |
| `materialSharedAxisX()` | `materialSharedAxisXIn()` | `materialSharedAxisXOut()` |
| `materialSharedAxisY()` | `materialSharedAxisYIn()` | `materialSharedAxisYOut()` |
| `materialSharedAxisZ()` | `materialSharedAxisZIn()` | `materialSharedAxisZOut()` |

You need to set the slide distance on the X/Y axis.

If wants, you can set the slide distance as `Dp` value using `rememberSlideDistance()`.

```kt
val slideDistance = rememberSlideDistance(30.dp)
AnimatedContent(
    ...
    transitionSpec = {
        materialSharedAxisX(
            forward = true or false,
            slideDistance = slideDistance
        )
    }
) { targetState ->
    // content
}
```

#### [Fade through](https://material.io/design/motion/the-motion-system.html#fade-through)

| ContentTransform | EnterTransition | ExitTransition |
| ---------------- | --------------- | -------------- |
| `materialFadeThrough()` | `materialFadeThroughIn()` | `materialFadeThroughOut()` |

#### Elevation scale

| ContentTransform | EnterTransition | ExitTransition |
| ---------------- | --------------- | -------------- |
| - | `materialElevationScaleIn()` | `materialElevationScaleOut()` |

#### Hold

| ContentTransform | EnterTransition | ExitTransition |
| ---------------- | --------------- | -------------- |
| - | `holdIn()` | `holdOut()` |


### Using with `AnimatedVisibility`

```kt
AnimatedVisibility(
    visible = true or false,
    modifier = Modifier,
    enter = ..., // EnterTransition
    exit = ...   // ExitTransition
) {
    // content
}
```

#### [Fade](https://material.io/design/motion/the-motion-system.html#fade)

| EnterTransition | ExitTransition |
| --------------- | -------------- |
| `materialFadeIn()` | `materialFadeOut()` |
