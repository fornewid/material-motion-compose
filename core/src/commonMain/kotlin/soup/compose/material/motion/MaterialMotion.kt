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
@file:Suppress("unused")

package soup.compose.material.motion

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * [MaterialMotion] allows to switch between two layouts with a material motion animation.
 *
 * @param targetState is a key representing your target layout state. Every time you change a key
 * the animation will be triggered. The [content] called with the old key will be faded out while
 * the [content] called with the new key will be faded in.
 * @param transitionSpec the [ContentTransform] to configure the enter/exit animation.
 * @param modifier Modifier to be applied to the animation container.
 * @param pop whether motion contents are rendered in reverse order.
 */
@ExperimentalAnimationApi
@Composable
public fun <S> MaterialMotion(
    targetState: S,
    transitionSpec: AnimatedContentScope<S>.() -> ContentTransform,
    modifier: Modifier = Modifier,
    pop: Boolean = false,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable AnimatedVisibilityScope.(targetState: S) -> Unit,
) {
    val transition = updateTransition(targetState = targetState, label = "MaterialMotion")
    transition.MaterialMotion(
        transitionSpec,
        modifier,
        pop,
        contentAlignment,
        content = content
    )
}

/**
 * [MaterialMotion] allows to switch between two layouts with a material motion animation.
 *
 * @param transitionSpec the [ContentTransform] to configure the enter/exit animation.
 * @param modifier Modifier to be applied to the animation container.
 * @param pop whether motion contents are rendered in reverse order.
 */
@ExperimentalAnimationApi
@Composable
public fun <S> Transition<S>.MaterialMotion(
    transitionSpec: AnimatedContentScope<S>.() -> ContentTransform,
    modifier: Modifier = Modifier,
    pop: Boolean = false,
    contentAlignment: Alignment = Alignment.TopStart,
    contentKey: (targetState: S) -> Any? = { it },
    content: @Composable AnimatedVisibilityScope.(targetState: S) -> Unit,
) {
    val forward: Boolean = pop.not()
    val contentZIndex = remember { mutableStateOf(0f) }
    AnimatedContent(
        modifier = modifier,
        transitionSpec = {
            val spec = transitionSpec()
            (spec.targetContentEnter with spec.initialContentExit)
                .apply {
                    // Show forward contents over the backward contents.
                    if (forward) {
                        contentZIndex.value += 0.0001f
                    } else {
                        contentZIndex.value -= 0.0001f
                    }
                    targetContentZIndex = contentZIndex.value
                }
        },
        contentAlignment = contentAlignment,
        contentKey = contentKey
    ) { currentState ->
        content(currentState)
    }
}
