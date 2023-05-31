package com.chirypto.signup

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.chirypto.MainActivity
import com.chirypto.utill.MainNavigation
import com.chirypto.utill.*
import com.chirypto.utill.accountManger.AccountManager
import com.chirypto.viewModel.signup.SignupViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule


@HiltAndroidTest
class SignupTest {
//    @get:Rule
//    val composeTestRule = createComposeRule()

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val rule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController
    private lateinit var viewModel: SignupViewModel

    @Inject
    lateinit var accountManager: AccountManager

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = SignupViewModel(accountManager)

        rule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainNavigation(navController = navController)
            navController.navigate(Screen.Signup.route)

        }
    }

    @Test
    fun performSignup() {
        launchSignup(rule) {
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
    fun performSignupWithUserNameError() {
        launchSignup(rule) {
            typeName("")
            typeEmail("mohsen@gmail.com")
            typePhone("09353900053")
            typePassword("123456")
            performClick()
        } verify {
            rule.onNodeWithText(SIGNUP_NAME_ERROR).assertIsDisplayed()
        }
    }

    @Test
    fun performSignupWithEmailError() {
        launchSignup(rule) {
            typeName("mohsen")
            typeEmail("mohsen")
            typePhone("09353900053")
            typePassword("123456")
            performClick()
        } verify {
            rule.onNodeWithText(SIGNUP_EMAIL_ERROR).assertIsDisplayed()
        }
    }

    @Test
    fun performSignupWithPhoneError() {
        launchSignup(rule) {
            typeName("mohsen")
            typeEmail("mohsen@gmail.com")
            typePhone("09356")
            typePassword("123456")
            performClick()
        } verify {
            rule.onNodeWithText(SIGNUP_PHONE_ERROR).assertIsDisplayed()
        }
    }

    @Test
    fun performSignupWithPasswordError() {
        launchSignup(rule) {
            typeName("mohsen")
            typeEmail("mohsen@gmail.com")
            typePhone("09356")
            typePassword("12")
            performClick()
        } verify {
            rule.onNodeWithText(SIGNUP_PASSWORD_ERROR).assertIsDisplayed()
        }
    }


    @Test
    fun appHeaderIsDisplayed() {
        rule.onNodeWithText(APP_NAME).assertIsDisplayed()
        rule.onNodeWithText(LOGIN_TXT).assertIsDisplayed()
    }
}

