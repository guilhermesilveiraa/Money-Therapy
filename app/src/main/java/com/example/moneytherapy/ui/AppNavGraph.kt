// AppNavGraph.kt
package com.example.moneytherapy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.ui.screens.EditGoalScreen
import com.example.moneytherapy.ui.screens.HomeScreen
import com.example.moneytherapy.ui.screens.InsertGoalsScreen
import com.example.moneytherapy.ui.viewModel.HomeViewModel

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
        composable(
            route = "editGoal/{goalId}",
            arguments = listOf(navArgument("goalId") { type = NavType.LongType })
        ) { backStackEntry ->
            val goalId = backStackEntry.arguments?.getLong("goalId") ?: return@composable
            val viewModel: HomeViewModel = hiltViewModel()
            val goal = viewModel.getGoalById(goalId) // You'll need to implement this

            EditGoalScreen(
                goal = goal,
                onUEGoal = { updatedGoal ->
                    viewModel.onUpdateGoal(updatedGoal)
                },
                onDeleteGoal = { id ->
                    viewModel.onDeleteGoal(id)
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
    }
}
