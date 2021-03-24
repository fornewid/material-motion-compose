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
package soup.compose.material.motion.sample.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import soup.compose.material.motion.Axis
import soup.compose.material.motion.sample.ui.MainDestinations.DEMO_ROUTE
import soup.compose.material.motion.sample.ui.MainDestinations.FADE_THROUGH_ROUTE
import soup.compose.material.motion.sample.ui.MainDestinations.HOME_ROUTE
import soup.compose.material.motion.sample.ui.MainDestinations.SHARED_AXIS_X_ROUTE
import soup.compose.material.motion.sample.ui.MainDestinations.SHARED_AXIS_Y_ROUTE
import soup.compose.material.motion.sample.ui.MainDestinations.SHARED_AXIS_Z_ROUTE
import soup.compose.material.motion.sample.ui.demo.DemoScreen
import soup.compose.material.motion.sample.ui.fadethrough.FadeThroughScreen
import soup.compose.material.motion.sample.ui.sharedaxis.SharedAxisScreen

private object MainDestinations {
    const val HOME_ROUTE = "home"
    const val DEMO_ROUTE = "demo"
    const val SHARED_AXIS_X_ROUTE = "shared_axis_x"
    const val SHARED_AXIS_Y_ROUTE = "shared_axis_y"
    const val SHARED_AXIS_Z_ROUTE = "shared_axis_z"
    const val FADE_THROUGH_ROUTE = "fade_through"
}

@Composable
fun NavGraph(
    startDestination: String = HOME_ROUTE,
) {
    val navController = rememberNavController()
    val actions = remember(navController) {
        MainActions(navController)
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(HOME_ROUTE) {
            HomeScreen(
                onItemClick = { menu ->
                    when (menu) {
                        HomeMenu.Demo -> actions.goToDemo()
                        HomeMenu.SharedAxisX -> actions.goToSharedAxis(Axis.X)
                        HomeMenu.SharedAxisY -> actions.goToSharedAxis(Axis.Y)
                        HomeMenu.SharedAxisZ -> actions.goToSharedAxis(Axis.Z)
                        HomeMenu.FadeThrough -> actions.goToFadeThrough()
                    }
                }
            )
        }
        composable(DEMO_ROUTE) {
            DemoScreen()
        }
        composable(SHARED_AXIS_X_ROUTE) {
            SharedAxisScreen(Axis.X, actions.upPress)
        }
        composable(SHARED_AXIS_Y_ROUTE) {
            SharedAxisScreen(Axis.Y, actions.upPress)
        }
        composable(SHARED_AXIS_Z_ROUTE) {
            SharedAxisScreen(Axis.Z, actions.upPress)
        }
        composable(FADE_THROUGH_ROUTE) {
            FadeThroughScreen(actions.upPress)
        }
    }
}

private class MainActions(navController: NavHostController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    val goToDemo: () -> Unit = {
        navController.navigate(DEMO_ROUTE)
    }
    val goToSharedAxis: (Axis) -> Unit = { axis ->
        val route = when (axis) {
            Axis.X -> SHARED_AXIS_X_ROUTE
            Axis.Y -> SHARED_AXIS_Y_ROUTE
            Axis.Z -> SHARED_AXIS_Z_ROUTE
        }
        navController.navigate(route)
    }
    val goToFadeThrough: () -> Unit = {
        navController.navigate(FADE_THROUGH_ROUTE)
    }
}
