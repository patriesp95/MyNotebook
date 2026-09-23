package com.patrimesp.mynotebook.data.response.notes

import kotlinx.serialization.Serializable

@Serializable
data class NoteResponse(
    val name: String
)
