package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneytherapy.ui.componentsUI.GoalDetailCard
import com.example.moneytherapy.ui.componentsUI.NavBar
import com.example.moneytherapy.ui.viewModel.ThemeViewModel
import com.example.moneytherapy.ui.componentsUI.ThemeDialog
import com.example.moneytherapy.ui.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToInsertGoal: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    // Add ThemeViewModel as a parameter with default value
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    // Collect states from both ViewModels
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
        // Update HomeTopAppBar to include the theme click handler
        topBar = {
            HomeTopAppBar(
                onThemeClick = { showThemeDialog = true }
            )
        },
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
            // Render short term goals
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

            // Render medium term goals
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

            // Render long term goals
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

// The HomeTopAppBar is now correctly set up to handle theme switching
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
                color = MaterialTheme.colorScheme.onSurface,  // Updated to use theme colors
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu de opções",
                    tint = MaterialTheme.colorScheme.onSurface  // Updated to use theme colors
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