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

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import soup.compose.material.motion.MotionSpec
import soup.compose.material.motion.VisibilityAnimationProvider
import soup.compose.material.motion.provider.CrossfadeProvider
import soup.compose.material.motion.provider.EmptyProvider

internal data class CrossfadeSpec(
    private val animationSpec: FiniteAnimationSpec<Float> = tween(),
) : MotionSpec(
    createPrimaryAnimatorProvider(animationSpec),
    createSecondaryAnimatorProvider()
) {

    @Composable
    override fun getDuration(appearing: Boolean): Int {
        return 0
    }

    companion object {

        private fun createPrimaryAnimatorProvider(animationSpec: FiniteAnimationSpec<Float>): VisibilityAnimationProvider {
            return CrossfadeProvider(animationSpec)
        }

        private fun createSecondaryAnimatorProvider(): VisibilityAnimationProvider {
            return EmptyProvider()
        }
    }
}
