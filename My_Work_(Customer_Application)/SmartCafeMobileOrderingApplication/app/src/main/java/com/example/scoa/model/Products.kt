package com.example.scoa.model

import com.example.scoa.model.entity.ProductEntity

// Predefined café products for initial database population
val initialProducts = listOf(

    ProductEntity(
        name = "Turkey & Swiss Sandwich",
        description = "Sliced turkey and Swiss cheese with mayo on soft bread",
        price = 6.50,
        imageName = "turkey_swiss_sandwich",
        allergens = listOf("Gluten", "Dairy"),
        availability = "All day",
        category = 1
    ),

    ProductEntity(
        name = "BLT on Sourdough",
        description = "Crispy bacon, lettuce and tomato on toasted sourdough",
        price = 6.20,
        imageName = "blt_on_sourdough",
        allergens = listOf("Gluten"),
        availability = "All day",
        category = 1
    ),

    ProductEntity(
        name = "Iced Vanilla Latte",
        description = "Espresso, milk and vanilla syrup poured over ice",
        price = 4.00,
        imageName = "iced_vanilla_latte",
        allergens = listOf("Dairy"),
        availability = "All day",
        category = 2
    ),

    ProductEntity(
        name = "Hot Chocolate",
        description = "Rich melted chocolate mixed with steamed milk",
        price = 3.60,
        imageName = "hot_chocolate",
        allergens = listOf("Dairy"),
        availability = "All day",
        category = 3
    ),

    ProductEntity(
        name = "Avocado & Egg Sandwich",
        description = "Smashed avocado and soft-boiled egg on seeded bread",
        price = 6.90,
        imageName = "avocado_egg_sandwich",
        allergens = listOf("Gluten", "Egg"),
        availability = "Until 2pm",
        category = 1
    ),

    ProductEntity(
        name = "Mango Smoothie",
        description = "Blended ripe mango with apple juice and ice",
        price = 4.50,
        imageName = "mango_smoothie",
        allergens = emptyList(),
        availability = "All day",
        category = 2
    ),

    ProductEntity(
        name = "Espresso",
        description = "Intense single shot of freshly pulled coffee",
        price = 2.40,
        imageName = "espresso",
        allergens = emptyList(),
        availability = "All day",
        category = 3
    ),

    ProductEntity(
        name = "Ham & Cheese Croissant Sandwich",
        description = "Warm ham and melted cheese inside a flaky croissant",
        price = 5.80,
        imageName = "ham_cheese_croissant_sandwich",
        allergens = listOf("Gluten", "Dairy"),
        availability = "Until 3pm",
        category = 1
    ),

    ProductEntity(
        name = "Iced Americano",
        description = "Espresso diluted with cold water and served over ice",
        price = 3.20,
        imageName = "iced_americano",
        allergens = emptyList(),
        availability = "All day",
        category = 2
    ),

    ProductEntity(
        name = "Cortado",
        description = "Equal parts espresso and warm silky milk",
        price = 3.10,
        imageName = "cortado",
        allergens = listOf("Dairy"),
        availability = "All day",
        category = 3
    ),

    ProductEntity(
        name = "Tuna Melt",
        description = "Tuna mayo topped with melted cheddar, grilled",
        price = 6.40,
        imageName = "tuna_melt",
        allergens = listOf("Gluten", "Dairy", "Fish"),
        availability = "All day",
        category = 1
    ),

    ProductEntity(
        name = "Iced Matcha Latte",
        description = "A refreshing blend of finely ground Japanese green tea (matcha) and milk, served over ice for a smooth, creamy drink with a distinctive earthy flavour.",
        price = 4.20,
        imageName = "iced_matcha_latte",
        allergens = listOf("Dairy"),
        availability = "All day",
        category = 2
    ),

    ProductEntity(
        name = "Fresh Orange Juice",
        description = "Fresh, zesty orange juice with a naturally sweet and tangy citrus flavour, served chilled for a refreshing drink.",
        price = 3.80,
        imageName = "fresh_orange_juice",
        allergens = emptyList(),
        availability = "All day",
        category = 2
    ),

    ProductEntity(
        name = "Cappuccino",
        description = "Espresso topped with thick steamed milk foam",
        price = 3.40,
        imageName = "cappuccino",
        allergens = listOf("Dairy"),
        availability = "All day",
        category = 3
    ),

    ProductEntity(
        name = "Caprese Panini",
        description = "Mozzarella, tomato and basil pressed in ciabatta",
        price = 6.80,
        imageName = "caprese_panini",
        allergens = listOf("Gluten", "Dairy"),
        availability = "All day",
        category = 1
    ),

    ProductEntity(
        name = "Cold Brew Coffee",
        description = "Smooth, low-acidity coffee steeped cold for 12 hours",
        price = 3.60,
        imageName = "cold_brew_coffee",
        allergens = emptyList(),
        availability = "All day",
        category = 2
    ),

    ProductEntity(
        name = "Chai Latte",
        description = "Spiced black tea blended with steamed milk",
        price = 3.80,
        imageName = "chai_latte",
        allergens = listOf("Dairy"),
        availability = "All day",
        category = 3
    ),

    ProductEntity(
        name = "Chicken Pesto Sandwich",
        description = "Grilled chicken with basil pesto and rocket",
        price = 7.20,
        imageName = "chicken_pesto_sandwich",
        allergens = listOf("Gluten", "Dairy", "Nuts"),
        availability = "All day",
        category = 1
    ),

    ProductEntity(
        name = "Strawberry Lemonade",
        description = "Fresh lemon juice mixed with strawberry purée",
        price = 3.50,
        imageName = "strawberry_lemonade",
        allergens = emptyList(),
        availability = "All day",
        category = 2
    ),

    ProductEntity(
        name = "Flat White",
        description = "Double espresso with velvety microfoam milk",
        price = 3.50,
        imageName = "flat_white",
        allergens = listOf("Dairy"),
        availability = "All day",
        category = 3
    ),

    ProductEntity(
        name = "Borscht",
        description = "Vibrant savoury-sour soup with rich beef, beetroot, cabbage, potatoes, carrots and onion",
        price = 7.50,
        imageName = "borscht",
        allergens = emptyList(),
        availability = "All day",
        category = 4
    )
)
