package com.example.moneytherapy.presentation.states

data class HomeScreenUiState(
    val selectedBottomNavIndex: Int = 0,
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = true,
)
