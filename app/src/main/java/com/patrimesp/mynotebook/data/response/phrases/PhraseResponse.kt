package com.patrimesp.mynotebook.data.response.phrases

import kotlinx.serialization.Serializable

@Serializable
data class PhraseResponse (
    val author: String,
    val text: String
)