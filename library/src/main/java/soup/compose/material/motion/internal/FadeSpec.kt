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
package soup.compose.material.motion.internal

import androidx.compose.runtime.Composable
import soup.compose.material.motion.MaterialMotions
import soup.compose.material.motion.MotionSpec
import soup.compose.material.motion.VisibilityAnimationProvider
import soup.compose.material.motion.provider.FadeProvider
import soup.compose.material.motion.provider.ScaleProvider

internal class FadeSpec : MotionSpec(
    createPrimaryAnimatorProvider(),
    createSecondaryAnimatorProvider()
) {

    @Composable
    override fun getDuration(appearing: Boolean): Int {
        return if (appearing) {
            MaterialMotions.durations.motionDurationShort2
        } else {
            MaterialMotions.durations.motionDurationShort1
        }
    }

    companion object {
        private const val DEFAULT_START_SCALE = 0.8f
        private const val DEFAULT_FADE_END_THRESHOLD_ENTER = 0.3f

        private fun createPrimaryAnimatorProvider(): VisibilityAnimationProvider {
            return FadeProvider().apply {
                incomingEndThreshold = DEFAULT_FADE_END_THRESHOLD_ENTER
            }
        }

        private fun createSecondaryAnimatorProvider(): VisibilityAnimationProvider {
            return ScaleProvider().apply {
                scaleOnDisappear = false
                incomingStartScale = DEFAULT_START_SCALE
            }
        }
    }
}
