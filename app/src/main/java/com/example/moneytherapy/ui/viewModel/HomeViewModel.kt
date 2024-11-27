package com.example.moneytherapy.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.feature_components.goals.domain.usecases.CreateGoalUseCase
import com.example.moneytherapy.feature_components.goals.domain.usecases.GetShortGoalUseCase
import com.example.moneytherapy.ui.states.HomeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val createGoalUseCase: CreateGoalUseCase,
    private val getShortGoalUseCase: GetShortGoalUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()
    val tabs = listOf("Investimentos", "Custos Fixos", "Sonhos")

    init {
        fetchShortTimeGoals()
    }

    // Função para buscar os objetivos de curto prazo
    private fun fetchShortTimeGoals() {
        viewModelScope.launch {
            try {
                val shortGoals = getShortGoalUseCase() // Chama o UseCase
                _uiState.update { currentState ->
                    currentState.copy(shortTermGoals = shortGoals)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onBottomItemCreateGoal(goal: Goals){
        viewModelScope.launch {
            createGoalUseCase(goal)
            fetchShortTimeGoals()
        }
    }

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