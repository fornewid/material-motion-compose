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

import soup.compose.material.motion.MotionSpec
import soup.compose.material.motion.VisibilityAnimationProvider
import soup.compose.material.motion.provider.FadeThroughProvider
import soup.compose.material.motion.provider.ScaleProvider

internal data class FadeThroughSpec(
    private val durationMillis: Int,
) : MotionSpec(
    createPrimaryAnimatorProvider().withDuration(durationMillis),
    createSecondaryAnimatorProvider().withDuration(durationMillis)
) {

    companion object {
        private const val DEFAULT_START_SCALE = 0.92f

        private fun VisibilityAnimationProvider.withDuration(durationMillis: Int) =
            apply { setDuration(durationMillis) }

        private fun createPrimaryAnimatorProvider(): VisibilityAnimationProvider {
            return FadeThroughProvider()
        }

        private fun createSecondaryAnimatorProvider(): VisibilityAnimationProvider {
            val scaleProvider = ScaleProvider()
            scaleProvider.scaleOnDisappear = false
            scaleProvider.incomingStartScale = DEFAULT_START_SCALE
            return scaleProvider
        }
    }
}
