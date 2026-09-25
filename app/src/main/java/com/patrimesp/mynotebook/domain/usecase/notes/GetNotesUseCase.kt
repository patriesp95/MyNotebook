package com.patrimesp.mynotebook.domain.usecase.notes

import com.patrimesp.mynotebook.domain.entity.notes.Note
import com.patrimesp.mynotebook.domain.repository.notes.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> = repository.notes

    suspend fun refresh() {
        repository.refreshNotes()
    }
}
