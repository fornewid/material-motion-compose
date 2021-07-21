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
package soup.compose.material.motion.experimental

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.MotionConstants

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
 * [materialSharedAxisX] allows to switch between two layouts with shared Y-axis transitions.
 *
 * @param forward whether the direction of the transitions is forward.
 * @param slideDistance the slide distance of the transitions.
 * @param durationMillis the duration of the transitions.
 */
@ExperimentalAnimationApi
fun materialSharedAxisX(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ContentTransform {
    return materialSharedAxisXIn(
        forward = forward,
        slideDistance = slideDistance,
        durationMillis = durationMillis
    ) with materialSharedAxisXOut(
        forward = forward,
        slideDistance = slideDistance,
        durationMillis = durationMillis
    )
}

/**
 * [materialSharedAxisY] allows to switch between two layouts with shared Y-axis transitions.
 *
 * @param forward whether the direction of the transitions is forward.
 * @param slideDistance the slide distance of the transitions.
 * @param durationMillis the duration of the transitions.
 */
@ExperimentalAnimationApi
fun materialSharedAxisY(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ContentTransform {
    return materialSharedAxisYIn(
        forward = forward,
        slideDistance = slideDistance,
        durationMillis = durationMillis
    ) with materialSharedAxisYOut(
        forward = forward,
        slideDistance = slideDistance,
        durationMillis = durationMillis
    )
}

/**
 * TODO: This is an experimental feature that is not fully implemented!
 *
 * [materialSharedAxisZ] allows to switch between two layouts with shared Z-axis transitions.
 *
 * @param forward whether the direction of the transitions is forward.
 * @param durationMillis the duration of the transitions.
 */
@ExperimentalAnimationApi
fun materialSharedAxisZ(
    forward: Boolean,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ContentTransform {
    return materialSharedAxisZIn(
        forward = forward,
        durationMillis = durationMillis
    ) with materialSharedAxisZOut(
        forward = forward,
        durationMillis = durationMillis
    )
}

/**
 * [materialSharedAxisXIn] allows to switch a layout with shared X-axis enter transition.
 *
 * @param forward whether the direction of the enter transition is forward.
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisXIn(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterTransition {
    return slideInHorizontally(
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

/**
 * [materialSharedAxisYIn] allows to switch a layout with shared Y-axis enter transition.
 *
 * @param forward whether the direction of the enter transition is forward.
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisYIn(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterTransition {
    return slideInVertically(
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

/**
 * TODO: This is an experimental feature that is not fully implemented!
 *
 * [materialSharedAxisZIn] allows to switch a layout with shared Z-axis enter transition.
 *
 * @param forward whether the direction of the enter transition is forward.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisZIn(
    forward: Boolean,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterTransition = fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForIncoming,
        delayMillis = durationMillis.ForOutgoing,
        easing = LinearOutSlowInEasing
    )
)

/**
 * [materialSharedAxisXOut] allows to switch a layout with shared X-axis exit transition.
 *
 * @param forward whether the direction of the exit transition is forward.
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisXOut(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitTransition {
    return slideOutHorizontally(
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

/**
 * [materialSharedAxisYOut] allows to switch a layout with shared Y-axis exit transition.
 *
 * @param forward whether the direction of the exit transition is forward.
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisYOut(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitTransition {
    return slideOutVertically(
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

/**
 * TODO: This is an experimental feature that is not fully implemented!
 *
 * [materialSharedAxisZOut] allows to switch a layout with shared Z-axis exit transition.
 *
 * @param forward whether the direction of the exit transition is forward.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisZOut(
    forward: Boolean,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitTransition = fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis.ForOutgoing,
        delayMillis = 0,
        easing = FastOutLinearInEasing
    )
)

@ExperimentalAnimationApi
fun materialSharedAxisZScale(
    targetState: EnterExitState,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): FiniteAnimationSpec<Float> = tween(
    durationMillis = durationMillis,
    easing = FastOutSlowInEasing
)

@ExperimentalAnimationApi
fun materialSharedAxisZScaleValueOf(
    targetState: EnterExitState,
    forward: Boolean,
): Float = when (targetState) {
    EnterExitState.PreEnter -> if (forward) 0.8f else 1.1f
    EnterExitState.Visible -> 1f
    EnterExitState.PostExit -> if (forward) 0.8f else 1.1f
}
