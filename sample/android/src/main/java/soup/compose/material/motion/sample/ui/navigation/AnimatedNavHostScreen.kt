/*
 * Copyright 2022 SOUP
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
package soup.compose.material.motion.sample.ui.navigation

import androidx.activity.compose.BackHandler
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance
import soup.compose.material.motion.sample.ui.common.DefaultScaffold

private enum class AnimatedNavDestination(
    val route: String,
    val color: Color,
    val nextRoute: String? = null,
    val root: Boolean = false,
) {
    Fourth("fourth", Color.Yellow),
    Third("third", Color.Green, nextRoute = Fourth.route),
    Second("second", Color.Red, nextRoute = Third.route),
    First("first", Color.Cyan, nextRoute = Second.route, root = true),
}

@Composable
fun AnimatedNavHostScreen(upPress: () -> Unit) {
    DefaultScaffold(upPress = upPress) { innerPadding ->
        val slideDistance = rememberSlideDistance()
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = AnimatedNavDestination.First.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                materialSharedAxisXIn(forward = true, slideDistance = slideDistance)
            },
            exitTransition = {
                materialSharedAxisXOut(forward = true, slideDistance = slideDistance)
            },
            popEnterTransition = {
                materialSharedAxisXIn(forward = false, slideDistance = slideDistance)
            },
            popExitTransition = {
                materialSharedAxisXOut(forward = false, slideDistance = slideDistance)
            },
        ) {
            AnimatedNavDestination.values().forEach { destination ->
                composable(route = destination.route) {
                    if (destination.root) {
                        BackHandler {
                            upPress()
                        }
                    }
                    AnimatedNavDestinationScreen(
                        destination = destination,
                        onNavigateClick = { route ->
                            navController.navigate(route)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimatedNavDestinationScreen(
    destination: AnimatedNavDestination,
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
            color = Color.Black,
        )
        if (destination.nextRoute != null) {
            Button(onClick = { onNavigateClick(destination.nextRoute) }) {
                Text(text = "go to ${destination.nextRoute}")
            }
        }
    }
}
