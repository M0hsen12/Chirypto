package com.chirypto.utill

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.OnAccountsUpdateListener
import android.os.Build
import android.provider.ContactsContract.Directory.ACCOUNT_TYPE
import android.util.Log
import com.chirypto.ui.splash.SplashState
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.Timer
import kotlin.concurrent.schedule

fun AccountManager.accountStateFlow(): Flow<SplashState> = callbackFlow {
    val listener = provideAccountManagerListener { status ->
        trySendBlocking(status)
            .onFailure { throwable ->
                Log.e("qqq", "accountStateFlow: error" )
                // Downstream has been cancelled or failed, can log here
            }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        addOnAccountsUpdatedListener(listener, null, true, arrayOf(ACCOUNT_TYPE))
    } else {
        addOnAccountsUpdatedListener(listener, null, true)
    }

    awaitClose {
        removeOnAccountsUpdatedListener(listener)
    }
}
private fun provideAccountManagerListener(onStatus: (status: SplashState) -> ChannelResult<Unit>): OnAccountsUpdateListener {
    return AboveOreoAccountUpdateListener(onStatus)
}
open class AboveOreoAccountUpdateListener(private val action: (status: SplashState) -> ChannelResult<Unit>) : OnAccountsUpdateListener {
    override fun onAccountsUpdated(accounts: Array<out Account>?) {
        Log.e("qqq", "onAccountsUpdated: ffa" )
        val status = if (accounts?.isNotEmpty() == true) {
            SplashState.SignedUser
        } else {
            SplashState.FirstTimer
        }
        action(status)
    }
}
