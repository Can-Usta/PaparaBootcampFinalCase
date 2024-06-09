package com.example.recipefinder.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_detail")
data class RecipeDetailEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val title: String = "",
    val image: String = "",
    val imageType: String = "",
    val servings: Int = 0,
    val readyInMinutes: Int = 0,
    val license: String? = null,
    val sourceName: String = "",
    val sourceUrl: String = "",
    val spoonacularSourceUrl: String = "",
    val healthScore: Double = 0.0,
    val spoonacularScore: Double = 0.0,
    val pricePerServing: Double = 0.0,
    val cheap: Boolean = false,
    val creditsText: String = "",
    val gaps: String = "",
    val glutenFree: Boolean = false,
    val instructions: String = "",
    val ketogenic: Boolean = false,
    val lowFodmap: Boolean = false,
    val sustainable: Boolean = false,
    val vegan: Boolean = false,
    val vegetarian: Boolean = false,
    val veryHealthy: Boolean = false,
    val veryPopular: Boolean = false,
    val whole30: Boolean = false,
    val weightWatcherSmartPoints: Int = 0,
    val summary: String = "",
    val winePairing: String = "",
    val isFavorite :Boolean = false
)