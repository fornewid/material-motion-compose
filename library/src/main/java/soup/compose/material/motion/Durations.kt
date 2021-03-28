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

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
class Durations(
    val motionDurationShort1: Int = MotionConstants.motionDurationShort1,
    val motionDurationShort2: Int = MotionConstants.motionDurationShort2,
    val motionDurationMedium1: Int = MotionConstants.motionDurationMedium1,
    val motionDurationMedium2: Int = MotionConstants.motionDurationMedium2,
    val motionDurationLong1: Int = MotionConstants.motionDurationLong1,
    val motionDurationLong2: Int = MotionConstants.motionDurationLong2,
) {

    /**
     * Returns a copy of this Shapes, optionally overriding some of the values.
     */
    fun copy(
        motionDurationShort1: Int = this.motionDurationShort1,
        motionDurationShort2: Int = this.motionDurationShort2,
        motionDurationMedium1: Int = this.motionDurationMedium1,
        motionDurationMedium2: Int = this.motionDurationMedium2,
        motionDurationLong1: Int = this.motionDurationLong1,
        motionDurationLong2: Int = this.motionDurationLong2,
    ): Durations = Durations(
        motionDurationShort1 = motionDurationShort1,
        motionDurationShort2 = motionDurationShort2,
        motionDurationMedium1 = motionDurationMedium1,
        motionDurationMedium2 = motionDurationMedium2,
        motionDurationLong1 = motionDurationLong1,
        motionDurationLong2 = motionDurationLong2
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Durations) return false

        if (motionDurationShort1 != other.motionDurationShort1) return false
        if (motionDurationShort2 != other.motionDurationShort2) return false
        if (motionDurationMedium1 != other.motionDurationMedium1) return false
        if (motionDurationMedium2 != other.motionDurationMedium2) return false
        if (motionDurationLong1 != other.motionDurationLong1) return false
        if (motionDurationLong2 != other.motionDurationLong2) return false

        return true
    }

    override fun hashCode(): Int {
        var result = motionDurationShort1
        result = 31 * result + motionDurationShort2
        result = 31 * result + motionDurationMedium1
        result = 31 * result + motionDurationMedium2
        result = 31 * result + motionDurationLong1
        result = 31 * result + motionDurationLong2
        return result
    }

    override fun toString(): String {
        return "Durations(motionDurationShort1=$motionDurationShort1, motionDurationShort2=$motionDurationShort2, motionDurationMedium1=$motionDurationMedium1, motionDurationMedium2=$motionDurationMedium2, motionDurationLong1=$motionDurationLong1, motionDurationLong2=$motionDurationLong2)"
    }
}

internal val LocalDurations = staticCompositionLocalOf { Durations() }
