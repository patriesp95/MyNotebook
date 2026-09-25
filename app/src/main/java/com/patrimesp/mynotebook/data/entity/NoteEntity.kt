package com.patrimesp.mynotebook.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    val text: String,
    @PrimaryKey val id: String = ""
)
