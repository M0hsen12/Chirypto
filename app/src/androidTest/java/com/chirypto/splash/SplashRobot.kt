package com.chirypto.splash

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.chirypto.MainActivity
import com.chirypto.R
import com.chirypto.ui.splash.SplashState
import com.chirypto.viewModel.splash.SplashViewModel

fun typeTheAppVersion(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SplashRobot.() -> Unit
): SplashRobot {
    return SplashRobot(rule).apply(block)

}

fun shouldUpdateScreenDisplay(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SplashRobot.(state: SplashState) -> Unit
): SplashRobot {
    val viewModel = SplashViewModel()
    val state =
        viewModel.shouldDisplayUpdateApp(rule.activity.getString(R.string.app_version).toInt())
    return SplashRobot(rule).apply { block.invoke(this, state) }
}


class SplashRobot(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    fun setAppVersion(appVersion: String) {
        val appV = rule.activity.getString(R.string.app_version)
        rule.onNodeWithText(appVersion).assertExists(appV)
    }

    infix fun verify(block: SplashVerification.() -> Unit): SplashVerification {

        return SplashVerification(rule).apply(block)
    }

    fun shouldUpdateAppDisplayed(
        rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
        splashState: SplashState
    ) {
        val updateTxt = rule.activity.getString(R.string.update_app)
        val splashTxt = rule.activity.getString(R.string.splash_txt)
        if (splashState == SplashState.UpdateDialog)
            rule.onNodeWithText(updateTxt).assertExists(updateTxt)
        else rule.onNodeWithText(splashTxt).assertExists(splashTxt)

    }


}

class SplashVerification(rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    fun appVersionIsDisplayed() {
    }

    fun isUpdateAppDisplayed(splashTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {

    }

}
