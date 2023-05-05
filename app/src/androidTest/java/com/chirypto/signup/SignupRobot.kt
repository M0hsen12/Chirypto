package com.chirypto.signup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.chirypto.MainActivity
import com.chirypto.R
import com.chirypto.splash.SplashVerification
import com.chirypto.ui.splash.SplashState
import com.chirypto.viewModel.splash.SplashViewModel


fun launchSignup(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SignupRobot.() -> Unit
): SignupRobot {
    return SignupRobot(rule).apply(block)

}
class SignupRobot(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    infix fun verify(block: SignupVerification.() -> Unit): SignupVerification {

        return SignupVerification(rule).apply(block)
    }

    fun typePassword(password:String) {
        rule.onNodeWithText(rule.activity.getString(R.string.password_hint)).performTextInput(password)
    }

    fun typeName(name: String) {
        rule.onNodeWithText(rule.activity.getString(R.string.name_hint)).performTextInput(name)
    }

    fun typeEmail(email: String) {
        rule.onNodeWithText(rule.activity.getString(R.string.email_hint)).performTextInput(email)
    }

    fun typePhone(phone :String) {
        rule.onNodeWithText(rule.activity.getString(R.string.phone_hint)).performTextInput(phone)
    }

    fun performClick() {
        rule.onNodeWithText(rule.activity.getString(R.string.signup_btn)).performClick()
    }


}
class SignupVerification(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    fun homeScreenDisplayed() {
        rule.onNodeWithText("Home").assertIsDisplayed()
    }


}