package com.patrimesp.mynotebook.domain.repository.phrases

import com.patrimesp.mynotebook.domain.entity.phrases.Phrase

interface PhraseRepository {
    suspend fun getPhraseById(id:Int): Phrase
}