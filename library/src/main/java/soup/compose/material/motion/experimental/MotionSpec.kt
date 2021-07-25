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
import androidx.compose.animation.core.Transition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@ExperimentalAnimationApi
class EnterMotionSpec(
    val transition: EnterTransition,
    val transitionExtra: TransitionExtra<Float> = TransitionExtra.None,
)

@ExperimentalAnimationApi
class ExitMotionSpec(
    val transition: ExitTransition,
    val transitionExtra: TransitionExtra<Float> = TransitionExtra.None,
)

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
