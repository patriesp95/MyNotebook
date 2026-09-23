package com.patrimesp.mynotebook.presentation.notes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrimesp.mynotebook.domain.usecase.notes.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(val addNoteUseCase: AddNoteUseCase): ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState.asStateFlow()

    fun addNote(text: String) {
        viewModelScope.launch {
            val note = addNoteUseCase(text = text)
            Log.i("patri", "nota añadida, texto: ${note.text}")
        }
    }
}

data class NotesUiState(
    val loading: Boolean = false,
    val error: String? = null
)