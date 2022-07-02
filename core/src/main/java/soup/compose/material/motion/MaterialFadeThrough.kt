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
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * [materialFadeThrough] allows to switch a layout with a fade through animation.
 *
 * @param durationMillis the duration of transition.
 */
@Deprecated(
    message = "Use materialFadeThrough() of animation package instead.",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "materialFadeThrough()",
        "soup.compose.material.motion.animation.materialFadeThrough",
    )
)
@ExperimentalAnimationApi
public fun materialFadeThrough(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): MotionSpec = TODO()

/**
 * [materialFadeThroughIn] allows to switch a layout with fade through enter transition.
 *
 * @param initialScale the starting scale of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@Deprecated(
    message = "Use materialFadeThroughIn() of animation package instead.",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "materialFadeThroughIn()",
        "soup.compose.material.motion.animation.materialFadeThroughIn",
    )
)
@ExperimentalAnimationApi
public fun materialFadeThroughIn(
    initialScale: Float = 0.92f,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterMotionSpec = EnterMotionSpec(
    transition = { _, _ ->
        soup.compose.material.motion.animation.materialFadeThroughIn(
            initialScale = initialScale,
            durationMillis = durationMillis
        )
    }
)

/**
 * [materialFadeThroughOut] allows to switch a layout with fade through exit transition.
 *
 * @param durationMillis the duration of the exit transition.
 */
@Deprecated(
    message = "Use materialFadeThroughOut() of animation package instead.",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "materialFadeThroughOut()",
        "soup.compose.material.motion.animation.materialFadeThroughOut",
    )
)
@ExperimentalAnimationApi
public fun materialFadeThroughOut(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitMotionSpec = ExitMotionSpec(
    transition = { _, _ ->
        soup.compose.material.motion.animation.materialFadeThroughOut(
            durationMillis = durationMillis
        )
    }
)

/**
 * [MaterialFadeThrough] allows to switch between two layouts with a fade through animation.
 *
 * @see com.google.android.material.transition.MaterialFadeThrough
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param modifier Modifier to be applied to the animation container.
 */
@ExperimentalAnimationApi
@Composable
public fun <T> MaterialFadeThrough(
    targetState: T,
    modifier: Modifier = Modifier,
    content: @Composable AnimatedVisibilityScope.(T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        transitionSpec = {
            soup.compose.material.motion.animation.materialFadeThrough()
        },
        modifier = modifier,
        content = content
    )
}
