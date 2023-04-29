package com.chirypto.viewModel.splash

import com.chirypto.BuildConfig
import com.chirypto.ui.splash.SplashState

class SplashViewModel {
    val TAG = "SplashViewModel"



    fun gettingTheSplashState(appVersion: Int): SplashState {
        return when {
            userIsOnline() -> displayNetworkConnectivityError()
            (BuildConfig.VERSION_CODE < appVersion) -> SplashState.UpdateDialog
            else -> SplashState.Normal
        }

    }

    private fun userIsOnline(): Boolean {
        return false
    }

    //fake it until you make it
    fun displayNetworkConnectivityError() =
        SplashState.NetworkConnectivityError // just for test purposes

    fun displayUpdateDialog() =
        SplashState.UpdateDialog // just for test purposes

    fun displaySplash() =
        SplashState.Normal // just for test purposes

}