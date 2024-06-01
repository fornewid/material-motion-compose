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
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import soup.compose.material.motion.MotionConstants

/**
 * [translateXIn] allows to switch a layout with a translate animation as x axis.
 */
public fun translateXIn(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
    initialOffsetX: (fullHeight: Int) -> Int = { -it / 2 },
): EnterTransition = slideInHorizontally(
    animationSpec = tween(durationMillis),
    initialOffsetX = initialOffsetX,
)

/**
 * [translateXOut] allows to switch a layout with a translate animation as x axis.
 */
public fun translateXOut(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
    targetOffsetX: (fullHeight: Int) -> Int = { -it / 2 },
): ExitTransition = slideOutHorizontally(
    animationSpec = tween(durationMillis),
    targetOffsetX = targetOffsetX,
)

/**
 * [translateYIn] allows to switch a layout with a translate animation as y axis.
 */
public fun translateYIn(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
    initialOffsetY: (fullHeight: Int) -> Int = { -it / 2 },
): EnterTransition = slideInVertically(
    animationSpec = tween(durationMillis),
    initialOffsetY = initialOffsetY,
)

/**
 * [translateYOut] allows to switch a layout with a translate animation as y axis.
 */
public fun translateYOut(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
    targetOffsetY: (fullHeight: Int) -> Int = { -it / 2 },
): ExitTransition = slideOutVertically(
    animationSpec = tween(durationMillis),
    targetOffsetY = targetOffsetY,
)
