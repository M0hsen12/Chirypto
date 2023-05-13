package com.chirypto.di

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.chirypto.utill.accountManger.AccountManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountMangerModule {

    @Singleton
    @Provides
    fun provideAccountManager(@ApplicationContext context: Context): AccountManager {
        return AccountManager(
            context,
            android.accounts.AccountManager.get(context)
        )
    }
}