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
package soup.compose.material.motion.sample.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.sample.ui.theme.SampleTheme

private data class Demo(
    val title: String,
    val description: String,
    val destination: Destination,
)

private data class Category(
    val title: String,
    val items: List<Demo>,
) {
    companion object {
        val items: List<Category> = listOf(
            Category(
                title = "Additional Examples (Core)",
                items = listOf(
                    Demo(
                        title = "MaterialSharedAxis",
                        description = "MaterialSharedAxisScreen",
                        destination = Destination.MaterialSharedAxis
                    ),
                    Demo(
                        title = "MaterialFadeThrough",
                        description = "MaterialFadeThroughScreen",
                        destination = Destination.MaterialFadeThrough
                    ),
                    Demo(
                        title = "MaterialFade",
                        description = "MaterialFadeScreen",
                        destination = Destination.MaterialFade
                    ),
                    Demo(
                        title = "MaterialElevationScale",
                        description = "MaterialElevationScaleScreen",
                        destination = Destination.MaterialElevationScale
                    ),
                    Demo(
                        title = "Hold",
                        description = "HoldScreen",
                        destination = Destination.Hold
                    ),
                    Demo(
                        title = "CircularReveal",
                        description = "CircularRevealScreen",
                        destination = Destination.CircularReveal
                    ),
                )
            ),
            Category(
                title = "Additional Examples (Navigation)",
                items = listOf(
                    Demo(
                        title = "MaterialMotionNavHost",
                        description = "MaterialMotionNavHostScreen",
                        destination = Destination.MaterialMotionNavHost
                    ),
                    Demo(
                        title = "AnimatedNavHost",
                        description = "AnimatedNavHostScreen",
                        destination = Destination.AnimatedNavHost
                    ),
                )
            ),
        )
    }
}

@Composable
fun HomeScreen(onItemClick: (Destination) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Transition for Jetpack Compose") })
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding
        ) {
            item {
                Column {
                    Text(
                        text = "Description",
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = "Transition choreography is a coordinated sequence of motion" +
                            " that maintains user focus as the interface adapts.",
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        style = MaterialTheme.typography.body2
                    )
                    HomeDemoItem(
                        Demo(
                            title = "Demo",
                            description = "DemoScreen",
                            destination = Destination.Demo
                        ),
                        onItemClick = onItemClick
                    )
                }
            }
            Category.items.forEachIndexed { index, category ->
                item(
                    key = index,
                    contentType = { "Category" }
                ) {
                    Divider()
                    HomeCategoryItem(title = category.title)
                }
                items(
                    items = category.items,
                    key = { it.destination.route },
                    contentType = { "Demo" }
                ) { demo ->
                    HomeDemoItem(demo, onItemClick = onItemClick)
                }
            }
        }
    }
}

@Composable
private fun HomeCategoryItem(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier.padding(start = 16.dp, top = 16.dp),
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
private fun HomeDemoItem(
    demo: Demo,
    onItemClick: (Destination) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(64.dp)
            .clickable { onItemClick(demo.destination) }
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = demo.title,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.body2
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = demo.description,
            style = MaterialTheme.typography.caption
        )
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultPreview() {
    SampleTheme {
        HomeScreen(onItemClick = {})
    }
}
