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

/**
 * [materialElevationScaleIn] allows to switch a layout with elevation scale enter transition.
 *
 * @param initialAlpha the starting alpha of the enter transition.
 * @param initialScale the starting scale of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
public fun materialElevationScaleIn(
    initialAlpha: Float = 0.85f,
    initialScale: Float = 0.85f,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = { _, _ ->
        soup.compose.material.motion.animation.materialElevationScaleIn(
            initialAlpha = initialAlpha,
            initialScale = initialScale,
            durationMillis = durationMillis
        )
    }
)

/**
 * [materialElevationScaleOut] allows to switch a layout with elevation scale exit transition.
 *
 * @param targetAlpha the target alpha of the exit transition.
 * @param targetScale the target scale of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
public fun materialElevationScaleOut(
    targetAlpha: Float = 0.85f,
    targetScale: Float = 0.85f,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec {
    return ExitMotionSpec(
        transition = { _, _ ->
            soup.compose.material.motion.animation.materialElevationScaleOut(
                targetAlpha = targetAlpha,
                targetScale = targetScale,
                durationMillis = durationMillis,
            )
        }
    )
}
