package com.chirypto.viewModel.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.chirypto.model.User
import com.chirypto.ui.splash.SplashState
import com.chirypto.utill.UNIVERSAL_PASSWORD
import com.chirypto.utill.accountManger.AccountManager
import com.chirypto.utill.accountStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val accountManager: AccountManager
) : ViewModel() {



    fun addUserAccount(user: User) {
        accountManager.addAccount(user, UNIVERSAL_PASSWORD)
    }


}