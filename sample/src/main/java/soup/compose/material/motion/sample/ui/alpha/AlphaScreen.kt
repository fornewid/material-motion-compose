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
package soup.compose.material.motion.sample.ui.alpha

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.alpha
import soup.compose.material.motion.sample.ui.common.DefaultScaffold
import soup.compose.material.motion.sample.ui.theme.SampleTheme

@Composable
fun AlphaScreen(upPress: () -> Unit) {
    DefaultScaffold(upPress = upPress) { innerPadding ->
        val (visible, onVisibleChanged) = remember { mutableStateOf(true) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Button(
                onClick = { onVisibleChanged(visible.not()) },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = if (visible) "HIDE FAB" else "SHOW FAB")
            }
            MaterialMotion(
                targetState = visible,
                motionSpec = alpha(0f, 1f),
                modifier = Modifier.align(Alignment.BottomEnd)
            ) { visible ->
                if (visible) {
                    FloatingActionButton(
                        onClick = {},
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    SampleTheme {
        AlphaScreen(upPress = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    SampleTheme(darkTheme = true) {
        AlphaScreen(upPress = {})
    }
}
