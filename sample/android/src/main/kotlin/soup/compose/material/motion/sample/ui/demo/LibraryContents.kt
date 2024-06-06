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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LibraryGridContents(
    items: List<MusicData.Album>,
    onItemClick: (MusicData.Album) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
    ) {
        items(items, key = { it.id }) {
            LibraryGridItem(it, onItemClick = onItemClick)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun LibraryGridItem(
    album: MusicData.Album,
    onItemClick: (MusicData.Album) -> Unit,
) {
    Card(
        onClick = { onItemClick(album) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Column {
            Image(
                painter = painterResource(album.cover),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = album.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                maxLines = 1,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = album.artist,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 2.dp),
                maxLines = 1,
                style = MaterialTheme.typography.body2,
            )
            Spacer(modifier = Modifier.requiredHeight(16.dp))
        }
    }
}

@Composable
fun LibraryLinearContents(
    items: List<MusicData.Album>,
    onItemClick: (MusicData.Album) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
    ) {
        items(items, key = { it.id }) {
            LibraryLinearItem(it, onItemClick = onItemClick)
            Divider()
        }
    }
}

@Composable
private fun LibraryLinearItem(
    album: MusicData.Album,
    onItemClick: (MusicData.Album) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(88.dp)
            .clickable { onItemClick(album) }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(album.cover),
            contentDescription = null,
            modifier = Modifier
                .requiredHeight(64.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = album.title,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = album.artist,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.body2,
            )
        }
        Text(
            text = album.duration,
            modifier = Modifier.wrapContentWidth(),
            color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.body2,
        )
    }
}
