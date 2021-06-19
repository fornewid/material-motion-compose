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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.MotionConstants

private const val ProgressThreshold = 0.35f

private val Int.ForOutgoing: Int
    get() = (this * ProgressThreshold).toInt()

private val Int.ForIncoming: Int
    get() = this - this.ForOutgoing

@Composable
fun rememberSlideDistance(slideDistance: Dp = 30.dp): Int {
    val density = LocalDensity.current
    return remember(density) {
        with(density) { slideDistance.roundToPx() }
    }
}

@ExperimentalAnimationApi
fun materialSharedAxisX(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ContentTransform {
    return materialSharedAxisXIn(
        forward = forward,
        slideDistance = slideDistance,
        durationMillis = durationMillis
    ) with materialSharedAxisXOut(
        forward = forward,
        slideDistance = slideDistance,
        durationMillis = durationMillis
    )
}

@ExperimentalAnimationApi
fun materialSharedAxisY(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ContentTransform {
    return materialSharedAxisYIn(
        forward = forward,
        slideDistance = slideDistance,
        durationMillis = durationMillis
    ) with materialSharedAxisYOut(
        forward = forward,
        slideDistance = slideDistance,
        durationMillis = durationMillis
    )
}

@ExperimentalAnimationApi
fun materialSharedAxisZ(
    forward: Boolean,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ContentTransform {
    return materialSharedAxisZIn(
        forward = forward,
        durationMillis = durationMillis
    ) with materialSharedAxisZOut(
        forward = forward,
        durationMillis = durationMillis
    )
}

@ExperimentalAnimationApi
fun materialSharedAxisXIn(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = {
            if (forward) slideDistance else -slideDistance
        },
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        )
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = durationMillis.ForIncoming,
            delayMillis = durationMillis.ForOutgoing,
            easing = LinearOutSlowInEasing
        )
    )
}

@ExperimentalAnimationApi
fun materialSharedAxisYIn(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterTransition {
    return slideInVertically(
        initialOffsetY = {
            if (forward) slideDistance else -slideDistance
        },
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        )
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = durationMillis.ForIncoming,
            delayMillis = durationMillis.ForOutgoing,
            easing = LinearOutSlowInEasing
        )
    )
}

@ExperimentalAnimationApi
fun materialSharedAxisZIn(
    forward: Boolean,
    initialScale: Float = 0.8f,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterTransition {
    return EnterTransition.None +
        // TODO: I want scaleIn() instead of expandIn()
        //      https://issuetracker.google.com/issues/191325593
        // expandIn(
        //    expandFrom = Alignment.Center,
        //    initialSize = { fullSize -> fullSize * initialScale },
        //    animationSpec = tween(
        //        durationMillis = durationMillis,
        //        easing = FastOutSlowInEasing
        //    )
        // )
        fadeIn(
            animationSpec = tween(
                durationMillis = durationMillis.ForIncoming,
                delayMillis = durationMillis.ForOutgoing,
                easing = LinearOutSlowInEasing
            )
        )
}

@ExperimentalAnimationApi
fun materialSharedAxisXOut(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = {
            if (forward) -slideDistance else slideDistance
        },
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = durationMillis.ForOutgoing,
            delayMillis = 0,
            easing = FastOutLinearInEasing
        )
    )
}

@ExperimentalAnimationApi
fun materialSharedAxisYOut(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitTransition {
    return slideOutVertically(
        targetOffsetY = {
            if (forward) -slideDistance else slideDistance
        },
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = durationMillis.ForOutgoing,
            delayMillis = 0,
            easing = FastOutLinearInEasing
        )
    )
}

@ExperimentalAnimationApi
fun materialSharedAxisZOut(
    forward: Boolean,
    targetScale: Float = 1.1f,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitTransition {
    return ExitTransition.None +
        // TODO: I want scaleOut() instead of shrinkOut()
        //      https://issuetracker.google.com/issues/191325593
        // shrinkOut(
        //    shrinkTowards = Alignment.Center,
        //    targetSize = { fullSize -> fullSize * targetScale },
        //    animationSpec = tween(
        //        durationMillis = durationMillis,
        //        easing = FastOutSlowInEasing
        //    )
        // )
        fadeOut(
            animationSpec = tween(
                durationMillis = durationMillis.ForOutgoing,
                delayMillis = 0,
                easing = FastOutLinearInEasing
            )
        )
}
