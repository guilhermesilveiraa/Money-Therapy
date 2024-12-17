package com.example.moneytherapy.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.moneytherapy.feature_components.theme.domain.models.ThemeMode
import com.example.moneytherapy.ui.viewModel.CostsExcelViewModel
import com.example.moneytherapy.ui.viewModel.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
abstract class ThemeActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            themeViewModel.themeMode.collect { theme ->
                if (savedInstanceState == null) {
                    AppCompatDelegate.setDefaultNightMode(
                        when (theme) {
                            ThemeMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                            ThemeMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                            ThemeMode.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                        }
                    )
                }
            }
        }
    }
}