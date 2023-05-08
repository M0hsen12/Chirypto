package com.chirypto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chirypto.ui.home.HomeScreen
import com.chirypto.ui.signup.SignupScreen
import com.chirypto.ui.splash.SplashScreen
import com.chirypto.ui.theme.ChiryptoTheme
import com.chirypto.utill.MainNavigation

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChiryptoTheme {
                MainNavigation()
            }
        }
    }

    @Composable
    fun AppNavHost(navController: NavHostController) {

        NavHost(navController = navController, startDestination = SPLASH_NAV_TAG) {
            composable(SPLASH_NAV_TAG) { SplashScreen(navController = navController) }
            composable(SIGNUP_NAV_TAG) { SignupScreen(navController = navController) }
            composable(HOME_NAV_TAG) { HomeScreen(navController = navController) }
        }
    }

    companion object {
        const val SPLASH_NAV_TAG = "Splash"
        const val SIGNUP_NAV_TAG = "Signup"
        const val HOME_NAV_TAG = "Home"
    }
}

