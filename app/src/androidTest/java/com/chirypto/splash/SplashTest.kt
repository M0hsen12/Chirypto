package com.chirypto.splash

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.chirypto.MainActivity
import com.chirypto.R
import org.junit.Rule
import org.junit.Test

/*
1.app version is displayed
2.dialog for updating the app displayed
3.user has internet if dont show dialog
4.loading state is shown
5.
 */

class SplashTest {
    @get:Rule
    val splashTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity> =
        createAndroidComposeRule()


    @Test
    fun appVersionDisplayed() {
        typeTheAppVersion(splashTestRule) {
            setAppVersion(splashTestRule.activity.getString(R.string.app_version))
        } verify {
            appVersionIsDisplayed()
        }
    }

    @Test
    fun displayDependsOnState() {
        getSplashState(splashTestRule) { state ->

            displayScreenDependsOnState(splashTestRule, state)

        }
    }


}