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

package soup.compose.material.motion.animation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import soup.compose.material.motion.MotionConstants

private const val DefaultFadeEndThresholdEnter = 0.3f

private val Int.ForFade: Int
    get() = (this * DefaultFadeEndThresholdEnter).toInt()

/**
 * [materialFadeIn] allows to switch a layout with a fade-in animation.
 */
public fun materialFadeIn(
    durationMillis: Int = MotionConstants.DefaultFadeInDuration,
): EnterTransition = fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForFade,
        easing = LinearEasing,
    ),
) + scaleIn(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing,
    ),
    initialScale = 0.8f,
)

/**
 * [materialFadeOut] allows to switch a layout with a fade-out animation.
 */
public fun materialFadeOut(
    durationMillis: Int = MotionConstants.DefaultFadeOutDuration,
): ExitTransition = fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = LinearEasing,
    ),
)
