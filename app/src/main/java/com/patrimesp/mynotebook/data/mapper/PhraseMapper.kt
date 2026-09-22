package com.patrimesp.mynotebook.data.mapper

import com.patrimesp.mynotebook.data.response.PhraseResponse
import com.patrimesp.mynotebook.domain.entity.Phrase

fun PhraseResponse.toDomain(): Phrase {
    return Phrase(
        author = author,
        text = text
    )
}