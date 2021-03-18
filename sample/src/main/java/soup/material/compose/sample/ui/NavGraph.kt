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
package soup.material.compose.sample.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

private object MainDestinations {
    const val HOME_ROUTE = "home"
    const val FADE_THROUGH_ROUTE = "fade_through"
    const val SHARED_AXIS_ROUTE = "shared_axis"
}

@Composable
fun NavGraph(
    startDestination: String = MainDestinations.HOME_ROUTE,
) {
    val navController = rememberNavController()
    val actions = remember(navController) {
        MainActions(navController)
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            HomeScreen(
                onItemClick = { menu ->
                    when (menu) {
                        HomeMenu.SharedAxis -> actions.goToSharedAxis()
                        HomeMenu.FadeThrough -> actions.goToFadeThrough()
                    }
                }
            )
        }
        composable(MainDestinations.SHARED_AXIS_ROUTE) {
            SharedAxisScreen()
        }
        composable(MainDestinations.FADE_THROUGH_ROUTE) {
            FadeThroughScreen()
        }
    }
}

private class MainActions(navController: NavHostController) {
    val goToSharedAxis: () -> Unit = {
        navController.navigate(MainDestinations.SHARED_AXIS_ROUTE)
    }
    val goToFadeThrough: () -> Unit = {
        navController.navigate(MainDestinations.FADE_THROUGH_ROUTE)
    }
}
