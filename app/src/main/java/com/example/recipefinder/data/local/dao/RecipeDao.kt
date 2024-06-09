package com.example.recipefinder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipefinder.data.local.entities.RecipeDetailEntity
import com.example.recipefinder.data.local.entities.RecipeEntity

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllRecipes(recipes: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRecipeDetail(recipe: RecipeDetailEntity)

    @Delete
    fun deleteRecipeDetail(recipe: RecipeDetailEntity)

    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipe_detail WHERE id = :id AND isFavorite = 1")
    suspend fun getById(id: Int): RecipeDetailEntity?

    @Query("UPDATE recipes SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, isFavorite: Boolean)
}