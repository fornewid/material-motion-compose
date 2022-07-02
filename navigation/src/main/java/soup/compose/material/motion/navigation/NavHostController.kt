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
package soup.compose.material.motion.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.rememberNavController

/**
 * Creates a NavHostController that handles the adding of the [ComposeNavigator], [DialogNavigator]
 * and [MaterialMotionComposeNavigator]. Additional [androidx.navigation.Navigator] instances should
 * be added in a [SideEffect] block.
 *
 * @see MaterialMotionNavHost
 */
@ExperimentalAnimationApi
@Composable
public fun rememberMaterialMotionNavController(
    vararg navigators: Navigator<out NavDestination>,
): NavHostController {
    val navigator = remember { MaterialMotionComposeNavigator() }
    return rememberNavController(navigator, *navigators)
}
