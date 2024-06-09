package com.example.recipefinder.feature.recipedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.data.remote.model.RecipeDetailModel
import com.example.recipefinder.data.remote.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    private val _recipeDetailModel = MutableStateFlow<RecipeDetailModel?>(null)
    val recipeDetailModel : StateFlow<RecipeDetailModel?> = _recipeDetailModel

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage : StateFlow<String?> = _errorMessage


    fun getRecipeDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRecipeById(id).catch {
                e->_errorMessage.value = e.message
            }.collect{response->
                if (response.isSuccessful){
                    _recipeDetailModel.value = response.body()
                }else{
                    _errorMessage.value = response.message()
                }
            }
        }
    }
}