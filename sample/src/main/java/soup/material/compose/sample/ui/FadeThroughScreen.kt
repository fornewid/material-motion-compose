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
package soup.material.compose.sample.ui

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
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import soup.material.compose.sample.R
import soup.material.compose.sample.ui.theme.SampleTheme
import soup.material.compose.sample.ui.widget.DefaultTopAppBar
import soup.material.transition.compose.FadeThrough

@Composable
fun FadeThroughScreen(upPress: () -> Unit) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(Tabs.Albums) }
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
        }
    ) { innerPadding ->
        FadeThrough(
            targetState = selectedTab,
            modifier = Modifier.padding(innerPadding)
        ) { currentTab ->
            FadeThroughContents(currentTab)
        }
    }
}

@Composable
private fun FadeThroughContents(selectedTab: Tabs) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Column {
            Row(modifier = Modifier.weight(1f)) {
                FadeThroughItem(selectedTab, modifier = Modifier.weight(1f))
                FadeThroughItem(selectedTab, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.weight(1f)) {
                FadeThroughItem(selectedTab, modifier = Modifier.weight(1f))
                FadeThroughItem(selectedTab, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.weight(1f)) {
                FadeThroughItem(selectedTab, modifier = Modifier.weight(1f))
                FadeThroughItem(selectedTab, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun FadeThroughItem(
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

private enum class Tabs {
    Albums, Photos, Search
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    SampleTheme {
        FadeThroughScreen(upPress = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    SampleTheme(darkTheme = true) {
        FadeThroughScreen(upPress = {})
    }
}
