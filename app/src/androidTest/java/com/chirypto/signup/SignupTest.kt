package com.chirypto.signup

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.chirypto.MainActivity
import org.junit.Rule
import org.junit.Test

class SignupTest {
    @get:Rule
    val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity> =
        createAndroidComposeRule()

    @Test
    fun performSignup() {
        launchSignup(rule) {
            typePassword("123456")
            typeName("mohsen")
            typeEmail("mohsen@gmail.com")
            typePhone("09353900053")
            performClick()
        } verify {
            homeScreenDisplayed()
        }
    }


}