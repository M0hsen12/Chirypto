package com.chirypto.ui.splash

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.chirypto.R
import com.chirypto.ui.composebles.DisplayAppVersion
import com.chirypto.ui.composebles.DisplayNoInternet
import com.chirypto.ui.composebles.DisplayProgressbar
import com.chirypto.ui.composebles.DisplaySplashScreen
import com.chirypto.ui.composebles.DisplayUpdateDialog
import com.chirypto.viewModel.splash.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = SplashViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF177DFF))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (viewModel.shouldDisplayUpdateApp(stringResource(id = R.string.app_version).toInt())) {
                SplashState.NetworkConnectivityError -> {
                    DisplayNoInternet(this)
                }

                SplashState.UpdateDialog -> {
                    DisplayUpdateDialog(this)
                }

                SplashState.SignedUser -> {}
                else -> {
                    DisplaySplashScreen(this,navController)
                }
            }

            DisplayAppVersion(this)
            DisplayProgressbar(this)

        }
    }
}







