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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AlbumScreen(album: MusicData.Album, upPress: () -> Unit) {
    val density = LocalDensity.current
    val collapsedHeaderSize = remember(density) {
        density.run { 56.dp.toPx().toInt() }
    }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    OnHeaderCollapse(listState, collapsedHeaderSize) { scrollOffset ->
        coroutineScope.launch {
            listState.animateScrollToItem(0, scrollOffset = scrollOffset)
        }
    }
    val collapse: Boolean by remember {
        derivedStateOf {
            listState.isHeaderCollapsed(collapsedHeaderSize)
        }
    }
    val showFab: Boolean by remember {
        derivedStateOf {
            val header = listState.layoutInfo.visibleItemsInfo.getOrNull(0)
            if (header != null) {
                (listState.firstVisibleItemScrollOffset / header.size.toFloat()) <= 0.2f
            } else {
                false
            }
        }
    }
    AlbumScaffold(
        upPress = upPress,
        collapse = collapse,
    ) {
        LazyColumn(state = listState) {
            item {
                AlbumHeader(album, showFab)
            }
            item {
                Spacer(modifier = Modifier.requiredHeight(8.dp))
            }
            items(album.tracks, key = { it.trackNo }) { track ->
                AlbumTrackItem(track = track)
            }
        }
    }
}

@Composable
private fun OnHeaderCollapse(
    listState: LazyListState,
    collapsedHeaderSize: Int,
    scrollToOffset: (Int) -> Unit,
) {
    if (!listState.isScrollInProgress.not() || listState.firstVisibleItemIndex != 0) return
    val header = listState.layoutInfo.visibleItemsInfo.getOrNull(0) ?: return
    val scrollOffset: Int by remember {
        derivedStateOf {
            if (listState.needsCollapse()) {
                header.size - collapsedHeaderSize
            } else {
                0
            }
        }
    }
    SideEffect {
        if (scrollOffset != listState.firstVisibleItemScrollOffset) {
            scrollToOffset(scrollOffset)
        }
    }
}

private fun LazyListState.needsCollapse(): Boolean {
    val header = layoutInfo.visibleItemsInfo.getOrNull(0)
    if (header != null) {
        return header.size / 2 < firstVisibleItemScrollOffset
    }
    return false
}

private fun LazyListState.isHeaderCollapsed(collapsedHeaderSize: Int): Boolean {
    if (firstVisibleItemIndex > 0) {
        return true
    }
    val header = layoutInfo.visibleItemsInfo.getOrNull(0)
    if (header != null) {
        return header.size - firstVisibleItemScrollOffset <= collapsedHeaderSize + 5
    }
    return false
}
