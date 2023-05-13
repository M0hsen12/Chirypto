package com.chirypto.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
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

fun getSplashState(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SplashRobot.(state: SplashState) -> Unit
): SplashRobot {
    val viewModel = SplashViewModel("")
    val state =
        viewModel.gettingTheSplashState(rule.activity.getString(R.string.app_version).toInt())
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

    fun displayScreenDependsOnState(
        rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
        splashState: SplashState
    ) {


        when (splashState) {
            SplashState.UpdateDialog -> {
                val updateTxt = rule.activity.getString(R.string.update_app)
                rule.onNodeWithText(updateTxt).assertExists(updateTxt)
            }

            SplashState.NetworkConnectivityError -> {
                val errorTxt = rule.activity.getString(R.string.no_internet)
                rule.onNodeWithText(errorTxt).assertExists(errorTxt)
            }

            else -> {
                val splashTxt = rule.activity.getString(R.string.app_name_txt).uppercase()
                rule.onNodeWithText(splashTxt).assertExists(splashTxt)
            }
        }
    }

    fun progressBarExistOnScreen(
        rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
        state: SplashState
    ) {
        rule.onNodeWithTag(rule.activity.getString(R.string.splash_progressbar_tag)).assertIsDisplayed()

    }

}

class SplashVerification(rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
    fun appVersionIsDisplayed() {
    }


}
