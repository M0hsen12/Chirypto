package com.chirypto.signup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.chirypto.utill.*


fun launchSignup(
    rule: ComposeContentTestRule,
    block: SignupRobot.() -> Unit
): SignupRobot {
    return SignupRobot(rule).apply(block)
}
class SignupRobot(private val rule: ComposeContentTestRule) {
    infix fun verify(block: SignupVerification.() -> Unit): SignupVerification {

        return SignupVerification(rule).apply(block)
    }

    fun typePassword(password:String) {
        rule.onNodeWithText(SIGNUP_PASSWORD_FIELD).performTextInput(password)
    }

    fun typeName(name: String) {
        rule.onNodeWithText(SIGNUP_NAME_FIELD).performTextInput(name)
    }

    fun typeEmail(email: String) {
        rule.onNodeWithText(SIGNUP_EMAIL_FIELD).performTextInput(email)
    }

    fun typePhone(phone :String) {
        rule.onNodeWithText(SIGNUP_PHONE_FIELD).performTextInput(phone)
    }

    fun performClick() {
        rule.onNodeWithText(SIGNUP_REGISTER_BTN).performClick()
    }


}
class SignupVerification(private val rule: ComposeContentTestRule) {
    fun homeScreenDisplayed() {
        rule.onNodeWithText("Home").assertIsDisplayed()
    }


}