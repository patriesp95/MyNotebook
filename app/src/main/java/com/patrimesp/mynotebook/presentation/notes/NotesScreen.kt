package com.patrimesp.mynotebook.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patrimesp.mynotebook.domain.entity.notes.Note
import com.patrimesp.mynotebook.ui.theme.MyNotebookTheme

@Composable
fun NotesScreen(notesViewModel: NotesViewModel = hiltViewModel()) {
    val uiState by notesViewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        notesViewModel.getNotes()
    }
    NotesContent(
        uiState = uiState,
        onTextChanged = { notesViewModel.onTextChanged(it) },//es lo mismo que la notacion de dos puntos notesViewModel::addNote
        onShowDialogChanged = { notesViewModel.onShowDialogChanged(it)},
        onNoteAdded = notesViewModel::addNote
    )
}

@Composable
private fun NotesContent(
    uiState: NotesUiState,
    onTextChanged: (String) -> Unit,
    onShowDialogChanged: (Boolean) -> Unit,
    onNoteAdded: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        NotesList(uiState)
        Box(modifier = Modifier.fillMaxSize()) {
            FabDialog(
                modifier = Modifier.align(Alignment.TopEnd).padding(16.dp),
                uiState = uiState,
                onTextChanged = onTextChanged,
                onShowDialogChanged = onShowDialogChanged,
                onNoteAdded = onNoteAdded
            )
        }

    }
}

@Composable
fun FabDialog(
    modifier: Modifier,
    uiState: NotesUiState,
    onTextChanged: (String) -> Unit,
    onShowDialogChanged: (Boolean) -> Unit,
    onNoteAdded: (String) -> Unit
) {
    FloatingActionButton(
        onClick = { onShowDialogChanged(true) },
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }

    AddTasksDialog(
        uiState = uiState,
        show = uiState.showDialog,
        onDismiss = { onShowDialogChanged(false) },
        onTextChanged = onTextChanged,
        onNoteAdded = onNoteAdded
    )
}

@Composable
fun AddTasksDialog(
    uiState: NotesUiState,
    show: Boolean,
    onDismiss: () -> Unit,
    onTextChanged: (String) -> Unit,
    onNoteAdded: (String) -> Unit
) {
    if(show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Añade tu tarea",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                OutlinedTextField(
                    value = uiState.text,
                    onValueChange = onTextChanged,
                    shape = OutlinedTextFieldDefaults.shape,
                    singleLine = true,
                    maxLines = 1,
                    colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.onSecondary)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onNoteAdded(uiState.text)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Añadir tarea")
                }
            }
        }
    }
}

@Composable
fun NotesList(uiState: NotesUiState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        itemsIndexed(uiState.notes, key = {_,item -> item.id }){ _, item ->
            Row {
                Text(
                    text = item.text,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NotesScreenPreview() {
    MyNotebookTheme {
        NotesContent(
            uiState = NotesUiState(
                notes = listOf(
                    Note(text = "Preparar la compra", id = "note-1"),
                    Note(text = "Terminar el proyecto", id = "note-2")
                )
            ),
            onTextChanged = {},
            onShowDialogChanged = {},
            onNoteAdded = {}
        )
    }
}
