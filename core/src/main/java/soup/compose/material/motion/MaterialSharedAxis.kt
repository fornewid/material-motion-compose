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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Deprecated(
    message = "Replaced with MotionConstants.DefaultSlideDistance",
    replaceWith = ReplaceWith(
        "MotionConstants.DefaultSlideDistance",
        "soup.compose.material.motion.MotionConstants"
    )
)
val DefaultSlideDistance: Dp = 30.dp

private const val ProgressThreshold = 0.35f

private val Int.ForOutgoing: Int
    get() = (this * ProgressThreshold).toInt()

private val Int.ForIncoming: Int
    get() = this - this.ForOutgoing

/**
 * [materialSharedAxisX] allows to switch a layout with shared X-axis transition.
 *
 * @param slideDistance the slide distance of transition.
 * @param durationMillis the duration of transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisX(
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): MotionSpec = materialSharedAxisXIn(
    slideDistance = slideDistance,
    durationMillis = durationMillis
) with materialSharedAxisXOut(
    slideDistance = slideDistance,
    durationMillis = durationMillis
)

/**
 * [materialSharedAxisXIn] allows to switch a layout with shared X-axis enter transition.
 *
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisXIn(
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = { forward, density ->
        soup.compose.material.motion.animation.materialSharedAxisXIn(
            forward = forward,
            density = density,
            slideDistance = slideDistance,
            durationMillis = durationMillis
        )
    }
)

/**
 * [materialSharedAxisXOut] allows to switch a layout with shared X-axis exit transition.
 *
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisXOut(
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec = ExitMotionSpec(
    transition = { forward, density ->
        soup.compose.material.motion.animation.materialSharedAxisXOut(
            forward = forward,
            density = density,
            slideDistance = slideDistance,
            durationMillis = durationMillis
        )
    }
)

/**
 * [materialSharedAxisY] allows to switch a layout with shared Y-axis transition.
 *
 * @param slideDistance the slide distance of transition.
 * @param durationMillis the duration of transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisY(
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): MotionSpec = materialSharedAxisYIn(
    slideDistance = slideDistance,
    durationMillis = durationMillis
) with materialSharedAxisYOut(
    slideDistance = slideDistance,
    durationMillis = durationMillis
)

/**
 * [materialSharedAxisYIn] allows to switch a layout with shared Y-axis enter transition.
 *
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisYIn(
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = { forward, density ->
        soup.compose.material.motion.animation.materialSharedAxisYIn(
            forward = forward,
            density = density,
            slideDistance = slideDistance,
            durationMillis = durationMillis
        )
    }
)

/**
 * [materialSharedAxisYOut] allows to switch a layout with shared Y-axis exit transition.
 *
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisYOut(
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec = ExitMotionSpec(
    transition = { forward, density ->
        soup.compose.material.motion.animation.materialSharedAxisYOut(
            forward = forward,
            density = density,
            slideDistance = slideDistance,
            durationMillis = durationMillis
        )
    }
)

/**
 * [materialSharedAxisZ] allows to switch a layout with shared Z-axis transition.
 *
 * @param durationMillis the duration of transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisZ(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): MotionSpec = materialSharedAxisZIn(
    durationMillis = durationMillis
) with materialSharedAxisZOut(
    durationMillis = durationMillis
)

/**
 * [materialSharedAxisZIn] allows to switch a layout with shared Z-axis enter transition.
 *
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisZIn(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = { forward, _ ->
        soup.compose.material.motion.animation.materialSharedAxisZIn(
            forward = forward,
            durationMillis = durationMillis
        )
    }
)

/**
 * [materialSharedAxisZOut] allows to switch a layout with shared Z-axis exit transition.
 *
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
fun materialSharedAxisZOut(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec = ExitMotionSpec(
    transition = { forward, _ ->
        soup.compose.material.motion.animation.materialSharedAxisZOut(
            forward = forward,
            durationMillis = durationMillis
        )
    }
)

/**
 * [MaterialSharedAxisX] allows to switch between two layouts with a shared X-axis animation.
 *
 * @see com.google.android.material.transition.MaterialSharedAxis
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param forward whether the direction of the animation is forward.
 * @param modifier Modifier to be applied to the animation container.
 * @param slideDistance slide distance of the animation.
 */
@ExperimentalAnimationApi
@Composable
fun <T> MaterialSharedAxisX(
    targetState: T,
    forward: Boolean,
    modifier: Modifier = Modifier,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    content: @Composable (T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        motionSpec = materialSharedAxisX(slideDistance),
        modifier = modifier,
        pop = forward.not(),
        content = content
    )
}

/**
 * [MaterialSharedAxisY] allows to switch between two layouts with a shared Y-axis animation.
 *
 * @see com.google.android.material.transition.MaterialSharedAxis
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param forward whether the direction of the animation is forward.
 * @param modifier Modifier to be applied to the animation container.
 * @param slideDistance slide distance of the animation.
 */
@ExperimentalAnimationApi
@Composable
fun <T> MaterialSharedAxisY(
    targetState: T,
    forward: Boolean,
    modifier: Modifier = Modifier,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    content: @Composable (T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        motionSpec = materialSharedAxisY(slideDistance),
        modifier = modifier,
        pop = forward.not(),
        content = content
    )
}

/**
 * [MaterialSharedAxisZ] allows to switch between two layouts with a shared Z-axis animation.
 *
 * @see com.google.android.material.transition.MaterialSharedAxis
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param forward whether the direction of the animation is forward.
 * @param modifier Modifier to be applied to the animation container.
 */
@ExperimentalAnimationApi
@Composable
fun <T> MaterialSharedAxisZ(
    targetState: T,
    forward: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        motionSpec = materialSharedAxisZ(),
        modifier = modifier,
        pop = forward.not(),
        content = content
    )
}
