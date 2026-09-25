package com.patrimesp.mynotebook.data.repository.notes

import com.patrimesp.mynotebook.data.api.ApiService
import com.patrimesp.mynotebook.data.database.datasource.local.NoteLocalDataSource
import com.patrimesp.mynotebook.data.mapper.toDomain
import com.patrimesp.mynotebook.data.mapper.toEntity
import com.patrimesp.mynotebook.data.mapper.toRequest
import com.patrimesp.mynotebook.domain.entity.notes.Note
import com.patrimesp.mynotebook.domain.repository.notes.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val noteLocalDataSource: NoteLocalDataSource
): NoteRepository {
    override val notes: Flow<List<Note>> = noteLocalDataSource.notes.map { entities ->
        entities.map { it.toDomain() }
    }

    override suspend fun refreshNotes() {
        val remoteNotes = api.getNotes()
            .orEmpty()
            .map { (id, response) -> response.toDomain(id).toEntity() }
        noteLocalDataSource.replaceAll(remoteNotes)
    }

    override suspend fun addNote(note: Note): Note {
        val response = api.addNote(note.toRequest())
        return note.copy(id = response.name).also { createdNote ->
            noteLocalDataSource.add(createdNote.toEntity())
        }
    }

    override suspend fun deleteNote(id: String) {
        api.deleteNote(id)
        noteLocalDataSource.delete(id)
    }
}
