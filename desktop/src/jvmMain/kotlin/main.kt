import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import soup.compose.material.motion.shared.ui.NavGraph
import soup.compose.material.motion.shared.ui.theme.SampleTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Sample",
    ) {
        SampleTheme {
            NavGraph()
        }
    }
}
