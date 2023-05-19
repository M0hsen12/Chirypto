package com.chirypto.ui.signup


import android.accounts.AccountManager
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AndroidUiDispatcher.Companion.Main
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import com.chirypto.ui.composebles.*
import com.chirypto.utill.*
import com.chirypto.viewModel.signup.SignupViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.prefs.Preferences


@Composable
fun SignupScreen(navController: NavController, signupViewModel: SignupViewModel = hiltViewModel()) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        val nameTextFieldState = rememberSaveable { mutableStateOf("") }
        val emailTextFieldState = rememberSaveable { mutableStateOf("") }
        val passwordTextFieldState = rememberSaveable { mutableStateOf("") }
        val phoneTextFieldState = rememberSaveable { mutableStateOf("") }
        val passwordVisibility = rememberSaveable { mutableStateOf(false) }


        displaySignupHeader()
        DisplaySignupIndicator()

        displayNormalFieldText(
            txt = nameTextFieldState,
            placeholder = "",
            label = SIGNUP_NAME_FIELD
        )
        displayNormalFieldText(
            txt = emailTextFieldState,
            placeholder = "",
            label = SIGNUP_EMAIL_FIELD
        )
        displayNormalFieldText(
            txt = phoneTextFieldState,
            placeholder = "",
            label = SIGNUP_PHONE_FIELD
        )
        displayPasswordFieldText(
            txt = passwordTextFieldState,
            visibility = passwordVisibility,
            placeholder = "",
            label = SIGNUP_PASSWORD_FIELD
        )
        displayRegisterBtn() { user ->
            signupViewModel.addUserAccount(user)
            navController.navigate(Screen.Home.route)
        }

    }
}

