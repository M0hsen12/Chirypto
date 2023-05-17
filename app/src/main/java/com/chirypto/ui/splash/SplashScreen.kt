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
import com.chirypto.utill.APP_VERSION
import com.chirypto.utill.Screen
import com.chirypto.viewModel.splash.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(navController: NavController) {
    val splashViewModel: SplashViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF177DFF))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {


            when (splashViewModel.gettingTheSplashState(APP_VERSION.toInt())) {
                SplashState.NetworkConnectivityError -> {
                    DisplayNoInternet(this)
                }

                SplashState.UpdateDialog -> {
                    DisplayUpdateDialog(this)
                }

                SplashState.SignedUser -> {
                    delayAndNavigateUserToHome(navController)
                }
                else -> {}
            }
            DisplaySplashScreen(this, navController)
            DisplayAppVersion(this)
            DisplayProgressbar(this)

        }
    }
}

fun delayAndNavigateUserToHome(navController: NavController) {
    CoroutineScope(Dispatchers.Main).launch {
        delay(5_000)
        navController.navigate(Screen.Home.route)

    }

}







