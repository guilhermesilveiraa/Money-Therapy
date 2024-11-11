package com.example.moneytherapy.presentation.componentsUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import  androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.moneytherapy.R
import com.example.moneytherapy.presentation.theme.MoneyTherapyTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(modifier: Modifier = Modifier,
                  /*topAppBarState: TopAppBarState = rememberTopAppBarState()*/){
    var expanded by remember {mutableStateOf(false)}
    val title = "Terapia Financeira"
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier,
                color = Color.DarkGray,
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
                DropdownMenuItem(text = { /*TODO*/ }, onClick = { /*TODO*/ })  //Configuracoes
                DropdownMenuItem(text = { /*TODO*/ }, onClick = { /*TODO*/ }) //Sobre
            }
        },
    )



}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TopPreview(){
    MoneyTherapyTheme {
        HomeTopAppBar(

        )
    }
}
