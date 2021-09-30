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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically

/**
 * [translateXIn] allows to switch a layout with a translate animation as x axis.
 */
@ExperimentalAnimationApi
fun translateXIn(
    initialOffsetX: (fullHeight: Int) -> Int = { -it / 2 },
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = { _, _ ->
        slideInHorizontally(
            animationSpec = tween(durationMillis),
            initialOffsetX = initialOffsetX
        )
    }
)

/**
 * [translateXOut] allows to switch a layout with a translate animation as x axis.
 */
@ExperimentalAnimationApi
fun translateXOut(
    targetOffsetX: (fullHeight: Int) -> Int = { -it / 2 },
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec = ExitMotionSpec(
    transition = { _, _ ->
        slideOutHorizontally(
            animationSpec = tween(durationMillis),
            targetOffsetX = targetOffsetX
        )
    }
)

/**
 * [translateYIn] allows to switch a layout with a translate animation as y axis.
 */
@ExperimentalAnimationApi
fun translateYIn(
    initialOffsetY: (fullHeight: Int) -> Int = { -it / 2 },
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = { _, _ ->
        slideInVertically(
            animationSpec = tween(durationMillis),
            initialOffsetY = initialOffsetY
        )
    }
)

/**
 * [translateYOut] allows to switch a layout with a translate animation as y axis.
 */
@ExperimentalAnimationApi
fun translateYOut(
    targetOffsetY: (fullHeight: Int) -> Int = { -it / 2 },
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec = ExitMotionSpec(
    transition = { _, _ ->
        slideOutVertically(
            animationSpec = tween(durationMillis),
            targetOffsetY = targetOffsetY
        )
    }
)
