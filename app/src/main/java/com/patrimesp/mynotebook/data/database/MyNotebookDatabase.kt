package com.patrimesp.mynotebook.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patrimesp.mynotebook.data.database.dao.NoteDao
import com.patrimesp.mynotebook.data.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class MyNotebookDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
