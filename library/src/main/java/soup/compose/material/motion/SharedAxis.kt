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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import soup.compose.material.motion.MotionConstants.motionDurationLong1

enum class Axis {
    X, Y, Z
}

/**
 * [SharedAxis] allows to switch between two layouts with a shared axis animation.
 *
 * @see com.google.android.material.transition.MaterialSharedAxis
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param axis movement axis of the animation.
 * @param forward whether the direction of the animation is forward.
 * @param modifier Modifier to be applied to the animation container.
 * @param durationMillis total duration of the animation.
 * @param slideDistance slide distance of the animation.
 */
@Composable
fun <T> SharedAxis(
    targetState: T,
    axis: Axis,
    forward: Boolean,
    modifier: Modifier = Modifier,
    durationMillis: Int = motionDurationLong1,
    slideDistance: Dp? = null,
    content: @Composable (T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        motionSpec = sharedAxis(axis, forward, durationMillis, slideDistance),
        modifier = modifier,
        content = content
    )
}
