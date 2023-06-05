package com.chirypto.ui.signup

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.chirypto.InstantTaskExecutorExtension
import com.chirypto.utill.accountManger.AccountManager
import com.chirypto.viewModel.signup.SignupViewModel
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(RobolectricTestRunner::class)
@ExtendWith(InstantTaskExecutorExtension::class)
class SignupUnitTest {
    lateinit var viewModel: SignupViewModel
    lateinit var accountManager: AccountManager

    @BeforeAll
    fun setup() {

        val context: Context = mock(Context::class.java)
        val acc = mock(android.accounts.AccountManager::class.java)
        accountManager = AccountManager(context, acc)
        viewModel = SignupViewModel(accountManager)
    }

    @ParameterizedTest
    @CsvSource(
        "''",
        "'m'",
        "'Mo'",
        "'Moh'",
        "'Mohs'",
        "'1234'"
    )
    fun gettingErrorForValidateUsername(username: String) {
        assertEquals(false, viewModel.validateUsername(username))

    }

    @ParameterizedTest
    @CsvSource(
        "''",
        "'mohsen'",
        "'mohsen!gmail'",
        "'mohsen.gmail.com'",
        "'121232331213'"
    )
    fun gettingErrorForValidatingEmail(email: String) {

        assertEquals(false, viewModel.validateEmail(email))
    }

    @ParameterizedTest
    @CsvSource(
        "''",
        "'013'",
        "'093539'",
        "'093539000'",
        "'mohsen'",
        "'0935390005'"
    )
    fun gettingErrorForValidatingPhone(phone: String) {
        assertEquals(false, viewModel.validatePhone(phone))
    }

    @ParameterizedTest
    @CsvSource(
        "''",
        "'m'",
        "'Mo'",
        "'Moh'",
        "'mohsen'",
        "'0'",
        "'12'",
        "'1234'",
        "'thisIsPassWithNoNumber'"
    )
    fun gettingErrorForValidatingPassword(pass: String) {
        assertEquals(false, viewModel.validatePassword(pass))
    }

}