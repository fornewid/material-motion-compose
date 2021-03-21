package soup.material.transition.compose

import androidx.compose.runtime.Composable

internal data class TransitionAnimationItem<T>(
    val key: T,
    val content: @Composable () -> Unit,
)
