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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import soup.material.compose.sample.ui.theme.SampleTheme
import soup.material.transition.compose.FadeThrough

@Composable
fun FadeThroughScreen() {
    val (flag, onFlagChanged) = remember {
        mutableStateOf(true)
    }
    FadeThrough(targetState = flag) { newFlag ->
        Surface(
            color = if (newFlag) {
                MaterialTheme.colors.background
            } else {
                MaterialTheme.colors.primary
            },
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onFlagChanged(newFlag.not())
                }
        ) {
            if (newFlag) {
                Text(text = "Hello Android!")
            } else {
                Text(text = "Hello FadeThrough!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SampleTheme {
        FadeThroughScreen()
    }
}
