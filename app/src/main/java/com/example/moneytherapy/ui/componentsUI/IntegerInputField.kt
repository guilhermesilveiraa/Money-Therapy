package com.example.moneytherapy.ui.componentsUI

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun IntegerInputField(
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (Int?) -> Unit // Recebe o valor inteiro ou null se o campo estiver vazio
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            // Permite apenas números inteiros
            if (newText.all { it.isDigit() } || newText.isEmpty()) {
                text = newText
                onValueChange(newText.toIntOrNull()) // Converte para Int ou retorna null se vazio
            }
        },
        label = { Text(label) },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Configura teclado numérico
    )
}

@Preview(showBackground = true)
@Composable
fun IntegerInputFieldPreview() {
    IntegerInputField(label = "Digite um número inteiro") { value ->
        println("Número inteiro inserido: $value")
    }
}