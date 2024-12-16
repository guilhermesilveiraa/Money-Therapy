package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moneytherapy.ui.componentsUI.*
import com.example.moneytherapy.ui.viewModel.*

/**
 * Main home screen of the Money Therapy application.
 * Displays all goals categorized by their terms and provides navigation to other screens.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToInsertGoal: () -> Unit,
    onNavigateToEditGoal: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    themeViewModel: ThemeViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    val themeMode by themeViewModel.themeMode.collectAsState()

    // Get the goals from uiState
    val shortTermGoals = uiState.shortTermGoals
    val mediumTermGoals = uiState.mediumTermGoals
    val longTermGoals = uiState.longTermGoals


    Scaffold(
        topBar = {
            HomeTopAppBar()
        },
        bottomBar = { NavBar(navController = navController)},
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToInsertGoal,
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
            // Render short term goals with proper navigation handling
            shortTermGoals.forEach { goal ->
                goal.title?.let {
                    GoalDetailCard(
                        title = it,
                        category = "Curto Prazo",
                        currentValue = goal.value.toDouble(),
                        targetValue = goal.goal.toDouble(),
                        onEditClick = { onNavigateToEditGoal(goal.id) }, // Navigate to edit screen
                        onDeleteClick = { viewModel.onDeleteGoal(goal.id) },
                        onAddValueClick = { TODO() }
                    )
                }
            }

            // Render medium term goals with proper navigation handling
            mediumTermGoals.forEach { goal ->
                goal.title?.let {
                    GoalDetailCard(
                        title = it,
                        category = "Médio Prazo",
                        currentValue = goal.value.toDouble(),
                        targetValue = goal.goal.toDouble(),
                        onEditClick = { onNavigateToEditGoal(goal.id) }, // Navigate to edit screen
                        onDeleteClick = { viewModel.onDeleteGoal(goal.id) },
                        onAddValueClick = { TODO() }
                    )
                }
            }

            // Render long term goals with proper navigation handling
            longTermGoals.forEach { goal ->
                goal.title?.let {
                    GoalDetailCard(
                        title = it,
                        category = "Longo Prazo",
                        currentValue = goal.value.toDouble(),
                        targetValue = goal.goal.toDouble(),
                        onEditClick = { onNavigateToEditGoal(goal.id) }, // Navigate to edit screen
                        onDeleteClick = { viewModel.onDeleteGoal(goal.id) },
                        onAddValueClick = { TODO() }
                    )
                }
            }
        }
    }
}

