package com.example.recipefinder.data.repository

import android.util.Log
import com.example.recipefinder.data.api.RecipeApi
import com.example.recipefinder.data.model.RecipeDetailModel
import com.example.recipefinder.data.model.RecipeResponse
import com.example.recipefinder.utils.Constant.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val api : RecipeApi){

    fun getAllRecipe(): Flow<Response<RecipeResponse>> = flow {
        val response = api.getAllRecipes(apiKey = API_KEY)
        emit(response)
    }

    fun getRecipeById(id: Int): Flow<Response<RecipeDetailModel>> = flow {
        val response = api.getRecipeById(id= id, apiKey = API_KEY)
        emit(response)
    }

}