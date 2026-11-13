package com.example.scoa.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scoa.viewmodel.HomeViewModel

@Composable
fun HomeScreen (
    navController: NavController,
               viewModel: HomeViewModel = viewModel()
) {
    // Observes ViewModel states.
    val products by viewModel.products.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    // Display products here
    AppScaffold(navController) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Popular Products",
                style = MaterialTheme.typography.bodyLarge
            )

            // Displays loading indicator.
            if (isLoading) {
                CircularProgressIndicator()
            }

            // Displays an error if retrieval fails.
            else if (error.isNotEmpty()) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error
                )
            }

            // Displays retrieved products.
            else if (products.isEmpty()) {
                Text("No products found")
            }

            else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(products) { product ->

                        Column (
                            modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.primary)
                        ) {

                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text (product.id.toString())

                            Text(product.description)

                            Text("£${"%.2f".format(product.price)}")

                            Text(product.availability)

                            Text(product.category.toString())
                            Text(product.allergens.toString())
                        }
                    }
                }
            }
        }
    }
}