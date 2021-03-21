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

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import soup.material.transition.compose.internal.MaterialTransition
import soup.material.transition.compose.internal.TransitionAnimationItem
import soup.material.transition.compose.internal.TransitionConstants.DefaultDurationMillis
import soup.material.transition.compose.internal.TransitionConstants.DefaultFadeThroughScale
import soup.material.transition.compose.internal.TransitionConstants.DefaultProgressThreshold

/**
 * [FadeThrough] allows to switch between two layouts with a fade through animation.
 *
 * @see com.google.android.material.transition.MaterialFadeThrough
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param modifier Modifier to be applied to the animation container.
 * @param durationMillis total duration of the animation.
 */
@Composable
fun <T> FadeThrough(
    targetState: T,
    modifier: Modifier = Modifier,
    durationMillis: Int = DefaultDurationMillis,
    content: @Composable (T) -> Unit,
) {
    MaterialTransition(
        targetState = targetState,
        modifier = modifier,
        transitionAnimationItem = { key, transition ->
            val easing = FastOutSlowInEasing
            val outgoingDurationMillis = (durationMillis * DefaultProgressThreshold).toInt()
            val incomingDurationMillis = durationMillis - outgoingDurationMillis
            TransitionAnimationItem(key) {
                val alpha by transition.animateFloat(
                    transitionSpec = {
                        if (targetState == key) {
                            tween(
                                durationMillis = incomingDurationMillis,
                                delayMillis = outgoingDurationMillis,
                                easing = easing
                            )
                        } else {
                            tween(
                                durationMillis = outgoingDurationMillis,
                                delayMillis = 0,
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
                                delayMillis = outgoingDurationMillis,
                                easing = easing
                            )
                        } else {
                            tween(
                                durationMillis = outgoingDurationMillis,
                                delayMillis = 0,
                                easing = easing
                            )
                        }
                    }
                ) { if (it == key) 1f else DefaultFadeThroughScale }
                Box(
                    Modifier
                        .alpha(alpha = alpha)
                        .scale(scale = scale)
                ) {
                    content(key)
                }
            }
        }
    )
}
