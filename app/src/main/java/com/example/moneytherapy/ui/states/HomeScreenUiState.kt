package com.example.moneytherapy.ui.states

import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import kotlinx.coroutines.flow.Flow

data class HomeScreenUiState(
    val selectedBottomNavIndex: Int = 0,
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = true,
    val shortTermGoals: Flow<List<Goals>> = emptyList() // Adicione esta propriedade
)
