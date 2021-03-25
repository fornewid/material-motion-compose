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
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import soup.compose.material.motion.MotionConstants.motionDurationLong1
import soup.compose.material.motion.VisibilityAnimationProvider

@SuppressLint("ModifierFactoryExtensionFunction")
class SlideDistanceProvider(
    private val slideEdge: SlideEdge,
    private val slideDistance: Dp? = null,
) : VisibilityAnimationProvider {

    enum class SlideEdge {
        LEFT, TOP, RIGHT, BOTTOM
    }

    private var durationMillis: Int = motionDurationLong1

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
        val slideDistance = getSlideDistanceOrDefault()
        return when (slideEdge) {
            SlideEdge.LEFT -> appearOffsetX(modifier, fraction, -slideDistance)
            SlideEdge.TOP -> appearOffsetY(modifier, fraction, -slideDistance)
            SlideEdge.RIGHT -> appearOffsetX(modifier, fraction, slideDistance)
            SlideEdge.BOTTOM -> appearOffsetY(modifier, fraction, slideDistance)
        }
    }

    override fun createDisappearAnimationSpec(): FiniteAnimationSpec<Float> {
        return tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        )
    }

    override fun disappear(modifier: Modifier, fraction: Float): Modifier {
        val slideDistance = getSlideDistanceOrDefault()
        return when (slideEdge) {
            SlideEdge.LEFT -> appearOffsetX(modifier, fraction, slideDistance)
            SlideEdge.TOP -> appearOffsetY(modifier, fraction, slideDistance)
            SlideEdge.RIGHT -> appearOffsetX(modifier, fraction, -slideDistance)
            SlideEdge.BOTTOM -> appearOffsetY(modifier, fraction, -slideDistance)
        }
    }

    private fun appearOffsetX(modifier: Modifier, fraction: Float, slideDistance: Dp): Modifier {
        return modifier.offset(x = lerp(slideDistance, 0.dp, fraction))
    }

    private fun appearOffsetY(modifier: Modifier, fraction: Float, slideDistance: Dp): Modifier {
        return modifier.offset(y = lerp(slideDistance, 0.dp, fraction))
    }

    private fun getSlideDistanceOrDefault(): Dp {
        return slideDistance ?: 30.dp
    }
}
