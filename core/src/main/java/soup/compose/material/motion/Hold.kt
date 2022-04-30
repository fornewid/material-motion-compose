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

/**
 * [holdIn] allows to switch a layout with no enter transition.
 *
 * @param durationMillis the duration of the enter transition.
 */
fun holdIn(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): EnterMotionSpec = EnterMotionSpec(
    transition = { _, _ ->
        soup.compose.material.motion.animation.holdIn(durationMillis)
    }
)

/**
 * [holdOut] allows to switch a layout with no exit transition.
 *
 * @param durationMillis the duration of the exit transition.
 */
fun holdOut(
    durationMillis: Int = MotionConstants.motionDurationLong1,
): ExitMotionSpec = ExitMotionSpec(
    transition = { _, _ ->
        soup.compose.material.motion.animation.holdOut(durationMillis)
    }
)
