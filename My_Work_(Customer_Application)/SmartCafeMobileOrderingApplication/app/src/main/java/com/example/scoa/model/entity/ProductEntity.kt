package com.example.scoa.model.entity

class ProductEntity {
    var id: Int = 1;
    var name: String = "";
    var description: String = "";
    var availability: String = "";
    var price: Double = 0.0;
    var imageName: String = "";
    var category: Int = 1;
    var allergens: ArrayList<String> = ArrayList<String>();
}