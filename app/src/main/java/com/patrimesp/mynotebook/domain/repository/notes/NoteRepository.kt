package com.patrimesp.mynotebook.domain.repository.notes

import com.patrimesp.mynotebook.domain.entity.notes.Note

interface NoteRepository {
    suspend fun addNote(note: Note): Note
    suspend fun getNotes(): List<Note>
    suspend fun deleteNote(id: String)
}
