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
import soup.material.transition.compose.Axis
import soup.material.transition.compose.SharedAxis

@Composable
fun SharedAxisScreen() {
    val axis = Axis.Y
    val (forward, onForwardChanged) = remember {
        mutableStateOf(false)
    }
    SharedAxis(axis = axis, forward = forward, targetState = forward) { forward ->
        Surface(
            color = if (forward) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.colors.background
            },
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onForwardChanged(forward.not())
                }
        ) {
            if (forward) {
                Text(text = "Hello SharedAxis!")
            } else {
                Text(text = "Hello Android!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SampleTheme {
        SharedAxisScreen()
    }
}
