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

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import soup.compose.material.motion.animation.rememberSlideDistance

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
 * @param label An optional label to differentiate from other animations in Android Studio.
 */
@Composable
public fun <T> MaterialSharedAxisX(
    targetState: T,
    forward: Boolean,
    modifier: Modifier = Modifier,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    label: String = "MaterialSharedAxisX",
    content: @Composable AnimatedVisibilityScope.(T) -> Unit,
) {
    val distance = rememberSlideDistance(slideDistance)
    MaterialMotion(
        targetState = targetState,
        transitionSpec = {
            soup.compose.material.motion.animation.materialSharedAxisX(
                forward = forward,
                slideDistance = distance,
            )
        },
        modifier = modifier,
        pop = forward.not(),
        label = label,
        content = content,
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
 * @param label An optional label to differentiate from other animations in Android Studio.
 */
@Composable
public fun <T> MaterialSharedAxisY(
    targetState: T,
    forward: Boolean,
    modifier: Modifier = Modifier,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    label: String = "MaterialSharedAxisY",
    content: @Composable AnimatedVisibilityScope.(T) -> Unit,
) {
    val distance = rememberSlideDistance(slideDistance)
    MaterialMotion(
        targetState = targetState,
        transitionSpec = {
            soup.compose.material.motion.animation.materialSharedAxisY(
                forward = forward,
                slideDistance = distance,
            )
        },
        modifier = modifier,
        pop = forward.not(),
        label = label,
        content = content,
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
 * @param label An optional label to differentiate from other animations in Android Studio.
 */
@Composable
public fun <T> MaterialSharedAxisZ(
    targetState: T,
    forward: Boolean,
    modifier: Modifier = Modifier,
    label: String = "MaterialSharedAxisZ",
    content: @Composable AnimatedVisibilityScope.(T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        transitionSpec = {
            soup.compose.material.motion.animation.materialSharedAxisZ(
                forward = forward,
            )
        },
        modifier = modifier,
        pop = forward.not(),
        label = label,
        content = content,
    )
}
