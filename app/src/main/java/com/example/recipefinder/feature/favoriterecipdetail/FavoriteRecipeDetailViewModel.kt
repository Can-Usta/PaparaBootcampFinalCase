package com.example.recipefinder.feature.favoriterecipdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.data.local.entities.RecipeDetailEntity
import com.example.recipefinder.data.remote.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipeDetailViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {
    private val _favoriteRecipeDetail = MutableStateFlow<RecipeDetailEntity?>(null)
    val favoriteRecipeDetail : StateFlow<RecipeDetailEntity?> = _favoriteRecipeDetail

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage : StateFlow<String?> = _errorMessage


    fun getFavoriteRecipeDetail(id: Int) {
        viewModelScope.launch {
            repository.getRecipeDetailFromDb(id).catch {e->
                _errorMessage.value = e.message
            }.collect{recipeDetail->
                if (recipeDetail != null){
                    _favoriteRecipeDetail.value = recipeDetail
                }else{
                    _errorMessage.value = "No data found"
                }
            }
        }

    }
}