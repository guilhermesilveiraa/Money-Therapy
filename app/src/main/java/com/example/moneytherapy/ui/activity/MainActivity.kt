// MainActivity.kt
package com.example.moneytherapy.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.moneytherapy.ui.navigation.AppNavGraph
import com.example.moneytherapy.ui.viewModel.HomeViewModel
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneyTherapyTheme {
                val navController = rememberNavController()
                val viewModel: HomeViewModel = hiltViewModel()

                AppNavGraph(
                    navController = navController,
                    startDestination = "home",
                    onSaveGoal = { goal: Goals ->
                        viewModel.onBottomItemCreateGoal(goal)
                    }
                    onEditGoal = {goal: Goals ->
                        viewModel.onEditGoal(goal.id)
                    }
                )
            }
        }
    }
}



/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoneyTherapyTheme {
        Greeting("Android")
    }
}*/