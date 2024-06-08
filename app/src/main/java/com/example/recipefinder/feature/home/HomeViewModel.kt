package com.example.recipefinder.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.data.model.Recipe
import com.example.recipefinder.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes : StateFlow<List<Recipe>> = _recipes

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
                        } else {
                            _errorMessage.value = "No recipes found"
                        }
                    } else {
                        _errorMessage.value = "Failed to fetch recipes: ${response.code()}"
                    }
                }
        }
    }
}