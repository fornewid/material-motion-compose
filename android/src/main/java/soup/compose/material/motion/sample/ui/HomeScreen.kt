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
import soup.compose.material.motion.sample.ui.Destination.Alpha
import soup.compose.material.motion.sample.ui.Destination.Crossfade
import soup.compose.material.motion.sample.ui.Destination.Demo
import soup.compose.material.motion.sample.ui.Destination.ExperimentalHold
import soup.compose.material.motion.sample.ui.Destination.ExperimentalMaterialElevationScale
import soup.compose.material.motion.sample.ui.Destination.ExperimentalMaterialFade
import soup.compose.material.motion.sample.ui.Destination.ExperimentalMaterialFadeThrough
import soup.compose.material.motion.sample.ui.Destination.ExperimentalMaterialSharedAxis
import soup.compose.material.motion.sample.ui.Destination.Hold
import soup.compose.material.motion.sample.ui.Destination.Home
import soup.compose.material.motion.sample.ui.Destination.MaterialElevationScale
import soup.compose.material.motion.sample.ui.Destination.MaterialFade
import soup.compose.material.motion.sample.ui.Destination.MaterialFadeThrough
import soup.compose.material.motion.sample.ui.Destination.MaterialSharedAxis
import soup.compose.material.motion.sample.ui.Destination.Scale
import soup.compose.material.motion.sample.ui.Destination.Translate
import soup.compose.material.motion.sample.ui.theme.SampleTheme

@Composable
fun HomeScreen(onItemClick: (Destination) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Transition for Jetpack Compose") })
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                    HomeMenuItem(Demo, onItemClick = onItemClick)
                    Divider()
                    Text(
                        text = "Additional Examples",
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
            items(Destination.additionalExampleList()) {
                HomeMenuItem(it, onItemClick = onItemClick)
            }
            item {
                Divider()
                Text(
                    text = "Experimental Examples",
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    style = MaterialTheme.typography.subtitle1
                )
            }
            items(Destination.experimentalList()) {
                HomeMenuItem(it, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
private fun HomeMenuItem(
    menu: Destination,
    onItemClick: (Destination) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(64.dp)
            .clickable { onItemClick(menu) }
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = menu.title,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.body2
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = menu.description,
            style = MaterialTheme.typography.caption
        )
    }
}

private val Destination.title: String
    get() = when (this) {
        ExperimentalMaterialSharedAxis -> "MaterialSharedAxis (Experimental)"
        ExperimentalMaterialFadeThrough -> "MaterialFadeThrough (Experimental)"
        ExperimentalMaterialFade -> "MaterialFade (Experimental)"
        ExperimentalMaterialElevationScale -> "MaterialElevationScale (Experimental)"
        ExperimentalHold -> "Hold (Experimental)"
        else -> name
    }

private val Destination.description: String
    get() = when (this) {
        Home -> "HomeScreen"
        Demo -> "DemoScreen"
        MaterialSharedAxis -> "MaterialSharedAxisScreen"
        MaterialFadeThrough -> "MaterialFadeThroughScreen"
        MaterialFade -> "MaterialFadeScreen"
        MaterialElevationScale -> "MaterialElevationScaleScreen"
        Hold -> "HoldScreen"
        Crossfade -> "CrossfadeScreen"
        Alpha -> "AlphaScreen"
        Scale -> "ScaleScreen"
        Translate -> "TranslateScreen"
        ExperimentalMaterialSharedAxis -> "MaterialSharedAxisScreen"
        ExperimentalMaterialFadeThrough -> "MaterialFadeThroughScreen"
        ExperimentalMaterialFade -> "MaterialFadeScreen"
        ExperimentalMaterialElevationScale -> "MaterialElevationScaleScreen"
        ExperimentalHold -> "HoldScreen"
    }

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultPreview() {
    SampleTheme {
        HomeScreen(onItemClick = {})
    }
}
