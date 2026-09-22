package com.patrimesp.mynotebook.data.response

import kotlinx.serialization.Serializable

@Serializable
data class PhraseResponse (
    val author: String,
    val text: String
)
