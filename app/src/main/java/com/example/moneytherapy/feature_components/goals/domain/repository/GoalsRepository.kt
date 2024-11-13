package com.example.moneytherapy.feature_components.goals.domain.repository

import com.example.moneytherapy.feature_components.goals.domain.models.Goals

interface GoalsRepository {

    /**
     * Save the Goals in the database
     */
    suspend fun saveGoal(goal: Goals)

    /**
     * Get the short-term goals
     */
    suspend fun getShortTimeGoals(): List<Goals>

    /**
     * Get the medium-term goals
     */
    suspend fun getMediumTimeGoals(): List<Goals>

    /**
     * Get the long-term goals
     */
    suspend fun getLongTimeGoals(): List<Goals>

    /**
     * Update the goals in the database
     */
    suspend fun updateGoal(goal: Goals)
}
