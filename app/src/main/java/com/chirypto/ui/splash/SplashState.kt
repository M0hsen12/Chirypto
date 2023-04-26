package com.chirypto.ui.splash

sealed class SplashState {
    object NetworkConnectivityError : SplashState()
    object Normal : SplashState()
    object UpdateDialog : SplashState()
    object SignedUser : SplashState()
}