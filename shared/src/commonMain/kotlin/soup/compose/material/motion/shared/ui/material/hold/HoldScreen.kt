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
package soup.compose.material.motion.shared.ui.material.hold

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.with
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.animation.holdIn
import soup.compose.material.motion.animation.holdOut
import soup.compose.material.motion.animation.translateXIn
import soup.compose.material.motion.animation.translateXOut
import soup.compose.material.motion.shared.ui.common.DefaultScaffold
import soup.compose.material.motion.shared.ui.common.ForwardBackwardContents
import soup.compose.material.motion.shared.ui.common.ForwardBackwardControls

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HoldScreen(upPress: () -> Unit) {
    val (forward, onForwardChanged) = remember { mutableStateOf(false) }
    DefaultScaffold(
        upPress = upPress,
        bottomBar = {
            ForwardBackwardControls(forward, onForwardChanged)
        }
    ) { innerPadding ->
        MaterialMotion(
            targetState = forward,
            transitionSpec = {
                when {
                    targetState -> translateXIn { it } with holdOut()
                    else -> holdIn() with translateXOut { it }
                }
            },
            modifier = Modifier.padding(innerPadding),
            pop = forward.not()
        ) { forward ->
            ForwardBackwardContents(forward)
        }
    }
}
