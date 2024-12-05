package com.example.moneytherapy.feature_components.theme.domain.repository

import com.example.moneytherapy.feature_components.theme.domain.models.ThemeMode

interface ThemeRepository {
    suspend fun getThemeMode() : ThemeMode
    suspend fun setThemeMode(mode: ThemeMode)
}
