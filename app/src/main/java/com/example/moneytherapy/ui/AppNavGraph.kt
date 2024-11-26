// AppNavGraph.kt
package com.example.moneytherapy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.ui.screens.HomeScreen
import com.example.moneytherapy.ui.screens.InsertGoalsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = "home",
    onSaveGoal: (Goals) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToInsertGoal = {
                    navController.navigate("insertGoal")
                }
            )
        }
        composable("insertGoal") {
            InsertGoalsScreen(
                onSaveGoal = {
                    goal-> onSaveGoal(goal)
                    navController.navigate("home")
                }
            )
        }
    }
}
