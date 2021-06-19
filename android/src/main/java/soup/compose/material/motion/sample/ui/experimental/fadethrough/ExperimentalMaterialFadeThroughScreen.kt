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
package soup.compose.material.motion.sample.ui.experimental.fadethrough

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.experimental.materialFadeThrough
import soup.compose.material.motion.sample.ui.common.BottomTabs
import soup.compose.material.motion.sample.ui.common.BottomTabsContents
import soup.compose.material.motion.sample.ui.common.BottomTabsControls
import soup.compose.material.motion.sample.ui.common.DefaultScaffold
import soup.compose.material.motion.sample.ui.theme.SampleTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExperimentalMaterialFadeThroughScreen(upPress: () -> Unit) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(BottomTabs.Albums) }
    DefaultScaffold(
        upPress = upPress,
        bottomBar = { BottomTabsControls(selectedTab, setSelectedTab) }
    ) { innerPadding ->
        AnimatedContent(
            targetState = selectedTab,
            modifier = Modifier.padding(innerPadding),
            transitionSpec = { materialFadeThrough() }
        ) { currentTab ->
            BottomTabsContents(currentTab)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    SampleTheme {
        ExperimentalMaterialFadeThroughScreen(upPress = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    SampleTheme(darkTheme = true) {
        ExperimentalMaterialFadeThroughScreen(upPress = {})
    }
}
