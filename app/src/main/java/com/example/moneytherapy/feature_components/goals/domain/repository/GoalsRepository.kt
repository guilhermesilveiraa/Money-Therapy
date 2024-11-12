package com.example.moneytherapy.feature_components.goals.domain.repository

import com.example.moneytherapy.feature_components.goals.domain.models.Goals

interface GoalsRepository {

    /**
     * Save the Goals in database
     */
    suspend fun saveGoals(goal:Goals)

    /**
     * Get the short time goals
     */
    suspend fun getShort()

}