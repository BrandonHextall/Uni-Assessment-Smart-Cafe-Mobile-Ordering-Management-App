package com.example.scoa.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scoa.model.FirebaseManager

@Composable
fun AppScaffold(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    val firebase = remember { FirebaseManager() }
    val loggedIn = firebase.isUserLoggedIn()
    var expanded by remember { mutableStateOf(false) }

    // Bottom bar of page
    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.primary) {
                Row (
                    modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    // Profile button and dropdown
                    Box {
                        Column (horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(onClick = {
                                if (loggedIn) expanded = !expanded
                                else navController.navigate("login")
                            }, modifier = Modifier.size(50.dp)) {
                                Icon (
                                    painter = rememberVectorPainter(Icons.Default.AccountBox),
                                    contentDescription = "Account",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            Text("Profile")
                        }
                        DropdownMenu (
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            containerColor = MaterialTheme.colorScheme.primary,
                            offset = DpOffset(x = (-120).dp, y = 0.dp)
                        ) {
                            if (loggedIn) {
                                DropdownMenuItem (
                                    text = { Text("My Account") },
                                    onClick = {
                                        expanded = false
                                        // Navigate to account
                                    }
                                )
                                DropdownMenuItem (
                                    text = { Text("Sign Out") },
                                    onClick = {
                                        expanded = false

                                        // Sign out of Firebase.
                                        firebase.logout()

                                        // Return to the start page.
                                        navController.navigate("start") {
                                            popUpTo(0) {
                                                inclusive = true
                                            }
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            } else {
                                DropdownMenuItem (
                                    text = { Text("Login") },
                                    onClick = {
                                        expanded = false
                                        // Navigate to login
                                    }
                                )
                                DropdownMenuItem (
                                    text = { Text("Register") },
                                    onClick = {
                                        expanded = false
                                        // Navigate to registration
                                    }
                                )
                            }
                        }
                    }
                    // Menu button
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton (
                            onClick = { /* do something */ },
                            modifier = Modifier.size(50.dp)
                        ) {
                            Icon(
                                painter = rememberVectorPainter(Icons.Default.Coffee),
                                contentDescription = "Localized description",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Text("Menu")
                    }
                    // Orders button
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton (
                            onClick = { /* do something */ },
                            modifier = Modifier.size(50.dp)
                        ) {
                            Icon (
                                painter = rememberVectorPainter(Icons.Default.Receipt),
                                contentDescription = "Localized description",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Text("Orders")
                    }
                    // Cart button
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = { /* do something */ }) {
                            Icon (
                                painter = rememberVectorPainter(Icons.Default.ShoppingCart),
                                contentDescription = "Localized description",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Text("Cart")
                    }
                }
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}