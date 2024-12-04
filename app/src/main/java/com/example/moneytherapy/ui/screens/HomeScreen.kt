package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneytherapy.ui.componentsUI.CircularProgressIndicator
import com.example.moneytherapy.ui.componentsUI.GoalBox
import com.example.moneytherapy.ui.componentsUI.GoalDetailCard
import com.example.moneytherapy.ui.componentsUI.HomeTopAppBar
import com.example.moneytherapy.ui.componentsUI.NavBar
import com.example.moneytherapy.ui.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToInsertGoal: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val shortTermGoals = uiState.shortTermGoals
    val mediumTermGoals = uiState.mediumTermGoals
    val longTermGoals = uiState.longTermGoals

    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = { NavBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToInsertGoal() },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Inserir")
            }
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            shortTermGoals.forEach { goal ->
                goal.title?.let {
                    GoalDetailCard(
                        title = it,
                        category = "Curto Prazo",
                        currentValue = goal.value.toDouble(),
                        targetValue = goal.goal.toDouble(),
                        onEditClick = { viewModel.onEditGoal(goal.id) },
                        onDeleteClick = { viewModel.onDeleteGoal(goal.id) },
                        onAddValueClick = { viewModel.onAddValue(goal.id) }
                    )
                }
            }

            mediumTermGoals.forEach { goal ->
                goal.title?.let {
                    GoalDetailCard(
                        title = it,
                        category = "Médio Prazo",
                        currentValue = goal.value.toDouble(),
                        targetValue = goal.goal.toDouble(),
                        onEditClick = { viewModel.onEditGoal(goal.id) },
                        onDeleteClick = { viewModel.onDeleteGoal(goal.id) },
                        onAddValueClick = { viewModel.onAddValue(goal.id) }
                    )
                }
            }

            longTermGoals.forEach { goal ->
                goal.title?.let {
                    GoalDetailCard(
                        title = it,
                        category = "Longo Prazo",
                        currentValue = goal.value.toDouble(),
                        targetValue = goal.goal.toDouble(),
                        onEditClick = { viewModel.onEditGoal(goal.id) },
                        onDeleteClick = { viewModel.onDeleteGoal(goal.id) },
                        onAddValueClick = { viewModel.onAddValue(goal.id) }
                    )
                }
            }
        }
    }
}

/*
@Preview(showBackground = true, device = "spec:width=411dp,height=731dp,dpi=480")
@Composable
fun HomePreview(){
    MoneyTherapyTheme {
        HomeScreen(onNavigateToInsertGoal = {
            //navController.navigate("insertGoal")
        })
    }
}
*/



