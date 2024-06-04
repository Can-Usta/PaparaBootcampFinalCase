package com.example.recipefinder.data.api

import com.example.recipefinder.data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/complexSearch")
    suspend fun getAllRecipes(
        @Query("number") number: Int = 30,
        @Query("offset") offset: Int = 0,
        @Query("apiKey") apiKey: String
    ): RecipeResponse

}