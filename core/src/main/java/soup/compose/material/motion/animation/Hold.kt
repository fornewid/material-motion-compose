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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import soup.compose.material.motion.MotionConstants

/**
 * [holdIn] allows to switch a layout with no enter transition.
 *
 * @param durationMillis the duration of the enter transition.
 */
public fun holdIn(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterTransition = fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = LinearEasing,
    ),
    initialAlpha = 1f,
)

/**
 * [holdOut] allows to switch a layout with no exit transition.
 *
 * @param durationMillis the duration of the exit transition.
 */
public fun holdOut(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitTransition = fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = LinearEasing,
    ),
    // TODO: Refer https://issuetracker.google.com/issues/192993290
    // targetAlpha = 1f
    targetAlpha = 0.999f,
)
