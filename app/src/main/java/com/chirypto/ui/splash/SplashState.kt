package com.chirypto.ui.splash

sealed class SplashState {
    object NetworkConnectivityError : SplashState()
    object FirstTimer : SplashState()
    object UpdateDialog : SplashState()
    object SignedUser : SplashState()
}