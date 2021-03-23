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
package soup.compose.material.motion

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.core.FadeThroughProvider
import soup.compose.material.motion.core.MaterialVisibility
import soup.compose.material.motion.core.MotionConstants.DefaultDurationMillis
import soup.compose.material.motion.core.ScaleProvider
import soup.compose.material.motion.core.SlideDistanceProvider
import soup.compose.material.motion.core.SlideEdge
import soup.compose.material.motion.core.VisibilityAnimationProvider

fun sharedAxisSpec(
    axis: Axis,
    forward: Boolean,
    slideDistance: Dp = 30.dp,
    durationMillis: Int = DefaultDurationMillis,
): MaterialVisibility = SharedAxisSpec(axis, forward, slideDistance, durationMillis)

private data class SharedAxisSpec(
    private val axis: Axis,
    private val forward: Boolean,
    private val slideDistance: Dp,
    private val durationMillis: Int,
) : MaterialVisibility(
    createPrimaryAnimatorProvider(axis, forward, slideDistance).withDuration(durationMillis),
    createSecondaryAnimatorProvider().withDuration(durationMillis)
) {

    companion object {

        private fun VisibilityAnimationProvider.withDuration(durationMillis: Int) =
            apply { setDuration(durationMillis) }

        private fun createPrimaryAnimatorProvider(
            axis: Axis,
            forward: Boolean,
            slideDistance: Dp,
        ): VisibilityAnimationProvider = when (axis) {
            Axis.X -> SlideDistanceProvider(
                if (forward) SlideEdge.RIGHT else SlideEdge.LEFT,
                slideDistance
            )
            Axis.Y -> SlideDistanceProvider(
                if (forward) SlideEdge.BOTTOM else SlideEdge.TOP,
                slideDistance
            )
            Axis.Z -> ScaleProvider(forward)
        }

        private fun createSecondaryAnimatorProvider(): VisibilityAnimationProvider {
            return FadeThroughProvider()
        }
    }
}
