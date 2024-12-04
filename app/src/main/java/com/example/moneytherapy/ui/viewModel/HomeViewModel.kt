package com.example.moneytherapy.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.feature_components.goals.domain.usecases.CreateGoalUseCase
import com.example.moneytherapy.feature_components.goals.domain.usecases.GetLongGoalUseCase
import com.example.moneytherapy.feature_components.goals.domain.usecases.GetMediumGoalUseCase
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
    private val getShortGoalUseCase: GetShortGoalUseCase,
    private val getMediumGoalUseCase: GetMediumGoalUseCase,
    private val getLongGoalUseCase: GetLongGoalUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState
    val tabs = listOf("Investimentos", "Custos Fixos", "Sonhos")

    init {
        fetchShortTimeGoals()
        fetchMediumTimeGoals()
        fetchLongTimeGoals()
    }

    private fun fetchShortTimeGoals() {
        viewModelScope.launch {
            getShortGoalUseCase().collect { goals ->
                _uiState.update { currentState ->
                    Log.d("ViewModel", "Short-term goals: $goals")
                    currentState.copy(
                        isLoading = false,
                        shortTermGoals = goals
                    )
                }
            }
        }
    }

    private fun fetchMediumTimeGoals() {
        viewModelScope.launch {
            getMediumGoalUseCase().collect { goals ->
                _uiState.update { currentState ->
                    Log.d("ViewModel", "Medium-term goals: $goals")
                    currentState.copy(
                        isLoading = false,
                        mediumTermGoals = goals
                    )
                }
            }
        }
    }

    private fun fetchLongTimeGoals() {
        viewModelScope.launch {
            getLongGoalUseCase().collect { goals ->
                _uiState.update { currentState ->
                    Log.d("ViewModel", "Long-term goals: $goals")
                    currentState.copy(
                        isLoading = false,
                        longTermGoals = goals
                    )
                }
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

    fun onEditGoal(id: Long) {

    }

    fun onDeleteGoal(id: Long) {

    }

    fun onAddValue(id: Long) {

    }


}