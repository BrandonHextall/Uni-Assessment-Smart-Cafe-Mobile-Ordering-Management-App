package com.example.scoa.model

class Product {
    var id: Int = 1;
    var name: String = "";
    var description: String = "";
    var availability: String = "";
    var price: Double = 0.0;
    var imageURL: String = "";
    var category: Int = 1;
    var allergens: ArrayList<String> = ArrayList<String>();
}