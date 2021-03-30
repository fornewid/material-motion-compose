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

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable

@Composable
fun AlbumScreen(album: MusicData.Album, upPress: () -> Unit) {
    val listState = rememberLazyListState()
    AlbumScaffold(upPress = upPress, collapse = listState.firstVisibleItemIndex > 0) {
        LazyColumn(state = listState) {
            item {
                AlbumHeader(album)
            }
            items(album.tracks) { track ->
                AlbumTrackItem(track = track)
            }
        }
    }
}
