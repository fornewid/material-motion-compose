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

import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@ExperimentalAnimationApi
class TransitionExtra<E>(
    val animateExtra: @Composable Transition<EnterExitState>.() -> State<E>,
    val modifierByExtra: (E) -> Modifier,
) {
    companion object {
        val None: TransitionExtra<Float> = TransitionExtra(
            animateExtra = { remember { mutableStateOf(1f) } },
            modifierByExtra = { Modifier }
        )
    }
}

@ExperimentalAnimationApi
fun scaleIn(
    initialScale: Float,
    animationSpec: FiniteAnimationSpec<Float>,
): TransitionExtra<Float> = scaleTransitionExtra(
    initialScale = initialScale,
    animationSpec = animationSpec
)

@ExperimentalAnimationApi
fun scaleOut(
    targetScale: Float,
    animationSpec: FiniteAnimationSpec<Float>,
): TransitionExtra<Float> = scaleTransitionExtra(
    initialScale = targetScale,
    animationSpec = animationSpec
)

@ExperimentalAnimationApi
private fun scaleTransitionExtra(
    initialScale: Float,
    targetScale: Float = 1f,
    animationSpec: FiniteAnimationSpec<Float> = spring(),
): TransitionExtra<Float> = TransitionExtra(
    animateExtra = {
        animateFloat(
            transitionSpec = { animationSpec },
            label = "scale"
        ) {
            when (it) {
                EnterExitState.PreEnter -> initialScale
                EnterExitState.Visible -> targetScale
                EnterExitState.PostExit -> initialScale
            }
        }
    },
    modifierByExtra = { scale ->
        Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
    }
)
