package com.chirypto.signup

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.chirypto.MainActivity
import com.chirypto.ui.composebles.MainNavigation
import com.chirypto.ui.composebles.Screen
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
        }
    }

    @Test
    fun performSignup() {
        launchSignup(composeTestRule) {
//            composeTestRule.waitUntilTimeout(900)
            typeName("mohsen")
            typeEmail("mohsen@gmail.com")
            typePhone("09353900053")
            typePassword("123456")
            performClick()
        } verify {
//            composeTestRule.waitUntilTimeout(100)
            homeScreenDisplayed()
        }
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
