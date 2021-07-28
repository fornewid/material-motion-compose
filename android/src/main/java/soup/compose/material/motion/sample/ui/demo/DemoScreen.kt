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

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.experimental.MaterialMotion
import soup.compose.material.motion.experimental.holdIn
import soup.compose.material.motion.experimental.holdOut
import soup.compose.material.motion.experimental.with
import soup.compose.material.motion.sample.ui.theme.SampleTheme
import soup.compose.material.motion.translateYIn
import soup.compose.material.motion.translateYOut

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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun DemoNavigation(
    state: Long?,
    modifier: Modifier = Modifier,
    content: @Composable (Long?) -> Unit,
) {
    val saveableStateHolder = rememberSaveableStateHolder()
    MaterialMotion(
        targetState = state,
        motionSpec = when {
            state != null -> translateYIn({ it }) with holdOut()
            else -> holdIn() with translateYOut({ it })
        },
        pop = state == null
    ) { currentId ->
        Box(modifier) {
            // Wrap the content representing the `currentScreen` inside `SaveableStateProvider`.
            // Here you can also add a screen switch animation like Crossfade where during the
            // animation multiple screens will be displayed at the same time.
            if (currentId != null) {
                content(currentId)
            } else {
                saveableStateHolder.SaveableStateProvider("library") {
                    content(currentId)
                }
            }
        }
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultPreview() {
    SampleTheme {
        DemoScreen()
    }
}
