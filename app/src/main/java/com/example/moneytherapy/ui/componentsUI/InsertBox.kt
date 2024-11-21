package com.example.moneytherapy.ui.componentsUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertBox(
    label: String,
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit // Aceita uma função com parâmetro String
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = if (expanded) label else label)
            if (expanded) {
                // Exibe o campo de entrada quando expandido
                OutlinedTextField(
                    value = value, // Vincula o valor recebido como parâmetro
                    onValueChange = onValueChange, // Atualiza diretamente o estado externo
                    label = { Text(label) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}



/*@Preview(showBackground = true)
@Composable
fun InsertBoxPreview(){
    MoneyTherapyTheme {
        InsertBox("Alo", value = goalTitle, onValueChange = { goalTitle = it })
    }
}*/