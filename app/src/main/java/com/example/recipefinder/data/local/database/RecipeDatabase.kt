package com.example.recipefinder.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipefinder.data.local.dao.RecipeDao
import com.example.recipefinder.data.local.entities.RecipeDetailEntity
import com.example.recipefinder.data.local.entities.RecipeEntity

@Database(entities = [RecipeEntity::class,RecipeDetailEntity::class], version = 3, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase(){
    abstract fun recipeDao(): RecipeDao

}