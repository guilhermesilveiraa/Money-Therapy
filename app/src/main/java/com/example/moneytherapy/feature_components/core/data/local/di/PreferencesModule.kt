package com.example.moneytherapy.feature_components.core.data.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.*
import com.example.moneytherapy.feature_components.core.data.local.repository.ThemeRepositoryImpl
import com.example.moneytherapy.feature_components.theme.domain.repository.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    // Name for our DataStore preferences file
    private const val USER_PREFERENCES = "user_preferences"

    // Provides DataStore instance
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }

    // Provides ThemeRepository implementation
    @Provides
    @Singleton
    fun provideThemeRepository(dataStore: DataStore<Preferences>): ThemeRepository {
        // Here we bind the implementation to the interface
        return ThemeRepositoryImpl(dataStore)
    }
}