package com.example.scoa.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.navigation.ui.NavigationUI.navigateUp
import com.example.scoa.theme.isDarkModeEnabled
import com.example.scoa.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.firebase.auth.FirebaseAuth
import com.example.scoa.model.AuthRepository
import com.example.scoa.model.isUserLoggedIn


@Composable
// Search bar for browsing menu
fun SearchBar(
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    modifier: Modifier = Modifier
) {
    val isDarkMode = isDarkModeEnabled()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavBars(
    modifier: Modifier = Modifier
) {
    val isDarkMode = isDarkModeEnabled()
    var expanded by remember { mutableStateOf(false) }

    // Bottom bar of page
    Scaffold (
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp, 30.dp, 10.dp, 0.dp)
                    .border(1.dp, androidx.compose.ui.graphics.Color.Black, androidx.compose.foundation.shape.RoundedCornerShape(1.dp)),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box (
                    modifier = Modifier.padding(0.dp)
                ) {
                    // Hamburger menu for dropdown
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Menu),
                            contentDescription = "Localized description",
                            modifier = Modifier.size(50.dp),
                        )
                    }
                    if (isUserLoggedIn()) {
                        // Dropdown menu for logged-in users
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            DropdownMenuItem(
                                text = { Text("My Account") },
                                onClick = { /* TODO */ }
                            )
                            DropdownMenuItem(
                                text = { Text("Sign Out") },
                                onClick = { FirebaseAuth.getInstance().signOut() }
                            )
                        }
                    } else {
                        // Dropdown menu for non-logged in users
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            DropdownMenuItem(
                                text = { Text("Login") },
                                onClick = { /* TODO */ }
                            )
                            DropdownMenuItem(
                                text = { Text("Register") },
                                onClick = { /* TODO */ }
                            )
                        }
                    }
                }

                // Logo
                IconButton(onClick = { /* do something */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.teacup_placeholder),
                        contentDescription = "Logo",
                        modifier = Modifier.size(50.dp)
                    )
                }

                // Account button
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Default.AccountBox),
                        contentDescription = "Localized description",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Home),
                            contentDescription = "Localized description",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Coffee),
                            contentDescription = "Localized description",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Receipt),
                            contentDescription = "Localized description",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.ShoppingCart),
                            contentDescription = "Localized description",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        }
    ) {}
}

@Composable
fun BrowseMenu() {
    NavBars(Modifier)
    SearchBar(textFieldState = TextFieldState(), onSearch = {"String"}, searchResults = listOf("String"), modifier = Modifier)
}