package com.example.scoa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scoa.theme.AppTheme
import com.example.scoa.view.CartScreen
import com.example.scoa.view.CheckoutScreen

// Temporary while converting to MVVM format

// Firebase

// Application screens
import com.example.scoa.view.HomeScreen
import com.example.scoa.view.StartPage
import com.example.scoa.view.LoginScreen
import com.example.scoa.view.RegisterScreen

// Application ViewModels
import com.example.scoa.viewmodel.LoginViewModel
import com.example.scoa.viewmodel.RegisterViewModel
import com.example.scoa.viewmodel.HomeViewModel
import com.example.scoa.viewmodel.CartViewModel

val pinyonScript = FontFamily(Font(R.font.pinyonscript_regular))

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Sets and displays startup page
        setContent {
             AppTheme {
                 // AppNavigation is now only for quickly reaching certain pages. Will not be used for main app
                AppNavigation()
                //BrowseMenu(navController = rememberNavController())
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
        /*composable("start") {
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
        }*/

        // Login page
        composable("login") {
            LoginScreen (
                viewModel = viewModel(),
                navController = navController
            )
        }

        // Register page
        composable("register") {
            RegisterScreen (
                viewModel = viewModel(),
                navController = navController
            )
        }

        // Cart page
        composable("cart") { CartScreen (navController = navController)}

        // Checkout page
        composable("checkout") { CheckoutScreen(navController) }

        composable("home") { HomeScreen(navController) }

        // Comment this line when using the start page
        composable("start") { HomeScreen(navController) }
    }
}
