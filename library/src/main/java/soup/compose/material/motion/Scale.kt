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

import soup.compose.material.motion.internal.GraphicsLayerSpec
import soup.compose.material.motion.provider.GraphicsLayerData

/**
 * [scale] allows to switch a layout with a scale animation.
 */
fun scale(
    fromX: Float,
    toX: Float,
    fromY: Float,
    toY: Float,
): MotionSpec = GraphicsLayerSpec(
    from = GraphicsLayerData(scaleX = fromX, scaleY = fromY),
    to = GraphicsLayerData(scaleX = toX, scaleY = toY)
)

/**
 * [scale] allows to switch a layout with a scale animation.
 */
fun scale(
    from: Float,
    to: Float,
): MotionSpec = scale(from, to, from, to)
