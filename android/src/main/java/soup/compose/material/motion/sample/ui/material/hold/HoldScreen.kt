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
package soup.compose.material.motion.sample.ui.material.hold

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.hold
import soup.compose.material.motion.sample.ui.common.DefaultScaffold
import soup.compose.material.motion.sample.ui.common.ForwardBackwardContents
import soup.compose.material.motion.sample.ui.common.ForwardBackwardControls
import soup.compose.material.motion.sample.ui.theme.SampleTheme
import soup.compose.material.motion.translateX

@Composable
fun HoldScreen(upPress: () -> Unit) {
    val (forward, onForwardChanged) = remember { mutableStateOf(false) }
    if (forward) {
        BackHandler { onForwardChanged(false) }
    }
    DefaultScaffold(
        upPress = upPress,
        bottomBar = {
            ForwardBackwardControls(forward, onForwardChanged)
        }
    ) { innerPadding ->
        BoxWithConstraints {
            val offset = LocalDensity.current.run { maxWidth.toPx() }
            MaterialMotion(
                targetState = forward,
                enterMotionSpec = if (forward) translateX(offset, 0f) else hold(),
                exitMotionSpec = if (forward) hold() else translateX(offset, 0f),
                pop = forward.not(),
                modifier = Modifier.padding(innerPadding)
            ) { forward ->
                ForwardBackwardContents(forward)
            }
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
