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
package soup.compose.material.motion.shared.material.fadethrough

import BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.MaterialFadeThrough
import soup.compose.material.motion.shared.common.BottomTabs
import soup.compose.material.motion.shared.common.BottomTabsContents
import soup.compose.material.motion.shared.common.BottomTabsControls
import soup.compose.material.motion.shared.common.DefaultScaffold
import soup.compose.material.motion.shared.theme.SampleTheme

@Composable
fun MaterialFadeThroughScreen(upPress: () -> Unit) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(BottomTabs.Albums) }
    BackHandler {
        upPress()
    }
    DefaultScaffold(
        upPress = upPress,
        bottomBar = { BottomTabsControls(selectedTab, setSelectedTab) },
    ) { innerPadding ->
        MaterialFadeThrough(
            targetState = selectedTab,
            modifier = Modifier.padding(innerPadding),
        ) { currentTab ->
            BottomTabsContents(currentTab)
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    SampleTheme {
        MaterialFadeThroughScreen(upPress = {})
    }
}
