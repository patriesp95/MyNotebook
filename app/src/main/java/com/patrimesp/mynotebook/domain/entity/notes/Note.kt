package com.patrimesp.mynotebook.domain.entity.notes

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val text: String,
    val id: String = ""
)
