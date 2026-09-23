package com.patrimesp.mynotebook.domain.entity.phrases

import kotlinx.serialization.Serializable

@Serializable
data class Phrase(
    val author: String,
    val text: String
)