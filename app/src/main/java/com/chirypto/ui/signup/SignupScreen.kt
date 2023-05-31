package com.chirypto.ui.signup


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirypto.ui.composebles.*
import com.chirypto.utill.*
import com.chirypto.viewModel.signup.SignupViewModel


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
        val errorTextState = rememberSaveable { mutableStateOf("") }
        val passwordVisibility = rememberSaveable { mutableStateOf(false) }
        val context = LocalContext.current

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
        displayRegisterBtn(errorTextState) { user ->
            signupViewModel.validateUsername(nameTextFieldState.value, onError = {
                errorTextState.value = SIGNUP_NAME_ERROR
            })
            signupViewModel.validateEmail(emailTextFieldState.value, onError = {
                errorTextState.value = SIGNUP_EMAIL_ERROR
            })
            signupViewModel.validatePhone(phoneTextFieldState.value, onError = {
                errorTextState.value = SIGNUP_PHONE_ERROR
            })
            signupViewModel.validatePassword(passwordTextFieldState.value, onError = {
                errorTextState.value = SIGNUP_PASSWORD_ERROR
            })

            if (errorTextState.value.isEmpty()) {
                signupViewModel.addUserAccount(user)
                navController.navigate(Screen.Home.route)
            }
        }

    }
}

