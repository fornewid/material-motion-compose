/*
 * Copyright 2022 SOUP
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

//TODO: (sungyong.an)
//import android.graphics.Path
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.takeOrElse
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.hypot

/**
 * Draws circular reveal animation.
 *
 * @param visible whether the circular reveal animation should be visible or not.
 * @param center The x,y coordinates at which the circular reveal animation starts or ends.
 * If undefined or [Offset.Unspecified], the x,y coordinates will be the center of [Size].
 * @param transitionSpec The transition spec to use when clipping the screen.
 * The boolean parameter defined for the transition is [visible].
 */
@ExperimentalAnimationApi
public fun Modifier.circularReveal(
    visible: Boolean,
    center: (fullSize: Size) -> Offset = { Offset.Unspecified },
    transitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "circularReveal"
        properties["visible"] = visible
        properties["center"] = center
    }
) {
    val transition = updateTransition(targetState = visible, label = "circularReveal")
    val progress: Float by transition.animateFloat(
        transitionSpec = transitionSpec,
        label = "progress",
        targetValueByState = { circularRevealVisible -> if (circularRevealVisible) 1f else 0f }
    )
    graphicsLayer {
        clip = progress < 1f
        shape = CircularRevealShape(
            progress = progress,
            center = center,
        )
    }
}

private class CircularRevealShape(
    private val progress: Float,
    private val center: (fullSize: Size) -> Offset = { Offset.Unspecified },
) : Shape {

    init {
        if (progress !in 0.0..1.0) {
            throw IllegalStateException()
        }
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
//TODO: (sungyong.an)
//        val center: Offset = center(size).takeOrElse { size.center }
//        val radius: Float = size.getLongestRadiusFrom(center) * progress
//        val path: Path = Path().also {
//            it.addCircle(center.x, center.y, radius, Path.Direction.CW)
//        }
//        return Outline.Generic(path = path.asComposePath())
        return Outline.Generic(path = Path())
    }

    private fun Size.getLongestRadiusFrom(center: Offset): Float {
        val topLeft: Float = hypot(center.x, center.y)
        val topRight: Float = hypot(width - center.x, center.y)
        val bottomLeft: Float = hypot(center.x, height - center.y)
        val bottomRight: Float = hypot(width - center.x, height - center.y)
        return maxOf(topLeft, topRight, bottomLeft, bottomRight)
    }
}
