package com.chirypto.ui.signup


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.chirypto.R
import com.chirypto.ui.composebles.displayNormalFieldText
import com.chirypto.ui.composebles.displayPasswordFieldText
import com.chirypto.ui.composebles.displayRegisterBtn

@Composable
fun SignupScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        val nameTextFieldState = rememberSaveable { mutableStateOf("") }
        val emailTextFieldState = rememberSaveable { mutableStateOf("") }
        val passwordTextFieldState = rememberSaveable { mutableStateOf("") }
        val phoneTextFieldState = rememberSaveable { mutableStateOf("") }
        val passwordVisibility = rememberSaveable { mutableStateOf(false) }




        displayNormalFieldText(
            txt = nameTextFieldState,
            placeholder = "namee",
            label = stringResource(id = R.string.name_hint)
        )
        displayNormalFieldText(
            txt = emailTextFieldState,
            placeholder = "emaill",
            label = stringResource(id = R.string.email_hint)
        )
        displayNormalFieldText(
            txt = phoneTextFieldState,
            placeholder = "phonee",
            label = stringResource(id = R.string.phone_hint)
        )
        displayPasswordFieldText(
            txt = passwordTextFieldState,
            visibility = passwordVisibility,
            placeholder = "namee",
            label = stringResource(id = R.string.password_hint)
        )
        displayRegisterBtn(columnScope = this, navController =navController )

    }
}