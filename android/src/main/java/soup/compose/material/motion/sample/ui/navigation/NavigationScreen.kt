package soup.compose.material.motion.sample.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import soup.compose.material.motion.materialElevationScaleIn
import soup.compose.material.motion.materialElevationScaleOut
import soup.compose.material.motion.navigation.MaterialMotionNavHost
import soup.compose.material.motion.navigation.composable
import soup.compose.material.motion.navigation.rememberMaterialMotionNavController
import soup.compose.material.motion.sample.ui.common.DefaultScaffold
import soup.compose.material.motion.translateXIn
import soup.compose.material.motion.translateXOut

private enum class Destination(
    val route: String,
    val color: Color,
    val nextRoute: String? = null,
    val root: Boolean = false,
) {
    Fourth("fourth", Color.Yellow),
    Third("third", Color.Green, nextRoute = Fourth.route),
    Second("second", Color.Red, nextRoute = Third.route),
    First("first", Color.Cyan, nextRoute = Second.route, root = true);
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationScreen(upPress: () -> Unit) {
    DefaultScaffold(upPress = upPress) { innerPadding ->
        val navController = rememberMaterialMotionNavController()
        MaterialMotionNavHost(
            navController = navController,
            startDestination = Destination.First.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            Destination.values().forEach { destination ->
                composable(
                    route = destination.route,
                    enterMotionSpec = { _, _ ->
                        translateXIn({ it })
                    },
                    exitMotionSpec = { _, _ ->
                        materialElevationScaleOut()
                    },
                    popEnterMotionSpec = { _, _ ->
                        materialElevationScaleIn()
                    },
                    popExitMotionSpec = { _, _ ->
                        translateXOut({ it })
                    }
                ) {
                    if (destination.root) {
                        BackHandler {
                            upPress()
                        }
                    }
                    DestinationScreen(
                        destination = destination,
                        onNavigateClick = { route ->
                            navController.navigate(route)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DestinationScreen(
    destination: Destination,
    onNavigateClick: (route: String) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = destination.color),
    ) {
        Text(
            text = destination.name,
            color = Color.Black
        )
        if (destination.nextRoute != null) {
            Button(onClick = { onNavigateClick(destination.nextRoute) }) {
                Text(text = "go to ${destination.nextRoute}")
            }
        }
    }
}
