package com.example.recipefinder.feature.favorite

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.recipefinder.R
import com.example.recipefinder.data.local.entities.RecipeEntity
import com.example.recipefinder.utils.RecipeFinderDestination

@Composable
fun FavoriteScreen(navController: NavHostController, viewModel: FavoriteViewModel = hiltViewModel(), innerPadding: PaddingValues) {
    val favoriteRecipes by viewModel.favoriteRecipes.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refreshFavoriteRecipe()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (errorMessage != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = errorMessage ?: "",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            RecipeFavoriteList(favoriteRecipes, innerPadding, navController)
        }
    }
}

@Composable
fun RecipeFavoriteList(favoriteRecipes: List<RecipeEntity>, innerPadding: PaddingValues, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(favoriteRecipes.size) { index ->
            RecipeFavoriteItem(favoriteRecipe = favoriteRecipes[index], navController = navController)
        }
    }
}

@Composable
fun RecipeFavoriteItem(favoriteRecipe: RecipeEntity, navController: NavHostController, viewModel: FavoriteViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = favoriteRecipe.id) {
        val favoriteStatus = viewModel.getFavoriteStatus(favoriteRecipe.id)
        isFavorite = favoriteStatus
    }

    Card(
        onClick = {
            navController.navigate("${RecipeFinderDestination.FAVORITE_DETAIL}/${favoriteRecipe.id}")
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(favoriteRecipe.image),
                    contentDescription = favoriteRecipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = favoriteRecipe.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                painter = painterResource(
                    id = if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        isFavorite = !isFavorite
                        viewModel.updateFavoriteStatus(favoriteRecipe.id, isFavorite)
                        if (!isFavorite) {
                            viewModel.removeFavoriteRecipe(favoriteRecipe.id)
                            Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
                        }
                    },
                tint = Color.Red
            )
        }
    }
}