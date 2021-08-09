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

import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

@ExperimentalAnimationApi
@Immutable
data class ScaleTransition(
    val initialScale: Float,
    val targetScale: Float = 1f,
    val animationSpec: FiniteAnimationSpec<Float>,
) {

    fun Modifier.animateModifier(transition: Transition<EnterExitState>): Modifier = composed {
        val scale by transition.animateFloat(
            transitionSpec = { animationSpec },
            label = "scale"
        ) {
            when (it) {
                EnterExitState.PreEnter -> initialScale
                EnterExitState.Visible -> targetScale
                EnterExitState.PostExit -> initialScale
            }
        }
        Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
    }

    companion object {
        val None: ScaleTransition = ScaleTransition(
            initialScale = 1f,
            animationSpec = tween(0)
        )
    }
}

@ExperimentalAnimationApi
fun scaleIn(
    initialScale: Float,
    animationSpec: FiniteAnimationSpec<Float>,
): ScaleTransition = ScaleTransition(
    initialScale = initialScale,
    animationSpec = animationSpec
)

@ExperimentalAnimationApi
fun scaleOut(
    targetScale: Float,
    animationSpec: FiniteAnimationSpec<Float>,
): ScaleTransition = ScaleTransition(
    initialScale = targetScale,
    animationSpec = animationSpec
)
