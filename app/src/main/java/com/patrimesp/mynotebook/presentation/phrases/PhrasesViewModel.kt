package com.patrimesp.mynotebook.presentation.phrases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrimesp.mynotebook.domain.usecase.GetRandomPhraseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhrasesViewModel @Inject constructor(val getRandomPhraseUseCase: GetRandomPhraseUseCase): ViewModel() {
    private val _uiState = MutableStateFlow(PhrasesUiState())
    val uiState: StateFlow<PhrasesUiState> = _uiState.asStateFlow()

    fun onScreenTapped(id: Int) {
        _uiState.update { state -> state.copy(loading = true) }
        viewModelScope.launch {
            try {
                val phrase = getRandomPhraseUseCase(id)
                _uiState.update { state -> state.copy(loading = false, author = phrase.author, text = phrase.text )}
            } catch(error: Exception){
                _uiState.update { state -> state.copy(loading = false, error = error.message) }
            }
        }
    }
}

data class PhrasesUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val author: String = "Sócrates",
    val text: String = "Solo sé que no sé nada."
)