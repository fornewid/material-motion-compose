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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.ContentAlpha
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import soup.compose.material.motion.MaterialFade
import soup.compose.material.motion.shared.theme.Purple200

@Composable
fun AlbumScaffold(
    upPress: () -> Unit,
    collapse: Boolean,
    content: @Composable () -> Unit,
) {
    val backgroundColor = if (collapse) {
        val color = MaterialTheme.colors.primarySurface
        val elevationOverlay = LocalElevationOverlay.current
        val absoluteElevation = LocalAbsoluteElevation.current + 1.dp
        elevationOverlay?.apply(color, absoluteElevation) ?: color
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
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
            title = {},
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null,
                    )
                }
            },
            backgroundColor = backgroundColor,
            contentColor = Color.White,
            elevation = 0.dp,
        )
    }
}

@Composable
fun AlbumHeader(album: MusicData.Album, showFab: Boolean) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            Image(
                painter = painterResource(album.cover),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(196.dp),
                shape = RectangleShape,
                backgroundColor = MaterialTheme.colors.primarySurface,
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
                        style = MaterialTheme.typography.h3,
                    )
                    Text(
                        text = album.artist,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
            }
        }
        MaterialFade(
            visible = showFab,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 152.dp),
        ) {
            FloatingActionButton(
                onClick = {},
                backgroundColor = MaterialTheme.colors.fabBackground,
                contentColor = MaterialTheme.colors.surface,
                modifier = Modifier.padding(16.dp),
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
            }
        }
    }
}

private val Colors.fabBackground: Color
    get() = if (isLight) Color.Black else Purple200

@Composable
fun AlbumTrackItem(track: MusicData.Track) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(56.dp)
            .padding(horizontal = 16.dp),
    ) {
        if (track.playing) {
            Image(
                Icons.AutoMirrored.Filled.VolumeUp,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                ),
            )
        } else {
            Spacer(
                modifier = Modifier.requiredSize(24.dp),
            )
        }
        Spacer(modifier = Modifier.requiredWidth(16.dp))
        Text(
            text = track.trackNo.toString(),
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.body2,
        )
        Spacer(modifier = Modifier.requiredWidth(16.dp))
        Text(
            text = track.title,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle2,
        )
        Spacer(modifier = Modifier.requiredWidth(16.dp))
        Text(
            text = track.duration,
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.body2,
        )
    }
}
