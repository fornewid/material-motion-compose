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
package soup.compose.material.motion.sample.ui.demo

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.hold
import soup.compose.material.motion.sample.ui.theme.SampleTheme
import soup.compose.material.motion.translateY

@Composable
fun DemoScreen() {
    val (state, onStateChanged) = rememberSaveable {
        mutableStateOf<Long?>(null)
    }
    DemoNavigation(state) { currentId ->
        if (currentId != null) {
            BackHandler { onStateChanged(null) }
            val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
            val album = MusicData.albums.first { it.id == currentId }
            AlbumScreen(album, upPress = { dispatcher?.onBackPressed() })
        } else {
            LibraryScreen(onItemClick = { onStateChanged(it.id) })
        }
    }
}

@Composable
private fun DemoNavigation(
    state: Long?,
    modifier: Modifier = Modifier,
    content: @Composable (Long?) -> Unit,
) {
    val saveableStateHolder = rememberSaveableStateHolder()

    BoxWithConstraints {
        val offset = LocalDensity.current.run { maxHeight.toPx() }
        val enterMotionSpec = when {
            state != null -> translateY(offset, 0f)
            else -> hold()
        }
        val exitMotionSpec = when {
            state != null -> hold()
            else -> translateY(offset, 0f)
        }
        MaterialMotion(
            targetState = state,
            enterMotionSpec = enterMotionSpec,
            exitMotionSpec = exitMotionSpec,
            pop = state == null
        ) { currentId ->
            Box(modifier) {
                // Wrap the content representing the `currentScreen` inside `SaveableStateProvider`.
                // Here you can also add a screen switch animation like Crossfade where during the
                // animation multiple screens will be displayed at the same time.
                saveableStateHolder.SaveableStateProvider(currentId.toString()) {
                    content(currentId)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    SampleTheme {
        DemoScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    SampleTheme(darkTheme = true) {
        DemoScreen()
    }
}
