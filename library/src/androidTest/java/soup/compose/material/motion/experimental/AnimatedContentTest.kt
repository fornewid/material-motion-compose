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
package soup.compose.material.motion.experimental

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.InternalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.test.filters.LargeTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import soup.compose.material.motion.Axis

@LargeTest
@RunWith(Parameterized::class)
@OptIn(ExperimentalTestApi::class, ExperimentalAnimationApi::class, InternalAnimationApi::class)
class AnimatedContentTest(
    private val forwardEnterTransition: EnterTransition,
    private val forwardExitTransition: ExitTransition,
    private val backwardEnterTransition: EnterTransition,
    private val backwardExitTransition: ExitTransition,
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = listOf(
            parameters(materialFadeThroughIn(), materialFadeThroughOut()),
            parametersOfSharedAxis(Axis.X),
            parametersOfSharedAxis(Axis.Y),
            parametersOfSharedAxis(Axis.Z),
            parameters(materialElevationScaleIn(), materialElevationScaleOut()),
            parameters(holdIn(), holdOut()),
        )

        private fun parameters(enter: EnterTransition, exit: ExitTransition) =
            arrayOf(enter, exit, enter, exit)

        private fun parametersOfSharedAxis(axis: Axis) = arrayOf(
            materialSharedAxisIn(axis, forward = true),
            materialSharedAxisOut(axis, forward = true),
            materialSharedAxisIn(axis, forward = false),
            materialSharedAxisOut(axis, forward = false)
        )
    }

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun AnimatedContentSizeTransformTest() {
        val size1 = 40
        val size2 = 200
        val testModifier by mutableStateOf(TestModifier())
        val transitionState = MutableTransitionState(true)
        var playTimeMillis by mutableStateOf(0)
        rule.mainClock.autoAdvance = false
        rule.setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f)) {
                val transition = updateTransition(transitionState)
                playTimeMillis = (transition.playTimeNanos / 1_000_000L).toInt()
                transition.AnimatedContent(
                    testModifier,
                    transitionSpec = {
                        if (true isTransitioningTo false) {
                            forwardEnterTransition with forwardExitTransition using SizeTransform { initialSize, targetSize ->
                                keyframes {
                                    durationMillis = 320
                                    IntSize(targetSize.width, initialSize.height) at 160 with
                                        LinearEasing
                                    targetSize at 320 with LinearEasing
                                }
                            }
                        } else {
                            backwardEnterTransition with backwardExitTransition using SizeTransform { _, _ ->
                                tween(durationMillis = 80, easing = LinearEasing)
                            }
                        }
                    }
                ) {
                    if (it) {
                        Box(modifier = Modifier.size(size = size1.dp))
                    } else {
                        Box(modifier = Modifier.size(size = size2.dp))
                    }
                }
            }
        }
        rule.runOnIdle {
            assertEquals(40, testModifier.height)
            assertEquals(40, testModifier.width)
            assertTrue(transitionState.targetState)
            transitionState.targetState = false
        }

        // Transition from item1 to item2 in 320ms, animating to full width in the first 160ms
        // then full height in the next 160ms
        while (transitionState.currentState != transitionState.targetState) {
            rule.runOnIdle {
                if (playTimeMillis <= 160) {
                    assertEquals(playTimeMillis + 40, testModifier.width)
                    assertEquals(40, testModifier.height)
                } else {
                    assertEquals(200, testModifier.width)
                    assertEquals(playTimeMillis - 120, testModifier.height)
                }
            }
            rule.mainClock.advanceTimeByFrame()
        }

        rule.runOnIdle {
            assertEquals(200, testModifier.width)
            assertEquals(200, testModifier.height)
            transitionState.targetState = true
        }

        // Transition from item2 to item1 in 80ms
        while (transitionState.currentState != transitionState.targetState) {
            rule.runOnIdle {
                if (playTimeMillis <= 80) {
                    assertEquals(200 - playTimeMillis * 2, testModifier.width)
                    assertEquals(200 - playTimeMillis * 2, testModifier.height)
                }
            }
            rule.mainClock.advanceTimeByFrame()
        }
    }
}

internal class TestModifier : LayoutModifier {
    var width: Int = 0
    var height: Int = 0
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints,
    ): MeasureResult {
        val placeable = measurable.measure(constraints)
        width = placeable.width
        height = placeable.height
        return layout(width, height) {
            placeable.place(0, 0)
        }
    }
}
