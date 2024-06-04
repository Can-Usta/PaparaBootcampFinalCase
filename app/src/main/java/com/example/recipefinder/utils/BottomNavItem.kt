package com.example.recipefinder.utils

import com.example.recipefinder.R

sealed class BottomNavItem(val title : String, val icon : Int, val route : String) {
    data object Home : BottomNavItem("Home", R.drawable.baseline_home_filled_24, "home")
    data object Favorites : BottomNavItem("Favorites", R.drawable.baseline_favorite_black, "favorites")
    data object Search : BottomNavItem("Search", R.drawable.baseline_search_24, "search")
}