package com.example.littlelemon

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@ExperimentalMaterial3Api
@Composable
fun NavigationComposable(navController: NavHostController, isLoggedIn: Boolean) {
    val startDestination = if (isLoggedIn) Home.route else Onboarding.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Onboarding.route) { OnboardingScreen(navController) }
        composable(Home.route) { HomeScreen(navController) }
        composable(Profile.route) { ProfileScreen(navController) }
    }
}
