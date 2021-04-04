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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import soup.compose.material.motion.MotionConstants.motionDurationLong1
import soup.compose.material.motion.VisibilityAnimationProvider

data class GraphicsLayerData(
    val scaleX: Float = 1f,
    val scaleY: Float = 1f,
    val alpha: Float = 1f,
    val translationX: Float = 0f,
    val translationY: Float = 0f,
    val rotationX: Float = 0f,
    val rotationY: Float = 0f,
    val rotationZ: Float = 0f,
)

@SuppressLint("ModifierFactoryExtensionFunction")
class GraphicsLayerProvider(
    private val from: GraphicsLayerData,
    private val to: GraphicsLayerData,
) : VisibilityAnimationProvider {

    private var appearDurationMillis: Int = motionDurationLong1
    private var disappearDurationMillis: Int = motionDurationLong1

    override fun setAppearDuration(durationMillis: Int) {
        this.appearDurationMillis = durationMillis
    }

    override fun createAppearAnimationSpec(): FiniteAnimationSpec<Float> {
        return createAnimationSpec(appearing = true)
    }

    override fun appear(modifier: Modifier, fraction: Float): Modifier {
        return modifier.graphicsLayer(
            scaleX = lerp(from.scaleX, to.scaleX, fraction),
            scaleY = lerp(from.scaleY, to.scaleY, fraction),
            alpha = lerp(from.alpha, to.alpha, fraction),
            translationX = lerp(from.translationX, to.translationX, fraction),
            translationY = lerp(from.translationY, to.translationY, fraction),
            rotationX = lerp(from.rotationX, to.rotationX, fraction),
            rotationY = lerp(from.rotationY, to.rotationY, fraction),
            rotationZ = lerp(from.rotationZ, to.rotationZ, fraction)
        )
    }

    override fun setDisappearDuration(durationMillis: Int) {
        this.disappearDurationMillis = durationMillis
    }

    override fun createDisappearAnimationSpec(): FiniteAnimationSpec<Float> {
        return createAnimationSpec(appearing = false)
    }

    override fun disappear(modifier: Modifier, fraction: Float): Modifier {
        return modifier.graphicsLayer(
            scaleX = lerp(to.scaleX, from.scaleX, fraction),
            scaleY = lerp(to.scaleY, from.scaleY, fraction),
            alpha = lerp(to.alpha, from.alpha, fraction),
            translationX = lerp(to.translationX, from.translationX, fraction),
            translationY = lerp(to.translationY, from.translationY, fraction),
            rotationX = lerp(to.rotationX, from.rotationX, fraction),
            rotationY = lerp(to.rotationY, from.rotationY, fraction),
            rotationZ = lerp(to.rotationZ, from.rotationZ, fraction)
        )
    }

    private fun createAnimationSpec(appearing: Boolean): FiniteAnimationSpec<Float> {
        return tween(
            durationMillis = if (appearing) appearDurationMillis else disappearDurationMillis,
            easing = FastOutSlowInEasing
        )
    }
}
