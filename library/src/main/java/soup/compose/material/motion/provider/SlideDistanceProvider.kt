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
    private val slideDistance: Dp,
) : VisibilityAnimationProvider {

    enum class SlideEdge {
        LEFT, TOP, RIGHT, BOTTOM
    }

    private var appearDurationMillis: Int = motionDurationLong1
    private var disappearDurationMillis: Int = motionDurationLong1

    override fun setAppearDuration(durationMillis: Int) {
        this.appearDurationMillis = durationMillis
    }

    override fun createAppearAnimationSpec(): FiniteAnimationSpec<Float> {
        return createSlideDistanceAnimationSpec(appearing = true)
    }

    override fun appear(modifier: Modifier, fraction: Float): Modifier {
        val slideDistance = getSlideDistanceOrDefault()
        return when (slideEdge) {
            SlideEdge.LEFT -> modifier.appearOffsetX(fraction, -slideDistance, 0.dp)
            SlideEdge.TOP -> modifier.appearOffsetY(fraction, -slideDistance, 0.dp)
            SlideEdge.RIGHT -> modifier.appearOffsetX(fraction, slideDistance, 0.dp)
            SlideEdge.BOTTOM -> modifier.appearOffsetY(fraction, slideDistance, 0.dp)
        }
    }

    override fun setDisappearDuration(durationMillis: Int) {
        this.disappearDurationMillis = durationMillis
    }

    override fun createDisappearAnimationSpec(): FiniteAnimationSpec<Float> {
        return createSlideDistanceAnimationSpec(appearing = false)
    }

    override fun disappear(modifier: Modifier, fraction: Float): Modifier {
        val slideDistance = getSlideDistanceOrDefault()
        return when (slideEdge) {
            SlideEdge.LEFT -> modifier.appearOffsetX(fraction, 0.dp, slideDistance)
            SlideEdge.TOP -> modifier.appearOffsetY(fraction, 0.dp, slideDistance)
            SlideEdge.RIGHT -> modifier.appearOffsetX(fraction, 0.dp, -slideDistance)
            SlideEdge.BOTTOM -> modifier.appearOffsetY(fraction, 0.dp, -slideDistance)
        }
    }

    private fun createSlideDistanceAnimationSpec(appearing: Boolean): FiniteAnimationSpec<Float> {
        return tween(
            durationMillis = if (appearing) appearDurationMillis else disappearDurationMillis,
            easing = FastOutSlowInEasing
        )
    }

    private fun Modifier.appearOffsetX(
        fraction: Float,
        startTranslation: Dp,
        endTranslation: Dp,
    ): Modifier {
        return offset(x = lerp(startTranslation, endTranslation, fraction))
    }

    private fun Modifier.appearOffsetY(
        fraction: Float,
        startTranslation: Dp,
        endTranslation: Dp,
    ): Modifier {
        return offset(y = lerp(startTranslation, endTranslation, fraction))
    }

    private fun getSlideDistanceOrDefault(): Dp {
        return slideDistance
    }
}
