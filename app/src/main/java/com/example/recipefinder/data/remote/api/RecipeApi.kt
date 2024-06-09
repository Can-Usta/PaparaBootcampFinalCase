package com.example.recipefinder.data.remote.api

import com.example.recipefinder.data.remote.model.RecipeDetailModel
import com.example.recipefinder.data.remote.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/complexSearch")
    suspend fun getAllRecipes(
        @Query("number") number: Int = 85,
        @Query("offset") offset: Int = 0,
        @Query("apiKey") apiKey: String
    ): Response<RecipeResponse>

    @GET("recipes/{id}/information")
    suspend fun getRecipeById(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Response<RecipeDetailModel>

}