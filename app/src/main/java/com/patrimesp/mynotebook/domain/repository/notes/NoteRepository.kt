package com.patrimesp.mynotebook.domain.repository.notes

import com.patrimesp.mynotebook.domain.entity.notes.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    val notes: Flow<List<Note>>

    suspend fun refreshNotes()
    suspend fun addNote(note: Note): Note
    suspend fun deleteNote(id: String)
}
