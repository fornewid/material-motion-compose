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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import soup.compose.material.motion.navigation.MaterialMotionNavHost
import soup.compose.material.motion.navigation.composable
import soup.compose.material.motion.navigation.rememberMaterialMotionNavController
import soup.compose.material.motion.sample.ui.circularreveal.CircularRevealScreen
import soup.compose.material.motion.sample.ui.demo.DemoScreen
import soup.compose.material.motion.sample.ui.material.elevationscale.MaterialElevationScaleScreen
import soup.compose.material.motion.sample.ui.material.fade.MaterialFadeScreen
import soup.compose.material.motion.sample.ui.material.fadethrough.MaterialFadeThroughScreen
import soup.compose.material.motion.sample.ui.material.hold.HoldScreen
import soup.compose.material.motion.sample.ui.material.sharedaxis.MaterialSharedAxisScreen
import soup.compose.material.motion.sample.ui.navigation.NavigationScreen

enum class Destination(val route: String) {
    Home("home"),
    Demo("demo"),
    MaterialSharedAxis("material_shared_axis"),
    MaterialFadeThrough("material_fade_through"),
    MaterialFade("material_fade"),
    MaterialElevationScale("material_elevation_scale"),
    Hold("hold"),
    CircularReveal("circular_reveal"),
    Navigation("navigation"),
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    startDestination: String = Destination.Home.route,
) {
    val navController = rememberMaterialMotionNavController()
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    val navigate: (Destination) -> Unit = {
        navController.navigate(route = it.route)
    }
    MaterialMotionNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Destination.Home.route) {
            HomeScreen(
                onItemClick = {
                    navController.navigate(route = it.route)
                }
            )
        }
        composable(Destination.Demo.route) {
            DemoScreen(upPress = upPress)
        }

        /* Core */
        composable(Destination.MaterialSharedAxis.route) {
            MaterialSharedAxisScreen(upPress = upPress)
        }
        composable(Destination.MaterialFadeThrough.route) {
            MaterialFadeThroughScreen(upPress = upPress)
        }
        composable(Destination.MaterialFade.route) {
            MaterialFadeScreen(upPress = upPress)
        }
        composable(Destination.MaterialElevationScale.route) {
            MaterialElevationScaleScreen(upPress = upPress)
        }
        composable(Destination.Hold.route) {
            HoldScreen(upPress = upPress)
        }
        composable(Destination.CircularReveal.route) {
            CircularRevealScreen(upPress = upPress)
        }

        /* Navigation */
        composable(Destination.Navigation.route) {
            NavigationScreen(upPress = upPress)
        }
    }
}
