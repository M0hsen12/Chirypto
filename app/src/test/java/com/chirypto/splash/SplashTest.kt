package com.chirypto.splash

import com.chirypto.BuildConfig
import com.chirypto.ui.splash.SplashState
import com.chirypto.viewModel.splash.SplashViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class SplashTest {
    /*
    1.testing update state
    2.testing error connection state
    3.testing normal state
    4.testing the signedUser (maybe after the sign up)

     */

    @Test
    fun gettingTheUpdateApplicationState() {
        val viewModel = SplashViewModel("")
        assertEquals(SplashState.UpdateDialog, viewModel.displayUpdateDialog())
    }   @Test
    fun gettingSplashState() {
        val viewModel = SplashViewModel("")
        assertEquals(SplashState.Normal, viewModel.displaySplash())
    }
    @Test
    fun gettingErrorNetworkConnectivity() {
        val viewModel = SplashViewModel("")
        assertEquals(SplashState.NetworkConnectivityError, viewModel.displayNetworkConnectivityError())
    }

    @Test
    fun gettingSplashScreenState(){
        val viewModel = SplashViewModel("")
        assertEquals(SplashState.Normal, viewModel.gettingTheSplashState(BuildConfig.VERSION_CODE))

    }
}