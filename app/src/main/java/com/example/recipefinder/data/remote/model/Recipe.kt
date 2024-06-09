package com.example.recipefinder.data.remote.model

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val isFavorite: Boolean
)
