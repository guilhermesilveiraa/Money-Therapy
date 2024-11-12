package com.example.moneytherapy.feature_components.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(goal: Goals)

    @Query("SELECT * FROM Goals  WHERE type = 1 LIMIT 3")
    fun getAllShortGoals() : Flow<List<Goals>>

    @Query("SELECT * FROM Goals  WHERE type = 2 LIMIT 3")
    fun getAllMediumGoals(): Flow<List<Goals>>

    @Query("SELECT * FROM Goals WHERE type = 3 LIMIT 10")
    fun getAllLargeGoals(): Flow<List<Goals>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(goal: Goals)
}