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
import soup.compose.material.motion.sample.ui.Destination.Crossfade
import soup.compose.material.motion.sample.ui.Destination.Demo
import soup.compose.material.motion.sample.ui.Destination.ElevationScale
import soup.compose.material.motion.sample.ui.Destination.Fade
import soup.compose.material.motion.sample.ui.Destination.FadeThrough
import soup.compose.material.motion.sample.ui.Destination.Hold
import soup.compose.material.motion.sample.ui.Destination.Home
import soup.compose.material.motion.sample.ui.Destination.SharedAxisX
import soup.compose.material.motion.sample.ui.Destination.SharedAxisY
import soup.compose.material.motion.sample.ui.Destination.SharedAxisZ
import soup.compose.material.motion.sample.ui.crossfade.CrossfadeScreen
import soup.compose.material.motion.sample.ui.demo.DemoScreen
import soup.compose.material.motion.sample.ui.elevationscale.ElevationScaleScreen
import soup.compose.material.motion.sample.ui.fade.FadeScreen
import soup.compose.material.motion.sample.ui.fadethrough.FadeThroughScreen
import soup.compose.material.motion.sample.ui.hold.HoldScreen
import soup.compose.material.motion.sample.ui.sharedaxis.SharedAxisScreen

enum class Destination(val route: String, val root: Boolean = false) {
    Home("home", root = true),
    Demo("demo"),
    SharedAxisX("shared_axis_x"),
    SharedAxisY("shared_axis_y"),
    SharedAxisZ("shared_axis_z"),
    FadeThrough("fade_through"),
    Fade("fade"),
    Crossfade("crossfade"),
    Hold("hold"),
    ElevationScale("elevation_scale"),
}

@Composable
fun NavGraph(
    startDestination: String = Home.route,
) {
    val navController = rememberNavController()
    val actions = remember(navController) {
        MainActions(navController)
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Home.route) {
            HomeScreen(
                onItemClick = { menu ->
                    when (menu) {
                        Home -> {}
                        Demo -> actions.goToDemo()
                        SharedAxisX -> actions.goToSharedAxis(Axis.X)
                        SharedAxisY -> actions.goToSharedAxis(Axis.Y)
                        SharedAxisZ -> actions.goToSharedAxis(Axis.Z)
                        FadeThrough -> actions.goToFadeThrough()
                        Fade -> actions.goToFade()
                        Crossfade -> actions.goToCrossfade()
                        Hold -> actions.goToHold()
                        ElevationScale -> actions.goToElevationScale()
                    }
                }
            )
        }
        composable(Demo.route) {
            DemoScreen()
        }
        composable(SharedAxisX.route) {
            SharedAxisScreen(Axis.X, actions.upPress)
        }
        composable(SharedAxisY.route) {
            SharedAxisScreen(Axis.Y, actions.upPress)
        }
        composable(SharedAxisZ.route) {
            SharedAxisScreen(Axis.Z, actions.upPress)
        }
        composable(FadeThrough.route) {
            FadeThroughScreen(actions.upPress)
        }
        composable(Fade.route) {
            FadeScreen(actions.upPress)
        }
        composable(Crossfade.route) {
            CrossfadeScreen(actions.upPress)
        }
        composable(Hold.route) {
            HoldScreen(actions.upPress)
        }
        composable(ElevationScale.route) {
            ElevationScaleScreen(actions.upPress)
        }
    }
}

private class MainActions(navController: NavHostController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    val goToDemo: () -> Unit = {
        navController.navigate(Demo.route)
    }
    val goToSharedAxis: (Axis) -> Unit = { axis ->
        val route = when (axis) {
            Axis.X -> SharedAxisX.route
            Axis.Y -> SharedAxisY.route
            Axis.Z -> SharedAxisZ.route
        }
        navController.navigate(route)
    }
    val goToFadeThrough: () -> Unit = {
        navController.navigate(FadeThrough.route)
    }
    val goToFade: () -> Unit = {
        navController.navigate(Fade.route)
    }
    val goToCrossfade: () -> Unit = {
        navController.navigate(Crossfade.route)
    }
    val goToHold: () -> Unit = {
        navController.navigate(Hold.route)
    }
    val goToElevationScale: () -> Unit = {
        navController.navigate(ElevationScale.route)
    }
}
