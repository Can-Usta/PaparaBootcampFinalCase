package com.example.recipefinder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipefinder.custom.bottomappbar.RecipeBottomNavigationBar
import com.example.recipefinder.custom.topapbar.RecipeTopAppBar
import com.example.recipefinder.navigation.RecipeFinderNavGraph
import com.example.recipefinder.utils.BottomNavItem
import com.example.recipefinder.utils.RecipeFinderDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            val bottomBarVisibility = remember { mutableStateOf(true) }

            val navBackStackEntry by navController.currentBackStackEntryAsState()

            bottomBarVisibility.value = when (navBackStackEntry?.destination?.route) {
                RecipeFinderDestination.HOME,
                BottomNavItem.Favorites.route, -> true
                "${RecipeFinderDestination.DETAIL}/{recipeId}" -> false
                else -> true
            }

            Scaffold(
                topBar = { RecipeTopAppBar() },
                bottomBar = {
                    if (bottomBarVisibility.value) {
                        BottomAppBar {
                            RecipeBottomNavigationBar(navController = navController)
                        }
                    }
                }
            ) { paddingValues ->
                RecipeFinderNavGraph(
                    paddingValues = paddingValues,
                    navController = navController
                )
            }
        }
    }
}



