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

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import soup.compose.material.motion.internal.MotionAnimationItem

/**
 * [MaterialMotion] allows to switch between two layouts with a material motion animation.
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param motionSpec the [MotionSpec] to configure the animation.
 * @param modifier Modifier to be applied to the animation container.
 * @param durationMillis total duration of the animation.
 */
@Composable
fun <T> MaterialMotion(
    targetState: T,
    motionSpec: MotionSpec,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit,
) {
    val items = remember { mutableStateListOf<MotionAnimationItem<T>>() }
    val transitionState = remember { MutableTransitionState(targetState) }
    val targetChanged = (targetState != transitionState.targetState)
    transitionState.targetState = targetState
    val transition = updateTransition(transitionState)
    if (targetChanged || items.isEmpty()) {
        // Only manipulate the list when the state is changed, or in the first run.
        val keys = items.map { it.key }.run {
            if (!contains(targetState)) {
                toMutableList().also { it.add(targetState) }
            } else {
                this
            }
        }
        items.clear()
        keys.mapTo(items) { key ->
            MotionAnimationItem(key) {

                fun Modifier.primary(appear: Boolean, fraction: Float): Modifier = run {
                    motionSpec.primaryAnimatorProvider.let { provider ->
                        if (appear) {
                            provider.appear(this, fraction)
                        } else {
                            provider.disappear(this, fraction)
                        }
                    }
                }

                fun Modifier.secondary(target: Boolean, fraction: Float): Modifier = run {
                    motionSpec.secondaryAnimatorProvider.let { provider ->
                        if (target) {
                            provider.appear(this, fraction)
                        } else {
                            provider.disappear(this, fraction)
                        }
                    }
                }

                val primaryFraction by transition.animateFloat(
                    transitionSpec = {
                        motionSpec.primaryAnimatorProvider.run {
                            if (targetState == key) {
                                createAppearAnimationSpec()
                            } else {
                                createDisappearAnimationSpec()
                            }
                        }
                    }
                ) { if (it == key) 1f else 0f }

                val secondaryFraction by transition.animateFloat(
                    transitionSpec = {
                        motionSpec.secondaryAnimatorProvider.run {
                            if (targetState == key) {
                                createAppearAnimationSpec()
                            } else {
                                createDisappearAnimationSpec()
                            }
                        }
                    }
                ) { if (it == key) 1f else 0f }

                val appear = transition.targetState == key
                Box(
                    modifier = Modifier
                        .primary(appear, primaryFraction)
                        .secondary(appear, secondaryFraction)
                ) {
                    content(key)
                }
            }
        }
    } else if (transitionState.currentState == transitionState.targetState) {
        // Remove all the intermediate items from the list once the animation is finished.
        items.removeAll { it.key != transitionState.targetState }
    }

    Box(modifier) {
        items.fastForEach {
            key(it.key) {
                it.content()
            }
        }
    }
}
