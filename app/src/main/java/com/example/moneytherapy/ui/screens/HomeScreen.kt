package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneytherapy.ui.componentsUI.HomeTopAppBar
import com.example.moneytherapy.ui.componentsUI.NavBar
import com.example.moneytherapy.ui.viewModel.HomeViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


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
                onClick = {
                    onNavigateToInsertGoal()
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = contentColorFor(MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Inserir")
            }
        },
        modifier = modifier
            .padding(0.dp)
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            GoalBox(
                title = "Curto Prazo",
                items = shortTermGoals.map { goal ->
                    goal.title to (goal.value.toFloat() / goal.goal.toFloat()).coerceIn(0f, 1f)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoalBox(
                title = "Médio Prazo",
                items = mediumTermGoals.map { goal ->
                    goal.title to (goal.value.toFloat() / goal.goal.toFloat()).coerceIn(0f, 1f)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoalBox(
                title = "Longo Prazo",
                items = longTermGoals.map { goal ->
                    goal.title to (goal.value.toFloat() / goal.goal.toFloat()).coerceIn(0f, 1f)
                }
            )
        }
    }
}

@Composable
fun GoalBox(
    title: String,
    items: List<Pair<String?, Float>>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (items.isEmpty()) {
                Text(
                    text = "Nenhum objetivo encontrado",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            } else {
                items.forEach { (itemName, progress) ->
                    Column(
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = itemName ?: "Sem título",
                            style = MaterialTheme.typography.bodyMedium,
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        LinearProgressIndicator(
                            progress = progress,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = if (progress < 0.5f) Color.Red else Color.Green
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
fun HomePreview(){
    MoneyTherapyTheme {
        HomeScreen(onNavigateToInsertGoal = {
            //navController.navigate("insertGoal")
        })
    }
}
*/

