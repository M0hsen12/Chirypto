package com.chirypto.signup

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.chirypto.utill.MainNavigation
import com.chirypto.utill.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Timer
import kotlin.concurrent.schedule

class SignupTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainNavigation(navController = navController)
            navController.navigate(Screen.Signup.route)
//            composeTestRule.waitUntilTimeout(10000)
        }
    }

    @Test
    fun performSignup() {
        launchSignup(composeTestRule) {
            typeName("mohsen")
            typeEmail("mohsen@gmail.com")
            typePhone("09353900053")
            typePassword("123456")
            performClick()
        } verify {
            homeScreenDisplayed()
        }
    }

    @Test
    fun appHeaderIsDisplayed() {
       composeTestRule.onNodeWithText(SIGNUP_APP_LOGO_TXT).assertIsDisplayed()
       composeTestRule.onNodeWithText(LOGIN_TXT).assertIsDisplayed()
    }
}

fun ComposeContentTestRule.waitUntilTimeout(
    timeoutMillis: Long
) {
    AsyncTimer.start(timeoutMillis)
    this.waitUntil(
        condition = { AsyncTimer.expired },
        timeoutMillis = timeoutMillis + 1000
    )
}

object AsyncTimer {
    var expired = false
    fun start(delay: Long = 1000) {
        expired = false
        Timer().schedule(delay) {
            expired = true
        }
    }
}
