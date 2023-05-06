package com.chirypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chirypto.ui.home.HomeScreen
import com.chirypto.ui.signup.SignupScreen
import com.chirypto.ui.splash.SplashScreen
import com.chirypto.ui.theme.ChiryptoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChiryptoTheme {
                 val navController = rememberNavController()


                    NavHost(navController = navController, startDestination = SPLASH_NAV_TAG) {
                        composable(SPLASH_NAV_TAG) { SplashScreen(navController = navController) }
                        composable(SIGNUP_NAV_TAG) { SignupScreen(navController = navController) }
                        composable(HOME_NAV_TAG) { HomeScreen(navController = navController) }


                }
            }
        }
    }

    companion object {
        const val SPLASH_NAV_TAG = "Splash"
        const val SIGNUP_NAV_TAG = "Signup"
        const val HOME_NAV_TAG = "Home"
    }
}

