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

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import soup.compose.material.motion.core.MotionConstants.DefaultDurationMillis
import soup.compose.material.motion.core.MotionConstants.DefaultProgressThreshold
import soup.compose.material.motion.internal.MaterialTransition
import soup.compose.material.motion.internal.MotionAnimationItem

enum class Axis {
    X, Y, Z
}

/**
 * [SharedAxis] allows to switch between two layouts with a shared axis animation.
 *
 * @see com.google.android.material.transition.MaterialSharedAxis
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param axis
 * @param forward
 * @param modifier Modifier to be applied to the animation container.
 * @param durationMillis total duration of the animation.
 */
@Composable
fun <T> SharedAxis(
    targetState: T,
    axis: Axis,
    forward: Boolean,
    modifier: Modifier = Modifier,
    durationMillis: Int = DefaultDurationMillis,
    slideDistance: Dp = 30.dp,
    content: @Composable (T) -> Unit,
) {
    MaterialTransition(
        targetState = targetState,
        modifier = modifier,
        transitionAnimationItem = { key, transition ->
            MotionAnimationItem(key) {
                val current: Boolean = transition.targetState == key

                fun primaryAnimationSpec(): FiniteAnimationSpec<Float> {
                    return tween(
                        durationMillis = durationMillis,
                        easing = FastOutSlowInEasing
                    )
                }
                fun Modifier.primary(
                    target: Boolean,
                    forward: Boolean,
                    fraction: Float,
                ): Modifier {
                    val (slideX, slideY, scale) = when (axis) {
                        Axis.X -> {
                            val start = if (target) {
                                if (forward) slideDistance else -slideDistance
                            } else {
                                if (forward) -slideDistance else slideDistance
                            }
                            val slide = lerp(start, 0.dp, fraction)
                            Triple(slide, 0.dp, 1f)
                        }
                        Axis.Y -> {
                            val start = if (target) {
                                if (forward) slideDistance else -slideDistance
                            } else {
                                if (forward) -slideDistance else slideDistance
                            }
                            val slide = lerp(start, 0.dp, fraction)
                            Triple(0.dp, slide, 1f)
                        }
                        Axis.Z -> {
                            val start = if (target) {
                                if (forward) 0.8f else 1.1f
                            } else {
                                if (forward) 1.1f else 0.8f
                            }
                            val scale = lerp(start, 1f, fraction)
                            Triple(0.dp, 0.dp, scale)
                        }
                    }
                    return offset(x = slideX, y = slideY)
                        .scale(scale = scale)
                }

                fun secondaryAnimationSpec(target: Boolean): FiniteAnimationSpec<Float> {
                    val outgoingDurationMillis = (durationMillis * DefaultProgressThreshold).toInt()
                    val incomingDurationMillis = durationMillis - outgoingDurationMillis
                    return if (target) {
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

                fun Modifier.secondary(
                    target: Boolean,
                    forward: Boolean,
                    fraction: Float,
                ): Modifier {
                    return alpha(alpha = fraction)
                }

                val primaryFraction by transition.animateFloat(
                    transitionSpec = { primaryAnimationSpec() }
                ) { if (it == key) 1f else 0f }

                val secondaryFraction by transition.animateFloat(
                    transitionSpec = { secondaryAnimationSpec(current) }
                ) { if (it == key) 1f else 0f }

                Box(
                    modifier = Modifier
                        .primary(current, forward, primaryFraction)
                        .secondary(current, forward, secondaryFraction)
                ) {
                    content(key)
                }
            }
        }
    )
}
