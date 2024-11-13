package com.example.moneytherapy.feature_components.goals.domain.usecases

import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.feature_components.goals.domain.repository.GoalsRepository

class CreateGoalUseCase(private val repository: GoalsRepository) {
    suspend operator fun invoke(goal:Goals){
        repository.saveGoal(goal)
    }





}