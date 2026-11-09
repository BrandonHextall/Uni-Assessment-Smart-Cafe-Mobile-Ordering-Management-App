package com.example.smartcafemobileorderingapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartcafemobileorderingapplication.view.BrowseMenu
import com.example.smartcafemobileorderingapplication.view.StartPage
import com.example.smartcafemobileorderingapplication.view.LoginScreen
import com.example.smartcafemobileorderingapplication.view.RegisterScreen

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
        startDestination = "start"
    ) {
    composable("start") {
        StartPage(
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
        RegisterScreen(
            onRegister = { fullName, email, password, repeatPassword, deliveryAddress, error ->

                //Temprary test response
                Toast.makeText(
                    context,
                    "Register button pressed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }
    composable("browseMenu") {
        BrowseMenu()
    }
    }
}
