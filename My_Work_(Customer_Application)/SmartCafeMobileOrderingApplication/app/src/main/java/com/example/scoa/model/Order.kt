package com.example.scoa.model

class Order {
    var orderID: String = "";
    var userID: String = "";
    var products: List<CartItem> = emptyList();
    var status: String = "";
}