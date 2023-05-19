package com.chirypto.splash

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.chirypto.ui.splash.SplashState
import com.chirypto.utill.APP_VERSION
import com.chirypto.utill.accountManger.AccountManager
import com.chirypto.viewModel.splash.SplashViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(application = HiltTestApplication::class)

class SplashUnitTest {
    /*
    1.testing update state
    2.testing error connection state
    3.testing normal state
    4.testing the signedUser (maybe after the sign up)

     */
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Mock
    lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var accountManager: AccountManager

    @Before
    fun setup() {
        hiltRule.inject()
        accountManager = AccountManager(context, android.accounts.AccountManager.get(context))
        viewModel = SplashViewModel(accountManager)
    }

    @Test
    fun gettingTheUpdateApplicationState() {

        assertEquals(SplashState.UpdateDialog, viewModel.displayUpdateDialog())
    }

    @Test
    fun gettingSplashState() {
        assertEquals(SplashState.FirstTimer, viewModel.displaySplash())
    }

    @Test
    fun gettingErrorNetworkConnectivity() {
        assertEquals(
            SplashState.NetworkConnectivityError,
            viewModel.displayNetworkConnectivityError()
        )
    }

    @Test
    fun gettingSplashScreenState() {
        assertEquals(SplashState.FirstTimer, viewModel.gettingTheSplashState(APP_VERSION.toInt()))

    }

}