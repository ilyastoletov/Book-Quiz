package com.dev.bookquiz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.bookquiz.presentation.screens.game.view.GameScreen
import com.dev.bookquiz.presentation.screens.results.view.ResultsScreen
import com.dev.bookquiz.presentation.screens.start.view.StartScreen
import com.dev.bookquiz.presentation.theme.BookQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookQuizTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHost()
                }
            }
        }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "startScreen") {
        composable("startScreen") { StartScreen(navController) }
        composable("game") { GameScreen() }
        composable("results") { ResultsScreen() }
    }
}