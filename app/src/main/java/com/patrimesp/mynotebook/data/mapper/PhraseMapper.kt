package com.patrimesp.mynotebook.data.mapper

import com.patrimesp.mynotebook.data.response.phrases.PhraseResponse
import com.patrimesp.mynotebook.domain.entity.phrases.Phrase

fun PhraseResponse.toDomain(): Phrase {
    return Phrase(
        author = author,
        text = text
    )
}