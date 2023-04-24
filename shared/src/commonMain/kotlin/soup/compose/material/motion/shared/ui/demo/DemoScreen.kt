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
package soup.compose.material.motion.shared.ui.demo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.animation.holdIn
import soup.compose.material.motion.animation.holdOut
import soup.compose.material.motion.animation.translateYIn
import soup.compose.material.motion.animation.translateYOut

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoScreen(upPress: () -> Unit) {
    val (albumId, onAlbumIdChanged) = rememberSaveable {
        mutableStateOf<Long?>(null)
    }
    MaterialMotion(
        targetState = albumId,
        pop = albumId == null,
        transitionSpec = {
            when {
                targetState != null -> translateYIn { it } with holdOut()
                else -> holdIn() with translateYOut { it }
            }
        }
    ) { currentAlbumId ->
        if (currentAlbumId == null) {
            LibraryScreen(
                upPress = upPress,
                onItemClick = { album ->
                    onAlbumIdChanged(album.id)
                }
            )
        } else {
            val album = MusicData.albums.first { album -> album.id == currentAlbumId }
            AlbumScreen(
                album,
                upPress = {
                    onAlbumIdChanged(null)
                }
            )
        }
    }
}
