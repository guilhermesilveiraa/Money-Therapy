package com.example.moneytherapy.ui.componentsUI


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme

// NavBar.kt
@Composable
fun NavBar(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 8.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 },
            icon = { Icon(Icons.Filled.Cloud, contentDescription = "Sonhos") },
            label = { Text("Sonhos") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            icon = { Icon(Icons.Filled.AttachMoney, contentDescription = "Custos") },
            label = { Text("Custos") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )

        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            icon = { Icon(Icons.Filled.Wallet, contentDescription = "Rendas e Investimentos") },
            label = { Text("Investimentos") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        )

        NavigationBarItem(
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 },
            icon = { Icon(Icons.Filled.InsertChart, contentDescription = "Informacoes") },
            label = { Text("Resumo") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
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