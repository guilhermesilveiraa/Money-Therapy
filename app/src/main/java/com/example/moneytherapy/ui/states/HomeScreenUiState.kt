package com.example.moneytherapy.ui.states

import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class HomeScreenUiState(
    val selectedBottomNavIndex: Int = 0,
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = true,
    val shortTermGoals: List<Goals> = emptyList(),
    val mediumTermGoals: List<Goals> = emptyList(),
    val longTermGoals: List<Goals> = emptyList()
)