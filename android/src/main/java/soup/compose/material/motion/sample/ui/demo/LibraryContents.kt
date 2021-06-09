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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LibraryGridContents(
    items: List<MusicData.Album>,
    onItemClick: (MusicData.Album) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
    ) {
        items(items) {
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
            .padding(4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            val (image, title, artist) = createRefs()
            Image(
                painter = painterResource(album.cover),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .constrainAs(image) {
                        width = Dimension.fillToConstraints
                        linkTo(
                            start = parent.start,
                            end = parent.end
                        )
                        top.linkTo(parent.top)
                    },
                contentScale = ContentScale.Crop
            )
            Text(
                text = album.title,
                modifier = Modifier
                    .constrainAs(title) {
                        width = Dimension.wrapContent
                        linkTo(
                            start = parent.start,
                            end = parent.end,
                            startMargin = 16.dp,
                            endMargin = 16.dp
                        )
                        top.linkTo(image.bottom, 16.dp)
                    },
                maxLines = 1,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = album.artist,
                modifier = Modifier
                    .constrainAs(artist) {
                        width = Dimension.wrapContent
                        linkTo(
                            start = parent.start,
                            end = parent.end,
                            startMargin = 16.dp,
                            endMargin = 16.dp,
                        )
                        top.linkTo(title.bottom, 2.dp)
                    },
                maxLines = 1,
                style = MaterialTheme.typography.body2
            )
            createVerticalChain(title, artist, chainStyle = ChainStyle.Packed)
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
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
    ) {
        items(items) {
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
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(88.dp)
            .clickable { onItemClick(album) }
    ) {
        val (image, title, artist, duration) = createRefs()
        Image(
            painter = painterResource(album.cover),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(4.dp))
                .constrainAs(image) {
                    height = Dimension.fillToConstraints
                    start.linkTo(parent.start, margin = 16.dp)
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom,
                        topMargin = 12.dp,
                        bottomMargin = 12.dp
                    )
                },
            contentScale = ContentScale.Crop
        )
        Text(
            text = album.title,
            modifier = Modifier
                .constrainAs(title) {
                    width = Dimension.fillToConstraints
                    linkTo(
                        start = image.end,
                        top = parent.top,
                        end = duration.start,
                        bottom = artist.top,
                        startMargin = 16.dp
                    )
                },
            style = MaterialTheme.typography.h6
        )
        Text(
            text = album.artist,
            modifier = Modifier
                .constrainAs(artist) {
                    width = Dimension.fillToConstraints
                    linkTo(
                        start = image.end,
                        top = title.bottom,
                        end = duration.start,
                        bottom = parent.bottom,
                        startMargin = 16.dp
                    )
                },
            style = MaterialTheme.typography.body2
        )
        Text(
            text = album.duration,
            modifier = Modifier
                .constrainAs(duration) {
                    width = Dimension.wrapContent
                    baseline.linkTo(title.baseline)
                    end.linkTo(parent.end, margin = 16.dp)
                },
            color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.body2
        )
        createVerticalChain(title, artist, chainStyle = ChainStyle.Packed)
    }
}
