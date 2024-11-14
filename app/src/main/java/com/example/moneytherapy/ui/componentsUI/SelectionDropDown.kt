package com.example.moneytherapy.ui.componentsUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SelectionDropdown(
    options: List<String>,      // Lista de opções para seleção
    label: String,              // Label do campo
    modifier: Modifier = Modifier,
    onOptionSelected: (String) -> Unit // Callback com a opção selecionada
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { expanded = true }, // Expande o menu ao clicar
            readOnly = true // Impede a edição direta do campo
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option // Atualiza a opção selecionada
                        onOptionSelected(option) // Passa a opção selecionada pelo callback
                        expanded = false // Fecha o menu
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectionDropdownPreview() {
    val options = listOf("Opção 1", "Opção 2", "Opção 3")

    SelectionDropdown(
        options = options,
        label = "Escolha uma opção"
    ) { selectedOption ->
        println("Opção selecionada: $selectedOption")
    }
}
