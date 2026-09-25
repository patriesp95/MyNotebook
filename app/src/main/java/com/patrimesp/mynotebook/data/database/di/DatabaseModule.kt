package com.patrimesp.mynotebook.data.database.di

import android.content.Context
import androidx.room.Room
import com.patrimesp.mynotebook.data.database.MyNotebookDatabase
import com.patrimesp.mynotebook.data.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideNotesDao(myNotebookDatabase: MyNotebookDatabase): NoteDao {
        return myNotebookDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideMyNotebookDatabase(@ApplicationContext appContext: Context): MyNotebookDatabase {
        return Room.databaseBuilder(appContext, MyNotebookDatabase::class.java, "MyNotebookDatabase").build()
    }
}