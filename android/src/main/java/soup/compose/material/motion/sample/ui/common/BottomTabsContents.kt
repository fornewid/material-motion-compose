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
package soup.compose.material.motion.sample.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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

enum class BottomTabs {
    Albums, Photos, Search
}

@Composable
fun BottomTabsControls(
    selectedTab: BottomTabs,
    setSelectedTab: (BottomTabs) -> Unit,
) {
    BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
        BottomTabs.values().forEach { tab ->
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
}

@Composable
fun BottomTabsContents(selectedTab: BottomTabs, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Column {
            Row(modifier = Modifier.weight(1f)) {
                BottomTabsContentsItem(selectedTab, modifier = Modifier.weight(1f))
                BottomTabsContentsItem(selectedTab, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.weight(1f)) {
                BottomTabsContentsItem(selectedTab, modifier = Modifier.weight(1f))
                BottomTabsContentsItem(selectedTab, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.weight(1f)) {
                BottomTabsContentsItem(selectedTab, modifier = Modifier.weight(1f))
                BottomTabsContentsItem(selectedTab, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun BottomTabsContentsItem(
    selectedTab: BottomTabs,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = when (selectedTab) {
        BottomTabs.Albums -> MaterialTheme.colors.primary
        BottomTabs.Photos -> MaterialTheme.colors.error
        BottomTabs.Search -> MaterialTheme.colors.secondary
    }
    val contentColor = when (selectedTab) {
        BottomTabs.Albums -> MaterialTheme.colors.onPrimary
        BottomTabs.Photos -> MaterialTheme.colors.onError
        BottomTabs.Search -> MaterialTheme.colors.onSecondary
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
private fun BottomTabs.icon(): Painter = when (this) {
    BottomTabs.Albums -> painterResource(R.drawable.ic_collections)
    BottomTabs.Photos -> painterResource(R.drawable.ic_photo)
    BottomTabs.Search -> rememberVectorPainter(Icons.Default.Search)
}
