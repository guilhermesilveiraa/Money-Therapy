package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneytherapy.ui.componentsUI.GoalDetailCard
import com.example.moneytherapy.ui.componentsUI.NavBar
import com.example.moneytherapy.ui.viewModel.ThemeViewModel
import com.example.moneytherapy.ui.componentsUI.ThemeDialog
import com.example.moneytherapy.ui.viewModel.HomeViewModel

/**
 * Main home screen of the Money Therapy application.
 * Displays all goals categorized by their terms and provides navigation to other screens.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToInsertGoal: () -> Unit,
    onNavigateToEditGoal: (Long) -> Unit, // Updated to accept Long type for goalId
    viewModel: HomeViewModel = hiltViewModel(),
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showThemeDialog by remember { mutableStateOf(false) }

    // Get the goals from uiState
    val shortTermGoals = uiState.shortTermGoals
    val mediumTermGoals = uiState.mediumTermGoals
    val longTermGoals = uiState.longTermGoals

    // Show theme dialog when needed
    if (showThemeDialog) {
        ThemeDialog(
            onDismiss = { showThemeDialog = false },
            onThemeSelected = { newTheme ->
                themeViewModel.setThemeMode(newTheme)
                showThemeDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onThemeClick = { showThemeDialog = true }
            )
        },
        bottomBar = { NavBar() },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    onThemeClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val title = "Terapia Financeira"

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu de opções",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Tema") },
                    onClick = {
                        expanded = false
                        onThemeClick()
                    }
                )
            }
        }
    )
}