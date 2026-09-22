package com.patrimesp.mynotebook.data.repository

import com.patrimesp.mynotebook.data.datasource.api.ApiService
import com.patrimesp.mynotebook.data.mapper.toDomain
import com.patrimesp.mynotebook.domain.entity.Phrase
import com.patrimesp.mynotebook.domain.repository.PhraseRepository
import javax.inject.Inject

class PhraseRepositoryImpl @Inject constructor(val api: ApiService): PhraseRepository {
    override suspend fun getPhraseById(id: Int): Phrase {
        return api.getPhraseById(id).toDomain()
    }
}