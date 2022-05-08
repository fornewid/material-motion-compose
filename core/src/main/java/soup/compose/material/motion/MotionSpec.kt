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

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Density

@Immutable
public data class MotionSpec(
    val enter: EnterMotionSpec,
    val exit: ExitMotionSpec,
)

public infix fun EnterMotionSpec.with(exit: ExitMotionSpec): MotionSpec = MotionSpec(this, exit)

@Immutable
public data class EnterMotionSpec(
    val transition: (forward: Boolean, density: Density) -> EnterTransition,
) {
    public companion object {
        public val None: EnterMotionSpec =
            EnterMotionSpec(transition = { _, _ -> EnterTransition.None })
    }
}

@Immutable
public data class ExitMotionSpec(
    val transition: (forward: Boolean, density: Density) -> ExitTransition,
) {
    public companion object {
        public val None: ExitMotionSpec =
            ExitMotionSpec(transition = { _, _ -> ExitTransition.None })
    }
}
