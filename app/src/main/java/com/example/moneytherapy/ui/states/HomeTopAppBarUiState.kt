package com.example.moneytherapy.ui.states

data class HomeTopAppBarUiState(
    val selectedBottomNavIndex: Int = 0,
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = true,
)
