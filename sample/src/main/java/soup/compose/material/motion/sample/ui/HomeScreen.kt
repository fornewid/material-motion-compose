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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import soup.compose.material.motion.sample.ui.theme.SampleTheme

@Composable
fun HomeScreen(onItemClick: (HomeMenu) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Column {
                    TopAppBar(
                        title = {
                            Text(text = "Transition for Jetpack Compose")
                        }
                    )
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
                    HomeMenuItem(HomeMenu.Demo, onItemClick = onItemClick)
                    Divider()
                    Text(
                        text = "Additional Examples",
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
            items(HomeMenu.values().filterNot { it == HomeMenu.Demo }) {
                HomeMenuItem(it, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
private fun HomeMenuItem(
    menu: HomeMenu,
    onItemClick: (HomeMenu) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(64.dp)
            .clickable { onItemClick(menu) }
    ) {
        val (title, description) = createRefs()
        Text(
            text = menu.title,
            modifier = Modifier
                .constrainAs(title) {
                    width = Dimension.fillToConstraints
                    linkTo(
                        start = parent.start,
                        top = parent.top,
                        end = parent.end,
                        bottom = description.top,
                        startMargin = 16.dp,
                        endMargin = 16.dp
                    )
                },
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.body2
        )
        Text(
            modifier = Modifier
                .constrainAs(description) {
                    width = Dimension.fillToConstraints
                    linkTo(
                        start = parent.start,
                        top = title.bottom,
                        end = parent.end,
                        bottom = parent.bottom,
                        startMargin = 16.dp,
                        endMargin = 16.dp
                    )
                },
            text = menu.description,
            style = MaterialTheme.typography.caption
        )
        createVerticalChain(title, description, chainStyle = ChainStyle.Packed)
    }
}

private val HomeMenu.title: String
    get() = when (this) {
        HomeMenu.Demo -> "Demo"
        HomeMenu.SharedAxisX -> "Shared Axis (X)"
        HomeMenu.SharedAxisY -> "Shared Axis (Y)"
        HomeMenu.SharedAxisZ -> "Shared Axis (Z)"
        HomeMenu.FadeThrough -> "Fade Through"
        HomeMenu.Fade -> "Fade"
        HomeMenu.Crossfade -> "Crossfade"
    }

private val HomeMenu.description: String
    get() = when (this) {
        HomeMenu.Demo -> "DemoScreen"
        HomeMenu.SharedAxisX,
        HomeMenu.SharedAxisY,
        HomeMenu.SharedAxisZ,
        -> "SharedAxisScreen"
        HomeMenu.FadeThrough -> "FadeThroughScreen"
        HomeMenu.Fade -> "FadeScreen"
        HomeMenu.Crossfade -> "CrossfadeScreen"
    }

enum class HomeMenu {
    Demo,
    SharedAxisX,
    SharedAxisY,
    SharedAxisZ,
    FadeThrough,
    Fade,
    Crossfade
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    SampleTheme {
        HomeScreen(onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    SampleTheme(darkTheme = true) {
        HomeScreen(onItemClick = {})
    }
}
