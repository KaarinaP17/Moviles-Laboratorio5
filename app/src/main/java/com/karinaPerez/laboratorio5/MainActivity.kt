package com.karinaPerez.laboratorio5

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karinaPerez.laboratorio5.ui.theme.Laboratorio5Theme
import com.karinaPerez.laboratorio5.views.HomeScreen

class MainActivity<GeneralViewModel> : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio5Theme {
                val navController = rememberNavController()
                val viewModel: GeneralViewModel = viewModel()
                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        HomeScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}