package com.example.moneytherapy.ui.componentsUI

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import  androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneytherapy.feature_components.theme.domain.models.ThemeMode
import com.example.moneytherapy.ui.activity.recreateWithAnimation
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme
import com.example.moneytherapy.ui.viewModel.ThemeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    val title = "Terapia Financeira"
    val context = LocalContext.current
    val activity = context as? Activity

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                style = TextStyle.Default
            )
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu de opções"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Modo Claro") },
                    onClick = {
                        themeViewModel.setThemeMode(ThemeMode.LIGHT)
                        expanded = false
                        activity?.recreateWithAnimation()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Modo Escuro") },
                    onClick = {
                        themeViewModel.setThemeMode(ThemeMode.DARK)
                        expanded = false
                        activity?.recreateWithAnimation()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Sistema") },
                    onClick = {
                        themeViewModel.setThemeMode(ThemeMode.SYSTEM)
                        expanded = false
                        activity?.recreateWithAnimation()
                    }
                )
            }
        }
    )
}