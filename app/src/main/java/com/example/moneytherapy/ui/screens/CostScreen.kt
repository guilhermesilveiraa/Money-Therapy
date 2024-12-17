package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.ui.componentsUI.NavBar
import com.example.moneytherapy.ui.componentsUI.costs.CostCard
import com.example.moneytherapy.ui.componentsUI.costs.CostsSummaryCard
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme
import com.example.moneytherapy.ui.viewModel.CostsExcelViewModel
import java.time.LocalDateTime
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostsScreen(
    modifier: Modifier = Modifier,
    onNavigateToInsertCost: () -> Unit,
    navController: NavController,
    viewModel: CostsExcelViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedCostType by remember { mutableStateOf("Todos") }
    var showFilterDialog by remember { mutableStateOf(false) }
    val monthCosts = uiState.monthCosts

    val costTypes = listOf(
        "Todos",
        "Pessoal",
        "Conhecimento",
        "Moradia",
        "Inesperado",
        "Lazer",
        "Outros",
        "Fixo"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Planilha de Custos") },
                actions = {
                    IconButton(onClick = { showFilterDialog = true }) {
                        Icon(Icons.Default.FilterList, "Filtrar custos")
                    }
                }
            )
        },
        bottomBar = {
            NavBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToInsertCost,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, "Adicionar custo")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Summary Card
            CostsSummaryCard(
                totalMonth = monthCosts.sumOf { it.value },
                fixedCosts = monthCosts.filter { it.isFixed }.sumOf { it.value },
                variableCosts = monthCosts.filter { !it.isFixed }.sumOf { it.value }
            )

            // Costs List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(monthCosts) { cost ->
                    CostCard(
                        cost = cost,
                    )
                }
            }
        }

        // Filter Dialog
        if (showFilterDialog) {
            AlertDialog(
                onDismissRequest = { showFilterDialog = false },
                title = { Text("Filtrar por tipo") },
                text = {
                    Column {
                        costTypes.forEach { type ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedCostType == type,
                                    onClick = {
                                        selectedCostType = type
                                        // Implementar filtro no ViewModel
                                    }
                                )
                                Text(
                                    text = type,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showFilterDialog = false }) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        selectedCostType = "Todos"
                        showFilterDialog = false
                    }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

private fun getSampleCosts(): List<CostsNote> {
    return listOf(
        CostsNote(
            id = 1,
            title = "Aluguel",
            paymentWay = "Débito",
            installments = null,
            value = 1200.0,
            costType = "Moradia",
            isFixed = true,
            date = LocalDateTime.now()
        ),
        CostsNote(
            id = 2,
            title = "Curso de Kotlin",
            paymentWay = "Crédito",
            installments = 6,
            value = 300.0,
            costType = "Conhecimento",
            isFixed = false,
            date = LocalDateTime.now()
        ),
        CostsNote(
            id = 3,
            title = "Academia",
            paymentWay = "Débito",
            installments = null,
            value = 89.90,
            costType = "Pessoal",
            isFixed = true,
            date = LocalDateTime.now()
        ),
        CostsNote(
            id = 4,
            title = "Cinema",
            paymentWay = "Crédito",
            installments = null,
            value = 45.0,
            costType = "Lazer",
            isFixed = false,
            date = LocalDateTime.now()
        )
    )
}


@Preview(
    showBackground = true,
    name = "Cost Card Preview"
)
@Composable
fun CostCardPreview() {
    MoneyTherapyTheme {
        CostCard(
            cost = CostsNote(
                id = 1,
                title = "Aluguel",
                paymentWay = "Débito",
                installments = null,
                value = 1200.0,
                costType = "Moradia",
                isFixed = true,
                date = LocalDateTime.now()
            )
        )
    }
}

@Preview(
    showBackground = true,
    name = "Summary Card Preview"
)
@Composable
fun CostsSummaryCardPreview() {
    MoneyTherapyTheme {
        CostsSummaryCard(
            totalMonth = 2500.0,
            fixedCosts = 1500.0,
            variableCosts = 1000.0
        )
    }
}