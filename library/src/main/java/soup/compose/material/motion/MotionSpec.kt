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

import android.annotation.SuppressLint
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import soup.compose.material.motion.internal.CrossfadeSpec
import soup.compose.material.motion.internal.HoldSpec
import soup.compose.material.motion.internal.MaterialElevationScaleSpec
import soup.compose.material.motion.internal.MaterialFadeSpec
import soup.compose.material.motion.internal.MaterialFadeThroughSpec
import soup.compose.material.motion.internal.MaterialSharedAxisSpec
import soup.compose.material.motion.internal.ScaleSpec

object MotionConstants {
    const val motionDurationShort1 = 75
    const val motionDurationShort2 = 150
    const val motionDurationMedium1 = 200
    const val motionDurationMedium2 = 250
    const val motionDurationLong1 = 300
    const val motionDurationLong2 = 350
}

abstract class MotionSpec(
    val primaryAnimatorProvider: VisibilityAnimationProvider,
    val secondaryAnimatorProvider: VisibilityAnimationProvider,
) {

    @SuppressLint("ComposableNaming")
    @Composable
    internal fun applyThemeValues() {
        primaryAnimatorProvider.apply {
            setAppearDuration(getDuration(appearing = true))
            setDisappearDuration(getDuration(appearing = false))
        }
        secondaryAnimatorProvider.apply {
            setAppearDuration(getDuration(appearing = true))
            setDisappearDuration(getDuration(appearing = false))
        }
    }

    @Composable
    protected abstract fun getDuration(appearing: Boolean): Int
}

@SuppressLint("ModifierFactoryExtensionFunction")
interface VisibilityAnimationProvider {
    fun setAppearDuration(durationMillis: Int)
    fun createAppearAnimationSpec(): FiniteAnimationSpec<Float>
    fun appear(modifier: Modifier, fraction: Float): Modifier

    fun setDisappearDuration(durationMillis: Int)
    fun createDisappearAnimationSpec(): FiniteAnimationSpec<Float>
    fun disappear(modifier: Modifier, fraction: Float): Modifier
}

/**
 * [materialSharedAxis] allows to switch a layout with a shared axis animation.
 *
 * @param axis movement axis of the animation.
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance slide distance of the animation.
 */
fun materialSharedAxis(
    axis: Axis,
    forward: Boolean,
    slideDistance: Dp? = null,
): MotionSpec = MaterialSharedAxisSpec(axis, forward, slideDistance)

/**
 * [materialFadeThrough] allows to switch a layout with a fade through animation.
 */
fun materialFadeThrough(): MotionSpec = MaterialFadeThroughSpec()

/**
 * [materialFade] allows to switch a layout with a fade animation.
 */
fun materialFade(): MotionSpec = MaterialFadeSpec()

/**
 * [materialElevationScale] allows to switch a layout with a elevation scale animation.
 */
fun materialElevationScale(): MotionSpec = MaterialElevationScaleSpec()

/**
 * [hold] allows to switch a layout with no animation.
 */
fun hold(): MotionSpec = HoldSpec()

/**
 * [crossfade] allows to switch a layout with a crossfade animation.
 *
 * @param animationSpec the [AnimationSpec] to configure the animation.
 */
fun crossfade(
    animationSpec: FiniteAnimationSpec<Float> = tween(),
): MotionSpec = CrossfadeSpec(animationSpec)

/**
 * [scale] allows to switch a layout with a scale animation.
 */
fun scale(): MotionSpec = ScaleSpec()
