package com.example.moneytherapy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.ui.screens.HomeScreen
import com.example.moneytherapy.ui.screens.InsertGoalsScreen
import com.example.moneytherapy.ui.screens.EditGoalScreen

/**
 * Main navigation graph for the Money Therapy application.
 * Handles routing between different screens and manages navigation state.
 *
 * @param navController The navigation controller managing app navigation
 * @param startDestination The initial route to display (defaults to "home")
 * @param onSaveGoal Callback function for saving new goals
 */
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
        // Home Screen Route
        // Displays the main dashboard with all goals
        composable("home") {
            HomeScreen(
                onNavigateToInsertGoal = {
                    navController.navigate("insertGoal")
                },
                onNavigateToEditGoal = { goalId ->
                    navController.navigate("editGoal/$goalId")
                }
            )
        }

        // Insert Goal Screen Route
        // Handles creation of new goals
        composable("insertGoal") {
            InsertGoalsScreen(
                onSaveGoal = { goal ->
                    onSaveGoal(goal)
                    navController.navigate("home") {
                        // Clear the back stack up to home
                        popUpTo("home") { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // Edit Goal Screen Route
        // Handles editing of existing goals
        // Requires a goalId parameter to identify which goal to edit
        composable(
            route = "editGoal/{goalId}",
            arguments = listOf(
                navArgument("goalId") {
                    type = NavType.LongType
                    nullable = false
                }
            )
        ) { entry ->
            // Extract the goalId from navigation arguments
            val goalId = entry.arguments?.getLong("goalId")
                ?: return@composable // Return if goalId is null (shouldn't happen)

            EditGoalScreen(
                goalId = goalId,
                onNavigateBack = {
                    navController.navigate("home") {
                        // Clear the back stack up to home
                        popUpTo("home") { inclusive = true }
                    }
                }
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