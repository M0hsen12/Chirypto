package com.chirypto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chirypto.ui.home.HomeScreen
import com.chirypto.ui.signup.SignupScreen
import com.chirypto.ui.splash.SplashScreen
import com.chirypto.ui.theme.ChiryptoTheme
import com.chirypto.utill.MainNavigation
import com.chirypto.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChiryptoTheme {
                MainNavigation()
            }
        }
    }
}

