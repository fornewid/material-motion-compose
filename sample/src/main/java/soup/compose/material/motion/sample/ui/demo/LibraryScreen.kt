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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.MotionSpec
import soup.compose.material.motion.fadeThrough
import soup.compose.material.motion.sample.R
import soup.compose.material.motion.sharedAxisY

private enum class SortType {
    A_TO_Z, Z_TO_A
}
private enum class ListType {
    Grid, Linear
}

private data class LibraryState(
    val sortType: SortType,
    val listType: ListType,
    val motionSpec: MotionSpec = fadeThrough()
)

@Composable
fun LibraryScreen() {
    val (state, onStateChanged) = remember {
        mutableStateOf(LibraryState(SortType.A_TO_Z, ListType.Grid))
    }
    fun onSortTypeChange(sortType: SortType) {
        onStateChanged(
            state.copy(
                sortType = sortType,
                motionSpec = sharedAxisY(forward = true)
            )
        )
    }
    fun onListTypeChange(listType: ListType) {
        onStateChanged(
            state.copy(
                listType = listType,
                motionSpec = fadeThrough()
            )
        )
    }
    LibraryScaffold(
        onSortTypeToggle = {
            val newType = when (state.sortType) {
                SortType.A_TO_Z -> SortType.Z_TO_A
                SortType.Z_TO_A -> SortType.A_TO_Z
            }
            onSortTypeChange(newType)
        },
        onListTypeToggle = {
            val newType = when (state.listType) {
                ListType.Grid -> ListType.Linear
                ListType.Linear -> ListType.Grid
            }
            onListTypeChange(newType)
        },
    ) { innerPadding ->
        MaterialMotion(
            targetState = state,
            motionSpec = state.motionSpec,
            modifier = Modifier.padding(innerPadding)
        ) { currentDestination ->
            LibraryContents(currentDestination)
        }
    }
}

@Composable
fun LibraryScaffold(
    onSortTypeToggle: () -> Unit,
    onListTypeToggle: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Library") },
                actions = {
                    IconButton(onClick = onSortTypeToggle) {
                        Icon(
                            painterResource(R.drawable.ic_sort_by_alpha_black_24dp),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = onListTypeToggle) {
                        Icon(
                            painterResource(R.drawable.ic_view_list_black_24dp),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = content
    )
}

@Composable
private fun LibraryContents(state: LibraryState) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val items = when (state.sortType) {
            SortType.A_TO_Z -> MusicData.albums
            SortType.Z_TO_A -> MusicData.albums.asReversed()
        }
        when (state.listType) {
            ListType.Grid -> AlbumGridContents(items)
            ListType.Linear -> AlbumLinearContents(items)
        }
    }
}
