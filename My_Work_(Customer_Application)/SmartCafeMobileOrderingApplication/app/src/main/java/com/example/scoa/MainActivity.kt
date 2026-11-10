package com.example.scoa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scoa.controller.FirebaseAuthRepository
import com.example.scoa.controller.LoginController
import com.example.scoa.controller.RegisterController
import com.example.scoa.model.UserRepository
import com.example.scoa.view.BrowseMenu
import com.example.scoa.view.StartPage
import com.example.scoa.view.LoginScreen
import com.example.scoa.view.RegisterScreen
import com.google.firebase.auth.FirebaseAuth

val pinyonScript = FontFamily(Font(R.font.pinyon_script_regular))

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                //AppNavigation()
                BrowseMenu()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost (
        navController = navController,
        startDestination = "start"
    ) {
        composable("start") {
            StartPage (
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
            LoginScreen (
                controller = remember {
                    LoginController(
                        authRepository = FirebaseAuthRepository(
                            FirebaseAuth.getInstance()
                        ),
                        userRepository = UserRepository()
                    )
                },
                onLoginSuccess = {
                    // Navigate to home screen
                    navController.navigate("home")
                }
            )
        }
        composable("register") {
            RegisterScreen (
                controller = remember {

                    RegisterController(
                        authRepository = FirebaseAuthRepository(
                            FirebaseAuth.getInstance()
                        ),

                        userRepository = UserRepository()
                    )
                },
                onRegistrationSuccess = {
                    // Navigate to home screen
                    navController.navigate("home")
                }
            )
        }
        composable("browseMenu") {
            BrowseMenu()
        }
        composable("home") {
            BrowseMenu()
        }
    }
}
