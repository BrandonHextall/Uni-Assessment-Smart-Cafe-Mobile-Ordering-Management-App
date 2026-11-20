package com.example.scoa.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scoa.model.database.CafeDatabase
import com.example.scoa.model.repository.CartRepository
import com.example.scoa.viewmodel.CartViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import com.example.scoa.model.database.ProductDao
import com.example.scoa.model.entity.CartItemEntity
import com.example.scoa.model.entity.ProductEntity

@Composable
fun CartScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val db = CafeDatabase.getDatabase(context)

    // Create CartViewModel and CartRepository
    val cartRepository = remember { CartRepository(db.cartDao(), db.productDao(), db.userDao()) }
    val cartViewModel = remember { CartViewModel(cartRepository) }

    // Save cart items with products to avoid re-collecting later
    val cartWithProducts = cartViewModel.cartItemsWithProducts.collectAsState().value

    // Create DAOs
    val cartDao = remember { db.cartDao() }
    val productDao = remember { db.productDao() }
    val userDao = remember { db.userDao() }

    val userCart = cartViewModel.cartContents.collectAsState().value

    // Load cart contents when screen first appears
    LaunchedEffect(Unit) {
        cartViewModel.loadCart()
    }

    AppScaffold(navController) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Your Cart", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onPrimary)

            Log.d("CART_SCREEN_DEBUG", "User cart: $userCart")

            if (userCart.isEmpty()) Text("Your cart is currently empty.", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onPrimary)
            else {
                DisplayCartContents(cart = cartWithProducts, viewModel = cartViewModel, modifier = Modifier.weight(1f))

                // Subtotal
                val subtotal = cartWithProducts.sumOf { it.first.price * it.first.quantity }
                //LaunchedEffect(userCart) { subtotal = userCart.sumOf { it.price * it.quantity } }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Subtotal: £%.2f".format(subtotal),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Button(onClick = {
                        // Checkout screen
                        navController.navigate("checkout")
                    }) {
                        Text("Checkout", fontSize = MaterialTheme.typography.bodyLarge.fontSize)
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayCartContents(cart: List<Pair<CartItemEntity, ProductEntity>>, viewModel: CartViewModel, modifier: Modifier = Modifier) {
    // Display cart contents
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(cart, key = { it.first.productID }) { product ->
            DisplayCartItem(item = product, viewModel = viewModel)
        }
    }
}

@Composable
fun DisplayCartItem(item: Pair<CartItemEntity, ProductEntity>, viewModel: CartViewModel) {
    val currentItem = item.first
    val currentProduct = item.second

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Product image
            val imageResource = LocalContext.current.resources.getIdentifier(currentProduct.imageName, "drawable", LocalContext.current.packageName)
            if (imageResource != 0) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = currentProduct.name,
                    modifier = Modifier.size(75.dp).clip(RoundedCornerShape(10.dp)),
                )
            }

            // Product info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    currentProduct.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Quantity: ${currentItem.quantity}", style = MaterialTheme.typography.titleMedium)

                    // Decrement button
                    IconButton(
                        onClick = { viewModel.decrementQuantity(currentItem) },
                        modifier = Modifier.size(40.dp).padding(start = 15.dp)
                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Remove),
                            contentDescription = "Decrease quantity",
                        )
                    }

                    // Increment button
                    IconButton(
                        onClick = { viewModel.incrementQuantity(currentItem) },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Add),
                            contentDescription = "Increase quantity",
                        )
                    }
                }

                // Cumulative product price
                Text(
                    "£${"%.2f".format(currentItem.price * currentItem.quantity)}",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            // Remove button
            Column() {
                IconButton(
                    onClick = {
                        // Remove product from cart
                        viewModel.removeFromCart(currentItem.productID)
                    },
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Default.Delete),
                        contentDescription = "Remove",
                        modifier = Modifier.size(50.dp),
                    )
                }
            }
        }
    }
}