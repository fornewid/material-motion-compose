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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@LargeTest
@RunWith(Parameterized::class)
@OptIn(ExperimentalAnimationApi::class)
class MaterialMotionTest(
    private val forwardEnterMotionSpec: EnterMotionSpec,
    private val forwardExitMotionSpec: ExitMotionSpec,
    private val backwardEnterMotionSpec: EnterMotionSpec,
    private val backwardExitMotionSpec: ExitMotionSpec,
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = listOf(
            // forwardEnterMotionSpec, forwardExitMotionSpec
            // backwardEnterMotionSpec, backwardExitMotionSpec

            arrayOf(
                materialFadeThroughIn(), materialFadeThroughOut(),
                materialFadeThroughIn(), materialFadeThroughOut()
            ),
            arrayOf(
                materialSharedAxisXIn(forward = true, slideDistance = 30),
                materialSharedAxisXOut(forward = true, slideDistance = 30),
                materialSharedAxisXIn(forward = false, slideDistance = 30),
                materialSharedAxisXOut(forward = false, slideDistance = 30),
            ),
            arrayOf(
                materialSharedAxisYIn(forward = true, slideDistance = 30),
                materialSharedAxisYOut(forward = true, slideDistance = 30),
                materialSharedAxisYIn(forward = false, slideDistance = 30),
                materialSharedAxisYOut(forward = false, slideDistance = 30),
            ),
            arrayOf(
                materialSharedAxisZIn(forward = true),
                materialSharedAxisZOut(forward = true),
                materialSharedAxisZIn(forward = false),
                materialSharedAxisZOut(forward = false),
            ),
            arrayOf(
                materialElevationScaleIn(), materialElevationScaleOut(),
                materialElevationScaleIn(), materialElevationScaleOut()
            ),
            arrayOf(
                holdIn(), holdOut(),
                holdIn(), holdOut()
            ),
        )
    }

    @get:Rule
    val rule = createComposeRule()

    private val forwardContentTag = "ForwardContent"
    private val backwardContentTag = "BackwardContent"

    @Before
    fun before() {
        isDebugInspectorInfoEnabled = true
    }

    @After
    fun after() {
        isDebugInspectorInfoEnabled = false
    }

    @Test
    fun changeContentsTest() {
        var forward by mutableStateOf(false)
        rule.setContent {
            MaterialMotion(
                targetState = forward,
                enterMotionSpec = if (forward) forwardEnterMotionSpec else backwardEnterMotionSpec,
                exitMotionSpec = if (forward) forwardExitMotionSpec else backwardExitMotionSpec
            ) { forward ->
                if (forward) {
                    Box(modifier = Modifier
                        .size(size = 20.dp)
                        .background(Color.White)
                        .testTag(forwardContentTag))
                } else {
                    Box(modifier = Modifier
                        .size(size = 40.dp)
                        .background(Color.Red)
                        .testTag(backwardContentTag))
                }
            }
        }
        rule.onNodeWithTag(forwardContentTag).assertDoesNotExist()
        rule.onNodeWithTag(backwardContentTag).assertExists()
        rule.runOnIdle {
            forward = true
        }
        rule.onNodeWithTag(forwardContentTag).assertExists()
        rule.onNodeWithTag(backwardContentTag).assertDoesNotExist()
        rule.runOnIdle {
            forward = false
        }
        rule.onNodeWithTag(forwardContentTag).assertDoesNotExist()
        rule.onNodeWithTag(backwardContentTag).assertExists()
    }
}
