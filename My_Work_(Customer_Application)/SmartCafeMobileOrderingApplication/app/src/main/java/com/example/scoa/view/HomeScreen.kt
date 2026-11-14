package com.example.scoa.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scoa.model.entity.ProductEntity
import com.example.scoa.theme.typography
import com.example.scoa.viewmodel.HomeViewModel

/*
    Plan for menu:
    Each product only displays name and image on main menu

    Once clicked, a popup appears with more details
    Where the product can be added to cart

    if user is not logged in, a second popup appears asking if
    the user wants to log in or register
 */


@Composable
fun HomeScreen (
    navController: NavController,
               viewModel: HomeViewModel = viewModel()
) {
    // Observes ViewModel states.
    val products by viewModel.products.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    // Variable for product popup
    var selectedProduct: ProductEntity? by remember { mutableStateOf<ProductEntity?>(null) }

    // Display products here
    AppScaffold(navController) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(text = "Browse Products", style = MaterialTheme.typography.bodyLarge)

            // Displays loading indicator.
            if (isLoading) CircularProgressIndicator()

            // Displays an error if retrieval fails.
            else if (error.isNotEmpty()) Text (text = error, color = MaterialTheme.colorScheme.error)

            // Displays a message if no products are found.
            else if (products.isEmpty()) Text("No products found")

            // Displays retrieved products.
            else {
                MakeProductGrid(products, onProductClick = { product ->
                    selectedProduct = product
                })

                selectedProduct?.let { product ->
                    ProductPopup(
                        product = product,
                        onDismiss = {
                            selectedProduct = null
                        }
                    )
                }
            }
        }
    }
}

// Creates grid where products are displayed
@Composable
fun MakeProductGrid(products: List<ProductEntity>, onProductClick: (ProductEntity) -> Unit) {
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(products) { product ->
            MakeProductCard(product, onClick = {
                onProductClick(product)
            })
        }
    }
}

// Creates a card for each product
@Composable
fun MakeProductCard(product: ProductEntity, onClick: () -> Unit) {
    Surface(onClick = onClick) {
        Column(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
        ) {
            val context = LocalContext.current

            // Gets image associated with Product
            val imageResource = remember(product.imageName) {
                context.resources.getIdentifier(
                    product.imageName,
                    "drawable",
                    context.packageName
                )
            }

            // If image is found, display it.
            if (imageResource != 0) {
                Image (
                    painter = painterResource(id = imageResource),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize().aspectRatio(1f)
                )
            }
            Text (
                text = product.name,
                textAlign = TextAlign.Center,
                style = typography.titleMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductPopup(product: ProductEntity, onDismiss: () -> Unit) {
    // LocalContext retrieves Android app resources like Drawable images, Strings, Colours and application information
    val context = LocalContext.current

    // Gets image associated with Product
    val imageResource = remember(product.imageName) {
        context.resources.getIdentifier(
            product.imageName,
            "drawable",
            context.packageName
        )
    }
    AlertDialog (
        title = { product.name },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton (
                onClick = {
                    // Add product to basket
                }
            ) {
                Text("Add to basket")
            }
        },
        dismissButton = {
            TextButton (
                onClick = onDismiss
            ) {
                Text("Dismiss")
            }
        },
        text = {
            var quantity by rememberSaveable { mutableIntStateOf(0) }
            Column() {
                Text (
                    text = product.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
                )
                Text(product.description)
                Image (
                    painter = painterResource(imageResource),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxWidth().aspectRatio(1.3f).padding(0.dp, 10.dp)
                )
                Text("Price: £%.2f".format(product.price))
                Text(if (product.allergens.isEmpty()) "None" else "Allergens: ${product.allergens.joinToString(", ")}")
                Text("Availability: ${product.availability}")
                Text("Category: ${product.category}") // Needs to be modified to retrieve category from Firebase
                OutlinedTextField (
                    value = quantity.toString(),
                    onValueChange = { quantity = it.toInt() },
                    label = { Text("Quantity") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(80.dp)
                )
            }
        }
    )
}