package soup.material.compose.sample.ui.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable

@Composable
fun DefaultTopAppBar(upPress: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Transition") },
        navigationIcon = {
            IconButton(onClick = upPress) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = null
                )
            }
        }
    )
}
