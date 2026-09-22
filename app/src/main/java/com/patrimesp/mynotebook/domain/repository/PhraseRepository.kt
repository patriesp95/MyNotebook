package com.patrimesp.mynotebook.domain.repository

import com.patrimesp.mynotebook.domain.entity.Phrase

interface PhraseRepository {
    suspend fun getPhraseById(id:Int): Phrase
}
