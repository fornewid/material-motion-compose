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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import soup.compose.material.motion.animation.materialFadeIn
import soup.compose.material.motion.animation.materialFadeOut

/**
 * [MaterialFade] animates the appearance and disappearance of its content, as [visible] value changes.
 *
 * @see com.google.android.material.transition.MaterialFade
 *
 * @param visible defines whether the [content] should be visible
 * @param modifier modifier for the [Layout] created to contain the [content]
 * @param enterDurationMillis enter duration
 * @param exitDurationMillis exit duration
 * @param label An optional label to differentiate from other animations in Android Studio.
 */
@Composable
public fun MaterialFade(
    visible: Boolean,
    modifier: Modifier = Modifier,
    enterDurationMillis: Int = MotionConstants.DefaultFadeInDuration,
    exitDurationMillis: Int = MotionConstants.DefaultFadeOutDuration,
    label: String = "MaterialFade",
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = materialFadeIn(enterDurationMillis),
        exit = materialFadeOut(exitDurationMillis),
        label = label,
    ) {
        content()
    }
}
