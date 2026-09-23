package com.patrimesp.mynotebook.data.repository.notes

import com.patrimesp.mynotebook.data.datasource.api.ApiService
import com.patrimesp.mynotebook.domain.entity.notes.Note
import com.patrimesp.mynotebook.domain.mapper.toData
import com.patrimesp.mynotebook.domain.repository.notes.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(val api: ApiService): NoteRepository {
    override suspend fun addNote(note: Note): Note {
        val response = api.addNote(note.toData())
        return note.copy(id = response.name)
    }
}
