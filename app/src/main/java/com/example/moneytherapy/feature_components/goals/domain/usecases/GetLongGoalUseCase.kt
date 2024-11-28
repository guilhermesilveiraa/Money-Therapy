package com.example.moneytherapy.feature_components.goals.domain.usecases

import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.feature_components.goals.domain.repository.GoalsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLongGoalUseCase @Inject constructor(
    val repository: GoalsRepository
){
    suspend operator fun invoke(): Flow<List<Goals>> {
        return repository.getLongTimeGoals()
    }
}