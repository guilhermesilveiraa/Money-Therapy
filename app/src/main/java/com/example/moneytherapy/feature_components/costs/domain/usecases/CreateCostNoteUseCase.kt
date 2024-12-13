package com.example.moneytherapy.feature_components.costs.domain.usecases

import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.feature_components.costs.domain.repository.CostsNoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateCostNoteUseCase @Inject constructor(private val repository: CostsNoteRepository){
    suspend operator fun invoke(costNote:CostsNote) {
        repository.saveCostsNote(costNote)
    }
}