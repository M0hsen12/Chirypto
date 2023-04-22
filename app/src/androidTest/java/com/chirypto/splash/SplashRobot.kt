package com.chirypto.splash

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.chirypto.MainActivity
import com.chirypto.R

fun typeTheAppVersion(
    rule : AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SplashRobot.() -> Unit
): SplashRobot {
    return SplashRobot(rule).apply(block)

}


class SplashRobot(private  val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    fun setAppVersion(appVersion: String) {
val appV = rule.activity.getString(R.string.app_version)
        rule.onNodeWithText(appVersion).assertExists(appV)
    }

    infix fun verify(block: SplashVerification.() -> Unit): SplashVerification {

        return SplashVerification(rule).apply(block)
    }


}

class SplashVerification(rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    fun appVersionIsDisplayed() {
    }

}
