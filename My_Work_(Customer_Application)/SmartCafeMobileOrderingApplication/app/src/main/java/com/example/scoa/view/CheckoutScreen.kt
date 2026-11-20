package com.example.scoa.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.scoa.model.database.CafeDatabase
import com.example.scoa.model.database.ProductDao
import com.example.scoa.model.entity.CartItemEntity
import com.example.scoa.model.entity.ProductEntity
import com.example.scoa.model.repository.CartRepository
import com.example.scoa.theme.*
import com.example.scoa.viewmodel.CartViewModel

@Composable
fun CheckoutScreen(navController: NavController) {
    val context = LocalContext.current
    val db = CafeDatabase.getDatabase(context)

    // Create CartViewModel and CartRepository
    val cartRepository = remember { CartRepository(db.cartDao(), db.productDao(), db.userDao()) }
    val cartViewModel = remember { CartViewModel(cartRepository) }

    // Create DAO
    val productDao = remember { db.productDao() }

    val userCart = cartViewModel.cartContents.collectAsState().value

    // Load cart contents when screen first appears
    LaunchedEffect(Unit) {
        cartViewModel.loadCart()
    }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Order Summary",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )

        DisplayOrderSummary(cart = userCart, dao = productDao, viewModel = cartViewModel, modifier = Modifier.weight(1f))
        // List of products in cart without the image and buttons
        // All items will have their details displayed in their own singular row


        // Total goes here (including delivery fee and other stuffs)

        // Delivery address goes here (also use button to change address)
        // Checkbox to save new address to profile

        // Button to confirm order
    }
}

@Composable
fun DisplayOrderSummary(cart: List<CartItemEntity>, dao: ProductDao, viewModel: CartViewModel, modifier: Modifier = Modifier) {
    // Fees incurred through the app
    val fees = mapOf(
        "Delivery" to 1.5,
        "Service" to 0.5,
        "Processing" to 0.75
    )

    // Calculate product subtotal using ProductDao
    var subtotal by remember { mutableStateOf(0.0) }
    LaunchedEffect(cart) { subtotal = cart.sumOf { item -> (dao.getProductById(item.productID)?.price ?: 0.0) * item.quantity } }

    val orderTotal = subtotal + fees.values.sum()

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp, 16.dp, 16.dp, 50.dp)) {
        // Scrollable section
        LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // Item heading
            item {
                Text(
                    "Your items",
                    modifier = Modifier.padding(top = 10.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // Column headings
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Name",
                        color = textDark,
                        modifier = Modifier.weight(6.7f)
                    )

                    Text(
                        "Price",
                        color = textDark,
                        modifier = Modifier.weight(1.5f),
                    )

                    Text(
                        "Qty",
                        color = textDark,
                        modifier = Modifier.weight(1.1f),
                    )

                    Text(
                        "Total",
                        color = textDark,
                        modifier = Modifier.weight(1.5f)
                    )
                }
            }

            // Cart product display
            items(cart.size, key = { cart[it].productID }) { product ->
                DisplayItemSummary(item = cart[product], dao = dao, viewModel = viewModel)
            }

            // Subtotal display
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Subtotal: £%.2f".format(subtotal),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }

            // Separator
            item {
                Text(
                    "------------------------------------",
                    modifier = Modifier,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // Individual fees
            items(fees.toList()) { fee ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(fee.first + " Fee", color = MaterialTheme.colorScheme.onPrimary)
                    Text("£%.2f".format(fee.second), color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }

        // Order total and confirm order button
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // Order total
            Text(
                "Order Total: £%.2f".format(orderTotal),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )

            // Confirm and Cancel buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Cancel
                Button(onClick = {}) {
                    Text("Cancel Order",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                // Confirm
                Button(onClick = {}) {
                    Text("Confirm Order",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun DisplayItemSummary(item: CartItemEntity, dao: ProductDao, viewModel: CartViewModel) {
    var product by remember { mutableStateOf<ProductEntity?>(null) }
    LaunchedEffect(item.productID) { product = dao.getProductById(item.productID) }
    val currentProd = product
    if (currentProd != null) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                //horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val textDark = MaterialTheme.colorScheme.onPrimary

                // Product name
                Text(
                    currentProd.name,
                    color = textDark,
                    modifier = Modifier.weight(6.7f)
                )

                // Product price
                Text(
                    "£%.2f".format(currentProd.price),
                    color = textDark,
                    modifier = Modifier.weight(1.5f),
                    textAlign = TextAlign.Start
                )

                // Product quantity
                Text(
                    item.quantity.toString(),
                    color = textDark,
                    modifier = Modifier.weight(1.1f),
                )

                // Total price for that product
                Text(
                    "£%.2f".format(currentProd.price * item.quantity),
                    color = textDark,
                    modifier = Modifier.weight(1.5f)
                )
            }
        }
    }
}