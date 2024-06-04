package com.example.recipefinder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipefinder.custom.bottomappbar.RecipeBottomNavigationBar
import com.example.recipefinder.custom.topapbar.RecipeTopAppBar
import com.example.recipefinder.navigation.RecipeFinderNavGraph
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

            Scaffold(
                topBar = { RecipeTopAppBar() },
                bottomBar = {
                    BottomAppBar {
                        RecipeBottomNavigationBar(navController = navController)
                    }
                }) { paddingValues ->
                RecipeFinderNavGraph(
                    paddingValues = paddingValues,
                    navController = navController
                )
            }
        }

    }
}



