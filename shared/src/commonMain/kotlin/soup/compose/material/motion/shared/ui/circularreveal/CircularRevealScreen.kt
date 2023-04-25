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
package soup.compose.material.motion.shared.ui.circularreveal

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.circularReveal
import soup.compose.material.motion.shared.ui.common.DefaultScaffold

private data class Position(
    val label: String,
    val alignment: Alignment,
    val center: Offset,
)

private val POSITIONS = listOf(
    Position(
        label = "TopStart",
        alignment = Alignment.TopStart,
        center = Offset(0f, 0f),
    ),
    Position(
        label = "TopCenter",
        alignment = Alignment.TopCenter,
        center = Offset(0.5f, 0f),
    ),
    Position(
        label = "TopEnd",
        alignment = Alignment.TopEnd,
        center = Offset(1f, 0f),
    ),
    Position(
        label = "CenterStart",
        alignment = Alignment.CenterStart,
        center = Offset(0f, 0.5f),
    ),
    Position(
        label = "Center",
        alignment = Alignment.Center,
        center = Offset(0.5f, 0.5f),
    ),
    Position(
        label = "CenterEnd",
        alignment = Alignment.CenterEnd,
        center = Offset(1f, 0.5f),
    ),
    Position(
        label = "BottomStart",
        alignment = Alignment.BottomStart,
        center = Offset(0f, 1f),
    ),
    Position(
        label = "BottomCenter",
        alignment = Alignment.BottomCenter,
        center = Offset(0.5f, 1f),
    ),
    Position(
        label = "BottomEnd",
        alignment = Alignment.BottomEnd,
        center = Offset(1f, 1f),
    ),
)

private data class State(
    val visible: Boolean,
    val center: Offset,
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CircularRevealScreen(upPress: () -> Unit) {
    val (state, onStateChanged) = remember {
        mutableStateOf(State(false, Offset.Zero))
    }
    DefaultScaffold(upPress = upPress) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Box(modifier = Modifier.fillMaxSize().padding(all = 16.dp)) {
                POSITIONS.forEach {
                    TextButton(
                        onClick = { onStateChanged(State(visible = true, center = it.center)) },
                        modifier = Modifier.align(it.alignment)
                    ) {
                        Text(text = it.label)
                    }
                }
            }
            Box(
                modifier = Modifier
                    .circularReveal(
                        visible = state.visible,
                        center = { fullSize ->
                            Offset(
                                x = state.center.x * fullSize.width,
                                y = state.center.y * fullSize.height,
                            )
                        },
                    )
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.secondary)
            ) {
                Button(
                    onClick = { onStateChanged(state.copy(visible = false)) },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("Close")
                }
            }
        }
    }
}
