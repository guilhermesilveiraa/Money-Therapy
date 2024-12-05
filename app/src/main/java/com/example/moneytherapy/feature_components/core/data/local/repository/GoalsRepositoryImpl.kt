package com.example.moneytherapy.feature_components.core.data.local.repository
import com.example.moneytherapy.feature_components.core.data.local.dao.GoalsDao
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.feature_components.goals.domain.repository.GoalsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoalsRepositoryImpl  @Inject constructor(
    private val goalsDao: GoalsDao
): GoalsRepository {

    override suspend fun saveGoal(goal: Goals){
        goalsDao.save(goal)
    }
    override suspend fun getShortTimeGoals(): Flow<List<Goals>> {
        return goalsDao.getAllShortGoals()
    }

    override suspend fun getMediumTimeGoals(): Flow<List<Goals>> {
        return goalsDao.getAllMediumGoals()
    }

    override suspend fun getLongTimeGoals(): Flow<List<Goals>> {
        return goalsDao.getAllLargeGoals()
    }

    override suspend fun updateGoal(goal: Goals){
        goalsDao.update(goal)
    }

    override suspend fun deleteGoal(goalId: Long) {
        goalsDao.deleteById(goalId)
    }
}