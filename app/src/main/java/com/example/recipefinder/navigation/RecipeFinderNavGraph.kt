package com.example.recipefinder.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipefinder.feature.favorite.FavoriteScreen
import com.example.recipefinder.feature.favoriterecipdetail.FavoriteRecipeDetailScreen
import com.example.recipefinder.feature.home.HomeScreen
import com.example.recipefinder.feature.recipedetail.RecipeDetailScreen
import com.example.recipefinder.feature.search.SearchScreen
import com.example.recipefinder.utils.BottomNavItem
import com.example.recipefinder.utils.RecipeFinderDestination

@Composable
fun RecipeFinderNavGraph(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = RecipeFinderDestination.HOME
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(
            route = RecipeFinderDestination.HOME
        ){
            HomeScreen(innerPadding = paddingValues, navController = navController)
        }
        composable(
            route = "${RecipeFinderDestination.DETAIL}/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
            RecipeDetailScreen(
                paddingValues = paddingValues,
                recipeId = recipeId
            )
        }
        composable(BottomNavItem.Search.route) {
            SearchScreen(navHostController = navController)
        }
        composable(BottomNavItem.Favorites.route) {
            FavoriteScreen(navController = navController, innerPadding = paddingValues)
        }
        composable(
            route = "${RecipeFinderDestination.FAVORITE_DETAIL}/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
            FavoriteRecipeDetailScreen(
                innerPadding = paddingValues,
                recipeId = recipeId
            )
        }
    }
}