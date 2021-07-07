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
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import soup.compose.material.motion.MotionConstants

private const val ProgressThreshold = 0.35f

private val Int.ForOutgoing: Int
    get() = (this * ProgressThreshold).toInt()

private val Int.ForIncoming: Int
    get() = this - this.ForOutgoing

/**
 * [materialFadeThrough] allows to switch between two layouts with fade through transitions.
 *
 * @param durationMillis the duration of the transitions.
 */
@ExperimentalAnimationApi
fun materialFadeThrough(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ContentTransform {
    return materialFadeThroughIn(durationMillis = durationMillis) with
        materialFadeThroughOut(durationMillis = durationMillis)
}

/**
 * TODO: This is an experimental feature that is not fully implemented!
 *
 * [materialFadeThroughIn] allows to switch a layout with fade through enter transition.
 *
 * @param initialScale the starting scale of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialFadeThroughIn(
    initialScale: Float = 0.92f,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterTransition = fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForIncoming,
        delayMillis = durationMillis.ForOutgoing,
        easing = LinearOutSlowInEasing
    )
)

/**
 * [materialFadeThroughOut] allows to switch a layout with fade through exit transition.
 *
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialFadeThroughOut(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitTransition = fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis.ForOutgoing,
        delayMillis = 0,
        easing = FastOutLinearInEasing
    )
)

@ExperimentalAnimationApi
fun materialFadeThroughScale(
    targetState: EnterExitState,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): FiniteAnimationSpec<Float> = when (targetState) {
    EnterExitState.PreEnter,
    EnterExitState.Visible,
    -> materialFadeThroughScaleIn(durationMillis)
    EnterExitState.PostExit -> materialFadeThroughScaleOut(durationMillis)
}

@ExperimentalAnimationApi
private fun materialFadeThroughScaleIn(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): FiniteAnimationSpec<Float> = tween(
    durationMillis = durationMillis.ForIncoming,
    delayMillis = durationMillis.ForOutgoing,
    easing = LinearOutSlowInEasing
)

@ExperimentalAnimationApi
private fun materialFadeThroughScaleOut(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): FiniteAnimationSpec<Float> = tween(
    durationMillis = durationMillis.ForOutgoing,
    delayMillis = 0,
    easing = FastOutLinearInEasing
)

@ExperimentalAnimationApi
fun materialFadeThroughScaleValueOf(
    targetState: EnterExitState,
): Float = when (targetState) {
    EnterExitState.PreEnter -> 0.92f
    EnterExitState.Visible -> 1f
    EnterExitState.PostExit -> 1f
}
