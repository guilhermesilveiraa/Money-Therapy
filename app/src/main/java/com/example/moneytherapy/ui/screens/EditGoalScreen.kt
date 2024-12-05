package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.ui.viewModel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun EditGoalScreen(
    goalId: Long,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Create coroutine scope for launching coroutines from composables
    val scope = rememberCoroutineScope()

    // State management for the goal and loading state
    var goal by remember { mutableStateOf<Goals?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Load the goal when the screen is first displayed
    LaunchedEffect(goalId) {
        // First try to find the goal in current lists to avoid database query
        goal = viewModel.findGoalInCurrentLists(goalId)

        // If not found in current lists, fetch from database
        if (goal == null) {
            goal = viewModel.getGoalById(goalId)
        }

        isLoading = false

        // Navigate back if goal not found
        if (goal == null) {
            onNavigateBack()
        }
    }

    // Show loading indicator while fetching goal data
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        // Display goal content once loaded
        goal?.let { nonNullGoal ->
            EditGoalContent(
                goal = nonNullGoal,
                onUpdateGoal = { updatedGoal ->
                    scope.launch {
                        viewModel.onUpdateGoal(updatedGoal)
                        onNavigateBack()
                    }
                },
                onDeleteGoal = { id ->
                    scope.launch {
                        viewModel.onDeleteGoal(id)
                        onNavigateBack()
                    }
                },
                onNavigateBack = onNavigateBack,
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditGoalContent(
    goal: Goals,
    onUpdateGoal: (Goals) -> Unit,
    onDeleteGoal: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // State for form fields
    var goalTitle by remember { mutableStateOf(goal.title ?: "") }
    var goalValue by remember { mutableIntStateOf(goal.goal) }
    var achievedValue by remember { mutableIntStateOf(goal.value) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Objetivo") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    OutlinedTextField(
                        value = goalTitle,
                        onValueChange = { goalTitle = it },
                        label = { Text("Título do Objetivo") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    OutlinedTextField(
                        value = if (goalValue == 0) "" else goalValue.toString(),
                        onValueChange = {
                            goalValue = it.filter { char -> char.isDigit() }.toIntOrNull() ?: 0
                        },
                        label = { Text("Valor do Objetivo") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        leadingIcon = {
                            Text(
                                "R$",
                                modifier = Modifier.padding(start = 12.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    )

                    OutlinedTextField(
                        value = if (achievedValue == 0) "" else achievedValue.toString(),
                        onValueChange = {
                            achievedValue = it.filter { char -> char.isDigit() }.toIntOrNull() ?: 0
                        },
                        label = { Text("Valor Já Alcançado") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        leadingIcon = {
                            Text(
                                "R$",
                                modifier = Modifier.padding(start = 12.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    )

                    // Action buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Save button
                        Button(
                            onClick = {
                                val updatedGoal = goal.copy(
                                    title = goalTitle,
                                    goal = goalValue,
                                    value = achievedValue
                                )
                                onUpdateGoal(updatedGoal)
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("Salvar")
                        }

                        // Delete button
                        Button(
                            onClick = { onDeleteGoal(goal.id) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Deletar Objetivo"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Excluir")
                        }
                    }
                }
            }
        }
    }
}