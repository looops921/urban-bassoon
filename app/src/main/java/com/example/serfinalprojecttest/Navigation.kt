package com.example.serfinalprojecttest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.serfinalprojecttest.ViewModels.ThemeViewModel
import com.example.serfinalprojecttest.screens.DetailScreen
import com.example.serfinalprojecttest.screens.HomeScreen
import com.example.serfinalprojecttest.screens.LoreScreen
import com.example.serfinalprojecttest.screens.SplashScreen
import com.example.serfinalprojecttest.viewmodels.CharacterViewModel

@Composable
fun AppNavigation(
    viewModel: CharacterViewModel,
    themeViewModel: ThemeViewModel // Add ThemeViewModel as a parameter
) {
    val navController = rememberNavController()
    val characters by viewModel.uniqueCharacters.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(
                characters = characters,
                onCharacterClick = { character ->
                    viewModel.selectCharacter(character)
                    navController.navigate("detail")
                },
                themeViewModel = themeViewModel
            )
        }
        composable("detail") {
            viewModel.selectedCharacter?.let { character ->
                DetailScreen(
                    character = character,
                    onBackClick = { navController.popBackStack() },
                    onReadLoreClick = { navController.navigate("lore") },
                    themeViewModel = themeViewModel
                )
            }
        }
        composable("lore") {
            viewModel.selectedCharacter?.let { character ->
                LoreScreen(
                    character = character,
                    onBackClick = { navController.popBackStack() },
                    themeViewModel = themeViewModel
                )
            }
        }
    }
}
