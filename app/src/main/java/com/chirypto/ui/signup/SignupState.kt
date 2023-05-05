package com.chirypto.ui.signup

sealed class SignupState {
    object BadEmail:SignupState()
    object BadPassword:SignupState()
    object Error:SignupState()
    object SuccessfulSignup:SignupState()

}