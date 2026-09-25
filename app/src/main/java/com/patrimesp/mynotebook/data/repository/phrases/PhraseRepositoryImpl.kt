package com.patrimesp.mynotebook.data.repository.phrases

import com.patrimesp.mynotebook.data.api.ApiService
import com.patrimesp.mynotebook.data.mapper.toDomain
import com.patrimesp.mynotebook.domain.entity.phrases.Phrase
import com.patrimesp.mynotebook.domain.repository.phrases.PhraseRepository
import javax.inject.Inject

class PhraseRepositoryImpl @Inject constructor(val api: ApiService): PhraseRepository {
    override suspend fun getPhraseById(id: Int): Phrase {
        return api.getPhraseById(id).toDomain()
    }
}