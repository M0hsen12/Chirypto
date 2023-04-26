package com.chirypto.viewModel.splash

import android.util.Log
import com.chirypto.BuildConfig
import com.chirypto.ui.splash.SplashState

class SplashViewModel {
    val TAG = "SplashViewModel"

    init {
        Log.e(TAG, "splash viewmodel init: ")
    }

    fun shouldDisplayUpdateApp(appVersion: Int): SplashState {
        return when {
            userIsOnline() -> displayNetworkConnectivityError()
            (BuildConfig.VERSION_CODE < appVersion) -> SplashState.UpdateDialog
            else -> SplashState.Normal
        }

    }

    private fun userIsOnline(): Boolean {
        return true
    }

    fun displayNetworkConnectivityError() =
        SplashState.NetworkConnectivityError // just for test purposes

}