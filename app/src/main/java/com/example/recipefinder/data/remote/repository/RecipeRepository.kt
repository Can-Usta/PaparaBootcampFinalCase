package com.example.recipefinder.data.remote.repository

import android.util.Log
import com.example.recipefinder.data.local.dao.RecipeDao
import com.example.recipefinder.data.local.entities.RecipeDetailEntity
import com.example.recipefinder.data.local.entities.RecipeEntity
import com.example.recipefinder.data.remote.api.RecipeApi
import com.example.recipefinder.data.remote.model.RecipeDetailModel
import com.example.recipefinder.data.remote.model.RecipeResponse
import com.example.recipefinder.utils.Constant.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val api : RecipeApi, private val recipeDao: RecipeDao){

    fun getAllRecipe(): Flow<Response<RecipeResponse>> = flow {
        val response = api.getAllRecipes(apiKey = API_KEY)
        emit(response)
    }

    fun getRecipeById(id: Int): Flow<Response<RecipeDetailModel>> = flow {
        val response = api.getRecipeById(id= id, apiKey = API_KEY)
        emit(response)
    }

    suspend fun insertRecipe(recipe: List<RecipeEntity>) {
        recipeDao.insertAllRecipes(recipe)
    }
    suspend fun getAllFavoriteRecipe() : List<RecipeEntity>{
        return recipeDao.getAllFavorite()
    }

    suspend fun insertRecipeDetail(recipeDetail: RecipeDetailEntity) {
        recipeDao.insertRecipeDetail(recipeDetail)
    }
    suspend fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean) {
        recipeDao.updateFavorite(recipeId, isFavorite)
    }

    suspend fun getRecipeDetailFromDb(recipeId: Int): Flow<RecipeDetailEntity?> = flow {
        val recipeDetail = recipeDao.getRecipeDetailById(recipeId)
        emit(recipeDetail)
    }

}