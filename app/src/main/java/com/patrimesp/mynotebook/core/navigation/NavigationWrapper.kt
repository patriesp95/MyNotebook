package com.patrimesp.mynotebook.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.patrimesp.mynotebook.presentation.phrases.PhrasesScreen

@Composable
fun NavigationWrapper() {
    val backstack = rememberNavBackStack(Phrases)
    NavDisplay(backStack = backstack, entryProvider = entryProvider {
        entry<Phrases> {
            PhrasesScreen()
        }
    })
}