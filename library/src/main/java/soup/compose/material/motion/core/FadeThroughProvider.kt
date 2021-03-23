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
package soup.compose.material.motion.core

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import soup.compose.material.motion.core.MotionConstants.DefaultDurationMillis

@SuppressLint("ModifierFactoryExtensionFunction")
class FadeThroughProvider : VisibilityAnimationProvider {

    private var durationMillis: Int = DefaultDurationMillis

    override fun setDuration(durationMillis: Int) {
        this.durationMillis = durationMillis
    }

    override fun createAppearAnimationSpec(): FiniteAnimationSpec<Float> {
        return createFadeThroughAnimationSpec(appear = true)
    }

    override fun appear(modifier: Modifier, fraction: Float): Modifier {
        return modifier.alpha(alpha = fraction)
    }

    override fun createDisappearAnimationSpec(): FiniteAnimationSpec<Float> {
        return createFadeThroughAnimationSpec(appear = false)
    }

    override fun disappear(modifier: Modifier, fraction: Float): Modifier {
        return modifier.alpha(alpha = fraction)
    }

    private fun createFadeThroughAnimationSpec(appear: Boolean): FiniteAnimationSpec<Float> {
        val outgoingDurationMillis = (durationMillis * PROGRESS_THRESHOLD).toInt()
        val incomingDurationMillis = durationMillis - outgoingDurationMillis
        return if (appear) {
            tween(
                durationMillis = incomingDurationMillis,
                delayMillis = outgoingDurationMillis,
                easing = LinearOutSlowInEasing
            )
        } else {
            tween(
                durationMillis = outgoingDurationMillis,
                delayMillis = 0,
                easing = FastOutLinearInEasing
            )
        }
    }

    companion object {

        private const val PROGRESS_THRESHOLD = 0.35f
    }
}
