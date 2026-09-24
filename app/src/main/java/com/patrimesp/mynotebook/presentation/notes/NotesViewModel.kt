package com.patrimesp.mynotebook.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrimesp.mynotebook.domain.entity.notes.Note
import com.patrimesp.mynotebook.domain.usecase.notes.AddNoteUseCase
import com.patrimesp.mynotebook.domain.usecase.notes.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    val addNoteUseCase: AddNoteUseCase,
    val getNotesUseCase: GetNotesUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState.asStateFlow()

    fun getNotes() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            try {
                val notes = getNotesUseCase()
                _uiState.update { state -> state.copy(loading = false, notes = notes) }
            } catch (error: Exception) {
                _uiState.update { state -> state.copy(loading = false, error = error.message) }
            }
        }
    }

    fun addNote(text: String) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading = true) }
            try {
                addNoteUseCase(text)
                val notes = getNotesUseCase()
                _uiState.update { state ->
                    state.copy(loading = false, notes = notes, showDialog = false, text = "")
                }
            } catch (error: Exception) {
                _uiState.update { state -> state.copy(loading = false, error = error.message) }
            }
        }
    }

    fun onTextChanged(text: String) {
        _uiState.update { state ->  state.copy(text = text) }
    }

    fun onShowDialogChanged(showDialog: Boolean) {
        _uiState.update { state -> state.copy(showDialog = showDialog) }
    }
}

data class NotesUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val text: String = "",
    val showDialog: Boolean = false,
    val notes: List<Note> = emptyList()
)
