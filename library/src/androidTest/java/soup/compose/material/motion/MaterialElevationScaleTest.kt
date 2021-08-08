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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import soup.compose.material.motion.MotionConstants.motionDurationLong1

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalAnimationApi::class)
class MaterialElevationScaleTest : MaterialMotionTest() {

    override val defaultDurationMillis: Int
        get() = motionDurationLong1

    override fun motionSpec(durationMillis: Int?): MotionSpec {
        return if (durationMillis != null) {
            materialElevationScaleIn(durationMillis = durationMillis) with
                materialElevationScaleOut(durationMillis = durationMillis)
        } else {
            materialElevationScaleIn() with materialElevationScaleOut()
        }
    }
}
