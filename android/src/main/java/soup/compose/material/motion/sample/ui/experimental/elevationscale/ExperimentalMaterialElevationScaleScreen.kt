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
package soup.compose.material.motion.sample.ui.experimental.elevationscale

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import soup.compose.material.motion.experimental.EnterMotionSpec
import soup.compose.material.motion.experimental.ExitMotionSpec
import soup.compose.material.motion.experimental.MaterialMotion
import soup.compose.material.motion.experimental.materialElevationScaleIn
import soup.compose.material.motion.experimental.materialElevationScaleOut
import soup.compose.material.motion.experimental.with
import soup.compose.material.motion.sample.ui.common.DefaultScaffold
import soup.compose.material.motion.sample.ui.common.ForwardBackwardContents
import soup.compose.material.motion.sample.ui.common.ForwardBackwardControls
import soup.compose.material.motion.sample.ui.theme.SampleTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExperimentalMaterialElevationScaleScreen(upPress: () -> Unit) {
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
        val motionSpec = when {
            forward -> EnterMotionSpec(
                transition = slideInVertically(initialOffsetY = { it }, animationSpec = tween(300))
            ) with materialElevationScaleOut()
            else -> materialElevationScaleIn() with ExitMotionSpec(
                transition = slideOutVertically(targetOffsetY = { it }, animationSpec = tween(300))
            )
        }
        MaterialMotion(
            targetState = forward,
            modifier = Modifier.padding(innerPadding),
            motionSpec = when {
                forward ->
                    EnterMotionSpec(
                        transition = slideInVertically(
                            initialOffsetY = { it },
                            animationSpec = tween(300)
                        )
                    ) with materialElevationScaleOut()
                else ->
                    materialElevationScaleIn() with
                        ExitMotionSpec(
                            transition = slideOutVertically(
                                targetOffsetY = { it },
                                animationSpec = tween(300)
                            )
                        )
            },
            pop = forward.not()
        ) { forward ->
            ForwardBackwardContents(forward)
        }
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultPreview() {
    SampleTheme {
        ExperimentalMaterialElevationScaleScreen(upPress = {})
    }
}
