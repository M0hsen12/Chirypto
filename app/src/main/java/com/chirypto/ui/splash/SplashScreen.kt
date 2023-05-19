package com.chirypto.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirypto.ui.composebles.DisplayAppVersion
import com.chirypto.ui.composebles.DisplayNoInternet
import com.chirypto.ui.composebles.DisplayProgressbar
import com.chirypto.ui.composebles.DisplaySplashScreen
import com.chirypto.ui.composebles.DisplayUpdateDialog
import com.chirypto.utill.Screen
import com.chirypto.viewModel.splash.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.chirypto.ui.splash.SplashState.*


@Composable
fun SplashScreen(navController: NavController, splashViewModel: SplashViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF177DFF))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            when (splashViewModel.gettingTheSplashState()) {

                NetworkConnectivityError -> DisplayNoInternet(this)

                UpdateDialog -> DisplayUpdateDialog(this)

                SignedUser -> delayAndNavigateUserToHome(navController, SignedUser)

                else -> delayAndNavigateUserToHome(navController, FirstTimer)

            }

            DisplaySplashScreen(this, navController)
            DisplayAppVersion(this)

        }
    }
}

fun delayAndNavigateUserToHome(navController: NavController, state: SplashState) {
    CoroutineScope(Dispatchers.Main).launch {
        delay(2_000)
        navController.navigate(
            if (state == SignedUser)
                Screen.Home.route
            else
                Screen.Signup.route
        )

    }

}







