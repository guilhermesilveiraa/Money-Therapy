// EditGoalScreen.kt
package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.text.input.KeyboardType
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.ui.componentsUI.HomeTopAppBar
import com.example.moneytherapy.ui.componentsUI.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGoalScreen(
    goal: Goals,
    onUpdateGoal: (Goals) -> Unit,
    onDeleteGoal: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var goalTitle by remember { mutableStateOf(goal.title ?: "") }
    var goalValue by remember { mutableIntStateOf(goal.goal) }
    var achievedValue by remember { mutableIntStateOf(goal.value) }

    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = { NavBar() },
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
                    Text(
                        text = "Editar Objetivo",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

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
                        leadingIcon = { Text("R$", modifier = Modifier.padding(start = 12.dp)) }
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
                        leadingIcon = { Text("R$", modifier = Modifier.padding(start = 12.dp)) }
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                val updatedGoal = goal.copy(
                                    title = goalTitle,
                                    goal = goalValue,
                                    value = achievedValue
                                )
                                onUpdateGoal(updatedGoal)
                                onNavigateBack()
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("Salvar Alterações")
                        }

                        Button(
                            onClick = {
                                onDeleteGoal(goal.id)
                                onNavigateBack()
                            },
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


// DeleteGoalUseCase.kt
package com.example.moneytherapy.feature_components.goals.domain.usecases











// Update AppNavGraph.kt
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = "home",
    onSaveGoal: (Goals) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // ... existing routes ...


        }
    }
}