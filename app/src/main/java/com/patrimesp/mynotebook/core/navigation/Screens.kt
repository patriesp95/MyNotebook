package com.patrimesp.mynotebook.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface TabRoute : NavKey {
    @Serializable
    data object Phrases : TabRoute

    @Serializable
    data object Notes : TabRoute
}