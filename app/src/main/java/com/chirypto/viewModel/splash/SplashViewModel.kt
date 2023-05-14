package com.chirypto.viewModel.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.chirypto.BuildConfig
import com.chirypto.model.User
import com.chirypto.ui.splash.SplashState
import com.chirypto.utill.accountManger.AccountManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountManager: AccountManager
) : ViewModel() {
    val TAG = "QQQ"


    fun gettingTheSplashState(appVersion: Int): SplashState {
        return when {
            userIsOnline() -> displayNetworkConnectivityError()
            (BuildConfig.VERSION_CODE < appVersion) -> SplashState.UpdateDialog
            else -> SplashState.Normal
        }

    }
    fun addAccount(){
        accountManager.addAccount(
            User(
                id = 1,
                firstName = "mohsenGG",
                lastName = "goddarzi",
                phone = "09353900053",
                avatar = "asshole",
                token = "0.1122",
                refreshToken = "1254545454"
            ), "123654"
        )
    }

    fun getUserName() = accountManager.getFirstName()

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