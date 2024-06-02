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
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import soup.compose.material.motion.animation.holdIn
import soup.compose.material.motion.animation.holdOut
import soup.compose.material.motion.animation.translateYIn
import soup.compose.material.motion.animation.translateYOut
import soup.compose.material.motion.sample.ui.theme.SampleTheme

private sealed interface DemoDestination {
    @Serializable
    data object Library : DemoDestination

    @Serializable
    data class Album(val albumId: Long) : DemoDestination
}

@Composable
fun DemoScreen(upPress: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = DemoDestination.Library) {
        composable<DemoDestination.Library>(
            enterTransition = { holdIn() },
            exitTransition = { holdOut() },
        ) {
            BackHandler {
                upPress()
            }
            LibraryScreen(
                onItemClick = {
                    navController.navigate(DemoDestination.Album(albumId = it.id))
                },
            )
        }
        composable<DemoDestination.Album>(
            enterTransition = { translateYIn { it } },
            exitTransition = { translateYOut { it } },
        ) { backStackEntry ->
            val currentId = backStackEntry.toRoute<DemoDestination.Album>().albumId
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

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultPreview() {
    SampleTheme {
        DemoScreen(upPress = {})
    }
}
