package com.example.moneytherapy.feature_components.theme.domain.usecases

import com.example.moneytherapy.feature_components.theme.domain.models.ThemeMode
import com.example.moneytherapy.feature_components.theme.domain.repository.ThemeRepository

class SetThemeModeUseCase(private val repository: ThemeRepository) {
    suspend operator fun invoke(mode: ThemeMode) = repository.setThemeMode(mode)
}
