package com.example.moneytherapy.ui.componentsUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalBox(
    title: String,
    items: List<Pair<String?, Float>>,
    onAddValueClick: (String?) -> Unit,
    onEditClick: (String?) -> Unit,
    onDeleteClick: (String?) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (items.isEmpty()) {
                Text(
                    text = "Nenhum objetivo encontrado",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                items.forEachIndexed { index, (itemName, progress) ->
                    GoalItem(
                        itemName = itemName,
                        progress = progress,
                        onAddValueClick = { onAddValueClick(itemName) },
                        onEditClick = { onEditClick(itemName) },
                        onDeleteClick = { onDeleteClick(itemName) }
                    )

                    if (index < items.size - 1) {
                        Divider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GoalItem(
    itemName: String?,
    progress: Float,
    onAddValueClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = itemName ?: "Sem título",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )

            Row {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Deletar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onAddValueClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
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

@Preview(showBackground = true)
@Composable
fun GoalBoxPreview() {
    MoneyTherapyTheme {
        GoalBox(
            title = "Objetivos de Teste",
            items = listOf(
                "Curso de Kotlin" to 0.7f,
                "Compra de Livros" to 0.3f,
                "Viagem para a praia" to 0.5f
            ),
            onAddValueClick = { goal -> println("Adicionar valor ao objetivo: $goal") },
            onEditClick = { goal -> println("Editar objetivo: $goal") },
            onDeleteClick = { goal -> println("Deletar objetivo: $goal") }
        )
    }
}