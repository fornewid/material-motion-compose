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
package soup.compose.material.motion.sample.ui.material.sharedaxis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.Axis
import soup.compose.material.motion.MaterialSharedAxis
import soup.compose.material.motion.sample.ui.common.DefaultScaffold
import soup.compose.material.motion.sample.ui.common.ForwardBackwardContents
import soup.compose.material.motion.sample.ui.common.ForwardBackwardControls
import soup.compose.material.motion.sample.ui.theme.SampleTheme

@Composable
fun MaterialSharedAxisScreen(upPress: () -> Unit) {
    val (selectedAxis, onAxisSelected) = remember { mutableStateOf(Axis.X) }
    val (forward, onForwardChanged) = remember { mutableStateOf(false) }
    DefaultScaffold(
        upPress = upPress,
        bottomBar = {
            Column {
                ForwardBackwardControls(forward, onForwardChanged)
                MaterialSharedAxisControls(selectedAxis, onAxisSelected)
            }
        }
    ) { innerPadding ->
        MaterialSharedAxis(
            axis = selectedAxis,
            forward = forward,
            targetState = forward,
            modifier = Modifier.padding(innerPadding)
        ) { forward ->
            ForwardBackwardContents(forward)
        }
    }
}

@Composable
private fun MaterialSharedAxisControls(
    selectedAxis: Axis,
    onAxisSelected: (Axis) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup(),
        horizontalArrangement = Arrangement.Center
    ) {
        Axis.values().forEach { axis ->
            Row(
                Modifier
                    .wrapContentSize()
                    .selectable(
                        selected = axis == selectedAxis,
                        onClick = { onAxisSelected(axis) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = axis == selectedAxis,
                    onClick = null
                )
                Spacer(modifier = Modifier.requiredWidth(4.dp))
                Text(text = axis.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    SampleTheme {
        MaterialSharedAxisScreen(upPress = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    SampleTheme(darkTheme = true) {
        MaterialSharedAxisScreen(upPress = {})
    }
}
