package com.patrimesp.mynotebook.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.patrimesp.mynotebook.presentation.notes.NotesScreen
import com.patrimesp.mynotebook.presentation.phrases.PhrasesScreen

private val tabRouteSaver = Saver<TabRoute, String>(
    save = { route ->
        when (route) {
            TabRoute.Phrases -> "phrases"
            TabRoute.Notes -> "notes"
        }
    },
    restore = { saved ->
        when (saved) {
            "notes" -> TabRoute.Notes
            else -> TabRoute.Phrases
        }
    }
)

@Composable
fun NavigationWrapper() {
    var selectedTab by rememberSaveable(stateSaver = tabRouteSaver) {
        mutableStateOf<TabRoute>(TabRoute.Phrases)
    }

    val phrasesBackStack = rememberNavBackStack(TabRoute.Phrases)
    val notesBackStack = rememberNavBackStack(TabRoute.Notes)

    val currentBackStack = when (selectedTab) {
        TabRoute.Phrases -> phrasesBackStack
        TabRoute.Notes -> notesBackStack
    }
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                listOf(TabRoute.Phrases, TabRoute.Notes).forEach { tab ->
                    val label = when (tab) {
                        TabRoute.Phrases -> "Frases"
                        TabRoute.Notes -> "Notas"
                    }
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = { Text(if (tab == TabRoute.Phrases) "❝" else "✎") },
                        label = { Text(label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.background,
                            selectedTextColor = MaterialTheme.colorScheme.onSurface,
                            indicatorColor = MaterialTheme.colorScheme.onSurface,
                            unselectedIconColor = MaterialTheme.colorScheme.secondary,
                            unselectedTextColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = currentBackStack,
            onBack = { currentBackStack.removeLastOrNull() },
            modifier = Modifier.padding(innerPadding),
            entryProvider = entryProvider {
                entry<TabRoute.Phrases> { PhrasesScreen() }
                entry<TabRoute.Notes> { NotesScreen() }
            }
        )
    }
}
