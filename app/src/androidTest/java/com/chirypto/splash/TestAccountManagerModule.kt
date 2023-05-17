package com.chirypto.splash

import android.content.Context
import com.chirypto.di.AccountMangerModule
import com.chirypto.utill.accountManger.AccountManager
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AccountMangerModule

    ::class]
)
abstract class TestAccountManagerModule {

    @Singleton
    @Provides
    abstract fun provideAccountManager(
        context: Context,
        accountManager: android.accounts.AccountManager
    ): AccountManager

}
