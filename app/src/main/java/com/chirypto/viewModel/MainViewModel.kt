package com.chirypto.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    appName: String
) : ViewModel() {
    init {
        Log.e("QQQ", "main viewmodel: APP NAME IS $appName")
    }
}
