package com.example.smartcafemobileorderingapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val pinyonScript = FontFamily(Font(R.font.pinyon_script_regular))

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
    composable("home") {
        HomeScreen(
            onLoginClick = {
                navController.navigate("login")
            },
            onRegisterClick = {
                navController.navigate("register")
            },
            onBrowseMenuClick = {
                navController.navigate("browseMenu")
            }
        )
    }
    composable("login") {
        LoginScreen(
            onLogin = { email, password ->

                //Temprary test response
                Toast.makeText(
                    context,
                    "Login button pressed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }
    composable("register") {
        //RegisterScreen()
    }
    composable("browseMenu") {
        //BrowseMenuScreen()
    }
    }
}
