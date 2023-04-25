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
package soup.compose.material.motion.shared.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import soup.compose.material.motion.MaterialSharedAxisZ
import soup.compose.material.motion.shared.ui.circularreveal.CircularRevealScreen
import soup.compose.material.motion.shared.ui.demo.DemoScreen
import soup.compose.material.motion.shared.ui.material.elevationscale.MaterialElevationScaleScreen
import soup.compose.material.motion.shared.ui.material.fade.MaterialFadeScreen
import soup.compose.material.motion.shared.ui.material.fadethrough.MaterialFadeThroughScreen
import soup.compose.material.motion.shared.ui.material.hold.HoldScreen
import soup.compose.material.motion.shared.ui.material.sharedaxis.MaterialSharedAxisScreen

enum class Destination(val route: String) {
    Home("Home"),
    Demo("Demo"),
    MaterialSharedAxis("MaterialSharedAxis"),
    MaterialFadeThrough("MaterialFadeThrough"),
    MaterialFade("MaterialFade"),
    MaterialElevationScale("MaterialElevationScale"),
    Hold("Hold"),
    CircularReveal("CircularReveal"),
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    startDestination: Destination = Destination.Home,
) {
    val (destination, onDestinationChanged) = rememberSaveable {
        mutableStateOf(startDestination)
    }
    val upPress: () -> Unit = {
        onDestinationChanged(startDestination)
    }
    MaterialSharedAxisZ(
        destination,
        forward = destination != startDestination,
        modifier = Modifier.fillMaxSize(),
    ) {
        when (it) {
            Destination.Home -> {
                HomeScreen(onItemClick = onDestinationChanged)
            }

            Destination.Demo -> {
                DemoScreen(upPress = upPress)
            }

            /* Core */

            Destination.MaterialSharedAxis -> {
                MaterialSharedAxisScreen(upPress = upPress)
            }

            Destination.MaterialFadeThrough -> {
                MaterialFadeThroughScreen(upPress = upPress)
            }

            Destination.MaterialFade -> {
                MaterialFadeScreen(upPress = upPress)
            }

            Destination.MaterialElevationScale -> {
                MaterialElevationScaleScreen(upPress = upPress)
            }

            Destination.Hold -> {
                HoldScreen(upPress = upPress)
            }

            Destination.CircularReveal -> {
                CircularRevealScreen(upPress = upPress)
            }
        }
    }
}
