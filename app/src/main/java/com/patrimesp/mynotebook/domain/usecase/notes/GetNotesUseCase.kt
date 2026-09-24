package com.patrimesp.mynotebook.domain.usecase.notes

import com.patrimesp.mynotebook.domain.entity.notes.Note
import com.patrimesp.mynotebook.domain.repository.notes.NoteRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(val repository: NoteRepository) {
    suspend operator fun invoke(): List<Note> {
        return repository.getNotes()
    }
}
