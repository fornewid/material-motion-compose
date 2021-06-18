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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState.PostExit
import androidx.compose.animation.EnterExitState.Visible
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InternalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@OptIn(ExperimentalTestApi::class, InternalAnimationApi::class)
class AnimatedVisibilityTest {

    @get:Rule
    val rule = createComposeRule()

    @OptIn(ExperimentalAnimationApi::class, InternalAnimationApi::class)
    @Test
    fun animateVisibilityMaterialFadeTest() {
        var visible by mutableStateOf(false)
        val easing = FastOutLinearInEasing
        val easingOut = FastOutSlowInEasing
        var alpha by mutableStateOf(0f)
        rule.setContent {
            AnimatedVisibility(
                visible,
                enter = materialFadeIn(animationSpec = tween(500, easing = easing)),
                exit = materialFadeOut(animationSpec = tween(300, easing = easingOut)),
            ) {
                Box(modifier = Modifier.size(size = 20.dp).background(Color.White))
                LaunchedEffect(visible) {
                    var exit = false
                    val enterExit = transition
                    while (true) {
                        withFrameNanos {
                            if (enterExit.targetState == Visible) {
                                alpha = enterExit.animations.firstOrNull {
                                    it.label == "alpha"
                                }?.value as Float
                                val fraction =
                                    (enterExit.playTimeNanos / 1_000_000) / 500f
                                if (enterExit.currentState != Visible) {
                                    assertEquals(easing.transform(fraction), alpha, 0.01f)
                                } else {
                                    // When currentState = targetState, the playTime will be reset
                                    // to 0. So compare alpha against expected visible value.
                                    assertEquals(1f, alpha)
                                    exit = true
                                }
                            } else if (enterExit.targetState == PostExit) {
                                alpha = enterExit.animations.firstOrNull {
                                    it.label == "alpha"
                                }?.value as Float
                                val fraction =
                                    (enterExit.playTimeNanos / 1_000_000) / 300f
                                if (enterExit.currentState != PostExit) {
                                    assertEquals(
                                        1f - easingOut.transform(fraction),
                                        alpha,
                                        0.01f
                                    )
                                } else {
                                    // When currentState = targetState, the playTime will be reset
                                    // to 0. So compare alpha against expected invisible value.
                                    assertEquals(0f, alpha)
                                    exit = true
                                }
                            } else {
                                exit = enterExit.currentState == enterExit.targetState
                            }
                        }
                        if (exit) break
                    }
                }
            }
        }
        rule.runOnIdle {
            visible = true
        }
        rule.runOnIdle {
            // At this point fade in has finished, expect alpha = 1
            assertEquals(1f, alpha)
            visible = false
        }
        rule.runOnIdle {
            // At this point fade out has finished, expect alpha = 0
            assertEquals(0f, alpha)
        }
    }
}
