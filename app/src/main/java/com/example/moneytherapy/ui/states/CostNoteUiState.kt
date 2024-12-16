package com.example.moneytherapy.ui.states

import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote

data class CostNoteUiState(
    val monthCosts : List<CostsNote> = emptyList()
)
