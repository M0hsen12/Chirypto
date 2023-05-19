package com.chirypto.viewModel.splash

import androidx.lifecycle.ViewModel
import com.chirypto.BuildConfig
import com.chirypto.ui.splash.SplashState
import com.chirypto.utill.APP_VERSION
import com.chirypto.utill.accountManger.AccountManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountManager: AccountManager
) : ViewModel() {
    val TAG = "QQQ"




    fun gettingTheSplashState(appVersion: Int = APP_VERSION.toInt()): SplashState {
        return when {
            isOnline() -> displayNetworkConnectivityError()
            isUpdateNeeded(appVersion) -> SplashState.UpdateDialog
            isUserSigned() -> SplashState.SignedUser
            else -> SplashState.FirstTimer
        }

    }

    private fun isUpdateNeeded(appVersion: Int) = (BuildConfig.VERSION_CODE < appVersion)




    private fun isOnline(): Boolean {
        return false
    }
    private fun isUserSigned() = accountManager.isLoggedIn()

    //fake it until you make it
    fun displayNetworkConnectivityError() =
        SplashState.NetworkConnectivityError // just for test purposes

    fun displayUpdateDialog() =
        SplashState.UpdateDialog // just for test purposes

    fun displaySplash() =
        SplashState.FirstTimer // just for test purposes

}