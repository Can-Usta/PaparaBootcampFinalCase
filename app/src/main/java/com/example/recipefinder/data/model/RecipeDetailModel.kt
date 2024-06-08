package com.example.recipefinder.data.model

data class RecipeDetailModel(
    val id: Int,
    val title: String,
    val image: String,
    val ingredients: List<ExtendedIngredient>,
    val instructions: String
)

data class ExtendedIngredient(
    val id: Int,
    val name: String,
    val amount: Double,
    val unit: String
)

