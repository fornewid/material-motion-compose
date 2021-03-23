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
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import soup.compose.material.motion.MotionConstants.DefaultDurationMillis
import soup.compose.material.motion.internal.FadeThroughSpec
import soup.compose.material.motion.internal.SharedAxisSpec

object MotionConstants {
    const val DefaultDurationMillis: Int = 300
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

fun sharedAxis(
    axis: Axis,
    forward: Boolean,
    durationMillis: Int = DefaultDurationMillis,
    slideDistance: Dp? = null,
): MotionSpec = SharedAxisSpec(axis, forward, durationMillis, slideDistance)

fun sharedAxisX(
    forward: Boolean,
    durationMillis: Int = DefaultDurationMillis,
    slideDistance: Dp? = null,
): MotionSpec = SharedAxisSpec(Axis.X, forward, durationMillis, slideDistance)

fun sharedAxisY(
    forward: Boolean,
    durationMillis: Int = DefaultDurationMillis,
    slideDistance: Dp? = null,
): MotionSpec = SharedAxisSpec(Axis.Y, forward, durationMillis, slideDistance)

fun sharedAxisZ(
    forward: Boolean,
    durationMillis: Int = DefaultDurationMillis
): MotionSpec = SharedAxisSpec(Axis.Z, forward, durationMillis)

fun fadeThrough(
    durationMillis: Int = DefaultDurationMillis,
): MotionSpec = FadeThroughSpec(durationMillis)
