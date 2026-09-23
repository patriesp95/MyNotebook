package com.patrimesp.mynotebook.domain.usecase.phrases

import com.patrimesp.mynotebook.domain.entity.phrases.Phrase
import com.patrimesp.mynotebook.domain.repository.phrases.PhraseRepository
import javax.inject.Inject

class GetRandomPhraseUseCase @Inject constructor(val repository: PhraseRepository){
    suspend operator fun invoke(id: Int): Phrase {
        return repository.getPhraseById(id)
    }
}