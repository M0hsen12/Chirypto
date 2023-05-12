package com.chirypto.utill

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun getUUID(context: Context): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}

fun getFormattedPersianDate(year: Int, month: Int, day: Int): String {
    return year.toString() + "-" + (1 + month) + "-" + day
}


fun enablePermission(context: Context) {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}

fun randomString(length: Int): String {
    val charPool: List<Char> =
        ('a'..'z') + ('A'..'Z') + ('0'..'9') + '!' + '@' + '#' + '$' + '%' + '&' + '*' + '(' + ')'
    return (1..length)
        .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun setMoneySeparator(price: Int): String {
    val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
    val symbols = formatter.decimalFormatSymbols

    symbols.groupingSeparator = ','
    formatter.decimalFormatSymbols = symbols
    return formatter.format(price.toLong())
}

fun getActivity(v: View): Activity? {
    var context = v.context
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun getApplicationName(context: Context): String? {
    val applicationInfo: ApplicationInfo = context.applicationInfo
    val stringId: Int = applicationInfo.labelRes
    return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(
        stringId
    )
}