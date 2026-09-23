package com.patrimesp.mynotebook.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.patrimesp.mynotebook.ui.theme.MyNotebookTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NotesScreen(notesViewModel: NotesViewModel = hiltViewModel()) {
    NotesContent(notesViewModel)
}

@Composable
private fun NotesContent(notesViewModel: NotesViewModel) {
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
        Box(modifier = Modifier.fillMaxSize()) {
            FabDialog(Modifier.align(Alignment.TopEnd).padding(16.dp), notesViewModel = notesViewModel)
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, notesViewModel: NotesViewModel, ) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    FloatingActionButton(
        onClick = { showDialog = true },
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }

    AddTasksDialog(
        show = showDialog,
        onDismiss = { showDialog = false },
        onTaskAdded = {
            showDialog = false
            notesViewModel.addNote(it)
        }
    )
}

@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit){
    var myTask by remember { mutableStateOf("") }
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
                    value = myTask,
                    onValueChange = { myTask = it },
                    shape = OutlinedTextFieldDefaults.shape,
                    singleLine = true,
                    maxLines = 1,
                    colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.onSecondary)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onTaskAdded(myTask)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Añadir tarea")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PhrasesScreenPreview() {
    MyNotebookTheme {
        AddTasksDialog(
            show = true,
            onDismiss = {},
            onTaskAdded = {}
        )
    }
}
