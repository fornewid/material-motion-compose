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

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import soup.compose.material.motion.MaterialSharedAxisZ
import soup.compose.material.motion.sample.ui.Destination.Demo
import soup.compose.material.motion.sample.ui.Destination.Hold
import soup.compose.material.motion.sample.ui.Destination.Home
import soup.compose.material.motion.sample.ui.Destination.MaterialElevationScale
import soup.compose.material.motion.sample.ui.Destination.MaterialFade
import soup.compose.material.motion.sample.ui.Destination.MaterialFadeThrough
import soup.compose.material.motion.sample.ui.Destination.MaterialSharedAxis
import soup.compose.material.motion.sample.ui.demo.DemoScreen
import soup.compose.material.motion.sample.ui.material.elevationscale.MaterialElevationScaleScreen
import soup.compose.material.motion.sample.ui.material.fade.MaterialFadeScreen
import soup.compose.material.motion.sample.ui.material.fadethrough.MaterialFadeThroughScreen
import soup.compose.material.motion.sample.ui.material.hold.HoldScreen
import soup.compose.material.motion.sample.ui.material.sharedaxis.MaterialSharedAxisScreen

enum class Destination(val route: String, val root: Boolean = false) {
    Home("home", root = true),
    Demo("demo"),
    MaterialSharedAxis("material_shared_axis"),
    MaterialFadeThrough("material_fade_through"),
    MaterialFade("material_fade"),
    MaterialElevationScale("material_elevation_scale"),
    Hold("hold");

    companion object {
        fun of(route: String): Destination {
            return values().first { it.route == route }
        }

        fun additionalExampleList(): List<Destination> = listOf(
            MaterialSharedAxis,
            MaterialFadeThrough,
            MaterialFade,
            MaterialElevationScale,
            Hold
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    startDestination: String = Home.route,
) {
    var route by rememberSaveable { mutableStateOf(startDestination) }
    val upPress: () -> Unit = {
        route = startDestination
    }
    val navigate: (Destination) -> Unit = {
        route = it.route
    }
    val destination = Destination.of(route)
    if (destination.root.not()) {
        BackHandler { upPress() }
    }
    val saveableStateHolder = rememberSaveableStateHolder()
    MaterialSharedAxisZ(
        targetState = destination,
        forward = destination.root.not(),
        modifier = Modifier.fillMaxSize()
    ) { current ->
        saveableStateHolder.SaveableStateProvider(current.route) {
            when (current) {
                Home -> HomeScreen { menu ->
                    when (menu) {
                        Home -> {
                        }
                        Demo -> navigate(Demo)
                        MaterialSharedAxis -> navigate(MaterialSharedAxis)
                        MaterialFadeThrough -> navigate(MaterialFadeThrough)
                        MaterialFade -> navigate(MaterialFade)
                        MaterialElevationScale -> navigate(MaterialElevationScale)
                        Hold -> navigate(Hold)
                    }
                }
                Demo -> {
                    DemoScreen()
                }

                /* Material transition patterns */
                MaterialSharedAxis -> {
                    MaterialSharedAxisScreen(upPress)
                }
                MaterialFadeThrough -> {
                    MaterialFadeThroughScreen(upPress)
                }
                MaterialFade -> {
                    MaterialFadeScreen(upPress)
                }
                MaterialElevationScale -> {
                    MaterialElevationScaleScreen(upPress)
                }
                Hold -> {
                    HoldScreen(upPress)
                }
            }
        }
    }
}
