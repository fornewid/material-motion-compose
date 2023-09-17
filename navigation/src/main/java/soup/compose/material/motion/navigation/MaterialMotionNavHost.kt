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

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import soup.compose.material.motion.MotionConstants.DefaultMotionDuration

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
 * @param enterTransition callback to define enter transitions for destination in this host
 * @param exitTransition callback to define exit transitions for destination in this host
 * @param popEnterTransition callback to define popEnter transitions for destination in this host
 * @param popExitTransition callback to define popExit transitions for destination in this host
 * @param builder the builder used to construct the graph
 */
@Deprecated(
    "MaterialMotionNavHost has been migrated back into Androidx Navigation. Replace " +
        "with navigation-compose NavHost with the same parameters. Change import from " +
        "soup.compose.material.motion.navigation.MaterialMotionNavHost to " +
        "androidx.navigation.compose.NavHost."
)
@Composable
@ExperimentalAnimationApi
public fun MaterialMotionNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    route: String? = null,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        fadeIn(animationSpec = tween(DefaultMotionDuration))
    },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        fadeOut(animationSpec = tween(DefaultMotionDuration))
    },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
    builder: NavGraphBuilder.() -> Unit,
): Unit = NavHost(
    navController,
    startDestination,
    modifier,
    contentAlignment,
    route,
    enterTransition,
    exitTransition,
    popEnterTransition,
    popExitTransition,
    builder
)

/**
 * Provides in place in the Compose hierarchy for self contained navigation to occur.
 *
 * Once this is called, any Composable within the given [NavGraphBuilder] can be navigated to from
 * the provided [navController].
 *
 * @param navController the navController for this host
 * @param graph the graph for this host
 * @param modifier The modifier to be applied to the layout.
 * @param enterTransition callback to define enter transitions for destination in this host
 * @param exitTransition callback to define exit transitions for destination in this host
 * @param popEnterTransition callback to define popEnter transitions for destination in this host
 * @param popExitTransition callback to define popExit transitions for destination in this host
 */
@Deprecated(
    "MaterialMotionNavHost has been migrated back into Androidx Navigation. Replace " +
        "with navigation-compose NavHost with the same parameters. Change import from " +
        "soup.compose.material.motion.navigation.MaterialMotionNavHost to " +
        "androidx.navigation.compose.NavHost."
)
@ExperimentalAnimationApi
@Composable
public fun MaterialMotionNavHost(
    navController: NavHostController,
    graph: NavGraph,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        fadeIn(animationSpec = tween(DefaultMotionDuration))
    },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        fadeOut(animationSpec = tween(DefaultMotionDuration))
    },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
): Unit = NavHost(
    navController,
    graph,
    modifier,
    contentAlignment,
    enterTransition,
    exitTransition,
    popEnterTransition,
    popExitTransition
)

@ExperimentalAnimationApi
internal val enterTransitions =
    mutableMapOf<String?, (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)?>()

@ExperimentalAnimationApi
internal val exitTransitions =
    mutableMapOf<String?, (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?>()

@ExperimentalAnimationApi
internal val popEnterTransitions =
    mutableMapOf<String?, (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)?>()

@ExperimentalAnimationApi
internal val popExitTransitions =
    mutableMapOf<String?, (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?>()
