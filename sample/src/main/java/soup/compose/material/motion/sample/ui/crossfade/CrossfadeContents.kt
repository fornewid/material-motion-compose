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
package soup.compose.material.motion.sample.ui.crossfade

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.sample.R
import soup.compose.material.motion.sample.ui.widget.DefaultTopAppBar

enum class Tabs {
    Albums, Photos, Search
}

@Composable
fun CrossfadeScaffold(
    upPress: () -> Unit,
    selectedTab: Tabs,
    setSelectedTab: (Tabs) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = { DefaultTopAppBar(upPress) },
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
                Tabs.values().forEach { tab ->
                    BottomNavigationItem(
                        icon = { Icon(tab.icon(), contentDescription = null) },
                        label = { Text(tab.name) },
                        selected = tab == selectedTab,
                        onClick = { setSelectedTab(tab) },
                        selectedContentColor = MaterialTheme.colors.primary,
                        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        },
        content = content
    )
}

@Composable
fun CrossfadeContents(selectedTab: Tabs) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Column {
            Row(modifier = Modifier.weight(1f)) {
                CrossfadeItem(selectedTab, modifier = Modifier.weight(1f))
                CrossfadeItem(selectedTab, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.weight(1f)) {
                CrossfadeItem(selectedTab, modifier = Modifier.weight(1f))
                CrossfadeItem(selectedTab, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.weight(1f)) {
                CrossfadeItem(selectedTab, modifier = Modifier.weight(1f))
                CrossfadeItem(selectedTab, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun CrossfadeItem(
    selectedTab: Tabs,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = when (selectedTab) {
        Tabs.Albums -> MaterialTheme.colors.primary
        Tabs.Photos -> MaterialTheme.colors.error
        Tabs.Search -> MaterialTheme.colors.secondary
    }
    val contentColor = when (selectedTab) {
        Tabs.Albums -> MaterialTheme.colors.onPrimary
        Tabs.Photos -> MaterialTheme.colors.onError
        Tabs.Search -> MaterialTheme.colors.onSecondary
    }
    Card(
        backgroundColor = backgroundColor,
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Image(
            painter = selectedTab.icon(),
            contentDescription = null,
            colorFilter = ColorFilter.tint(contentColor)
        )
    }
}

@Composable
private fun Tabs.icon(): Painter = when (this) {
    Tabs.Albums -> painterResource(R.drawable.ic_collections)
    Tabs.Photos -> painterResource(R.drawable.ic_photo)
    Tabs.Search -> rememberVectorPainter(Icons.Default.Search)
}
