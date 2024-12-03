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

    // Coletar os objetivos de forma síncrona
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
        modifier = modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Curto Prazo
            GoalBox(
                title = "Objetivos de Curto Prazo",
                items = shortTermGoals.map { goal ->
                    "${goal.title} (R$ ${goal.value}/${goal.goal})" to
                            (goal.value.toFloat() / goal.goal.toFloat()).coerceIn(0f, 1f)
                },
                onAddValueClick = { goalName ->
                    // TODO: Implementar lógica para adicionar valor
                    println("TODO: Adicionar valor para o objetivo $goalName de curto prazo")
                },
                onEditClick = { goalName ->
                    // TODO: Implementar lógica para editar
                    println("TODO: Editar o objetivo $goalName de curto prazo")
                },
                onDeleteClick = { goalName ->
                    // TODO: Implementar lógica para deletar
                    println("TODO: Deletar o objetivo $goalName de curto prazo")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Médio Prazo
            GoalBox(
                title = "Objetivos de Médio Prazo",
                items = mediumTermGoals.map { goal ->
                    "${goal.title} (R$ ${goal.value}/${goal.goal})" to
                            (goal.value.toFloat() / goal.goal.toFloat()).coerceIn(0f, 1f)
                },
                onAddValueClick = { goalName ->
                    // TODO: Implementar lógica para adicionar valor
                    println("TODO: Adicionar valor para o objetivo $goalName de médio prazo")
                },
                onEditClick = { goalName ->
                    // TODO: Implementar lógica para editar
                    println("TODO: Editar o objetivo $goalName de médio prazo")
                },
                onDeleteClick = { goalName ->
                    // TODO: Implementar lógica para deletar
                    println("TODO: Deletar o objetivo $goalName de médio prazo")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Longo Prazo
            GoalBox(
                title = "Objetivos de Longo Prazo",
                items = longTermGoals.map { goal ->
                    "${goal.title} (R$ ${goal.value}/${goal.goal})" to
                            (goal.value.toFloat() / goal.goal.toFloat()).coerceIn(0f, 1f)
                },
                onAddValueClick = { goalName ->
                    // TODO: Implementar lógica para adicionar valor
                    println("TODO: Adicionar valor para o objetivo $goalName de longo prazo")
                },
                onEditClick = { goalName ->
                    // TODO: Implementar lógica para editar
                    println("TODO: Editar o objetivo $goalName de longo prazo")
                },
                onDeleteClick = { goalName ->
                    // TODO: Implementar lógica para deletar
                    println("TODO: Deletar o objetivo $goalName de longo prazo")
                }
            )
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



