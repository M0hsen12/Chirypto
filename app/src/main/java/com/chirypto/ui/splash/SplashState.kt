package com.chirypto.ui.splash

sealed class SplashState {
    object Error : SplashState()
    object Normal : SplashState()
    object UpdateDialog : SplashState()
    object SignedUser : SplashState()
}