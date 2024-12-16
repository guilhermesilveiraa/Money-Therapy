package com.example.moneytherapy.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moneytherapy.feature_components.costs.domain.usecases.GetAllMonthCostNoteUseCase
import com.example.moneytherapy.ui.states.CostNoteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CostsExcelViewModel @Inject constructor(
    private val getAllMonthCostNoteUseCase : GetAllMonthCostNoteUseCase,
) : ViewModel(){

    private val _uiState = MutableStateFlow(CostNoteUiState())
    val uiState: StateFlow<CostNoteUiState> = _uiState.asStateFlow()

    init {
        fetchAllMonthCosts()
    }


    private fun fetchAllMonthCosts() {
        viewModelScope.launch {
            getAllMonthCostNoteUseCase().collect {costNotes->
                _uiState.update { currentState ->
                    Log.d("ViewModel", "All monthly costs: $costNotes")
                    currentState.copy(
                        monthCosts = costNotes
                    )
                }
            }
        }
    }

}