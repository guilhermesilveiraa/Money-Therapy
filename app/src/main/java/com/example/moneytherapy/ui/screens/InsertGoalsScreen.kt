package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.ui.componentsUI.HomeTopAppBar
import com.example.moneytherapy.ui.componentsUI.NavBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertGoalsScreen(
    modifier: Modifier = Modifier,
    onSaveGoal: (Goals) -> Unit,
    navController: NavController,
    onNavigateBack: () -> Boolean
) {
    var goalId by remember { mutableLongStateOf(0L) }
    var goalTitle by remember { mutableStateOf("") }
    var goalValue by remember { mutableIntStateOf(0) }
    var achievedValue by remember { mutableIntStateOf(0) }
    var goalType by remember { mutableStateOf("Curto Prazo") }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = { NavBar(navController = navController) },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp),
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
                        text = "Novo Objetivo",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = goalTitle,
                        onValueChange = { goalTitle = it },
                        label = { Text("Título do Objetivo") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
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

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {expanded=it},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = goalType,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Tipo do Objetivo") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {expanded=false}
                        ) {
                            listOf("Curto Prazo", "Médio Prazo", "Longo Prazo").forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        goalType = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            if (goalTitle.isNotBlank() && goalValue > 0) {
                                val newGoals = Goals(
                                    id = goalId,
                                    title = goalTitle,
                                    value = achievedValue,
                                    goal = goalValue,
                                    type = goalType
                                )
                                onSaveGoal(newGoals)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            "Salvar Objetivo",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}
/*
@Preview(showBackground = true, device = "spec:width=411dp,height=731dp,dpi=480")
@Composable
fun InsertGoalsPreview(){
    MoneyTherapyTheme {
        InsertGoalsScreen(onSaveGoal = { goal ->
            // Função de salvar objetivo passada como parâmetro
        })
    }
}*/