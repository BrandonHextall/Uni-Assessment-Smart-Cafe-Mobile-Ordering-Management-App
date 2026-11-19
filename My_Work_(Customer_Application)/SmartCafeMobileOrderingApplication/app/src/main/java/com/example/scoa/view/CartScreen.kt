package com.example.scoa.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.scoa.model.database.ProductDao
import com.example.scoa.model.entity.CartItemEntity
import com.example.scoa.model.entity.ProductEntity

@Composable
fun CartScreen (
    navController: NavController,
) {
    val context = LocalContext.current
    val db = CafeDatabase.getDatabase(context)

    // Create CartViewModel and CartRepository
    val cartRepository = remember { CartRepository(db.cartDao(), db.productDao(), db.userDao()) }
    val cartViewModel = remember { CartViewModel(cartRepository) }

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
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Your Cart", style = MaterialTheme.typography.headlineMedium)

            Log.d("CART_SCREEN_DEBUG", "User cart: $userCart")

            DisplayCartContents(cart = userCart, dao = productDao)

            // Subtotal
            var subtotal by remember { mutableStateOf(0.0) }
            LaunchedEffect(userCart) {
                subtotal = userCart.sumOf { it.price * it.quantity }
            }
            Text("Subtotal: £%.2f".format(subtotal), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun DisplayCartContents(cart: List<CartItemEntity>, dao: ProductDao) {
    // Display cart contents
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(cart.size, key = { cart[it].productID }) { product ->
            DisplayCartItem(item = cart[product], dao = dao)
        }
    }
}

@Composable
fun DisplayCartItem(item: CartItemEntity, dao: ProductDao) {
    var product by remember { mutableStateOf<ProductEntity?>(null) }
    LaunchedEffect(item.productID) {
        product = dao.getProductById(item.productID)
    }
    val currentProduct = product
    if (currentProduct != null) {
        Card (
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth().padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Product image
                val imageResource = LocalContext.current.resources.getIdentifier (currentProduct.imageName, "drawable", LocalContext.current.packageName)
                if (imageResource != 0) {
                    Image (
                        painter = painterResource(id = imageResource),
                        contentDescription = currentProduct.name,
                        modifier = Modifier.size(75.dp).clip(RoundedCornerShape(10.dp)),
                    )
                }

                // Product info
                Column(modifier = Modifier.weight(1f)) {
                    Text (
                        currentProduct.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text (
                        "Quantity: ${item.quantity}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text (
                        "£${"%.2f".format(item.price * item.quantity)}",
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                // Remove button
                Column() {
                    IconButton (
                        onClick = { /*TODO*/ },
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
}