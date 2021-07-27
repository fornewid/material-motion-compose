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

package soup.compose.material.motion.experimental

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import soup.compose.material.motion.MaterialFade
import soup.compose.material.motion.MotionConstants

private const val DefaultFadeEndThresholdEnter = 0.3f

private val Int.ForFade: Int
    get() = (this * DefaultFadeEndThresholdEnter).toInt()

/**
 * [materialFadeIn] allows to switch a layout with a fade-in animation.
 */
@ExperimentalAnimationApi
private fun materialFadeIn(
    durationMillis: Int = MotionConstants.motionDurationShort2,
): EnterTransition = fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForFade,
        easing = LinearEasing
    )
)

/**
 * [materialFadeOut] allows to switch a layout with a fade-out animation.
 */
@ExperimentalAnimationApi
private fun materialFadeOut(
    durationMillis: Int = MotionConstants.motionDurationShort1,
): ExitTransition = fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = LinearEasing
    )
)

/**
 * [MaterialFade] animates the appearance and disappearance of its content, as [visible] value changes.
 *
 * @see com.google.android.material.transition.MaterialFade
 *
 * @param visible defines whether the [content] should be visible
 * @param modifier modifier for the [Layout] created to contain the [content]
 * @param enterDurationMillis enter duration
 * @param exitDurationMillis exit duration
 */
@ExperimentalAnimationApi
@Composable
fun MaterialFade(
    visible: Boolean,
    modifier: Modifier = Modifier,
    enterDurationMillis: Int = MotionConstants.motionDurationShort2,
    exitDurationMillis: Int = MotionConstants.motionDurationShort1,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = materialFadeIn(enterDurationMillis),
        exit = materialFadeOut(exitDurationMillis)
    ) {
        val scale by transition.animateFloat(
            transitionSpec = {
                when (targetState) {
                    EnterExitState.PreEnter -> tween(durationMillis = 0)
                    EnterExitState.Visible -> tween(
                        durationMillis = enterDurationMillis,
                        easing = FastOutSlowInEasing
                    )
                    EnterExitState.PostExit -> tween(durationMillis = 0)
                }
            },
            label = "scale"
        ) {
            when (it) {
                EnterExitState.PreEnter -> 0.8f
                EnterExitState.Visible -> 1f
                EnterExitState.PostExit -> 1f
            }
        }
        Box(
            modifier = Modifier.graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
        ) {
            content()
        }
    }
}
