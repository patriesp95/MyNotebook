package com.patrimesp.mynotebook.data.database.datasource.local

import com.patrimesp.mynotebook.data.database.dao.NoteDao
import com.patrimesp.mynotebook.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteLocalDataSource @Inject constructor(
    private val noteDao: NoteDao
) {
    val notes: Flow<List<NoteEntity>> = noteDao.getNotes()

    suspend fun add(note: NoteEntity) = noteDao.addNote(note)

    suspend fun replaceAll(notes: List<NoteEntity>) = noteDao.replaceAll(notes)

    suspend fun delete(id: String) {
        noteDao.deleteNote(id)
    }
}
