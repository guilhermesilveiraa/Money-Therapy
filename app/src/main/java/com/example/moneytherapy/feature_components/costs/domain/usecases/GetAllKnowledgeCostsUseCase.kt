package com.example.moneytherapy.feature_components.costs.domain.usecases

import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.feature_components.costs.domain.repository.CostsNoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllKnowledgeCostsUseCase @Inject constructor(
    val repository: CostsNoteRepository
) {
    suspend operator fun invoke() : Flow<List<CostsNote>> {
        return repository.getKnowledgeCosts()
    }
}