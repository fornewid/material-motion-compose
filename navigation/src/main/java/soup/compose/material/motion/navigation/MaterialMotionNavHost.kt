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
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
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
import soup.compose.material.motion.materialSharedAxisZIn
import soup.compose.material.motion.materialSharedAxisZOut
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
    contentAlignment: Alignment = Alignment.Center,
    route: String? = null,
    enterMotionSpec: (AnimatedContentScope<NavBackStackEntry>.() -> EnterMotionSpec) = { materialSharedAxisZIn() },
    exitMotionSpec: (AnimatedContentScope<NavBackStackEntry>.() -> ExitMotionSpec) = { materialSharedAxisZOut() },
    popEnterMotionSpec: (AnimatedContentScope<NavBackStackEntry>.() -> EnterMotionSpec) = enterMotionSpec,
    popExitMotionSpec: (AnimatedContentScope<NavBackStackEntry>.() -> ExitMotionSpec) = exitMotionSpec,
    builder: NavGraphBuilder.() -> Unit,
) {
    MaterialMotionNavHost(
        navController,
        remember(route, startDestination, builder) {
            navController.createGraph(startDestination, route, builder)
        },
        modifier,
        contentAlignment,
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
    contentAlignment: Alignment = Alignment.Center,
    enterMotionSpec: (AnimatedContentScope<NavBackStackEntry>.() -> EnterMotionSpec) = { materialSharedAxisZIn() },
    exitMotionSpec: (AnimatedContentScope<NavBackStackEntry>.() -> ExitMotionSpec) = { materialSharedAxisZOut() },
    popEnterMotionSpec: (AnimatedContentScope<NavBackStackEntry>.() -> EnterMotionSpec) = enterMotionSpec,
    popExitMotionSpec: (AnimatedContentScope<NavBackStackEntry>.() -> ExitMotionSpec) = exitMotionSpec,
) {

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
    val visibleTransitionsInProgress = rememberVisibleList(transitionsInProgress)
    val visibleBackStack = rememberVisibleList(backStack)
    visibleTransitionsInProgress.PopulateVisibleList(transitionsInProgress)
    visibleBackStack.PopulateVisibleList(backStack)

    val backStackEntry = visibleTransitionsInProgress.lastOrNull() ?: visibleBackStack.lastOrNull()
    if (backStackEntry != null) {
        val finalEnter: AnimatedContentScope<NavBackStackEntry>.() -> EnterMotionSpec = {
            val targetDestination =
                targetState.destination as MaterialMotionComposeNavigator.Destination
            if (composeNavigator.isPop.value) {
                targetDestination.hierarchy.firstNotNullOfOrNull { destination ->
                    popEnterMotionSpecs[destination.route]?.invoke(this)
                } ?: popEnterMotionSpec.invoke(this)
            } else {
                targetDestination.hierarchy.firstNotNullOfOrNull { destination ->
                    enterMotionSpecs[destination.route]?.invoke(this)
                } ?: enterMotionSpec.invoke(this)
            }
        }

        val finalExit: AnimatedContentScope<NavBackStackEntry>.() -> ExitMotionSpec = {
            val initialDestination =
                initialState.destination as MaterialMotionComposeNavigator.Destination
            if (composeNavigator.isPop.value) {
                initialDestination.hierarchy.firstNotNullOfOrNull { destination ->
                    popExitMotionSpecs[destination.route]?.invoke(this)
                } ?: popExitMotionSpec.invoke(this)
            } else {
                initialDestination.hierarchy.firstNotNullOfOrNull { destination ->
                    exitMotionSpecs[destination.route]?.invoke(this)
                } ?: exitMotionSpec.invoke(this)
            }
        }

        val transition = updateTransition(backStackEntry, label = "entry")
        transition.MaterialMotion(
            motionSpec = { finalEnter(this) with finalExit(this) },
            modifier = modifier,
            pop = composeNavigator.isPop.value,
            contentAlignment = contentAlignment,
            contentKey = { it.id }
        ) {
            val currentEntry = transitionsInProgress.lastOrNull { entry ->
                it == entry
            } ?: backStack.lastOrNull { entry ->
                it == entry
            }
            // while in the scope of the composable, we provide the navBackStackEntry as the
            // ViewModelStoreOwner and LifecycleOwner
            currentEntry?.LocalOwnersProvider(saveableStateHolder) {
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
    mutableMapOf<String?, (AnimatedContentScope<NavBackStackEntry>.() -> EnterMotionSpec?)?>()

@ExperimentalAnimationApi
internal val exitMotionSpecs =
    mutableMapOf<String?, (AnimatedContentScope<NavBackStackEntry>.() -> ExitMotionSpec?)?>()

@ExperimentalAnimationApi
internal val popEnterMotionSpecs =
    mutableMapOf<String?, (AnimatedContentScope<NavBackStackEntry>.() -> EnterMotionSpec?)?>()

@ExperimentalAnimationApi
internal val popExitMotionSpecs =
    mutableMapOf<String?, (AnimatedContentScope<NavBackStackEntry>.() -> ExitMotionSpec?)?>()

@Composable
private fun MutableList<NavBackStackEntry>.PopulateVisibleList(
    transitionsInProgress: Collection<NavBackStackEntry>
) {
    transitionsInProgress.forEach { entry ->
        DisposableEffect(entry.lifecycle) {
            val observer = LifecycleEventObserver { _, event ->
                // ON_START -> add to visibleBackStack, ON_STOP -> remove from visibleBackStack
                if (event == Lifecycle.Event.ON_START) {
                    // We want to treat the visible lists as Sets but we want to keep
                    // the functionality of mutableStateListOf() so that we recompose in response
                    // to adds and removes.
                    if (!contains(entry)) {
                        add(entry)
                    }
                }
                if (event == Lifecycle.Event.ON_STOP) {
                    remove(entry)
                }
            }
            entry.lifecycle.addObserver(observer)
            onDispose {
                entry.lifecycle.removeObserver(observer)
            }
        }
    }
}

@Composable
private fun rememberVisibleList(transitionsInProgress: Collection<NavBackStackEntry>) =
    remember(transitionsInProgress) {
        mutableStateListOf<NavBackStackEntry>().also {
            it.addAll(
                transitionsInProgress.filter { entry ->
                    entry.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                }
            )
        }
    }
