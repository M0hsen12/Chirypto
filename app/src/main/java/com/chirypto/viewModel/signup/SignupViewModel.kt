package com.chirypto.viewModel.signup

import androidx.lifecycle.ViewModel
import com.chirypto.model.User
import com.chirypto.utill.UNIVERSAL_PASSWORD
import com.chirypto.utill.accountManger.AccountManager
import com.chirypto.utill.doesContainAtSign
import com.chirypto.utill.doesContainNumeric
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val accountManager: AccountManager
) : ViewModel() {


    fun addUserAccount(user: User) {
        accountManager.addAccount(user, UNIVERSAL_PASSWORD)
    }

    fun validateUsername(value: String, onError: (() -> Unit)? = null): Boolean {
        return if (value.length > 4) true
        else {
            onError?.invoke()
            false
        }
    }

    fun validateEmail(value: String, onError: (() -> Unit)? = null): Boolean {
        return if (value.length > 4 && value.doesContainAtSign()) true
        else {
            onError?.invoke()
            false
        }
    }

    fun validatePhone(value: String, onError: (() -> Unit)? = null): Boolean {
        return if (value.length > 10) true
        else {
            onError?.invoke()
            false
        }
    }

    fun validatePassword(value: String, onError: (() -> Unit)? = null): Boolean {
        return if (value.length > 5 && value.doesContainNumeric()) true
        else {
            onError?.invoke()
            false
        }
    }


}