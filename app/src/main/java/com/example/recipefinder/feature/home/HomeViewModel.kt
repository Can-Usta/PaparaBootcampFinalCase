package com.example.recipefinder.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.data.local.dao.RecipeDao
import com.example.recipefinder.data.local.entities.RecipeDetailEntity
import com.example.recipefinder.data.local.entities.RecipeEntity
import com.example.recipefinder.data.remote.model.Recipe
import com.example.recipefinder.data.remote.model.RecipeDetailModel
import com.example.recipefinder.data.remote.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RecipeRepository, private val recipeDao: RecipeDao) : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() {
        viewModelScope.launch {
            repository.getAllRecipe()
                .catch { e -> _errorMessage.value = e.message }
                .collect { response ->
                    if (response.isSuccessful) {
                        val recipeResponse = response.body()
                        if (recipeResponse != null) {
                            _recipes.value = recipeResponse.results
                            insertRecipesToDatabase(recipeResponse.results.map {
                                RecipeEntity(
                                    id = it.id,
                                    title = it.title,
                                    image = it.image,
                                    isFavorite = it.isFavorite
                                )
                            })
                        } else {
                            _errorMessage.value = "No recipes found"
                        }
                    } else {
                        _errorMessage.value = "Failed to fetch recipes: ${response.code()}"
                    }
                }
        }
    }

    private fun insertRecipesToDatabase(recipes: List<RecipeEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRecipe(recipes)
        }
    }

    fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteStatus(recipeId, isFavorite)
            if (isFavorite) {
                try {
                    repository.getRecipeById(recipeId)
                        .catch { e -> _errorMessage.value = e.message }
                        .collect { response ->
                            if (response.isSuccessful) {
                                val recipeDetail = response.body()
                                if (recipeDetail != null) {
                                    val recipeDetailEntity = RecipeDetailEntity(
                                        id = recipeDetail.id,
                                        title = recipeDetail.title,
                                        image = recipeDetail.image,
                                        imageType = recipeDetail.imageType,
                                        servings = recipeDetail.servings,
                                        readyInMinutes = recipeDetail.readyInMinutes,
                                        license = recipeDetail.license,
                                        sourceName = recipeDetail.sourceName,
                                        sourceUrl = recipeDetail.sourceUrl,
                                        spoonacularSourceUrl = recipeDetail.spoonacularSourceUrl,
                                        healthScore = recipeDetail.healthScore,
                                        spoonacularScore = recipeDetail.spoonacularScore,
                                        pricePerServing = recipeDetail.pricePerServing,
                                        cheap = recipeDetail.cheap,
                                        creditsText = recipeDetail.creditsText,
                                        gaps = recipeDetail.gaps,
                                        glutenFree = recipeDetail.glutenFree,
                                        instructions = recipeDetail.instructions,
                                        ketogenic = recipeDetail.ketogenic,
                                        lowFodmap = recipeDetail.lowFodmap,
                                        sustainable = recipeDetail.sustainable,
                                        vegan = recipeDetail.vegan,
                                        vegetarian = recipeDetail.vegetarian,
                                        veryHealthy = recipeDetail.veryHealthy,
                                        veryPopular = recipeDetail.veryPopular,
                                        whole30 = recipeDetail.whole30,
                                        weightWatcherSmartPoints = recipeDetail.weightWatcherSmartPoints,
                                        summary = recipeDetail.summary,
                                        winePairing = recipeDetail.winePairing.toString(),
                                        isFavorite = true
                                    )
                                    repository.insertRecipeDetail(recipeDetailEntity)
                                }
                            }
                        }
                } catch (e: Exception) {
                    _errorMessage.value = e.message
                }
            }
        }
    }
     suspend fun getFavoriteStatus(recipeId: Int): Boolean {
         return withContext(Dispatchers.IO) {
             recipeDao.getFavoriteStatus(recipeId)
         }
    }
}