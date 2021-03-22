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
package soup.material.compose.sample.ui.fadethrough

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.FadeThrough
import soup.material.compose.sample.ui.theme.SampleTheme

@Composable
fun FadeThroughScreen(upPress: () -> Unit) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(Tabs.Albums) }
    FadeThroughScaffold(
        upPress = upPress,
        selectedTab = selectedTab,
        setSelectedTab = setSelectedTab
    ) { innerPadding ->
        FadeThrough(
            targetState = selectedTab,
            modifier = Modifier.padding(innerPadding)
        ) { currentTab ->
            FadeThroughContents(currentTab)
        }
    }
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
