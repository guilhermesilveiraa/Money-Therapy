package com.example.moneytherapy.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.ui.componentsUI.HomeTopAppBar
import com.example.moneytherapy.ui.componentsUI.InsertBox
import com.example.moneytherapy.ui.componentsUI.IntegerInputField
import com.example.moneytherapy.ui.componentsUI.NavBar
import com.example.moneytherapy.ui.componentsUI.SelectionDropdown
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme

@Composable
fun InsertGoalsScreen(
    modifier: Modifier = Modifier,
    onSaveGoal: Any,
){
    Scaffold(
        topBar = { HomeTopAppBar()},
        bottomBar = { NavBar()},
        modifier = modifier
            .padding(1.dp)
            .fillMaxSize()
    ){
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .padding()
        ){
            Spacer(modifier = Modifier.height(60.dp))
            InsertBox("Inserir Objetivo")

            Spacer(modifier = Modifier.height(10.dp))
            IntegerInputField("Inserir valor objetivo"){}

            Spacer(modifier = Modifier.height(10.dp))
            IntegerInputField("Inserir valor já alcançado"){}

            Spacer(modifier = Modifier.height(10.dp))
            SelectionDropdown(
                options = listOf("Curto Prazo", "Médio Prazo", "Longo Prazo"),
                label = "Escolher Prazo"
            ) { selectedOption ->
                // Lógica de processamento para a opção selecionada
            }

            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .height(50.dp)
                    .width(300.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Salvar Objetivo")
            }

        }

    }
}


@Preview(showBackground = true, device = "spec:width=411dp,height=731dp,dpi=480")
@Composable
fun InsertGoalsPreview(){
    MoneyTherapyTheme {
        InsertGoalsScreen(onSaveGoal = { goal ->
            // Função de salvar objetivo passada como parâmetro
        })
    }
}