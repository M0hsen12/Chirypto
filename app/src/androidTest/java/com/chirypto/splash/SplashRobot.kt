package com.chirypto.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.chirypto.ui.splash.SplashState
import com.chirypto.utill.APP_NAME
import com.chirypto.utill.APP_VERSION
import com.chirypto.viewModel.splash.SplashViewModel

fun typeTheAppVersion(
    rule: ComposeContentTestRule,
    block: SplashRobot.() -> Unit
): SplashRobot {
    return SplashRobot(rule).apply(block)

}

fun getSplashState(
    viewModel: SplashViewModel,
    rule: ComposeContentTestRule,
    block: SplashRobot.(state: SplashState) -> Unit
): SplashRobot {
    val state = viewModel.gettingTheSplashState(APP_VERSION.toInt())
    return SplashRobot(rule).apply { block.invoke(this, state) }
}


class SplashRobot(private val rule: ComposeContentTestRule) {
    fun setAppVersion(appVersion: String) {

        rule.onNodeWithText(appVersion).assertIsDisplayed()
    }

    infix fun verify(block: SplashVerification.() -> Unit): SplashVerification {

        return SplashVerification(rule).apply(block)
    }

    fun displayScreenDependsOnState(
        rule: ComposeContentTestRule,
        splashState: SplashState
    ) {


        when (splashState) {
            SplashState.UpdateDialog -> {
                val updateTxt = "Update the application"
                rule.onNodeWithText(updateTxt).assertExists(updateTxt)
            }

            SplashState.NetworkConnectivityError -> {
                val errorTxt = "you have no Connectivity to Internet."
                rule.onNodeWithText(errorTxt).assertExists(errorTxt)
            }

            else -> {
                val splashTxt = APP_NAME.uppercase()
                rule.onNodeWithText(splashTxt).assertExists(splashTxt)
            }
        }
    }

    fun progressBarExistOnScreen(
        rule: ComposeContentTestRule,
        state: SplashState
    ) {
        rule.onNodeWithTag("myProgressBar").assertIsDisplayed()

    }

}

class SplashVerification(rule: ComposeContentTestRule) {


}
