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
package soup.compose.material.motion.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import soup.compose.material.motion.animation.materialSharedAxisZIn
import soup.compose.material.motion.animation.materialSharedAxisZOut
import soup.compose.material.motion.shared.circularreveal.CircularRevealScreen
import soup.compose.material.motion.shared.demo.DemoScreen
import soup.compose.material.motion.shared.material.elevationscale.MaterialElevationScaleScreen
import soup.compose.material.motion.shared.material.fade.MaterialFadeScreen
import soup.compose.material.motion.shared.material.fadethrough.MaterialFadeThroughScreen
import soup.compose.material.motion.shared.material.hold.HoldScreen
import soup.compose.material.motion.shared.material.sharedaxis.MaterialSharedAxisScreen
import soup.compose.material.motion.shared.navigation.AnimatedNavHostScreen

enum class Destination(val route: String) {
    Home("Home"),
    Demo("Demo"),
    MaterialSharedAxis("MaterialSharedAxis"),
    MaterialFadeThrough("MaterialFadeThrough"),
    MaterialFade("MaterialFade"),
    MaterialElevationScale("MaterialElevationScale"),
    Hold("Hold"),
    CircularReveal("CircularReveal"),
    AnimatedNavHost("AnimatedNavHost"),
}

@Composable
fun NavGraph(
    startDestination: String = Destination.Home.route,
) {
    val navController = rememberNavController()
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize(),
        enterTransition = { materialSharedAxisZIn(forward = true) },
        exitTransition = { materialSharedAxisZOut(forward = true) },
        popEnterTransition = { materialSharedAxisZIn(forward = false) },
        popExitTransition = { materialSharedAxisZOut(forward = false) },
    ) {
        composable(Destination.Home.route) {
            HomeScreen(
                onItemClick = {
                    navController.navigate(route = it.route)
                },
            )
        }
        composable(Destination.Demo.route) {
            DemoScreen(upPress = upPress)
        }

        // Core
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

        // Navigation
        composable(Destination.AnimatedNavHost.route) {
            AnimatedNavHostScreen(upPress = upPress)
        }
    }
}
