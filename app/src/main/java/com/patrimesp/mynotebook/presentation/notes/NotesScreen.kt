package com.patrimesp.mynotebook.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patrimesp.mynotebook.ui.theme.MyNotebookTheme

@Composable
fun NotesScreen(notesViewModel: NotesViewModel = hiltViewModel()) {
    val uiState by notesViewModel.uiState.collectAsStateWithLifecycle()
    PhrasesContent(uiState = uiState)
}

@Composable
private fun PhrasesContent(uiState: NotesUiState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text("note taking screen", color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PhrasesScreenPreview() {
    MyNotebookTheme {
        PhrasesContent(
            uiState = NotesUiState()
        )
    }
}
