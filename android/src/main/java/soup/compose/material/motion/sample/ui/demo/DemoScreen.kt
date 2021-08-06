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
package soup.compose.material.motion.sample.ui.demo

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import soup.compose.material.motion.holdIn
import soup.compose.material.motion.holdOut
import soup.compose.material.motion.navigation.MaterialMotionNavHost
import soup.compose.material.motion.navigation.composable
import soup.compose.material.motion.navigation.rememberMaterialMotionNavController
import soup.compose.material.motion.sample.ui.theme.SampleTheme
import soup.compose.material.motion.translateYIn
import soup.compose.material.motion.translateYOut

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoScreen() {
    val navController = rememberMaterialMotionNavController()
    MaterialMotionNavHost(navController, startDestination = "library") {
        composable(
            "library",
            enterMotionSpec = { _, _ -> holdIn() },
            exitMotionSpec = { _, _ -> holdOut() },
        ) {
            LibraryScreen(
                onItemClick = {
                    navController.navigate("album/${it.id}")
                }
            )
        }
        composable(
            "album/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.LongType }),
            enterMotionSpec = { _, _ -> translateYIn({ it }) },
            exitMotionSpec = { _, _ -> translateYOut({ it }) },
        ) { backStackEntry ->
            BackHandler { navController.popBackStack() }
            val currentId = backStackEntry.arguments?.getLong("albumId")
            val album = MusicData.albums.first { it.id == currentId }
            AlbumScreen(
                album,
                upPress = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultPreview() {
    SampleTheme {
        DemoScreen()
    }
}
