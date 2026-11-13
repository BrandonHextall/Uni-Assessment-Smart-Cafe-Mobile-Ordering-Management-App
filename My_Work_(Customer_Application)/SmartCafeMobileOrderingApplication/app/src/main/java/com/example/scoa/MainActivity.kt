package com.example.scoa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Temporary while converting to MVVM format

// Firebase

// Application screens
import com.example.scoa.view.BrowseMenu
import com.example.scoa.view.StartPage
import com.example.scoa.view.LoginScreen
import com.example.scoa.view.RegisterScreen
import com.example.scoa.viewmodel.RegisterViewModel

val pinyonScript = FontFamily(Font(R.font.pinyon_script_regular))

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Sets and displays startup page
        setContent {
            MaterialTheme {
                AppNavigation()
                //BrowseMenu()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost (
        navController = navController,
        startDestination = "start"
    ) {

        // Start page
        composable("start") {
            StartPage (
                onLoginClick = {
                    navController.navigate("login")
                },
                onRegisterClick = {
                    navController.navigate("register")
                },
                onBrowseMenuClick = {
                    navController.navigate("home")
                }
            )
        }

        // Login page
        composable("login") {
            LoginScreen (
                onLoginSuccess = {
                    // Navigate to home screen
                    navController.navigate("home") {

                    // Remove previous destinations from backstack
                    // (basically back button history)
                        popUpTo("start") {
                            inclusive = true
                        }

                        // Prevents multiple instances of home screen
                        launchSingleTop = true
                    }
                }
            )
        }

        // Register page
        composable("register") {
            RegisterScreen (
                vm = remember {
                    RegisterViewModel()
                },

                onLoginClick = {
                    navController.navigate("login")
                },

                onRegistrationSuccess = {
                    // Navigate to home screen
                    navController.navigate("home") {
                        // Remove previous destinations from backstack
                        // (basically back button history)
                        popUpTo("start") {
                            inclusive = true
                        }

                        // Prevents multiple instances of home screen
                        launchSingleTop = true
                    }
                    }
            )
        }

        composable("home") {
            BrowseMenu()
        }
    }
}
