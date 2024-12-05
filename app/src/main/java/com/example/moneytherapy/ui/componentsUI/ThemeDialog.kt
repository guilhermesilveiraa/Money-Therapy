package com.example.moneytherapy.ui.componentsUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.feature_components.theme.domain.models.ThemeMode


// 1. First, let's create a ThemeDialog component to show theme options
@Composable
fun ThemeDialog(
    onDismiss: () -> Unit,
    onThemeSelected: (ThemeMode) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Escolha o tema",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ThemeOption(
                    title = "Claro",
                    description = "Usar tema claro",
                    onClick = { onThemeSelected(ThemeMode.LIGHT) }
                )
                ThemeOption(
                    title = "Escuro",
                    description = "Usar tema escuro",
                    onClick = { onThemeSelected(ThemeMode.DARK) }
                )
                ThemeOption(
                    title = "Sistema",
                    description = "Seguir tema do sistema",
                    onClick = { onThemeSelected(ThemeMode.SYSTEM) }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Fechar")
            }
        }
    )
}

// 2. Create a component for each theme option
@Composable
private fun ThemeOption(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}
