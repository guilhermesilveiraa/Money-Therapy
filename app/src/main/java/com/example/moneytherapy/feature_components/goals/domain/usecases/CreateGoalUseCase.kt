package com.example.moneytherapy.feature_components.goals.domain.usecases

import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.feature_components.goals.domain.repository.GoalsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateGoalUseCase  @Inject constructor(private val repository: GoalsRepository){
    suspend operator fun invoke(goal:Goals){
        repository.saveGoal(goal)
    }

}