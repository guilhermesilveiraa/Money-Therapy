package com.example.moneytherapy.presentation.states

data class HomeTopAppBarUiState(
    val selectedBottomNavIndex: Int = 0,
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = true,
)