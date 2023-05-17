package com.chirypto.splash

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.chirypto.HiltTestActivity
import com.chirypto.MainActivity
import com.chirypto.signup.waitUntilTimeout
import com.chirypto.ui.theme.ChiryptoTheme
import com.chirypto.utill.APP_VERSION
import com.chirypto.utill.MainNavigation
import com.chirypto.utill.Screen
import com.chirypto.utill.accountManger.AccountManager
import com.chirypto.viewModel.splash.SplashViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

/*
1.app version is displayed
2.dialog for updating the app displayed
3.user has internet if dont show dialog
4.loading state is shown
5.
 */
@HiltAndroidTest
class SplashTest {


    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    //    @get:Rule(order = 1)
//    val splashTestRule = createComposeRule()
    @get:Rule(order = 1)
    val splashTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var accountManager: AccountManager

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltRule.inject()

        viewModel = SplashViewModel(accountManager)

        splashTestRule.activity.setContent {
            ChiryptoTheme() {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                MainNavigation(navController = navController)
                navController.navigate(Screen.Splash.route)
            }

        }
    }
    @Test
    fun appVersionDisplayed() {

        typeTheAppVersion(splashTestRule) {
            setAppVersion(APP_VERSION.toString())
        } verify {
            // no verify needed
        }
    }
    @Test
    fun displayDependsOnState() {
        getSplashState(viewModel, splashTestRule) { state ->

            displayScreenDependsOnState(splashTestRule, state)

        }
    }



    @Test
    fun isProgressBarDisplayed() {
        getSplashState(viewModel, splashTestRule) { state ->
            progressBarExistOnScreen(splashTestRule, state)
        }
    }


}