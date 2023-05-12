package com.chirypto.ui.splash

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.chirypto.R
import com.chirypto.model.User
import com.chirypto.ui.composebles.DisplayAppVersion
import com.chirypto.ui.composebles.DisplayNoInternet
import com.chirypto.ui.composebles.DisplayProgressbar
import com.chirypto.ui.composebles.DisplaySplashScreen
import com.chirypto.ui.composebles.DisplayUpdateDialog
import com.chirypto.utill.accountManger.AccountManager
import com.chirypto.viewModel.splash.SplashViewModel


@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = SplashViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF177DFF))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            val a = AccountManager(
                LocalContext.current,
                android.accounts.AccountManager.get(LocalContext.current)
            )
            a.addAccount(
                User(
                    id = 1,
                    firstName = "mohsen",
                    lastName = "goddarzi",
                    phone = "09353900053",
                    avatar = "asshole",
                    token = "0.1122",
                    refreshToken = "1254545454"
                ),"123654"
            )

            when (viewModel.gettingTheSplashState(stringResource(id = R.string.app_version).toInt())) {
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

            Toast.makeText(LocalContext.current, a.getToken(), Toast.LENGTH_SHORT).show()
        }
    }
}







