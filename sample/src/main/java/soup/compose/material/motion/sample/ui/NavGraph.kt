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
import soup.compose.material.motion.sample.ui.Destination.Alpha
import soup.compose.material.motion.sample.ui.Destination.Crossfade
import soup.compose.material.motion.sample.ui.Destination.Demo
import soup.compose.material.motion.sample.ui.Destination.Hold
import soup.compose.material.motion.sample.ui.Destination.Home
import soup.compose.material.motion.sample.ui.Destination.MaterialElevationScale
import soup.compose.material.motion.sample.ui.Destination.MaterialFade
import soup.compose.material.motion.sample.ui.Destination.MaterialFadeThrough
import soup.compose.material.motion.sample.ui.Destination.MaterialSharedAxis
import soup.compose.material.motion.sample.ui.Destination.Scale
import soup.compose.material.motion.sample.ui.alpha.AlphaScreen
import soup.compose.material.motion.sample.ui.crossfade.CrossfadeScreen
import soup.compose.material.motion.sample.ui.demo.DemoScreen
import soup.compose.material.motion.sample.ui.material.elevationscale.MaterialElevationScaleScreen
import soup.compose.material.motion.sample.ui.material.fade.MaterialFadeScreen
import soup.compose.material.motion.sample.ui.material.fadethrough.MaterialFadeThroughScreen
import soup.compose.material.motion.sample.ui.material.hold.HoldScreen
import soup.compose.material.motion.sample.ui.material.sharedaxis.MaterialSharedAxisScreen
import soup.compose.material.motion.sample.ui.scale.ScaleScreen

enum class Destination(val route: String, val root: Boolean = false) {
    Home("home", root = true),
    Demo("demo"),
    MaterialSharedAxis("material_shared_axis"),
    MaterialFadeThrough("material_fade_through"),
    MaterialFade("material_fade"),
    MaterialElevationScale("material_elevation_scale"),
    Hold("hold"),
    Crossfade("crossfade"),
    Alpha("alpha"),
    Scale("scale"),
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
                        Home -> {
                        }
                        Demo -> actions.goToDemo()
                        MaterialSharedAxis -> actions.goToMaterialSharedAxis()
                        MaterialFadeThrough -> actions.goToMaterialFadeThrough()
                        MaterialFade -> actions.goToMaterialFade()
                        MaterialElevationScale -> actions.goToMaterialElevationScale()
                        Hold -> actions.goToHold()
                        Alpha -> actions.goToAlpha()
                        Scale -> actions.goToScale()
                        Crossfade -> actions.goToCrossfade()
                    }
                }
            )
        }
        composable(Demo.route) {
            DemoScreen()
        }

        /* Material transition patterns */
        composable(MaterialSharedAxis.route) {
            MaterialSharedAxisScreen(actions.upPress)
        }
        composable(MaterialFadeThrough.route) {
            MaterialFadeThroughScreen(actions.upPress)
        }
        composable(MaterialFade.route) {
            MaterialFadeScreen(actions.upPress)
        }
        composable(MaterialElevationScale.route) {
            MaterialElevationScaleScreen(actions.upPress)
        }
        composable(Hold.route) {
            HoldScreen(actions.upPress)
        }

        /* Etc */
        composable(Crossfade.route) {
            CrossfadeScreen(actions.upPress)
        }
        composable(Alpha.route) {
            AlphaScreen(actions.upPress)
        }
        composable(Scale.route) {
            ScaleScreen(actions.upPress)
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
    val goToMaterialSharedAxis: () -> Unit = {
        navController.navigate(MaterialSharedAxis.route)
    }
    val goToMaterialFadeThrough: () -> Unit = {
        navController.navigate(MaterialFadeThrough.route)
    }
    val goToMaterialFade: () -> Unit = {
        navController.navigate(MaterialFade.route)
    }
    val goToMaterialElevationScale: () -> Unit = {
        navController.navigate(MaterialElevationScale.route)
    }
    val goToHold: () -> Unit = {
        navController.navigate(Hold.route)
    }
    val goToCrossfade: () -> Unit = {
        navController.navigate(Crossfade.route)
    }
    val goToAlpha: () -> Unit = {
        navController.navigate(Alpha.route)
    }
    val goToScale: () -> Unit = {
        navController.navigate(Scale.route)
    }
}
