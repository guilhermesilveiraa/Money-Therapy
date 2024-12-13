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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme
//import com.example.moneytherapy.ui.viewModel.CostsViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostsScreen(
    modifier: Modifier = Modifier,
    onNavigateToAddCost: () -> Unit,
    //viewModel: CostsViewModel = hiltViewModel()
) {
    var selectedCostType by remember { mutableStateOf("Todos") }
    var showFilterDialog by remember { mutableStateOf(false) }

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
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddCost,
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
                totalMonth = 2500.0, // Replace with actual data from ViewModel
                fixedCosts = 1500.0,
                variableCosts = 1000.0
            )

            // Costs List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(getSampleCosts()) { cost -> // Replace with actual data from ViewModel
                    CostCard(cost = cost)
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
                                    onClick = { selectedCostType = type }
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
                    TextButton(onClick = { showFilterDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Composable
private fun CostsSummaryCard(
    totalMonth: Double,
    fixedCosts: Double,
    variableCosts: Double
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Resumo do Mês",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Total: R$ ${String.format("%.2f", totalMonth)}")
                    Text("Fixos: R$ ${String.format("%.2f", fixedCosts)}")
                    Text("Variáveis: R$ ${String.format("%.2f", variableCosts)}")
                }
                CircularProgressIndicator(
                    progress = (fixedCosts / totalMonth).toFloat(),
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CostCard(cost: CostsNote) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = cost.title ?: "Sem título",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = cost.costType,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Text(
                    text = "R$ ${String.format("%.2f", cost.value)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Payment,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = cost.paymentWay,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                if (cost.installments != null) {
                    Text(
                        text = "${cost.installments}x",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

// Sample data function
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
            paymentWay = "Débito Automático",
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
    showSystemUi = true,
    name = "Costs Screen Preview"
)
@Composable
fun CostsScreenPreview() {
    MoneyTherapyTheme {
        CostsScreen(
            onNavigateToAddCost = {}
        )
    }
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