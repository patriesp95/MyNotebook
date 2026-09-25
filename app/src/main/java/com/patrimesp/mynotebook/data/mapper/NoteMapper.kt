package com.patrimesp.mynotebook.data.mapper

import com.patrimesp.mynotebook.data.entity.NoteEntity
import com.patrimesp.mynotebook.data.request.notes.NoteRequest
import com.patrimesp.mynotebook.data.response.notes.NoteDataResponse
import com.patrimesp.mynotebook.domain.entity.notes.Note

fun NoteDataResponse.toDomain(id: String): Note {
    return Note(
        id = id,
        text = text
    )
}

fun NoteEntity.toDomain(): Note {
    return Note(
        id = id,
        text = text
    )
}

fun Note.toEntity(): NoteEntity = NoteEntity(
    id = id,
    text = text
)

fun Note.toRequest(): NoteRequest = NoteRequest(text = text)
