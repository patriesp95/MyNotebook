package com.patrimesp.mynotebook.domain.usecase

import com.patrimesp.mynotebook.domain.entity.Phrase
import com.patrimesp.mynotebook.domain.repository.PhraseRepository
import javax.inject.Inject

class GetRandomPhraseUseCase @Inject constructor(val repository: PhraseRepository){
    suspend operator fun invoke(id: Int): Phrase {
        return repository.getPhraseById(id)
    }
}