package com.example.moneytherapy.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.feature_components.goals.domain.usecases.*
import com.example.moneytherapy.ui.states.HomeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val createGoalUseCase: CreateGoalUseCase,
    private val updateGoalUseCase: UpdateGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase,
    private val getGoalByIdUseCase: GetGoalByIdUseCase,
    private val getShortGoalUseCase: GetShortGoalUseCase,
    private val getMediumGoalUseCase: GetMediumGoalUseCase,
    private val getLongGoalUseCase: GetLongGoalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    private val _selectedGoal = MutableStateFlow<Goals?>(null)
    val selectedGoal: StateFlow<Goals?> = _selectedGoal.asStateFlow()

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

    fun onBottomItemCreateGoal(goal: Goals) {
        viewModelScope.launch {
            createGoalUseCase(goal)
            fetchShortTimeGoals()
        }
    }

    fun onUpdateGoal(goal: Goals) {
        viewModelScope.launch {
            updateGoalUseCase(goal)
            // Refresh all goals lists to ensure UI is updated
            fetchShortTimeGoals()
            fetchMediumTimeGoals()
            fetchLongTimeGoals()
        }
    }

    fun onDeleteGoal(goalId: Long) {
        viewModelScope.launch {
            deleteGoalUseCase(goalId)
            // Refresh all goals lists after deletion
            fetchShortTimeGoals()
            fetchMediumTimeGoals()
            fetchLongTimeGoals()
        }
    }

    suspend fun getGoalById(goalId: Long): Goals? {
        return withContext(Dispatchers.IO) {
            getGoalByIdUseCase(goalId).also { goal ->
                _selectedGoal.value = goal
            }
        }
    }

    // Helper method to find a goal in current lists without database query
    fun findGoalInCurrentLists(goalId: Long): Goals? {
        return uiState.value.shortTermGoals.find { it.id == goalId }
            ?: uiState.value.mediumTermGoals.find { it.id == goalId }
            ?: uiState.value.longTermGoals.find { it.id == goalId }
    }

    fun onBottomNavItemSelected(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedBottomNavIndex = index
            )
        }
    }

    fun onTabSelected(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedTabIndex = index
            )
        }
    }

}