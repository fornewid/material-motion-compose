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

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule

fun ComposeContentTestRule.setContentWithMaterialMotion(
    durationMillis: Int? = null,
    composable: @Composable () -> Unit,
) {
    setContent {
        ProvideMaterialMotions(
            durations = durationMillis?.run(::durations) ?: MaterialMotions.durations,
            content = composable
        )
    }
}

private fun durations(durationMillis: Int): Durations {
    return Durations(
        motionDurationShort1 = durationMillis,
        motionDurationShort2 = durationMillis,
        motionDurationMedium1 = durationMillis,
        motionDurationMedium2 = durationMillis,
        motionDurationLong1 = durationMillis,
        motionDurationLong2 = durationMillis,
    )
}
