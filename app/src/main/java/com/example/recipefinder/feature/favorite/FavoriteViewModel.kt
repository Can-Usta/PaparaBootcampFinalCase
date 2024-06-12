package com.example.recipefinder.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.data.local.dao.RecipeDao
import com.example.recipefinder.data.local.entities.RecipeEntity
import com.example.recipefinder.data.remote.model.Recipe
import com.example.recipefinder.data.remote.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val recipeDao: RecipeDao
) : ViewModel() {

    private val _favoriteRecipes = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val favoriteRecipes: StateFlow<List<RecipeEntity>> = _favoriteRecipes

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getAllFavoriteRecipe()
    }

    private fun getAllFavoriteRecipe() {
        viewModelScope.launch {
            try {
                val favoriteRecipes = recipeRepository.getAllFavoriteRecipe()
                _favoriteRecipes.value = favoriteRecipes
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    suspend fun getFavoriteStatus(recipeId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            recipeDao.getFavoriteStatus(recipeId)
        }
    }

    fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.updateFavoriteStatus(recipeId, isFavorite)
        }
    }

    fun removeFavoriteRecipe(recipeId: Int) {
        _favoriteRecipes.value = _favoriteRecipes.value.filter { it.id != recipeId }
    }

    fun refreshFavoriteRecipe() {
        getAllFavoriteRecipe()
    }
}