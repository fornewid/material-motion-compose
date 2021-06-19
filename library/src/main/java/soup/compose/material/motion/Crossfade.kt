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

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * [crossfade] allows to switch a layout with a crossfade animation.
 *
 * @param animationSpec the [AnimationSpec] to configure the animation.
 */
@Deprecated(message = "Use official Crossfade() instead", level = DeprecationLevel.ERROR)
fun crossfade(
    animationSpec: FiniteAnimationSpec<Float> = tween(),
): MotionSpec = throw IllegalArgumentException("Unsupported MotionSpec: crossfade")

/**
 * [Crossfade] allows to switch between two layouts with a crossfade animation.
 *
 * @see androidx.compose.animation.Crossfade
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param modifier Modifier to be applied to the animation container.
 */
@Deprecated(
    "Replaced with official Crossfade()",
    ReplaceWith(
        """Crossfade(
        targetState = targetState,
        modifier = modifier,
        animationSpec = animationSpec,
        content = content
    )""",
        imports = arrayOf(
            "androidx.compose.animation.Crossfade",
            "androidx.compose.animation.core.tween"
        )
    )
)
@Composable
fun <T> Crossfade(
    targetState: T,
    modifier: Modifier = Modifier,
    animationSpec: FiniteAnimationSpec<Float> = tween(),
    content: @Composable (T) -> Unit,
) {
    androidx.compose.animation.Crossfade(
        targetState = targetState,
        modifier = modifier,
        animationSpec = animationSpec,
        content = content
    )
}
