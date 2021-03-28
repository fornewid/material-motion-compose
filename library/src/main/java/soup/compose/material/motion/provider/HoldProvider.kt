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
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import soup.compose.material.motion.MotionConstants.motionDurationLong1
import soup.compose.material.motion.VisibilityAnimationProvider

@SuppressLint("ModifierFactoryExtensionFunction")
class HoldProvider : VisibilityAnimationProvider {

    private var appearDurationMillis: Int = motionDurationLong1
    private var disappearDurationMillis: Int = motionDurationLong1

    override fun setAppearDuration(durationMillis: Int) {
        this.appearDurationMillis = durationMillis
    }

    override fun createAppearAnimationSpec(): FiniteAnimationSpec<Float> {
        return createHoldAnimationSpec(appearing = true)
    }

    override fun appear(modifier: Modifier, fraction: Float): Modifier {
        return modifier
    }

    override fun setDisappearDuration(durationMillis: Int) {
        this.disappearDurationMillis = durationMillis
    }

    override fun createDisappearAnimationSpec(): FiniteAnimationSpec<Float> {
        return createHoldAnimationSpec(appearing = false)
    }

    override fun disappear(modifier: Modifier, fraction: Float): Modifier {
        return modifier
    }

    private fun createHoldAnimationSpec(appearing: Boolean): FiniteAnimationSpec<Float> {
        return tween(
            durationMillis = if (appearing) appearDurationMillis else disappearDurationMillis
        )
    }
}
