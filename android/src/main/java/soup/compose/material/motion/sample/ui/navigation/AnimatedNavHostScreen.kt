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
import kotlinx.serialization.Serializable
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance
import soup.compose.material.motion.sample.ui.common.DefaultScaffold

private sealed interface AnimatedNavDestination {

    @Serializable
    data object Fourth : AnimatedNavDestination

    @Serializable
    data object Third : AnimatedNavDestination

    @Serializable
    data object Second : AnimatedNavDestination

    @Serializable
    data object First : AnimatedNavDestination
}

@Composable
fun AnimatedNavHostScreen(upPress: () -> Unit) {
    DefaultScaffold(upPress = upPress) { innerPadding ->
        val slideDistance = rememberSlideDistance()
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = AnimatedNavDestination.First,
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
            composable<AnimatedNavDestination.First> {
                BackHandler {
                    upPress()
                }
                AnimatedNavDestinationScreen(
                    name = "First",
                    backgroundColor = Color.Cyan,
                    nextRoute = AnimatedNavDestination.Second,
                    onNavigateClick = { route ->
                        navController.navigate(route)
                    },
                )
            }
            composable<AnimatedNavDestination.Second> {
                AnimatedNavDestinationScreen(
                    name = "Second",
                    backgroundColor = Color.Red,
                    nextRoute = AnimatedNavDestination.Third,
                    onNavigateClick = { route ->
                        navController.navigate(route)
                    },
                )
            }
            composable<AnimatedNavDestination.Third> {
                AnimatedNavDestinationScreen(
                    name = "Third",
                    backgroundColor = Color.Green,
                    nextRoute = AnimatedNavDestination.Fourth,
                    onNavigateClick = { route ->
                        navController.navigate(route)
                    },
                )
            }
            composable<AnimatedNavDestination.Fourth> {
                AnimatedNavDestinationScreen(
                    name = "Fourth",
                    backgroundColor = Color.Yellow,
                    nextRoute = null,
                    onNavigateClick = { route ->
                        navController.navigate(route)
                    },
                )
            }
        }
    }
}

@Composable
private fun AnimatedNavDestinationScreen(
    name: String,
    backgroundColor: Color,
    nextRoute: AnimatedNavDestination?,
    onNavigateClick: (destination: AnimatedNavDestination) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
    ) {
        Text(
            text = name,
            color = Color.Black,
        )
        if (nextRoute != null) {
            Button(onClick = { onNavigateClick(nextRoute) }) {
                Text(text = "go to $nextRoute")
            }
        }
    }
}
