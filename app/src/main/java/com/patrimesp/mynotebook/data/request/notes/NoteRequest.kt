package com.patrimesp.mynotebook.data.request.notes

import kotlinx.serialization.Serializable

@Serializable
data class NoteRequest(
    val text: String
)
