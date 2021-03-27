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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import soup.compose.material.motion.MotionConstants.motionDurationLong1
import soup.compose.material.motion.internal.CrossfadeSpec
import soup.compose.material.motion.internal.ElevationScaleSpec
import soup.compose.material.motion.internal.FadeSpec
import soup.compose.material.motion.internal.FadeThroughSpec
import soup.compose.material.motion.internal.HoldSpec
import soup.compose.material.motion.internal.SharedAxisSpec

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
)

@SuppressLint("ModifierFactoryExtensionFunction")
interface VisibilityAnimationProvider {
    fun setDuration(durationMillis: Int)

    fun createAppearAnimationSpec(): FiniteAnimationSpec<Float>
    fun appear(modifier: Modifier, fraction: Float): Modifier

    fun createDisappearAnimationSpec(): FiniteAnimationSpec<Float>
    fun disappear(modifier: Modifier, fraction: Float): Modifier
}

/**
 * [sharedAxis] allows to switch a layout with a shared axis animation.
 *
 * @param axis movement axis of the animation.
 * @param forward whether the direction of the animation is forward.
 * @param durationMillis total duration of the animation.
 * @param slideDistance slide distance of the animation.
 */
fun sharedAxis(
    axis: Axis,
    forward: Boolean,
    durationMillis: Int = motionDurationLong1,
    slideDistance: Dp? = null,
): MotionSpec = SharedAxisSpec(axis, forward, durationMillis, slideDistance)

/**
 * [sharedAxis] allows to switch a layout with a shared axis animation for [Axis.X].
 *
 * @param forward whether the direction of the animation is forward.
 * @param durationMillis total duration of the animation.
 * @param slideDistance slide distance of the animation.
 */
fun sharedAxisX(
    forward: Boolean,
    durationMillis: Int = motionDurationLong1,
    slideDistance: Dp? = null,
): MotionSpec = SharedAxisSpec(Axis.X, forward, durationMillis, slideDistance)

/**
 * [sharedAxis] allows to switch a layout with a shared axis animation for [Axis.Y].
 *
 * @param forward whether the direction of the animation is forward.
 * @param durationMillis total duration of the animation.
 * @param slideDistance slide distance of the animation.
 */
fun sharedAxisY(
    forward: Boolean,
    durationMillis: Int = motionDurationLong1,
    slideDistance: Dp? = null,
): MotionSpec = SharedAxisSpec(Axis.Y, forward, durationMillis, slideDistance)

/**
 * [sharedAxis] allows to switch a layout with a shared axis animation for [Axis.Z].
 *
 * @param forward whether the direction of the animation is forward.
 * @param durationMillis total duration of the animation.
 * @param slideDistance slide distance of the animation.
 */
fun sharedAxisZ(
    forward: Boolean,
    durationMillis: Int = motionDurationLong1,
    slideDistance: Dp? = null,
): MotionSpec = SharedAxisSpec(Axis.Z, forward, durationMillis, slideDistance)

/**
 * [fadeThrough] allows to switch a layout with a fade through animation.
 *
 * @param durationMillis total duration of the animation.
 */
fun fadeThrough(
    durationMillis: Int = motionDurationLong1,
): MotionSpec = FadeThroughSpec(durationMillis)

/**
 * [fade] allows to switch a layout with a fade animation.
 */
fun fade(): MotionSpec = FadeSpec()

/**
 * [crossfade] allows to switch a layout with a crossfade animation.
 *
 * @param animationSpec the [AnimationSpec] to configure the animation.
 */
fun crossfade(
    animationSpec: FiniteAnimationSpec<Float> = tween(),
): MotionSpec = CrossfadeSpec(animationSpec)

/**
 * [hold] allows to switch a layout with no animation.
 *
 * @param durationMillis total duration of the animation.
 */
fun hold(
    durationMillis: Int = motionDurationLong1,
): MotionSpec = HoldSpec(durationMillis)

/**
 * [elevationScale] allows to switch a layout with a elevation scale animation.
 *
 * @param durationMillis total duration of the animation.
 */
fun elevationScale(
    durationMillis: Int = motionDurationLong1,
): MotionSpec = ElevationScaleSpec(durationMillis)
