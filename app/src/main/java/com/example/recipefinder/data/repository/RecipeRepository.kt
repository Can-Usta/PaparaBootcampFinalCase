package com.example.recipefinder.data.repository

import com.example.recipefinder.data.api.RecipeApi
import com.example.recipefinder.data.model.RecipeResponse
import com.example.recipefinder.utils.Constant.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val api : RecipeApi){

    fun getAllRecipe(): Flow<RecipeResponse> = flow {
        val response = api.getAllRecipes(apiKey = API_KEY)
        emit(response)
    }

}