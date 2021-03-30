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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import soup.compose.material.motion.sample.R

@Composable
fun AlbumScaffold(
    upPress: () -> Unit,
    collapse: Boolean,
    content: @Composable () -> Unit,
) {
    val backgroundColor = if (collapse) {
        MaterialTheme.colors.primarySurface
    } else {
        Color.Transparent
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(modifier = Modifier.fillMaxSize(), content = content)
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            navigationIcon = {
                IconButton(onClick = upPress) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            title = {},
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null
                    )
                }
            },
            backgroundColor = backgroundColor,
            contentColor = Color.White,
            elevation = 0.dp
        )
    }
}

@Composable
fun AlbumHeader(album: MusicData.Album) {
    Image(
        painter = painterResource(album.cover),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentScale = ContentScale.Crop
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(196.dp),
        shape = RectangleShape,
        backgroundColor = MaterialTheme.colors.primarySurface
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 56.dp, end = 16.dp),
        ) {
            Text(
                text = album.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.h3
            )
            Text(
                text = album.artist,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
    Spacer(modifier = Modifier.requiredHeight(8.dp))
}

@Composable
fun AlbumTrackItem(track: MusicData.Track) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(48.dp)
            .padding(horizontal = 16.dp)
    ) {
        val (playingIcon, number, title, duration) = createRefs()
        Image(
            painter = painterResource(R.drawable.ic_volume_up_black_24dp),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(playingIcon) {
                    start.linkTo(parent.start)
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom
                    )
                },
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                LocalContentColor.current.copy(alpha = ContentAlpha.medium)
            )
        )
        Text(
            text = track.track.toString(),
            modifier = Modifier
                .constrainAs(number) {
                    start.linkTo(playingIcon.end, margin = 16.dp)
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom,
                    )
                },
            style = MaterialTheme.typography.body2
        )
        Text(
            text = track.title,
            modifier = Modifier
                .constrainAs(title) {
                    width = Dimension.fillToConstraints
                    linkTo(
                        start = number.end,
                        top = parent.top,
                        end = duration.start,
                        bottom = parent.bottom,
                        startMargin = 16.dp,
                        endMargin = 16.dp
                    )
                },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            text = track.duration,
            modifier = Modifier
                .constrainAs(duration) {
                    end.linkTo(parent.end)
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom
                    )
                },
            style = MaterialTheme.typography.body2
        )
        createVerticalChain(title, number, chainStyle = ChainStyle.Packed)
    }
}
