package com.chirypto.signup

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.chirypto.MainActivity
import com.chirypto.ui.signup.SignupScreen
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Timer
import kotlin.concurrent.schedule

class SignupTest {
    @get:Rule
    val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity> =
        createAndroidComposeRule()

    lateinit var navController: TestNavHostController

//    @Before
//    fun setupAppNavHost() {
//
//        rule.setContent {
//            navController = TestNavHostController(LocalContext.current)
//            navController.navigatorProvider.addNavigator(ComposeNavigator())
////            AppNavHost(navController = navController)
//            navController.navigate("Signup")
//
//        }
//    }
    @Test
    fun performSignup() {
        launchSignup(rule) {
            rule.waitUntilTimeout(7000)
            typeName("mohsen")
            typeEmail("mohsen@gmail.com")
            typePhone("09353900053")
            typePassword("123456")
            performClick()
        } verify {
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
