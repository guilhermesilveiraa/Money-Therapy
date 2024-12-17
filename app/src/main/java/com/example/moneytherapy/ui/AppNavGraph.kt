package com.example.moneytherapy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.ui.screens.HomeScreen
import com.example.moneytherapy.ui.screens.InsertGoalsScreen
import com.example.moneytherapy.ui.screens.EditGoalScreen
import com.example.moneytherapy.ui.screens.InsertCostNoteScreen
import com.example.moneytherapy.ui.screens.CostsScreen


/**
 * Main navigation graph for the Money Therapy application.
 * Handles routing between different screens and manages navigation state.
 *
 * @param navController The navigation controller managing app navigation
 * @param startDestination The initial route to display (defaults to "home")
 * @param onSaveGoal Callback function for saving new goals
 * @param onSaveCostNote Callback function for saving new CostNote
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = "home",
    onSaveGoal: (Goals) -> Unit,
    onSaveCostNote: (CostsNote) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Home Screen Route
        composable("home") {
            HomeScreen(
                onNavigateToInsertGoal = {
                    navController.navigate("insertGoal")
                },
                onNavigateToEditGoal = { goalId ->
                    navController.navigate("editGoal/$goalId")
                },
                navController = navController
            )
        }

        // Insert Goal Screen Route
        composable("insertGoal") {
            InsertGoalsScreen(
                onSaveGoal = { goal ->
                    onSaveGoal(goal)
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.navigateUp()
                },
                navController = navController
            )
        }

        composable("costs") {
            CostsScreen(
                onNavigateToInsertCost = {
                    navController.navigate("insertCost")
                },
                navController = navController
            )
        }

        composable("insertCost") {
            InsertCostNoteScreen(
                onSaveCostNote = { costNote ->
                    onSaveCostNote(costNote)
                    navController.navigate("costs") {
                        popUpTo("costs") { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.navigateUp()
                    true
                },
                navController = navController
            )
        }

        // Edit Goal Screen Route
        composable(
            route = "editGoal/{goalId}",
            arguments = listOf(
                navArgument("goalId") {
                    type = NavType.LongType
                    nullable = false
                }
            )
        ) { entry ->
            val goalId = entry.arguments?.getLong("goalId")
                ?: return@composable

            EditGoalScreen(
                goalId = goalId,
                onNavigateBack = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                navController = navController
            )
        }
    }
}

/**
 * Sealed class representing the different screens in the application
 * Makes navigation type-safe and allows for easy addition of new screens
 */
sealed class MoneyTherapyScreen(val route: String) {
    object Home : MoneyTherapyScreen("home")
    object InsertGoal : MoneyTherapyScreen("insertGoal")
    object Costs : MoneyTherapyScreen("costs")  // Adicionado
    object InsertCost : MoneyTherapyScreen("insertCost")  // Adicionado

    // Edit goal route with parameter
    data class EditGoal(val goalId: Long) : MoneyTherapyScreen("editGoal/$goalId") {
        companion object {
            const val route = "editGoal/{goalId}"
        }
    }
}

/**
 * Extension functions for the NavHostController to make navigation calls more concise and type-safe
 */
fun NavHostController.navigateToHome() {
    navigate(MoneyTherapyScreen.Home.route) {
        popUpTo(MoneyTherapyScreen.Home.route) { inclusive = true }
    }
}

fun NavHostController.navigateToInsertGoal() {
    navigate(MoneyTherapyScreen.InsertGoal.route)
}

fun NavHostController.navigateToEditGoal(goalId: Long) {
    navigate(MoneyTherapyScreen.EditGoal(goalId).route)
}

fun NavHostController.navigateToCosts() {
    navigate(MoneyTherapyScreen.Costs.route) {
        popUpTo(MoneyTherapyScreen.Home.route)
        launchSingleTop = true
    }
}

fun NavHostController.navigateToInsertCost() {
    navigate(MoneyTherapyScreen.InsertCost.route)
}
