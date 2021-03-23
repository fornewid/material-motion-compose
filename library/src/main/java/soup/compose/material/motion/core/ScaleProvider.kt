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
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.util.lerp
import soup.compose.material.motion.core.MotionConstants.DefaultDurationMillis

@SuppressLint("ModifierFactoryExtensionFunction")
class ScaleProvider(
    private val growing: Boolean = true,
) : VisibilityAnimationProvider {

    private var durationMillis: Int = DefaultDurationMillis

    var scaleOnDisappear = true
    var outgoingStartScale = 1f
    var outgoingEndScale = 1.1f
    var incomingStartScale = 0.8f
    var incomingEndScale = 1f

    override fun setDuration(durationMillis: Int) {
        this.durationMillis = durationMillis
    }

    override fun createAppearAnimationSpec(): FiniteAnimationSpec<Float> {
        return tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        )
    }

    override fun appear(modifier: Modifier, fraction: Float): Modifier {
        return modifier.scale(
            scale = if (growing) {
                lerp(incomingStartScale, incomingEndScale, fraction)
            } else {
                lerp(outgoingEndScale, outgoingStartScale, fraction)
            }
        )
    }

    override fun createDisappearAnimationSpec(): FiniteAnimationSpec<Float> {
        return tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        )
    }

    override fun disappear(modifier: Modifier, fraction: Float): Modifier {
        if (!scaleOnDisappear) {
            return modifier
        }
        return modifier.scale(
            scale = if (growing) {
                lerp(outgoingEndScale, outgoingStartScale, fraction)
            } else {
                lerp(incomingStartScale, incomingEndScale, fraction)
            }
        )
    }
}
