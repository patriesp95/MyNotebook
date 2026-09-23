package com.patrimesp.mynotebook.domain.mapper

import com.patrimesp.mynotebook.data.request.notes.NoteRequest
import com.patrimesp.mynotebook.domain.entity.notes.Note


fun Note.toData(): NoteRequest {
    return NoteRequest(
        text = text
    )
}
