package com.example.moneytherapy.feature_components.core.data.local.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.moneytherapy.feature_components.theme.domain.models.ThemeMode
import com.example.moneytherapy.feature_components.theme.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
) : ThemeRepository {
    private val THEME_KEY = stringPreferencesKey("theme_mode")

    override suspend fun getThemeMode(): ThemeMode {
        return dataStore.data.map { preferences ->
            ThemeMode.valueOf(preferences[THEME_KEY] ?: ThemeMode.SYSTEM.name)
        }.first()
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = mode.name
        }
    }
}

/*
    O metodo getThemeMode pode ser destrinchado da seguinte forma:

        dataStore.data = retorna um flow
        map { preferences - > } = transforma cada preferência num objeto
        preferences[THEME_KEY] = recupera a string salva
        ?: ThemeMoe.valueOf() = converte a String para o enum
        .first() =  pega o valor atual provindo do flow
*/

