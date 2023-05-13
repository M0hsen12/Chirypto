package com.chirypto.ui.signup


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chirypto.R
import com.chirypto.ui.composebles.*
import com.chirypto.utill.SIGNUP_EMAIL_FIELD
import com.chirypto.utill.SIGNUP_NAME_FIELD
import com.chirypto.utill.SIGNUP_PASSWORD_FIELD
import com.chirypto.utill.SIGNUP_PHONE_FIELD
import com.chirypto.viewModel.splash.SplashViewModel


@Composable
fun SignupScreen(navController: NavController) {
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
        displayRegisterBtn(navController = navController)
    }
}
