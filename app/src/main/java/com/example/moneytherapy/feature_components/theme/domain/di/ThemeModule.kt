package com.example.moneytherapy.feature_components.theme.domain.di

import com.example.moneytherapy.feature_components.theme.domain.repository.ThemeRepository
import com.example.moneytherapy.feature_components.theme.domain.usecases.GetThemeModeUseCase
import com.example.moneytherapy.feature_components.theme.domain.usecases.SetThemeModeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {

    @Provides
    @Singleton
    fun provideGetThemeModeUseCase(repository: ThemeRepository): GetThemeModeUseCase {
        return GetThemeModeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSetThemeModeUseCase(repository: ThemeRepository): SetThemeModeUseCase {
        return SetThemeModeUseCase(repository)
    }
}