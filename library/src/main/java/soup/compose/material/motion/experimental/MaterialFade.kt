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

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import soup.compose.material.motion.MotionConstants

private const val DefaultFadeEndThresholdEnter = 0.3f

private val Int.ForFade: Int
    get() = (this * DefaultFadeEndThresholdEnter).toInt()

/**
 * TODO: This is an experimental feature that is not fully implemented!
 *
 * [materialFadeIn] allows to switch a layout with a fade-in animation.
 */
@ExperimentalAnimationApi
fun materialFadeIn(
    durationMillis: Int = MotionConstants.motionDurationShort2,
    animationSpec: FiniteAnimationSpec<Float>? = null,
): EnterTransition {
    return fadeIn(
        animationSpec = animationSpec ?: tween(
            durationMillis = durationMillis.ForFade,
            easing = LinearEasing
        )
    )
    // TODO: I want scaleIn() instead of expandIn()
    //      https://issuetracker.google.com/issues/191325593
    // + expandIn(
    //    expandFrom = Alignment.Center,
    //    initialSize = { fullSize -> fullSize * 0.8f },
    //    animationSpec = tween(
    //        durationMillis = durationMillis,
    //        easing = LinearOutSlowInEasing
    //    )
    // )
}

/**
 * [materialFadeOut] allows to switch a layout with a fade-out animation.
 */
@ExperimentalAnimationApi
fun materialFadeOut(
    durationMillis: Int = MotionConstants.motionDurationShort1,
    animationSpec: FiniteAnimationSpec<Float>? = null,
): ExitTransition {
    return fadeOut(
        animationSpec = animationSpec ?: tween(
            durationMillis = durationMillis.ForFade,
            easing = LinearEasing
        )
    )
}
