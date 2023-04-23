package com.chirypto.viewModel.splash

import android.content.Context
import android.util.Log
import com.chirypto.BuildConfig
import com.chirypto.R
import com.chirypto.ui.splash.SplashState

class SplashViewModel {
    val TAG = "SplashViewModel"

    init {
        Log.e(TAG, "splash viewmodel init: ")
    }

    fun shouldDisplayUpdateApp(appVersion: Int): SplashState {
        return if (BuildConfig.VERSION_CODE < appVersion)
            SplashState.UpdateDialog
        else SplashState.Normal
    }

}