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

import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import soup.compose.material.motion.MotionConstants

/**
 * TODO: This is an experimental feature that is not fully implemented!
 *
 * [materialElevationScaleIn] allows to switch a layout with elevation scale enter transition.
 *
 * @param initialAlpha the starting alpha of the enter transition.
 * @param initialScale the starting scale of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialElevationScaleIn(
    initialAlpha: Float = 0.85f,
    initialScale: Float = 0.85f,
    durationMillis: Int = MotionConstants.motionDurationLong1,
    animationSpec: FiniteAnimationSpec<Float>? = null,
): EnterTransition {
    return fadeIn(
        initialAlpha = initialAlpha,
        animationSpec = animationSpec ?: tween(
            durationMillis = durationMillis,
            easing = LinearEasing
        )
    )
}

/**
 * TODO: This is an experimental feature that is not fully implemented!
 *
 * [materialElevationScaleOut] allows to switch a layout with elevation scale exit transition.
 *
 * @param targetAlpha the target alpha of the exit transition.
 * @param targetScale the target scale of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialElevationScaleOut(
    targetAlpha: Float = 0.85f,
    targetScale: Float = 0.85f,
    durationMillis: Int = MotionConstants.motionDurationLong1,
    animationSpec: FiniteAnimationSpec<Float>? = null,
): ExitTransition {
    return fadeOut(
        targetAlpha = targetAlpha,
        animationSpec = animationSpec ?: tween(
            durationMillis = durationMillis,
            easing = LinearEasing
        )
    )
}

@ExperimentalAnimationApi
fun materialElevationScaleSpec(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): FiniteAnimationSpec<Float> = tween(
    durationMillis = durationMillis,
    easing = FastOutSlowInEasing
)

@ExperimentalAnimationApi
fun materialElevationScaleValueOf(
    targetState: EnterExitState,
): Float = when (targetState) {
    EnterExitState.PreEnter -> 0.85f
    EnterExitState.Visible -> 1f
    EnterExitState.PostExit -> 0.85f
}
