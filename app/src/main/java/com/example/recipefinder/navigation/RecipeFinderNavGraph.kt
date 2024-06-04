package com.example.recipefinder.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipefinder.feature.favorite.FavoriteScreen
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
    NavHost(navController = navController, startDestination = startDestination,modifier = modifier) {
        composable(
            route = RecipeFinderDestination.HOME
        ){
            HomeScreen(innerPadding = paddingValues,navController=navController)
        }
        composable(
            route = RecipeFinderDestination.DETAIL
        ){
            RecipeDetailScreen(paddingValues = paddingValues,navController=navController)
        }
        composable(BottomNavItem.Search.route){
            SearchScreen(navHostController = navController)
        }
        composable(BottomNavItem.Favorites.route){
            FavoriteScreen(navController = navController)
        }
    }
}