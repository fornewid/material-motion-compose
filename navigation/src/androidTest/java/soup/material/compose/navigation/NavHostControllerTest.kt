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
package soup.material.compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.NoOpNavigator
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.get
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import soup.compose.material.motion.navigation.MaterialMotionComposeNavigator
import soup.compose.material.motion.navigation.MaterialMotionNavHost
import soup.compose.material.motion.navigation.composable
import soup.compose.material.motion.navigation.rememberMaterialMotionNavController

@OptIn(ExperimentalAnimationApi::class)
@RunWith(AndroidJUnit4::class)
class NavHostControllerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRememberMaterialMotionNavController() {
        lateinit var navController: NavHostController

        composeTestRule.setContent {
            navController = rememberMaterialMotionNavController()
            // get state to trigger recompose on navigate
            navController.currentBackStackEntryAsState().value
            MaterialMotionNavHost(navController, startDestination = first) {
                composable(first) { BasicText(first) }
                composable(second) { BasicText(second) }
            }
        }

        val navigator = composeTestRule.runOnIdle {
            navController.navigatorProvider[MaterialMotionComposeNavigator::class]
        }

        // trigger recompose
        composeTestRule.runOnIdle {
            navController.navigate(second)
        }

        composeTestRule.runOnIdle {
            assertThat(navController.navigatorProvider[MaterialMotionComposeNavigator::class])
                .isEqualTo(navigator)
        }
    }

    @Test
    fun testRememberMaterialMotionNavControllerAddsCustomNavigator() {
        lateinit var navController: NavHostController

        composeTestRule.setContent {
            val customNavigator = remember { NoOpNavigator() }
            navController = rememberMaterialMotionNavController(customNavigator)
            // get state to trigger recompose on navigate
            navController.currentBackStackEntryAsState().value
            MaterialMotionNavHost(navController, startDestination = first) {
                composable(first) { BasicText(first) }
                composable(second) { BasicText(second) }
            }
        }

        val navigator = composeTestRule.runOnIdle {
            navController.navigatorProvider[NoOpNavigator::class]
        }

        // trigger recompose
        composeTestRule.runOnIdle {
            navController.navigate(second)
        }

        composeTestRule.runOnIdle {
            assertThat(navController.navigatorProvider[NoOpNavigator::class])
                .isEqualTo(navigator)
        }
    }
}

private const val first = "first"
private const val second = "second"
