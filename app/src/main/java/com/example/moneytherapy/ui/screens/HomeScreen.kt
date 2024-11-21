package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.ui.componentsUI.HomeTopAppBar
import com.example.moneytherapy.ui.componentsUI.NavBar
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToInsertGoal: () -> Unit,
) {
    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = { NavBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = contentColorFor(MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Inserir")
            }
        },
        modifier = modifier
            .padding(0.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(it) // Para evitar sobreposição da navbar
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Exemplo de uma box de "Curto Prazo" com progresso para cada item
            GoalBox(
                title = "Curto Prazo",
                items = listOf(
                    "carro 50k" to 0.2f,
                    "mesa 1k" to 0.8f
                )
            )

            // Exemplo de uma box de "Médio Prazo"
            GoalBox(
                title = "Médio Prazo",
                items = listOf(
                    "viagem 20k" to 0.5f
                )
            )

            // Outro exemplo de box "Médio Prazo"
            GoalBox(
                title = "Médio Prazo",
                items = listOf(
                    "curso 5k" to 0.3f
                )
            )
        }
    }
}

@Composable
fun GoalBox(
    title: String,
    items: List<Pair<String, Float>> // Lista de tuplas (String, Float)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column {
            // Título (Curto Prazo, Médio Prazo, etc)
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Renderiza cada item com sua barra de progresso individual
            items.forEach { (itemName, progress) ->
                Column(
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    // Nome do item
                    Text(
                        text = itemName,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Barra de progresso individual
                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (progress < 0.5f) Color.Red else Color.Green // cor condicional com base no progresso
                    )
                }
            }

            // Link de edição
            Text(
                text = "edit",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }

}

/*
@Preview(showBackground = true, device = "spec:width=411dp,height=731dp,dpi=480")
@Composable
fun HomePreview(){
    MoneyTherapyTheme {
        HomeScreen(onNavigateToInsertGoal = {
            //navController.navigate("insertGoal")
        })
    }
}
*/

