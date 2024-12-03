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
import com.example.moneytherapy.ui.componentsUI.HomeTopAppBar
import com.example.moneytherapy.ui.componentsUI.NavBar
import com.example.moneytherapy.ui.viewModel.HomeViewModel


@Composable
fun GoalBox(
    title: String,
    items: List<Pair<String?, Float>>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            if (items.isEmpty()) {
                Text(
                    text = "Nenhum objetivo encontrado",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                items.forEachIndexed { index, (itemName, progress) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = if (index < items.size - 1) 12.dp else 0.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = itemName ?: "Sem título",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = "${(progress * 100).toInt()}%",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        CircularProgressIndicator(
                            progress = progress,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                    }
                }
            }
        }
    }
}

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
            GoalBox(
                title = "Objetivos de Curto Prazo",
                items = shortTermGoals.map { goal ->
                    "${goal.title} (R$ ${goal.value}/${goal.goal})" to
                            (goal.value.toFloat() / goal.goal.toFloat()).coerceIn(0f, 1f)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoalBox(
                title = "Objetivos de Médio Prazo",
                items = mediumTermGoals.map { goal ->
                    "${goal.title} (R$ ${goal.value}/${goal.goal})" to
                            (goal.value.toFloat() / goal.goal.toFloat()).coerceIn(0f, 1f)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoalBox(
                title = "Objetivos de Longo Prazo",
                items = longTermGoals.map { goal ->
                    "${goal.title} (R$ ${goal.value}/${goal.goal})" to
                            (goal.value.toFloat() / goal.goal.toFloat()).coerceIn(0f, 1f)
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



