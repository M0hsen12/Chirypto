package com.chirypto.utill.accountManger

import android.accounts.*
import android.accounts.AccountManager.*
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.TextUtils

@SuppressLint("MissingPermission")
class Authenticator(private val mContext: Context) : AbstractAccountAuthenticator(mContext) {

    override fun editProperties(
        accountAuthenticatorResponse: AccountAuthenticatorResponse,
        s: String
    ): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun addAccount(
        accountAuthenticatorResponse: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        strings: Array<String?>?,
        options: Bundle?
    ): Bundle {
        val intent =
            mContext.packageManager.getLaunchIntentForPackage(mContext.packageName)?.apply {
                putExtra(
                    KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,
                    accountAuthenticatorResponse
                )
                putExtra("account_type", accountType)
                putExtra("auth_type", authTokenType)
                putExtra("new_account", true)
            }
        return Bundle().apply {
            putParcelable(KEY_INTENT, intent)
        }
    }

    override fun confirmCredentials(
        accountAuthenticatorResponse: AccountAuthenticatorResponse,
        account: Account,
        bundle: Bundle
    ): Bundle? {
        return null
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse,
        account: Account,
        authTokenType: String,
        options: Bundle
    ): Bundle {
        // Extract the username and password from the Account Manager, and ask
        // the server for an appropriate AuthToken.
        val am = get(mContext)

        val authToken = am.peekAuthToken(account, authTokenType)

        // Lets give another try to authenticate the user

        // If we get an authToken - we return it
        if (!TextUtils.isEmpty(authToken)) {
            return Bundle().apply {
                putString(KEY_ACCOUNT_NAME, account.name)
                putString(KEY_ACCOUNT_TYPE, account.type)
                putString(KEY_AUTHTOKEN, authToken)
            }
        }

        // If we get here, then we couldn't access the user's password - so we
        // need to re-prompt them for their credentials. We do that by creating
        // an intent to display our AuthenticatorActivity.
        val intent =
            mContext.packageManager.getLaunchIntentForPackage(mContext.packageName)?.apply {
                putExtra(KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
                putExtra("account_type", account.type)
                putExtra("auth_type", authTokenType)
            }
        return Bundle().apply {
            putParcelable(KEY_INTENT, intent)
        }
    }

    override fun getAuthTokenLabel(s: String): String? {
        return null
    }

    override fun updateCredentials(
        accountAuthenticatorResponse: AccountAuthenticatorResponse,
        account: Account,
        s: String,
        bundle: Bundle
    ): Bundle? {
        return null
    }

    override fun hasFeatures(
        accountAuthenticatorResponse: AccountAuthenticatorResponse,
        account: Account,
        strings: Array<String>
    ): Bundle? {
        return null
    }

}
