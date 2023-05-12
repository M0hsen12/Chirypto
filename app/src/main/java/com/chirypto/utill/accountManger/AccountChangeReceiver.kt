package com.chirypto.utill.accountManger

import android.Manifest
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.chirypto.utill.accountManger.AccountManager.Companion.logOut
import java.sql.SQLException

class AccountChangeReceiver : BroadcastReceiver() {

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context, intent: Intent) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.GET_ACCOUNTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        val accounType = context.packageManager.getApplicationInfo(
            context.packageName,
            PackageManager.GET_META_DATA
        ).metaData.getString("account_type") ?: ""
        val accountsByType = AccountManager
            .get(context)
            .getAccountsByType(accounType)
        if (accountsByType.isEmpty()) {
            try {
                logOut(context)
            } catch (e: SQLException) {
                e.printStackTrace()
            }

        }
    }
}