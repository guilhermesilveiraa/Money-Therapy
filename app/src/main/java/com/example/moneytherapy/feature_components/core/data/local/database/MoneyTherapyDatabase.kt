package com.example.moneytherapy.feature_components.core.data.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moneytherapy.feature_components.core.data.local.converters.DateConverters
import com.example.moneytherapy.feature_components.core.data.local.dao.CostsNoteDao
import com.example.moneytherapy.feature_components.core.data.local.dao.GoalsDao
import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.feature_components.goals.domain.models.Goals

@Database(
    entities = [Goals::class, CostsNote::class],
    exportSchema = false,
    version = 6
)

@TypeConverters(DateConverters::class)
abstract class MoneyTherapyDatabase: RoomDatabase() {
    abstract fun goalsDao() : GoalsDao
    abstract fun costsNoteDao() : CostsNoteDao
}
