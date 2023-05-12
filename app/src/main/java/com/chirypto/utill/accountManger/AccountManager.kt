package com.chirypto.utill.accountManger

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import com.chirypto.model.User
import com.chirypto.utill.getApplicationName
import com.google.gson.Gson



class AccountManager(
    val context: Context,
    val accountManager: AccountManager
) {


    private val accountType: String? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.packageManager.getApplicationInfo(
            context.packageName,
            PackageManager.ApplicationInfoFlags.of(0)
        ).metaData.getString("account_type") ?: ""
    } else {
        context.packageManager.getApplicationInfo(
            "com.chirypto",
            PackageManager.GET_META_DATA
        ).metaData.getString("account_type") ?: ""
    }


    fun addAccount(user: User, password: String?) {
        if (getAccount().isEmpty()) {
            val account = Account(
                getApplicationName(context),
                accountType
            )

            val userData = Bundle().apply {
                putString(
                    USER,
                    Gson().toJson(user)
                )
                putString(
                    USER_PHONE,
                    user.phone
                )
                putString(
                    USER_NAME,
                    user.userName
                )
                putString(
                    FIRST_NAME,
                    user.firstName
                )
                putString(
                    AVATAR,
                    user.avatar
                )
            }
            accountManager.addAccountExplicitly(account, password, userData)
            accountManager.setAuthToken(account, TOKEN, user.token)
            accountManager.setAuthToken(
                getAccount().takeIf { it.isNotEmpty() }?.firstOrNull() ?: account,
                REFRESH_TOKEN,
                user.refreshToken
            )
        } else {
            accountManager.setAuthToken(getAccount().first(), TOKEN, user.token)
            accountManager.setAuthToken(getAccount().first(), REFRESH_TOKEN, user.refreshToken)
            setUserData(
                USER,
                Gson().toJson(user)
            )
            setUserData(
                USER_PHONE,
                user.phone ?: ""
            )
            setUserData(
                USER_NAME,
                user.userName ?: ""
            )
            setUserData(
                FIRST_NAME,
                user.firstName ?: ""
            )
            setUserData(
                AVATAR,
                user.avatar ?: ""
            )
        }
    }

    fun getAccount(): Array<Account> {
        return accountManager.getAccountsByType(accountType)
    }

    fun removeAccount() {
        val account = getAccount()
        if (account.isNotEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                if (context is Activity)
                    accountManager.removeAccount(account[0], context, null, null)
            } else
                accountManager.removeAccount(account[0], null, null)
        }
    }

    fun getPassword(): String? {
        val account = getAccount()
        return if (account.isNotEmpty()) {
            accountManager.getPassword(account[0])
        } else {
            null
        }
    }

    fun setPassword(password: String?) {
        val account = getAccount()
        if (account.isNotEmpty()) {
            val token = getToken()
            accountManager.setPassword(account[0], password)
            setToken(token)
        }
    }

    fun getToken(): String? {
        val account = getAccount()
        return if (account.isNotEmpty()) {
            accountManager.peekAuthToken(account[0], TOKEN)
        } else {
            null
        }
    }

    fun setToken(token: String?) {
        val account = getAccount()
        if (account.isNotEmpty()) {
            accountManager.setAuthToken(account[0], TOKEN, token)
        }
    }

    fun getRefreshToken(): String? {
        val account = getAccount()
        return if (account.isNotEmpty()) {
            accountManager.peekAuthToken(account[0], REFRESH_TOKEN)
        } else {
            null
        }
    }

    fun setRefreshToken(token: String?) {
        val account = getAccount()
        if (account.isNotEmpty()) {
            accountManager.setAuthToken(account[0], REFRESH_TOKEN, token)
        }
    }

    fun setUserData(key: String, value: String) {
        val account = getAccount()
        if (account.isNotEmpty()) {
            return accountManager.setUserData(account[0], key, value)
        }
    }

    fun getUserData(key: String): String? {
        val account = getAccount()
        return if (account.isNotEmpty()) {
            accountManager.getUserData(account[0], key)
        } else {
            null
        }
    }

    fun getTokenAsync(
        context: Context,
        callback: GetTokenCallback
    ) {
        Thread(Runnable { callback.done(getToken()) }).start()
    }

    fun setUser(user: User) {
        val account = getAccount()
        if (account.isNotEmpty()) {
            accountManager.setUserData(
                account[0],
                USER,
                Gson().toJson(user)
            )
        }
    }

    fun getUser(): User? {
        val account = getAccount()
        if (account.isNotEmpty()) {
            val userJson = accountManager.getUserData(
                account[0],
                USER
            )
            if (userJson.isNullOrEmpty()) {
                return null
            } else {
                return Gson().fromJson(userJson , User::class.java)

            }

        }
        return null
    }

    fun setUserName(username: String) {
        val account = getAccount()
        if (account.isNotEmpty()) {
            accountManager.setUserData(
                account[0],
                USER_NAME,
                username
            )
        }
    }

    fun getUserName(): String {
        val account = getAccount()
        return if (account.isNotEmpty()) {
            accountManager.getUserData(
                account[0],
                USER_NAME
            ) ?: ""
        } else {
            ""
        }
    }


    fun setFirstName(username: String) {
        val account = getAccount()
        if (account.isNotEmpty()) {
            accountManager.setUserData(
                account[0],
                FIRST_NAME,
                username
            )
        }
    }

    fun getFirstName(): String {
        val account = getAccount()
        return if (account.isNotEmpty()) {
            accountManager.getUserData(
                account[0],
                FIRST_NAME
            ) ?: ""
        } else {
            ""
        }
    }

    fun setUserPhone(phone: String) {
        val account = getAccount()
        if (account.isNotEmpty()) {
            accountManager.setUserData(
                account[0],
                USER_PHONE,
                phone
            )
        }
    }

    fun getUserPhone(): String {
        val account = getAccount()
        return if (account.isNotEmpty()) {
            accountManager.getUserData(
                account[0],
                USER_PHONE
            ) ?: ""
        } else {
            ""
        }
    }

    fun setUserAvatar(avatar: String) {
        val account = getAccount()
        if (account.isNotEmpty()) {
            accountManager.setUserData(
                account[0],
                AVATAR,
                avatar
            )
        }
    }

    fun getUserAvatar(): String {
        val account = getAccount()
        return if (account.isNotEmpty()) {
            accountManager.getUserData(
                account[0],
                AVATAR
            ) ?: ""
        } else {
            ""
        }
    }

    interface GetTokenCallback {
        fun done(token: String?)
    }

    companion object {


        fun isLoggedIn(context: Context): Boolean {
            return AccountManager(
                context,
                AccountManager.get(context)
            ).getAccount().isNotEmpty()
        }

        fun logOut(context: Context) {


            AccountManager(
                context,
                AccountManager.get(context)
            ).removeAccount()
        }

        const val USER_PHONE = "user_phone"
        const val USER_NAME = "user_name"
        const val FIRST_NAME = "first_name"
        const val AVATAR = "avatar"
        const val USER = "user"
        const val TOKEN = "token"
        const val REFRESH_TOKEN = "refresh_token"

    }
}

