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

package soup.compose.material.motion.internal

import androidx.annotation.FloatRange

internal object MotionUtils {

    fun lerp(startValue: Float, endValue: Float, fraction: Float): Float {
        return startValue + fraction * (endValue - startValue)
    }

    // TODO(b/169309512): Remove in favor of AnimationUtils implementation
    fun lerp(
        startValue: Float,
        endValue: Float,
        @FloatRange(from = 0.0, to = 1.0) startFraction: Float,
        @FloatRange(from = 0.0, to = 1.0) endFraction: Float,
        @FloatRange(from = 0.0, to = 1.0) fraction: Float,
    ): Float {
        return lerp(
            startValue = startValue,
            endValue = endValue,
            startFraction = startFraction,
            endFraction = endFraction,
            fraction = fraction,
            allowOvershoot = false
        )
    }

    fun lerp(
        startValue: Float,
        endValue: Float,
        @FloatRange(from = 0.0, to = 1.0) startFraction: Float,
        @FloatRange(from = 0.0, to = 1.0) endFraction: Float,
        @FloatRange(from = 0.0) fraction: Float,
        allowOvershoot: Boolean,
    ): Float {
        if (allowOvershoot && (fraction < 0 || fraction > 1)) {
            return lerp(startValue, endValue, fraction)
        }
        if (fraction < startFraction) {
            return startValue
        }
        if (fraction > endFraction) {
            return endValue
        }
        return lerp(
            startValue = startValue,
            endValue = endValue,
            fraction = (fraction - startFraction) / (endFraction - startFraction)
        )
    }

    fun lerp(
        startValue: Int,
        endValue: Int,
        @FloatRange(from = 0.0, to = 1.0) startFraction: Float,
        @FloatRange(from = 0.0, to = 1.0) endFraction: Float,
        @FloatRange(from = 0.0, to = 1.0) fraction: Float,
    ): Int {
        if (fraction < startFraction) {
            return startValue
        }
        if (fraction > endFraction) {
            return endValue
        }
        return lerp(
            startValue = startValue.toFloat(),
            endValue = endValue.toFloat(),
            fraction = (fraction - startFraction) / (endFraction - startFraction)
        ).toInt()
    }
}
