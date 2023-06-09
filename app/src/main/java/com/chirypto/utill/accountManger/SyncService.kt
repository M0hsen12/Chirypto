package com.chirypto.utill.accountManger

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Define a Service that returns an IBinder for the
 * sync adapter class, allowing the sync adapter framework to call
 * onPerformSync().
 */
class SyncService : Service() {

    /*
     * Instantiate the sync adapter obj.
     */
    override fun onCreate() {
        /*
	     * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */
        synchronized(sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = UserSyncAdapter(
                        applicationContext,
                        true
                    )
            }
        }
    }

    /**
     * Return an obj that allows the system to invoke
     * the sync adapter.
     */
    override fun onBind(intent: Intent): IBinder? {
        /*
         * Get the obj that allows external processes
         * to call onPerformSync(). The obj is created
         * in the base class code when the SyncAdapter
         * constructors call super()
         */
        return sSyncAdapter?.syncAdapterBinder
    }

    companion object {
        // Object to use as a thread-safe lock
        private val sSyncAdapterLock = Any()

        // Storage for an instance of the sync adapter
        private var sSyncAdapter: UserSyncAdapter? =
            null
    }
}