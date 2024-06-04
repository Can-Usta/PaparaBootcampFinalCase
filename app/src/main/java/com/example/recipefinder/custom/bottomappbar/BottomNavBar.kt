package com.example.recipefinder.custom.bottomappbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.recipefinder.utils.BottomNavItem

@Composable
fun RecipeBottomNavigationBar(navController: NavController) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.Search, BottomNavItem.Favorites)

    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(BottomNavItem.Home.route) }


    items.forEachIndexed{ index, item ->
        if (currentRoute == item.route) {
            selectedItem = index
        }
    }
    NavigationBar {
        items.forEachIndexed{ index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                selected = selectedItem == index,
                label = { Text(text = item.title)},
                icon = {Icon(painter = painterResource(id = item.icon), contentDescription = item.title) },
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route)
                })
        }
    }
}