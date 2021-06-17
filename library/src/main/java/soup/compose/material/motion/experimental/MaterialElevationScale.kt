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
package soup.compose.material.motion.experimental

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import soup.compose.material.motion.MotionConstants

@ExperimentalAnimationApi
fun materialElevationScaleIn(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterTransition {
    return fadeIn(
        initialAlpha = 0.85f,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = LinearEasing
        )
    ) + EnterTransition.None
    // TODO: I want scaleIn() instead of expandIn()
    //      https://issuetracker.google.com/issues/191325593
    // expandIn(
    //    expandFrom = Alignment.Center,
    //    initialSize = { fullSize -> fullSize * 0.85f },
    //    animationSpec = tween(
    //        durationMillis = durationMillis,
    //        easing = FastOutSlowInEasing
    //    )
    // )
}

@ExperimentalAnimationApi
fun materialElevationScaleOut(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitTransition {
    return fadeOut(
        targetAlpha = 0.85f,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = LinearEasing
        )
    ) + ExitTransition.None
    // TODO: I want scaleOut() instead of shrinkOut()
    //      https://issuetracker.google.com/issues/191325593
    // shrinkOut(
    //    shrinkTowards = Alignment.Center,
    //    targetSize = { fullSize -> fullSize * 0.85f },
    //    animationSpec = tween(
    //        durationMillis = durationMillis,
    //        easing = FastOutSlowInEasing
    //    )
    // )
}
