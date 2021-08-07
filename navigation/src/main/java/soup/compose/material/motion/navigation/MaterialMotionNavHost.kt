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

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.DialogHost
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.LocalOwnersProvider
import androidx.navigation.createGraph
import androidx.navigation.get
import soup.compose.material.motion.EnterMotionSpec
import soup.compose.material.motion.ExitMotionSpec
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.materialFadeThroughIn
import soup.compose.material.motion.materialFadeThroughOut
import soup.compose.material.motion.with

/**
 * Provides in place in the Compose hierarchy for self contained navigation to occur.
 *
 * Once this is called, any Composable within the given [NavGraphBuilder] can be navigated to from
 * the provided [navController].
 *
 * The builder passed into this method is [remember]ed. This means that for this NavHost, the
 * contents of the builder cannot be changed.
 *
 * @param navController the navController for this host
 * @param startDestination the route for the start destination
 * @param modifier The modifier to be applied to the layout.
 * @param route the route for the graph
 * @param enterMotionSpec callback to define enter transitions for destination in this host
 * @param exitMotionSpec callback to define exit transitions for destination in this host
 * @param popEnterMotionSpec callback to define popEnter transitions for destination in this host
 * @param popExitMotionSpec callback to define popExit transitions for destination in this host
 * @param builder the builder used to construct the graph
 */
@Composable
@ExperimentalAnimationApi
fun MaterialMotionNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    route: String? = null,
    enterMotionSpec: (initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec = { _, _ ->
        materialFadeThroughIn()
    },
    exitMotionSpec: (initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec = { _, _ ->
        materialFadeThroughOut()
    },
    popEnterMotionSpec: ((initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec) = enterMotionSpec,
    popExitMotionSpec: ((initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec) = exitMotionSpec,
    builder: NavGraphBuilder.() -> Unit,
) {
    MaterialMotionNavHost(
        navController,
        remember(route, startDestination, builder) {
            navController.createGraph(startDestination, route, builder)
        },
        modifier,
        enterMotionSpec,
        exitMotionSpec,
        popEnterMotionSpec,
        popExitMotionSpec
    )
}

/**
 * Provides in place in the Compose hierarchy for self contained navigation to occur.
 *
 * Once this is called, any Composable within the given [NavGraphBuilder] can be navigated to from
 * the provided [navController].
 *
 * @param navController the navController for this host
 * @param graph the graph for this host
 * @param modifier The modifier to be applied to the layout.
 * @param enterMotionSpec callback to define enter transitions for destination in this host
 * @param exitMotionSpec callback to define exit transitions for destination in this host
 * @param popEnterMotionSpec callback to define popEnter transitions for destination in this host
 * @param popExitMotionSpec callback to define popExit transitions for destination in this host
 */
@ExperimentalAnimationApi
@Composable
public fun MaterialMotionNavHost(
    navController: NavHostController,
    graph: NavGraph,
    modifier: Modifier = Modifier,
    enterMotionSpec: (initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec = { _, _ ->
        materialFadeThroughIn()
    },
    exitMotionSpec: (initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec = { _, _ ->
        materialFadeThroughOut()
    },
    popEnterMotionSpec: ((initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec) = enterMotionSpec,
    popExitMotionSpec: ((initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec) = exitMotionSpec,
) {
    enterMotionSpecs[graph.route] = enterMotionSpec
    exitMotionSpecs[graph.route] = exitMotionSpec
    popEnterMotionSpecs[graph.route] = popEnterMotionSpec
    popExitMotionSpecs[graph.route] = popExitMotionSpec

    val lifecycleOwner = LocalLifecycleOwner.current
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "NavHost requires a ViewModelStoreOwner to be provided via LocalViewModelStoreOwner"
    }
    val onBackPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current
    val onBackPressedDispatcher = onBackPressedDispatcherOwner?.onBackPressedDispatcher

    // on successful recompose we setup the navController with proper inputs
    // after the first time, this will only happen again if one of the inputs changes
    navController.setLifecycleOwner(lifecycleOwner)
    navController.setViewModelStore(viewModelStoreOwner.viewModelStore)
    if (onBackPressedDispatcher != null) {
        navController.setOnBackPressedDispatcher(onBackPressedDispatcher)
    }

    navController.graph = graph

    val saveableStateHolder = rememberSaveableStateHolder()

    // Find the ComposeNavigator, returning early if it isn't found
    // (such as is the case when using TestNavHostController)
    val composeNavigator = navController.navigatorProvider.get<Navigator<out NavDestination>>(
        MaterialMotionComposeNavigator.NAME
    ) as? MaterialMotionComposeNavigator ?: return
    val backStack by composeNavigator.backStack.collectAsState()
    val transitionsInProgress by composeNavigator.transitionsInProgress.collectAsState()

    val backStackEntry = transitionsInProgress.lastOrNull { entry ->
        entry.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
    } ?: backStack.lastOrNull { entry ->
        entry.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
    }
    if (backStackEntry != null) {
        val destination = backStackEntry.destination as MaterialMotionComposeNavigator.Destination

        val leavingEntry = transitionsInProgress.lastOrNull { entry ->
            !entry.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
        }

        // When there is no leaving entry, that means this is the start destination so this
        // transition never happens.
        val finalEnter = if (leavingEntry != null) {
            if (composeNavigator.isPop.value) {
                destination.popEnterMotionSpec?.invoke(leavingEntry, backStackEntry)
                    ?: popEnterMotionSpecs[
                        (
                            destination.hierarchy.first {
                                popEnterMotionSpecs.containsKey(it.route)
                            }
                            ).route
                    ]?.invoke(leavingEntry, backStackEntry) as EnterMotionSpec
            } else {
                destination.enterMotionSpec?.invoke(leavingEntry, backStackEntry)
                    ?: enterMotionSpecs[
                        (
                            destination.hierarchy.first { enterMotionSpecs.containsKey(it.route) }
                            ).route
                    ]?.invoke(leavingEntry, backStackEntry) as EnterMotionSpec
            }
        } else {
            EnterMotionSpec.None
        }

        val finalExit = if (leavingEntry != null) {
            if (composeNavigator.isPop.value) {
                (leavingEntry.destination as? MaterialMotionComposeNavigator.Destination)
                    ?.popExitMotionSpec?.invoke(
                        leavingEntry, backStackEntry
                    ) ?: popExitMotionSpecs[
                    (
                        leavingEntry.destination.hierarchy.first {
                            popExitMotionSpecs.containsKey(it.route)
                        }
                        ).route
                ]?.invoke(leavingEntry, backStackEntry) as ExitMotionSpec
            } else {
                (leavingEntry.destination as? MaterialMotionComposeNavigator.Destination)
                    ?.exitMotionSpec?.invoke(
                        leavingEntry, backStackEntry
                    ) ?: exitMotionSpecs[
                    (
                        leavingEntry.destination.hierarchy.first {
                            exitMotionSpecs.containsKey(it.route)
                        }
                        ).route
                ]?.invoke(leavingEntry, backStackEntry) as ExitMotionSpec
            }
        } else {
            ExitMotionSpec.None
        }
        val transition = updateTransition(backStackEntry, label = "entry")
        transition.MaterialMotion(
            motionSpec = finalEnter with finalExit,
            modifier = modifier,
            pop = composeNavigator.isPop.value,
        ) { currentEntry ->
            // while in the scope of the composable, we provide the navBackStackEntry as the
            // ViewModelStoreOwner and LifecycleOwner
            currentEntry.LocalOwnersProvider(saveableStateHolder) {
                (currentEntry.destination as MaterialMotionComposeNavigator.Destination)
                    .content(currentEntry)
            }
        }
        if (transition.currentState == transition.targetState) {
            transitionsInProgress.forEach { entry ->
                composeNavigator.markTransitionComplete(entry)
            }
        }
    }

    val dialogNavigator = navController.navigatorProvider.get<Navigator<out NavDestination>>(
        "dialog"
    ) as? DialogNavigator ?: return

    // Show any dialog destinations
    DialogHost(dialogNavigator)
}

@ExperimentalAnimationApi
internal val enterMotionSpecs =
    mutableMapOf<String?, (initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec>()

@ExperimentalAnimationApi
internal val exitMotionSpecs =
    mutableMapOf<String?, (initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec>()

@ExperimentalAnimationApi
internal val popEnterMotionSpecs =
    mutableMapOf<String?, (initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterMotionSpec>()

@ExperimentalAnimationApi
internal val popExitMotionSpecs =
    mutableMapOf<String?, (initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitMotionSpec>()
