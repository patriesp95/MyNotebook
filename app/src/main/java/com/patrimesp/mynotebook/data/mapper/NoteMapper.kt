package com.patrimesp.mynotebook.data.mapper

import com.patrimesp.mynotebook.data.response.notes.NoteDataResponse
import com.patrimesp.mynotebook.domain.entity.notes.Note

fun NoteDataResponse.toDomain(id: String): Note {
    return Note(
        id = id,
        text = text
    )
}
