package com.chirypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chirypto.ui.home.HomeScreen
import com.chirypto.ui.login.LoginScreen
import com.chirypto.ui.splash.SplashScreen
import com.chirypto.ui.theme.ChiryptoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChiryptoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Splash") {
                    composable("Splash") { SplashScreen(navController = navController) }
                    composable("Login") { LoginScreen(navController = navController)  }
                    composable("Home") { HomeScreen(navController = navController)  }

                }
            }
        }
    }
}

