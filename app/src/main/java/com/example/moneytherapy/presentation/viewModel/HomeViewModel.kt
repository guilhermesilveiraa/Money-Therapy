package com.example.moneytherapy.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.moneytherapy.presentation.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    val tabs = listOf("Investimentos", "Custos Fixos", "Sonhos")

    // Atualiza o estado da aba inferior selecionada
    fun onBottomNavItemSelected(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedBottomNavIndex = index
            )
        }
    }

    // Atualiza o estado da aba superior
    fun onTabSelected(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedTabIndex = index
            )
        }
    }

}