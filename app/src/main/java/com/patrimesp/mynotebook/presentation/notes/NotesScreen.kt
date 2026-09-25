package com.patrimesp.mynotebook.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patrimesp.mynotebook.domain.entity.notes.Note
import com.patrimesp.mynotebook.ui.theme.MyNotebookTheme
import kotlinx.coroutines.launch

private const val MAX_NOTE_LENGTH = 120

@Composable
fun NotesScreen(
    notesViewModel: NotesViewModel = hiltViewModel(),
) {
    val uiState by notesViewModel.uiState.collectAsStateWithLifecycle()

    NotesContent(
        uiState = uiState,
        onTextChanged = notesViewModel::onTextChanged,
        onShowDialogChanged = { notesViewModel.onShowDialogChanged(it) },
        onNoteAdded = notesViewModel::addNote,
        onNoteDeleted = { notesViewModel.deleteNote(it) }
    )
}

@Composable
private fun NotesContent(
    uiState: NotesUiState,
    onTextChanged: (String) -> Unit,
    onShowDialogChanged: (Boolean) -> Unit,
    onNoteAdded: (String) -> Unit,
    onNoteDeleted: (String) -> Unit
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
        NotesList(uiState, onNoteDeleted = onNoteDeleted)
        Box(modifier = Modifier.fillMaxSize()) {
            FabDialog(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(32.dp),
                uiState = uiState,
                onTextChanged = onTextChanged,
                onShowDialogChanged = onShowDialogChanged,
                onNoteAdded = onNoteAdded,
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
    if (show) {
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
                    onValueChange = { text ->
                        if (text.length <= MAX_NOTE_LENGTH) {
                            onTextChanged(text)
                        }
                    },
                    shape = OutlinedTextFieldDefaults.shape,
                    maxLines = 1,
                    supportingText = {
                        Text("${uiState.text.length}/$MAX_NOTE_LENGTH")
                    },
                    colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.onSecondary)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = { onNoteAdded(uiState.text) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState.text.isNotBlank()
                ) {
                    Text("Añadir tarea")
                }
            }
        }
    }
}

@Composable
fun NotesList(
    uiState: NotesUiState,
    onNoteDeleted: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val showScrollToTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex >= 20
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(vertical = 60.dp,horizontal = 16.dp)
                .align(Alignment.TopStart)
                .fillMaxSize(),
            contentPadding = PaddingValues(top = 35.dp, bottom = 96.dp)
        ) {
            itemsIndexed(uiState.notes, key = { _, note -> note.id }) { _, note ->
                NoteItem(note, onNoteDeleted = onNoteDeleted)
            }
        }

        if (showScrollToTop) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .alpha(0.55f),
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSurface,
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Volver al principio"
                )
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, onNoteDeleted: (String) -> Unit) {
    Row {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = note.text,
            color = MaterialTheme.colorScheme.onSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.weight(1.5f))
        Button(
            onClick = { onNoteDeleted(note.id) },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground)
        ) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete note",
                modifier = Modifier
                    .size(ButtonDefaults.IconSize),
                tint = Color.Red
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Delete", color = MaterialTheme.colorScheme.onSecondary)
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesScreenPreview() {
    MyNotebookTheme {
        NotesContent(
            uiState = NotesUiState(
                notes = listOf(
                    Note(text = "Preparar la compra", id = "note-1"),
                    Note(text = "Terminar el proyecto de ayer", id = "note-2")
                )
            ),
            onTextChanged = {},
            onShowDialogChanged = {},
            onNoteAdded = {},
            onNoteDeleted = {}
        )
    }
}
