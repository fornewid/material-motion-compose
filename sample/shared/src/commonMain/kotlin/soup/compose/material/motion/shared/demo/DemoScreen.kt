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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.animation.holdIn
import soup.compose.material.motion.animation.holdOut
import soup.compose.material.motion.animation.translateYIn
import soup.compose.material.motion.animation.translateYOut
import soup.compose.material.motion.shared.theme.SampleTheme

@Composable
fun DemoScreen(upPress: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "library") {
        composable(
            "library",
            enterTransition = { holdIn() },
            exitTransition = { holdOut() },
        ) {
            BackHandler {
                upPress()
            }
            LibraryScreen(
                onItemClick = {
                    navController.navigate("album/${it.id}")
                },
            )
        }
        composable(
            "album/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.LongType }),
            enterTransition = { translateYIn { it } },
            exitTransition = { translateYOut { it } },
        ) { backStackEntry ->
            val currentId = backStackEntry.arguments?.getLong("albumId")
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
