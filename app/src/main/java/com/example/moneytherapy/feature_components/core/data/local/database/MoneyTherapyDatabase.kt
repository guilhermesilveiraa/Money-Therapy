package com.example.moneytherapy.feature_components.core.data.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moneytherapy.feature_components.core.data.local.dao.GoalsDao
import com.example.moneytherapy.feature_components.goals.domain.models.Goals

@Database(
    entities = [Goals::class],
    exportSchema = false,
    version = 4
)

abstract class MoneyTherapyDatabase: RoomDatabase() {
    abstract fun goalsDao() : GoalsDao
}