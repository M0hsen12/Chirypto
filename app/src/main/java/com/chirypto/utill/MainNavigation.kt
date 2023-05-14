package com.chirypto.utill

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chirypto.ui.home.HomeScreen
import com.chirypto.ui.signup.SignupScreen
import com.chirypto.ui.splash.SplashScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Signup : Screen("Signup")
    object Splash : Screen("Splash")
}


@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Signup.route) { SignupScreen(navController) }
    }
}