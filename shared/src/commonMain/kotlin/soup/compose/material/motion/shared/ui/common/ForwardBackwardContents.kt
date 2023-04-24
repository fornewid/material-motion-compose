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
package soup.compose.material.motion.shared.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ForwardBackwardControls(
    forward: Boolean,
    onForwardChanged: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        TextButton(
            onClick = { onForwardChanged(false) },
            modifier = Modifier.align(Alignment.BottomStart),
            enabled = forward
        ) {
            Text(text = "Back".uppercase())
        }
        Button(
            onClick = { onForwardChanged(true) },
            modifier = Modifier.align(Alignment.BottomEnd),
            enabled = forward.not()
        ) {
            Text(text = "Next".uppercase())
        }
    }
}

@Composable
fun ForwardBackwardContents(forward: Boolean, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        if (forward) {
            ForwardContents()
        } else {
            BackwardContents()
        }
    }
}

@Composable
private fun BackwardContents() {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        Spacer(modifier = Modifier.requiredHeight(48.dp))
        Icon(
            Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier
                .requiredSize(72.dp)
                .align(Alignment.CenterHorizontally),
            tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
        )
        Spacer(modifier = Modifier.requiredHeight(8.dp))
        Text(
            "Hi David Park",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.requiredHeight(8.dp))
        Text(
            "Sign in with your account",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.requiredHeight(24.dp))

        val (emailOrPhoneNumber, onEmailOrPhoneNumberChanged) = remember { mutableStateOf("") }
        OutlinedTextField(
            value = emailOrPhoneNumber,
            onValueChange = onEmailOrPhoneNumberChanged,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            label = {
                Text(text = "Email or phone number")
            }
        )
        Spacer(modifier = Modifier.requiredHeight(12.dp))
        TextButton(onClick = {}) {
            Text(text = "Forgot email?".uppercase())
        }
        Spacer(modifier = Modifier.requiredHeight(8.dp))
        TextButton(onClick = {}) {
            Text(text = "Create account".uppercase())
        }
    }
}

@Composable
private fun ForwardContents() {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        Text(
            "Streamline your courses",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.requiredHeight(8.dp))
        Text(
            text = "Bundled categories appear as groups in your feed." +
                " You can always change this later.",
            textAlign = TextAlign.Center,
            color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.requiredHeight(48.dp))
        SwitchableItem(text = "Arts & Crafts")
        Spacer(modifier = Modifier.requiredHeight(32.dp))
        SwitchableItem(text = "Business")
        Spacer(modifier = Modifier.requiredHeight(32.dp))
        SwitchableItem(text = "Illustration")
        Spacer(modifier = Modifier.requiredHeight(32.dp))
        SwitchableItem(text = "Design")
        Spacer(modifier = Modifier.requiredHeight(32.dp))
        SwitchableItem(text = "Culinary")
    }
}

@Composable
private fun SwitchableItem(text: String) {
    val (checked, onCheckedChange) = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.CenterStart)) {
            Text(text = text)
            Text(
                text = if (checked) "Bundled" else "Shown individually",
                color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.caption
            )
        }
        val color = MaterialTheme.colors.surface
        val elevationOverlay = LocalElevationOverlay.current
        val absoluteElevation = LocalAbsoluteElevation.current + 4.dp
        val uncheckedThumbColor = elevationOverlay?.apply(color, absoluteElevation) ?: color
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterEnd),
            colors = SwitchDefaults.colors(uncheckedThumbColor = uncheckedThumbColor)
        )
    }
}
