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

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import soup.compose.material.motion.Axis
import soup.compose.material.motion.MotionConstants

private const val ProgressThreshold = 0.35f

@ExperimentalAnimationApi
fun materialSharedAxis(
    axis: Axis,
    forward: Boolean,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ContentTransform {
    return materialSharedAxisIn(
        axis = axis,
        forward = forward,
        durationMillis = durationMillis
    ) with materialSharedAxisOut(
        axis = axis,
        forward = forward,
        durationMillis = durationMillis
    )
}

@ExperimentalAnimationApi
fun materialSharedAxisIn(
    axis: Axis,
    forward: Boolean,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterTransition {
    val outgoingDurationMillis = (durationMillis * ProgressThreshold).toInt()
    val incomingDurationMillis = durationMillis - outgoingDurationMillis
    return when (axis) {
        Axis.X -> {
            slideInHorizontally(
                initialOffsetX = {
                    // TODO: if possible, use 30.dp instead.
                    if (forward) 30 else -30
                },
                animationSpec = tween(
                    durationMillis = durationMillis,
                    easing = FastOutSlowInEasing
                )
            )
        }
        Axis.Y -> {
            slideInVertically(
                initialOffsetY = {
                    // TODO: if possible, use 30.dp instead.
                    if (forward) 30 else -30
                },
                animationSpec = tween(
                    durationMillis = durationMillis,
                    easing = FastOutSlowInEasing
                )
            )
        }
        Axis.Z -> {
            EnterTransition.None
            // TODO: I want scaleIn() instead of expandIn()
            //      https://issuetracker.google.com/issues/191325593
            // expandIn(
            //    expandFrom = Alignment.Center,
            //    initialSize = { fullSize -> fullSize * 0.8f },
            //    animationSpec = tween(
            //        durationMillis = durationMillis,
            //        easing = FastOutSlowInEasing
            //    )
            // )
        }
    } + fadeIn(
        animationSpec = tween(
            durationMillis = incomingDurationMillis,
            delayMillis = outgoingDurationMillis,
            easing = LinearOutSlowInEasing
        )
    )
}

@ExperimentalAnimationApi
fun materialSharedAxisOut(
    axis: Axis,
    forward: Boolean,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitTransition {
    val outgoingDurationMillis = (durationMillis * ProgressThreshold).toInt()
    return when (axis) {
        Axis.X -> slideOutHorizontally(
            targetOffsetX = {
                // TODO: if possible, use 30.dp instead.
                if (forward) -30 else 30
            },
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            )
        )
        Axis.Y -> slideOutVertically(
            targetOffsetY = {
                // TODO: if possible, use 30.dp instead.
                if (forward) -30 else 30
            },
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            )
        )
        Axis.Z -> ExitTransition.None
        // TODO: I want scaleOut() instead of shrinkOut()
        //      https://issuetracker.google.com/issues/191325593
        // shrinkOut(
        //    shrinkTowards = Alignment.Center,
        //    targetSize = { fullSize -> fullSize * 1.1f },
        //    animationSpec = tween(
        //        durationMillis = durationMillis,
        //        easing = FastOutSlowInEasing
        //    )
        // )
    } + fadeOut(
        animationSpec = tween(
            durationMillis = outgoingDurationMillis,
            delayMillis = 0,
            easing = FastOutLinearInEasing
        )
    )
}
