package com.example.moneytherapy.ui.componentsUI.costs

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CostsSummaryCard(
    totalMonth: Double,
    fixedCosts: Double,
    variableCosts: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
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

@Composable
fun CostCard(
    cost: CostsNote,
    modifier: Modifier = Modifier
) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("pt", "BR"))

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
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
                Text(
                    text = cost.date.format(dateFormatter),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "R$ ${String.format("%.2f", cost.value)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Payment,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = cost.paymentWay,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 2.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    if (cost.installments != null) {
                        Text(
                            text = " • ${cost.installments}x",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                if (cost.isFixed) {
                    Text(
                        text = "Custo Fixo",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
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
            ),
        )
    }
}

@Preview(showBackground = true)
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