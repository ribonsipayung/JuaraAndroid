package com.ribonsipayung.foodrecipes.model

data class Recipe(
    val name: String,
    val description: String,
    val image: Int,
    val ingredients: List<String>,
    val instructions: List<String>
)
