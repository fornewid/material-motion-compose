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

package soup.material.transition.compose

import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.util.fastForEach

private const val defaultStartScale = 0.92f
private const val progressThreshold = 0.35f

/**
 * [FadeThrough] allows to switch between two layouts with a fade through animation.
 *
 * @see com.google.android.material.transition.MaterialFadeThrough
 * @see androidx.compose.animation.Crossfade
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param modifier Modifier to be applied to the animation container.
 * @param durationMillis total duration of the animation.
 * @param delayMillis the amount of time in milliseconds that animation waits before starting.
 * @param easing the easing curve that will be used to interpolate between start and end.
 */
@Composable
fun <T> FadeThrough(
    targetState: T,
    modifier: Modifier = Modifier,
    durationMillis: Int = DefaultDurationMillis,
    delayMillis: Int = 0,
    easing: Easing = FastOutSlowInEasing,
    content: @Composable (T) -> Unit,
) {
    val items = remember { mutableStateListOf<FadeThroughAnimationItem<T>>() }
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
            val outgoingDurationMillis = (durationMillis * progressThreshold).toInt()
            val incomingDurationMillis = durationMillis - outgoingDurationMillis
            FadeThroughAnimationItem(key) {
                val alpha by transition.animateFloat(
                    transitionSpec = {
                        if (targetState == key) {
                            tween(
                                durationMillis = incomingDurationMillis,
                                delayMillis = delayMillis + outgoingDurationMillis,
                                easing = easing
                            )
                        } else {
                            tween(
                                durationMillis = outgoingDurationMillis,
                                delayMillis = delayMillis,
                                easing = easing
                            )
                        }
                    }
                ) { if (it == key) 1f else 0f }
                val scale by transition.animateFloat(
                    transitionSpec = {
                        if (targetState == key) {
                            tween(
                                durationMillis = incomingDurationMillis,
                                delayMillis = delayMillis + outgoingDurationMillis,
                                easing = easing
                            )
                        } else {
                            tween(
                                durationMillis = outgoingDurationMillis,
                                delayMillis = delayMillis,
                                easing = easing
                            )
                        }
                    }
                ) { if (it == key) 1f else defaultStartScale }
                Box(
                    Modifier
                        .alpha(alpha = alpha)
                        .scale(scale = scale)
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

private data class FadeThroughAnimationItem<T>(
    val key: T,
    val content: @Composable () -> Unit,
)
