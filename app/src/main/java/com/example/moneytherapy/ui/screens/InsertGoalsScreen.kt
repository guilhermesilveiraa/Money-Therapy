package com.example.moneytherapy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.ui.componentsUI.HomeTopAppBar
import com.example.moneytherapy.ui.componentsUI.InsertBox
import com.example.moneytherapy.ui.componentsUI.IntegerInputField
import com.example.moneytherapy.ui.componentsUI.NavBar
import com.example.moneytherapy.ui.componentsUI.SelectionDropdown
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*



@Composable
fun InsertGoalsScreen(
    modifier: Modifier = Modifier,
    onSaveGoal: (Goals) -> Unit
) {
    var goalId by remember { mutableLongStateOf(0L) } // Garantir consistência com Long
    var goalTitle by remember { mutableStateOf("") }
    var goalValue by remember { mutableIntStateOf(0) }
    var achievedValue by remember { mutableIntStateOf(0) }
    var goalType by remember { mutableStateOf("Curto Prazo") }

    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = { NavBar() },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp), // Margem horizontal para centralizar os elementos
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))


            // Campo de título
            InsertBox(
                label = "Inserir Título do Objetivo",
                value = goalTitle,

                onValueChange = { goalTitle = it }
            )

            // Campo de valor do objetivo
            IntegerInputField(
                label = "Inserir Valor do Objetivo",
                value = goalValue,
                onValueChange = { it?.let { goalValue = it } }
            )

            // Campo de valor alcançado
            IntegerInputField(
                label = "Inserir Valor Já Alcançado",
                value = achievedValue,
                onValueChange = { it?.let { achievedValue = it } }
            )

            // Dropdown para o tipo de objetivo
            SelectionDropdown(
                options = listOf("Curto Prazo", "Médio Prazo", "Longo Prazo"),
                label = "Escolher Prazo do Objetivo"
            ) { selectedOption ->
                goalType = selectedOption
            }

            // Botão de salvar
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    // Verificação básica dos campos
                    if (goalTitle.isBlank() || goalValue <= 0) {
                        // Mensagem de erro ou tratamento adicional pode ser adicionado aqui
                        return@Button
                    }

                    // Criação do objeto e chamada da função
                    val newGoals = Goals(
                        id = goalId,
                        title = goalTitle,
                        value = achievedValue,
                        goal = goalValue,
                        type = goalType
                    )
                    onSaveGoal(newGoals)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text("Salvar Objetivo")
            }
        }
    }
}

/*
@Preview(showBackground = true, device = "spec:width=411dp,height=731dp,dpi=480")
@Composable
fun InsertGoalsPreview(){
    MoneyTherapyTheme {
        InsertGoalsScreen(onSaveGoal = { goal ->
            // Função de salvar objetivo passada como parâmetro
        })
    }
}*/