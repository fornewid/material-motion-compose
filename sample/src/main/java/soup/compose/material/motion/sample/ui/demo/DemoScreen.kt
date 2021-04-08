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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.Axis
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.hold
import soup.compose.material.motion.materialSharedAxis
import soup.compose.material.motion.sample.ui.theme.SampleTheme

@Composable
fun DemoScreen() {
    val (state, onStateChanged) = remember {
        mutableStateOf<Long?>(null)
    }
    MaterialMotion(
        targetState = state,
        enterMotionSpec = if (state != null) materialSharedAxis(Axis.Z, true) else hold(),
        exitMotionSpec = if (state != null) hold() else materialSharedAxis(Axis.Z, false),
        pop = state == null
    ) { currentId ->
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
