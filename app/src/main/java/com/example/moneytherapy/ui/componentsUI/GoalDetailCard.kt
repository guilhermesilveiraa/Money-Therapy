package com.example.moneytherapy.ui.componentsUI


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme

@Composable
fun GoalDetailCard(
    title: String,
    category: String,
    currentValue: Double,
    targetValue: Double,
    icon: ImageVector = Icons.Default.Category,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onAddValueClick: () -> Unit
) {
    val progress = (currentValue / targetValue).coerceIn(0.0, 1.0)
    val isCompleted = progress >= 1.0

    // Define theme colors based on category
    val themeColor = when (category) {
        "Curto Prazo" -> MaterialTheme.colorScheme.onSurface
        "Médio Prazo" -> MaterialTheme.colorScheme.inverseOnSurface
        "Longo Prazo" -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.primary
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = themeColor
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = category,
                            style = MaterialTheme.typography.bodyMedium,
                            color = themeColor
                        )
                    }
                }

                Row {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar Objetivo",
                            tint = themeColor
                        )
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Status do Objetivo",
                            tint = if (isCompleted) themeColor else themeColor.copy(alpha = 0.3f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Progresso",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = themeColor
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = progress.toFloat(),
                modifier = Modifier.fillMaxWidth(),
                color = themeColor,
                trackColor = themeColor.copy(alpha = 0.2f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.MonetizationOn,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = themeColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Atual: R$ ${String.format("%.2f", currentValue)}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.MonetizationOn,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = themeColor.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Faltam: R$ ${String.format("%.2f", targetValue - currentValue)}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Text(
                    text = "Meta: R$ ${String.format("%.2f", targetValue)}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = themeColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAddValueClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = themeColor,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar Valor",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Adicionar Valor")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoalDetailCardPreview() {
    MoneyTherapyTheme {
        GoalDetailCard(
            title = "Comprar Notebook",
            category = "Tecnologia",
            currentValue = 1500.0,
            targetValue = 3000.0,
            onEditClick = { /* Ação de editar */ },
            onDeleteClick = { /* Ação de deletar */ },
            onAddValueClick = { /* Ação de adicionar valor */ }
        )
    }
}