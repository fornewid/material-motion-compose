/*
 * Copyright 2021 SOUP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("unused")

package soup.compose.material.motion

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val DefaultSlideDistance: Dp = 30.dp

private const val ProgressThreshold = 0.35f

private val Int.ForOutgoing: Int
    get() = (this * ProgressThreshold).toInt()

private val Int.ForIncoming: Int
    get() = this - this.ForOutgoing

/**
 * Returns the provided [Dp] as an [Int] value by the [LocalDensity].
 *
 * @param slideDistance Value to the slide distance dimension, 30dp by default.
 */
@Composable
fun rememberSlideDistance(slideDistance: Dp = DefaultSlideDistance): Int {
    val density = LocalDensity.current
    return remember(density) {
        with(density) { slideDistance.roundToPx() }
    }
}

/**
 * [materialSharedAxisX] allows to switch a layout with shared X-axis transition.
 *
 * @param slideDistance the slide distance of transition.
 * @param durationMillis the duration of transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisX(
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): MotionSpec = materialSharedAxisXIn(
    slideDistance = slideDistance,
    durationMillis = durationMillis
) with materialSharedAxisXOut(
    slideDistance = slideDistance,
    durationMillis = durationMillis
)

/**
 * [materialSharedAxisXIn] allows to switch a layout with shared X-axis enter transition.
 *
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisXIn(
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = { forward ->
        slideInHorizontally(
            initialOffsetX = {
                if (forward) slideDistance else -slideDistance
            },
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = durationMillis.ForIncoming,
                delayMillis = durationMillis.ForOutgoing,
                easing = LinearOutSlowInEasing
            )
        )
    }
)

/**
 * [materialSharedAxisXOut] allows to switch a layout with shared X-axis exit transition.
 *
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisXOut(
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec = ExitMotionSpec(
    transition = { forward ->
        slideOutHorizontally(
            targetOffsetX = {
                if (forward) -slideDistance else slideDistance
            },
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            )
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = durationMillis.ForOutgoing,
                delayMillis = 0,
                easing = FastOutLinearInEasing
            )
        )
    }
)

/**
 * [materialSharedAxisY] allows to switch a layout with shared Y-axis transition.
 *
 * @param slideDistance the slide distance of transition.
 * @param durationMillis the duration of transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisY(
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): MotionSpec = materialSharedAxisYIn(
    slideDistance = slideDistance,
    durationMillis = durationMillis
) with materialSharedAxisYOut(
    slideDistance = slideDistance,
    durationMillis = durationMillis
)

/**
 * [materialSharedAxisYIn] allows to switch a layout with shared Y-axis enter transition.
 *
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisYIn(
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = { forward ->
        slideInVertically(
            initialOffsetY = {
                if (forward) slideDistance else -slideDistance
            },
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = durationMillis.ForIncoming,
                delayMillis = durationMillis.ForOutgoing,
                easing = LinearOutSlowInEasing
            )
        )
    }
)

/**
 * [materialSharedAxisYOut] allows to switch a layout with shared Y-axis exit transition.
 *
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisYOut(
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec = ExitMotionSpec(
    transition = { forward ->
        slideOutVertically(
            targetOffsetY = {
                if (forward) -slideDistance else slideDistance
            },
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            )
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = durationMillis.ForOutgoing,
                delayMillis = 0,
                easing = FastOutLinearInEasing
            )
        )
    }
)

/**
 * [materialSharedAxisZ] allows to switch a layout with shared Z-axis transition.
 *
 * @param durationMillis the duration of transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisZ(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): MotionSpec = materialSharedAxisZIn(
    durationMillis = durationMillis
) with materialSharedAxisZOut(
    durationMillis = durationMillis
)

/**
 * [materialSharedAxisZIn] allows to switch a layout with shared Z-axis enter transition.
 *
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisZIn(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = {
        fadeIn(
            animationSpec = tween(
                durationMillis = durationMillis.ForIncoming,
                delayMillis = durationMillis.ForOutgoing,
                easing = LinearOutSlowInEasing
            )
        )
    },
    transitionExtra = { forward ->
        scaleIn(
            initialScale = if (forward) 0.8f else 1.1f,
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            )
        )
    }
)

/**
 * [materialSharedAxisZOut] allows to switch a layout with shared Z-axis exit transition.
 *
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisZOut(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec = ExitMotionSpec(
    transition = {
        fadeOut(
            animationSpec = tween(
                durationMillis = durationMillis.ForOutgoing,
                delayMillis = 0,
                easing = FastOutLinearInEasing
            )
        )
    },
    transitionExtra = { forward ->
        scaleOut(
            targetScale = if (forward) 1.1f else 0.8f,
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            )
        )
    }
)

/**
 * [MaterialSharedAxisX] allows to switch between two layouts with a shared X-axis animation.
 *
 * @see com.google.android.material.transition.MaterialSharedAxis
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param forward whether the direction of the animation is forward.
 * @param modifier Modifier to be applied to the animation container.
 * @param slideDistance slide distance of the animation.
 */
@ExperimentalAnimationApi
@Composable
fun <T> MaterialSharedAxisX(
    targetState: T,
    forward: Boolean,
    modifier: Modifier = Modifier,
    slideDistance: Dp = DefaultSlideDistance,
    content: @Composable (T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        motionSpec = materialSharedAxisX(rememberSlideDistance(slideDistance)),
        modifier = modifier,
        pop = forward.not(),
        content = content
    )
}

/**
 * [MaterialSharedAxisY] allows to switch between two layouts with a shared Y-axis animation.
 *
 * @see com.google.android.material.transition.MaterialSharedAxis
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param forward whether the direction of the animation is forward.
 * @param modifier Modifier to be applied to the animation container.
 * @param slideDistance slide distance of the animation.
 */
@ExperimentalAnimationApi
@Composable
fun <T> MaterialSharedAxisY(
    targetState: T,
    forward: Boolean,
    modifier: Modifier = Modifier,
    slideDistance: Dp = DefaultSlideDistance,
    content: @Composable (T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        motionSpec = materialSharedAxisY(rememberSlideDistance(slideDistance)),
        modifier = modifier,
        pop = forward.not(),
        content = content
    )
}

/**
 * [MaterialSharedAxisZ] allows to switch between two layouts with a shared Z-axis animation.
 *
 * @see com.google.android.material.transition.MaterialSharedAxis
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param forward whether the direction of the animation is forward.
 * @param modifier Modifier to be applied to the animation container.
 */
@ExperimentalAnimationApi
@Composable
fun <T> MaterialSharedAxisZ(
    targetState: T,
    forward: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        motionSpec = materialSharedAxisZ(),
        modifier = modifier,
        pop = forward.not(),
        content = content
    )
}
