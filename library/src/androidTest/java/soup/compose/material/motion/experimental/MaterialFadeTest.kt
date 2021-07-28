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
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import soup.compose.material.motion.MaterialFade

@RunWith(AndroidJUnit4::class)
@LargeTest
@OptIn(ExperimentalAnimationApi::class)
class MaterialFadeTest {

    @get:Rule
    val rule = createComposeRule()

    private val contentTag = "Content"

    @Before
    fun before() {
        isDebugInspectorInfoEnabled = true
    }

    @After
    fun after() {
        isDebugInspectorInfoEnabled = false
    }

    @Test
    fun showAndHideContents() {
        var visible by mutableStateOf(false)
        rule.setContent {
            MaterialFade(
                visible = visible,
                enterDurationMillis = 500,
                exitDurationMillis = 300
            ) {
                Box(
                    modifier = Modifier
                        .size(size = 20.dp)
                        .background(Color.White)
                        .testTag(contentTag)
                )
            }
        }
        rule.onNodeWithTag(contentTag).assertDoesNotExist()
        rule.runOnIdle {
            visible = true
        }
        rule.onNodeWithTag(contentTag).assertExists()
        rule.runOnIdle {
            visible = false
        }
        rule.onNodeWithTag(contentTag).assertDoesNotExist()
    }
}
