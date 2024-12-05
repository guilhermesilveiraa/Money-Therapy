package com.example.moneytherapy.feature_components.theme.domain.usecases

import com.example.moneytherapy.feature_components.theme.domain.models.ThemeMode
import com.example.moneytherapy.feature_components.theme.domain.repository.ThemeRepository
import javax.inject.Inject

class GetThemeModeUseCase @Inject constructor(
    private val repository: ThemeRepository
) {
    suspend operator fun invoke(): ThemeMode = repository.getThemeMode()
}
