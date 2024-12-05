package com.example.moneytherapy.feature_components.theme.domain.usecases

import com.example.moneytherapy.feature_components.theme.domain.models.ThemeMode
import com.example.moneytherapy.feature_components.theme.domain.repository.ThemeRepository
import javax.inject.Inject

class SetThemeModeUseCase @Inject constructor(
    private val repository: ThemeRepository
) {
    suspend operator fun invoke(mode: ThemeMode) = repository.setThemeMode(mode)
}