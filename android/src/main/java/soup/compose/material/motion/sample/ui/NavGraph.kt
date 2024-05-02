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

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import soup.compose.material.motion.animation.materialSharedAxisZIn
import soup.compose.material.motion.animation.materialSharedAxisZOut
import soup.compose.material.motion.sample.ui.circularreveal.CircularRevealScreen
import soup.compose.material.motion.sample.ui.demo.DemoScreen
import soup.compose.material.motion.sample.ui.material.elevationscale.MaterialElevationScaleScreen
import soup.compose.material.motion.sample.ui.material.fade.MaterialFadeScreen
import soup.compose.material.motion.sample.ui.material.fadethrough.MaterialFadeThroughScreen
import soup.compose.material.motion.sample.ui.material.hold.HoldScreen
import soup.compose.material.motion.sample.ui.material.sharedaxis.MaterialSharedAxisScreen
import soup.compose.material.motion.sample.ui.navigation.AnimatedNavHostScreen

sealed interface Destination {

    @Serializable
    data object Home : Destination

    @Serializable
    data object Demo : Destination

    @Serializable
    data object MaterialSharedAxis : Destination

    @Serializable
    data object MaterialFadeThrough : Destination

    @Serializable
    data object MaterialFade : Destination

    @Serializable
    data object MaterialElevationScale : Destination

    @Serializable
    data object Hold : Destination

    @Serializable
    data object CircularReveal : Destination

    @Serializable
    data object AnimatedNavHost : Destination
}

@Composable
fun NavGraph(
    startDestination: Destination = Destination.Home,
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
        composable<Destination.Home> {
            HomeScreen(
                onItemClick = {
                    navController.navigate(route = it)
                }
            )
        }
        composable<Destination.Demo> {
            DemoScreen(upPress = upPress)
        }

        /* Core */
        composable<Destination.MaterialSharedAxis> {
            MaterialSharedAxisScreen(upPress = upPress)
        }
        composable<Destination.MaterialFadeThrough> {
            MaterialFadeThroughScreen(upPress = upPress)
        }
        composable<Destination.MaterialFade> {
            MaterialFadeScreen(upPress = upPress)
        }
        composable<Destination.MaterialElevationScale> {
            MaterialElevationScaleScreen(upPress = upPress)
        }
        composable<Destination.Hold> {
            HoldScreen(upPress = upPress)
        }
        composable<Destination.CircularReveal> {
            CircularRevealScreen(upPress = upPress)
        }

        /* Navigation */
        composable<Destination.AnimatedNavHost> {
            AnimatedNavHostScreen(upPress = upPress)
        }
    }
}
