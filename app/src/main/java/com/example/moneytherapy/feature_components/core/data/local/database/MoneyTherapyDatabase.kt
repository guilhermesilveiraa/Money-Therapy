package com.example.moneytherapy.feature_components.core.data.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moneytherapy.feature_components.core.data.local.dao.CostsNoteDao
import com.example.moneytherapy.feature_components.core.data.local.dao.GoalsDao
import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.feature_components.goals.domain.models.Goals

@Database(
    entities = [Goals::class, CostsNote::class],
    exportSchema = false,
    version = 5
)

abstract class MoneyTherapyDatabase: RoomDatabase() {
    abstract fun goalsDao() : GoalsDao
    abstract fun costsNoteDao() : CostsNoteDao
}
