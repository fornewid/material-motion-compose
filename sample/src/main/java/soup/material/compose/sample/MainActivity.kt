package soup.material.compose.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import soup.material.compose.sample.ui.theme.SampleTheme
import soup.material.transition.compose.FadeThrough

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleTheme {
                val (flag, onFlagChanged) = remember {
                    mutableStateOf(true)
                }
                FadeThrough(targetState = flag) { newFlag ->
                    Surface(
                        color = if (newFlag) {
                            MaterialTheme.colors.background
                        } else {
                            MaterialTheme.colors.primary
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                onFlagChanged(newFlag.not())
                            }
                    ) {
                        Greeting(if (newFlag) "Android" else "Fade Through")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleTheme {
        Greeting("Android")
    }
}
