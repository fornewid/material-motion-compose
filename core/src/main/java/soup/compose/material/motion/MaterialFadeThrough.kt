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

/**
 * [MaterialFadeThrough] allows to switch between two layouts with a fade through animation.
 *
 * @see com.google.android.material.transition.MaterialFadeThrough
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param modifier Modifier to be applied to the animation container.
 * @param label An optional label to differentiate from other animations in Android Studio.
 */
@Composable
public fun <T> MaterialFadeThrough(
    targetState: T,
    modifier: Modifier = Modifier,
    label: String = "MaterialFadeThrough",
    content: @Composable AnimatedVisibilityScope.(T) -> Unit,
) {
    MaterialMotion(
        targetState = targetState,
        transitionSpec = {
            soup.compose.material.motion.animation.materialFadeThrough()
        },
        modifier = modifier,
        label = label,
        content = content,
    )
}
