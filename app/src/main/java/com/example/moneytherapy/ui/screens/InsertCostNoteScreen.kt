package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.ui.componentsUI.HomeTopAppBar
import com.example.moneytherapy.ui.componentsUI.NavBar
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertCostNoteScreen(
    modifier: Modifier = Modifier,
    onSaveCostNote: (CostsNote) -> Unit,
    onNavigateBack: () -> Boolean,
    navController: NavController
) {
    var costNoteId by remember { mutableLongStateOf(0L) }
    var costNoteTitle by remember { mutableStateOf("") }
    var costNotePaymentWay by remember { mutableStateOf("") }
    var costNoteInstallments by remember { mutableIntStateOf(0) }
    var costNoteValue by remember { mutableIntStateOf(0) }
    var costNoteCostType by remember { mutableStateOf("") }
    var costNoteIsFixed by remember { mutableStateOf(false) }
    var costNoteDate by remember { mutableStateOf(LocalDateTime.now()) }
    var expandedCostType by remember { mutableStateOf(false) }
    var expandedPayment by remember { mutableStateOf(false) }


    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = { NavBar(navController = navController)},
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
                        text = "Novo Gasto",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = costNoteTitle,
                        onValueChange = { costNoteTitle = it },
                        label = { Text("Título do Gasto") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )

                    ExposedDropdownMenuBox(
                        expanded = expandedPayment,
                        onExpandedChange = { expandedPayment = it },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = costNotePaymentWay,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Forma de Pagamento") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPayment) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        ExposedDropdownMenu(
                            expanded = expandedPayment,
                            onDismissRequest = { expandedPayment = false }
                        ) {
                            listOf("Dinheiro", "Cartão de Crédito", "Cartão de Débito", "PIX").forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        costNotePaymentWay = option
                                        expandedPayment = false
                                    }
                                )
                            }
                        }
                    }

                    if (costNotePaymentWay == "Cartão de Crédito") {
                        OutlinedTextField(
                            value = if (costNoteInstallments == 0) "" else costNoteInstallments.toString(),
                            onValueChange = {
                                costNoteInstallments = it.filter { char -> char.isDigit() }.toIntOrNull() ?: 0
                            },
                            label = { Text("Número de Parcelas") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }

                    OutlinedTextField(
                        value = if (costNoteValue == 0) "" else costNoteValue.toString(),
                        onValueChange = {
                            costNoteValue = it.filter { char -> char.isDigit() }.toIntOrNull() ?: 0
                        },
                        label = { Text("Valor do Gasto") },
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
                        expanded = expandedCostType,
                        onExpandedChange = { expandedCostType = it },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = costNoteCostType,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Tipo de Gasto") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCostType) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        ExposedDropdownMenu(
                            expanded = expandedCostType,
                            onDismissRequest = { expandedCostType = false }
                        ) {
                            listOf(
                                "Pessoal",
                                "Conhecimento",
                                "Moradia",
                                "Inesperado",
                                "Lazer",
                                "Outros"
                            ).forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        costNoteCostType = option
                                        expandedCostType = false
                                    }
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Gasto Fixo?")
                        Switch(
                            checked = costNoteIsFixed,
                            onCheckedChange = { costNoteIsFixed = it }
                        )
                    }

                    Button(
                        onClick = {
                            if (costNoteTitle.isNotBlank() && costNoteValue > 0) {
                                val newCostNote = CostsNote(
                                    id = costNoteId,
                                    title = costNoteTitle,
                                    paymentWay = costNotePaymentWay,
                                    installments = if (costNotePaymentWay == "Cartão de Crédito") costNoteInstallments else null,
                                    value = costNoteValue.toDouble(),
                                    costType = costNoteCostType,
                                    isFixed = costNoteIsFixed,
                                    date = costNoteDate
                                )
                                onSaveCostNote(newCostNote)
                                onNavigateBack()
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
                            "Salvar Gasto",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

/*
  var paymentWay: String,
    var installments: Int?, //If it's intallment the next month must contains the parcel from these cost
    var value: Double,
    var costType: String,
    var isFixed: Boolean,
    val date: LocalDateTime = LocalDateTime.now()

 */