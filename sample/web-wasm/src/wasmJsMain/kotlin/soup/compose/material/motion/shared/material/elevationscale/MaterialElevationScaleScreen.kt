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
package soup.compose.material.motion.shared.material.elevationscale

import BackHandler
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.animation.materialElevationScaleIn
import soup.compose.material.motion.animation.materialElevationScaleOut
import soup.compose.material.motion.animation.translateYIn
import soup.compose.material.motion.animation.translateYOut
import soup.compose.material.motion.shared.common.DefaultScaffold
import soup.compose.material.motion.shared.common.ForwardBackwardContents
import soup.compose.material.motion.shared.common.ForwardBackwardControls
import soup.compose.material.motion.shared.theme.SampleTheme

@Composable
fun MaterialElevationScaleScreen(upPress: () -> Unit) {
    val (forward, onForwardChanged) = remember { mutableStateOf(false) }
    BackHandler {
        if (forward) {
            onForwardChanged(false)
        } else {
            upPress()
        }
    }
    DefaultScaffold(
        upPress = upPress,
        bottomBar = {
            ForwardBackwardControls(forward, onForwardChanged)
        },
    ) { innerPadding ->
        MaterialMotion(
            targetState = forward,
            modifier = Modifier.padding(innerPadding),
            transitionSpec = {
                when {
                    targetState -> translateYIn { it } togetherWith materialElevationScaleOut()
                    else -> materialElevationScaleIn() togetherWith translateYOut { it }
                }
            },
            pop = forward.not(),
        ) { forward ->
            ForwardBackwardContents(forward)
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    SampleTheme {
        MaterialElevationScaleScreen(upPress = {})
    }
}
