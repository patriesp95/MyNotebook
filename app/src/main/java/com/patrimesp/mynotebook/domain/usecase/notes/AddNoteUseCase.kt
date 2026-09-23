package com.patrimesp.mynotebook.domain.usecase.notes

import com.patrimesp.mynotebook.domain.entity.notes.Note
import com.patrimesp.mynotebook.domain.repository.notes.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(val repository: NoteRepository){
    suspend operator fun invoke(text: String): Note {
        return repository.addNote(
            Note(text = text)
        )
    }
}
