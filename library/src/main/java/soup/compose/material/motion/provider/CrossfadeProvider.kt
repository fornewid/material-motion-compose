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
package soup.compose.material.motion.provider

import android.annotation.SuppressLint
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import soup.compose.material.motion.VisibilityAnimationProvider
import soup.compose.material.motion.internal.MotionUtils.lerp

@SuppressLint("ModifierFactoryExtensionFunction")
class CrossfadeProvider(
    private val animationSpec: FiniteAnimationSpec<Float> = tween(),
) : VisibilityAnimationProvider {

    override fun setAppearDuration(durationMillis: Int) {
    }

    override fun createAppearAnimationSpec(): FiniteAnimationSpec<Float> {
        return animationSpec
    }

    override fun appear(modifier: Modifier, fraction: Float): Modifier {
        return modifier.alpha(alpha = lerp(0f, 1f, 0f, 1f, fraction))
    }

    override fun setDisappearDuration(durationMillis: Int) {
    }

    override fun createDisappearAnimationSpec(): FiniteAnimationSpec<Float> {
        return animationSpec
    }

    override fun disappear(modifier: Modifier, fraction: Float): Modifier {
        return modifier.alpha(alpha = lerp(1f, 0f, 0f, 1f, fraction))
    }
}
