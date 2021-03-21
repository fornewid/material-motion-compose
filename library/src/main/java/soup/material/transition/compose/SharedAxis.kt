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

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.lerp
import soup.material.transition.compose.TransitionConstants.DefaultDurationMillis
import soup.material.transition.compose.TransitionConstants.DefaultProgressThreshold

enum class Axis {
    X, Y, Z
}

@Composable
fun <T> SharedAxis(
    targetState: T,
    axis: Axis,
    forward: Boolean,
    modifier: Modifier = Modifier,
    durationMillis: Int = DefaultDurationMillis,
    content: @Composable (T) -> Unit,
) {
    val items = remember { mutableStateListOf<SharedAxisAnimationItem<T>>() }
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
            val slideDistance = 30.dp
            val outgoingDurationMillis = (durationMillis * DefaultProgressThreshold).toInt()
            val incomingDurationMillis = durationMillis - outgoingDurationMillis
            SharedAxisAnimationItem(key) {
                val alpha by transition.animateFloat(
                    transitionSpec = {
                        if (targetState == key) {
                            tween(
                                durationMillis = incomingDurationMillis,
                                delayMillis = outgoingDurationMillis,
                                easing = LinearOutSlowInEasing
                            )
                        } else {
                            tween(
                                durationMillis = outgoingDurationMillis,
                                delayMillis = 0,
                                easing = FastOutLinearInEasing
                            )
                        }
                    }
                ) { if (it == key) 1f else 0f }
                val fraction by transition.animateFloat(
                    transitionSpec = {
                        tween(
                            durationMillis = durationMillis,
                            easing = FastOutSlowInEasing
                        )
                    }
                ) { if (it == key) 1f else 0f }
                val (slideX, slideY, scale) = when (axis) {
                    Axis.X, Axis.Y -> {
                        val start = if (transition.targetState == key) {
                            if (forward) slideDistance else -slideDistance
                        } else {
                            if (forward) -slideDistance else slideDistance
                        }
                        val slide = lerp(start, 0.dp, fraction)
                        Triple(
                            slide.takeIf { axis == Axis.X } ?: 0.dp,
                            slide.takeIf { axis == Axis.Y } ?: 0.dp,
                            1f
                        )
                    }
                    Axis.Z -> {
                        val start = if (transition.targetState == key) {
                            if (forward) 0.8f else 1.1f
                        } else {
                            if (forward) 1.1f else 0.8f
                        }
                        val scale = lerp(start, 1f, fraction)
                        Triple(
                            0.dp,
                            0.dp,
                            scale
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .offset(x = slideX, y = slideY)
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

private data class SharedAxisAnimationItem<T>(
    val key: T,
    val content: @Composable () -> Unit,
)
