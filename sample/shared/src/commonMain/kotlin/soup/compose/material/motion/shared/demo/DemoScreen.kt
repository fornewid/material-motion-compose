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
package soup.compose.material.motion.shared.demo

import BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.animation.holdIn
import soup.compose.material.motion.animation.holdOut
import soup.compose.material.motion.animation.translateYIn
import soup.compose.material.motion.animation.translateYOut
import soup.compose.material.motion.shared.theme.SampleTheme

sealed class Route {
    @Serializable
    object Library: Route()
    @Serializable
    data class Album(val albumId: Long): Route()
}

@Composable
fun DemoScreen(upPress: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Route.Library) {
        composable<Route.Library>(
            enterTransition = { holdIn() },
            exitTransition = { holdOut() },
        ) {
            BackHandler {
                upPress()
            }
            LibraryScreen(
                upPress = upPress,
                onItemClick = {
                    navController.navigate(Route.Album(it.id))
                },
            )
        }
        composable<Route.Album>(
            enterTransition = { translateYIn { it } },
            exitTransition = { translateYOut { it } },
        ) { backStackEntry ->
            val currentId = backStackEntry.toRoute<Route.Album>().albumId
            val album = MusicData.albums.first { it.id == currentId }
            AlbumScreen(
                album,
                upPress = {
                    navController.popBackStack()
                },
            )
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    SampleTheme {
        DemoScreen(upPress = {})
    }
}
