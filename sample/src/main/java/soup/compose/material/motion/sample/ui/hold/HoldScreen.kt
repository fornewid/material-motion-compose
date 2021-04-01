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
package soup.compose.material.motion.sample.ui.hold

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.Axis
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.hold
import soup.compose.material.motion.materialSharedAxis
import soup.compose.material.motion.sample.ui.sharedaxis.ForwardBackwardContents
import soup.compose.material.motion.sample.ui.sharedaxis.ForwardBackwardScaffold
import soup.compose.material.motion.sample.ui.theme.SampleTheme

@Composable
fun HoldScreen(upPress: () -> Unit) {
    val (forward, onForwardChanged) = remember { mutableStateOf(false) }
    ForwardBackwardScaffold(
        upPress = upPress,
        forward = forward,
        onForwardChanged = onForwardChanged,
    ) { innerPadding ->
        MaterialMotion(
            targetState = forward,
            enterMotionSpec = if (forward) materialSharedAxis(Axis.Z, forward) else hold(),
            exitMotionSpec = if (forward) hold() else materialSharedAxis(Axis.Z, forward),
            pop = forward.not(),
            modifier = Modifier.padding(innerPadding)
        ) { forward ->
            ForwardBackwardContents(forward)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    SampleTheme {
        HoldScreen {}
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    SampleTheme(darkTheme = true) {
        HoldScreen {}
    }
}
