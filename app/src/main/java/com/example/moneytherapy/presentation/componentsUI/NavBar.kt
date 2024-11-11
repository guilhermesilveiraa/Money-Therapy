package com.example.moneytherapy.presentation.componentsUI


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneytherapy.presentation.theme.MoneyTherapyTheme
import com.google.android.libraries.places.api.model.Money

@Composable
fun NavBar(modifier: Modifier = Modifier){
    var selectedItem by remember {mutableStateOf(0)}

    NavigationBar(
        containerColor = Color.LightGray,
        contentColor = Color.Black,
        modifier = modifier.fillMaxWidth()
    ){
        NavigationBarItem(
            selected = selectedItem == 1,
            icon = {Icon(Icons.Filled.Cloud, contentDescription = "Sonhos")},
            label = { Text("Sonhos")},
            onClick = { /*TODO*/ },
        )

        NavigationBarItem(
            selected = selectedItem == 1,
            icon = {Icon(Icons.Filled.AttachMoney, contentDescription = "Custos")},
            label = { Text("Custos")},
            onClick = { /*TODO*/ },
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Wallet, contentDescription = "Rendas e Investimentos")},
            label = {Text("Investimentos")},
            selected = selectedItem == 1,
            onClick = { /*TODO*/ },

        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.InsertChart, contentDescription = "Informacoes")},
            label = {Text("Resumo")},
            selected = selectedItem == 1,
            onClick = { /*TODO*/ },

            )

    }
}

@Preview(showBackground = true)
@Composable
fun navBarPreview(){
    MoneyTherapyTheme {
        NavBar()
    }
}