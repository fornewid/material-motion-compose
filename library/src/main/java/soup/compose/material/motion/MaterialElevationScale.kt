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
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

/**
 * [materialElevationScale] allows to switch a layout with a elevation scale animation.
 *
 * @param durationMillis the duration of transition.
 */
@Deprecated(
    message = "Replaced with materialElevationScaleIn() and materialElevationScaleOut()",
    replaceWith = ReplaceWith(
        "materialElevationScaleIn(durationMillis = durationMillis) with " +
            "materialElevationScaleOut(durationMillis = durationMillis)",
        "soup.compose.material.motion.materialElevationScaleIn",
        "soup.compose.material.motion.materialElevationScaleOut"
    ),
)
@ExperimentalAnimationApi
fun materialElevationScale(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): MotionSpec = materialElevationScaleIn(
    durationMillis = durationMillis
) with materialElevationScaleOut(
    durationMillis = durationMillis
)

/**
 * [materialElevationScaleIn] allows to switch a layout with elevation scale enter transition.
 *
 * @param initialAlpha the starting alpha of the enter transition.
 * @param initialScale the starting scale of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialElevationScaleIn(
    initialAlpha: Float = 0.85f,
    initialScale: Float = 0.85f,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = fadeIn(
        initialAlpha = initialAlpha,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = LinearEasing
        )
    ),
    transitionExtra = scaleIn(
        initialScale = initialScale,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        )
    )
)

/**
 * [materialElevationScaleOut] allows to switch a layout with elevation scale exit transition.
 *
 * @param targetAlpha the target alpha of the exit transition.
 * @param targetScale the target scale of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialElevationScaleOut(
    targetAlpha: Float = 0.85f,
    targetScale: Float = 0.85f,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec {
    return ExitMotionSpec(
        transition = fadeOut(
            targetAlpha = targetAlpha,
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            )
        ),
        transitionExtra = scaleOut(
            targetScale = targetScale,
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            )
        )
    )
}
