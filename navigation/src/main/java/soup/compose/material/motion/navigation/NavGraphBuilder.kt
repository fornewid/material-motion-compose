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
package soup.compose.material.motion.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.get
import androidx.navigation.navigation
import soup.compose.material.motion.EnterMotionSpec
import soup.compose.material.motion.ExitMotionSpec
import soup.compose.material.motion.materialSharedAxisZIn
import soup.compose.material.motion.materialSharedAxisZOut

/**
 * Add the [Composable] to the [NavGraphBuilder]
 *
 * @param route route for the destination
 * @param arguments list of arguments to associate with destination
 * @param deepLinks list of deep links to associate with the destinations
 * @param enterMotionSpec callback to determine the destination's enter transition
 * @param exitMotionSpec callback to determine the destination's exit transition
 * @param popEnterMotionSpec callback to determine the destination's popEnter transition
 * @param popExitMotionSpec callback to determine the destination's popExit transition
 * @param content composable for the destination
 */
@ExperimentalAnimationApi
fun NavGraphBuilder.composable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterMotionSpec: (initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec = { _, _ ->
        materialSharedAxisZIn()
    },
    exitMotionSpec: (initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec = { _, _ ->
        materialSharedAxisZOut()
    },
    popEnterMotionSpec: ((initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec) = enterMotionSpec,
    popExitMotionSpec: ((initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec) = exitMotionSpec,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    addDestination(
        MaterialMotionComposeNavigator.Destination(
            provider[MaterialMotionComposeNavigator::class],
            content,
            enterMotionSpec,
            exitMotionSpec,
            popEnterMotionSpec,
            popExitMotionSpec
        ).apply {
            this.route = route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

/**
 * Construct a nested [NavGraph]
 *
 * @param startDestination the starting destination's route for this NavGraph
 * @param route the destination's unique route
 * @param enterMotionSpec callback to define enter transitions for destination in this NavGraph
 * @param exitMotionSpec callback to define exit transitions for destination in this NavGraph
 * @param popEnterMotionSpec callback to define pop enter transitions for destination in this
 * NavGraph
 * @param popExitMotionSpec callback to define pop exit transitions for destination in this NavGraph
 * @param builder the builder used to construct the graph
 *
 * @return the newly constructed nested NavGraph
 */
@ExperimentalAnimationApi
public fun NavGraphBuilder.navigation(
    startDestination: String,
    route: String,
    enterMotionSpec: (initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec = { _, _ ->
        materialSharedAxisZIn()
    },
    exitMotionSpec: (initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec = { _, _ ->
        materialSharedAxisZOut()
    },
    popEnterMotionSpec: ((initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec) = enterMotionSpec,
    popExitMotionSpec: ((initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec) = exitMotionSpec,
    builder: NavGraphBuilder.() -> Unit,
) {
    navigation(startDestination, route, builder).apply {
        enterMotionSpec.let { enterMotionSpecs[route] = enterMotionSpec }
        exitMotionSpec.let { exitMotionSpecs[route] = exitMotionSpec }
        popEnterMotionSpec.let { popEnterMotionSpecs[route] = popEnterMotionSpec }
        popExitMotionSpec.let { popExitMotionSpecs[route] = popExitMotionSpec }
    }
}
