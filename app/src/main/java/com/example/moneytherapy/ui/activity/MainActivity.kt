package com.example.moneytherapy.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.moneytherapy.feature_components.goals.domain.models.Goals
import com.example.moneytherapy.ui.AppNavGraph
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme
import com.example.moneytherapy.ui.viewModel.HomeViewModel
import com.example.moneytherapy.ui.viewModel.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ThemeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Recupera o estado se existir
        val previousState = intent.getBundleExtra("state")
        previousState?.let {
            onRestoreInstanceState(it)
        }

        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val themeMode by themeViewModel.themeMode.collectAsState()

            MoneyTherapyTheme(themeMode = themeMode) {
                val navController = rememberNavController()
                val viewModel: HomeViewModel = hiltViewModel()

                AppNavGraph(
                    navController = navController,
                    startDestination = "home",
                    onSaveGoal = { goal: Goals ->
                        viewModel.onBottomItemCreateGoal(goal)
                    }
                )
            }
        }
    }
}