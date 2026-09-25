package com.patrimesp.mynotebook.domain.usecase.notes

import com.patrimesp.mynotebook.domain.repository.notes.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(id: String) {
        repository.deleteNote(id)
    }
}
