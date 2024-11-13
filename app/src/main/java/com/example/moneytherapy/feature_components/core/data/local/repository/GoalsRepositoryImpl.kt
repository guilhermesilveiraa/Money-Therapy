package com.example.moneytherapy.feature_components.core.data.local.repository
import com.example.moneytherapy.feature_components.core.data.local.dao.GoalsDao
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import kotlinx.coroutines.flow.Flow

class GoalsRepositoryImpl(
    private val goalsDao: GoalsDao
) {
    suspend fun saveGoals(goal: Goals){
        goalsDao.save(goal)
    }
    suspend fun getShortTimeGoals(): Flow<List<Goals>> {
        return goalsDao.getAllShortGoals()
    }

    suspend fun getMediumTimeGoals(): Flow<List<Goals>>{
        return goalsDao.getAllMediumGoals()
    }

    suspend fun getLongTimeGoals(): Flow<List<Goals>>{
        return goalsDao.getAllLargeGoals()
    }

    suspend fun updateGoal(goal: Goals){
        goalsDao.update(goal)
    }
}