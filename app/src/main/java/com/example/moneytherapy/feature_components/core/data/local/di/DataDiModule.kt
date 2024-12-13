package com.example.moneytherapy.feature_components.core.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.moneytherapy.feature_components.core.data.local.dao.CostsNoteDao
import com.example.moneytherapy.feature_components.core.data.local.dao.GoalsDao
import com.example.moneytherapy.feature_components.core.data.local.database.MoneyTherapyDatabase
import com.example.moneytherapy.feature_components.core.data.local.repository.CostsNoteRepositoryImpl
import com.example.moneytherapy.feature_components.core.data.local.repository.GoalsRepositoryImpl
import com.example.moneytherapy.feature_components.costs.domain.repository.CostsNoteRepository
import com.example.moneytherapy.feature_components.goals.domain.repository.GoalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDiModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MoneyTherapyDatabase {
        return Room.databaseBuilder(
            context,
            MoneyTherapyDatabase::class.java,
            "moneytherapy_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGoalsDao(database: MoneyTherapyDatabase): GoalsDao {
        return database.goalsDao()
    }

    @Provides
    @Singleton
    fun provideGoalsRepository(
        goalsDao: GoalsDao
    ): GoalsRepository {
        return GoalsRepositoryImpl(goalsDao)
    }

    @Provides
    @Singleton
    fun provideCostsNoteDao(database: MoneyTherapyDatabase) : CostsNoteDao {
        return database.costsNoteDao()
    }

    @Provides
    @Singleton
    fun provideCostsNoteRepository(
        costsNote: CostsNoteDao
    ) : CostsNoteRepository{
        return CostsNoteRepositoryImpl(costsNote)
    }

}
