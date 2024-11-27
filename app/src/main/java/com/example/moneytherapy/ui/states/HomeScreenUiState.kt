package com.example.moneytherapy.ui.states

data class HomeScreenUiState(
    val selectedBottomNavIndex: Int = 0,
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = true,
    val shortTermGoals: List<Any> = emptyList()
)
