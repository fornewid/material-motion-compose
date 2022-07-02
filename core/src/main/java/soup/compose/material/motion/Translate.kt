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
 * [translateXIn] allows to switch a layout with a translate animation as x axis.
 */
@Deprecated(
    message = "Use translateXIn() of animation package instead.",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "translateXIn()",
        "soup.compose.material.motion.animation.translateXIn",
    )
)
public fun translateXIn(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
    initialOffsetX: (fullHeight: Int) -> Int = { -it / 2 },
): EnterMotionSpec = EnterMotionSpec(
    transition = { _, _ ->
        soup.compose.material.motion.animation.translateXIn(
            durationMillis = durationMillis,
            initialOffsetX = initialOffsetX
        )
    }
)

/**
 * [translateXOut] allows to switch a layout with a translate animation as x axis.
 */
@Deprecated(
    message = "Use translateXOut() of animation package instead.",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "translateXOut()",
        "soup.compose.material.motion.animation.translateXOut",
    )
)
public fun translateXOut(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
    targetOffsetX: (fullHeight: Int) -> Int = { -it / 2 },
): ExitMotionSpec = ExitMotionSpec(
    transition = { _, _ ->
        soup.compose.material.motion.animation.translateXOut(
            durationMillis = durationMillis,
            targetOffsetX = targetOffsetX
        )
    }
)

/**
 * [translateYIn] allows to switch a layout with a translate animation as y axis.
 */
@Deprecated(
    message = "Use translateYIn() of animation package instead.",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "translateYIn()",
        "soup.compose.material.motion.animation.translateYIn",
    )
)
public fun translateYIn(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
    initialOffsetY: (fullHeight: Int) -> Int = { -it / 2 },
): EnterMotionSpec = EnterMotionSpec(
    transition = { _, _ ->
        soup.compose.material.motion.animation.translateYIn(
            durationMillis = durationMillis,
            initialOffsetY = initialOffsetY
        )
    }
)

/**
 * [translateYOut] allows to switch a layout with a translate animation as y axis.
 */
@Deprecated(
    message = "Use translateYOut() of animation package instead.",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith(
        "translateYOut()",
        "soup.compose.material.motion.animation.translateYOut",
    )
)
public fun translateYOut(
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
    targetOffsetY: (fullHeight: Int) -> Int = { -it / 2 },
): ExitMotionSpec = ExitMotionSpec(
    transition = { _, _ ->
        soup.compose.material.motion.animation.translateYOut(
            durationMillis = durationMillis,
            targetOffsetY = targetOffsetY
        )
    }
)
