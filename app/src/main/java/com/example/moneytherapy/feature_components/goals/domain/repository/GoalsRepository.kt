package com.example.moneytherapy.feature_components.goals.domain.repository

import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import kotlinx.coroutines.flow.Flow

interface GoalsRepository {

    /**
     * Save the Goals in the database
     */
    suspend fun saveGoal(goal: Goals)

    /**
     * Get the short-term goals
     */

    suspend fun getShortTimeGoals(): Flow<List<Goals>>

    /**
     * Get the medium-term goals
     */
    suspend fun getMediumTimeGoals(): Flow<List<Goals>>

    /**
     * Get the long-term goals
     */
    suspend fun getLongTimeGoals(): Flow<List<Goals>>

    /**
     * Update the goals in the database
     */
    suspend fun updateGoal(goal: Goals)

    suspend fun deleteGoal(goalId: Long)

    suspend fun getGoalById(goalId: Long): Goals?

}
