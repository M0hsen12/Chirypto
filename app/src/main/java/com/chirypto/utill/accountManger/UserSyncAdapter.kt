package com.chirypto.utill.accountManger

import android.accounts.Account
import android.content.*
import android.os.Bundle

class UserSyncAdapter : AbstractThreadedSyncAdapter {
    // Global variables
    // Define a variable to contain a content resolver instance
    internal var mContentResolver: ContentResolver

    /**
     * Set up the sync adapter
     */
    constructor(context: Context, autoInitialize: Boolean) : super(context, autoInitialize) {
        /*
		 * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.contentResolver
    }

    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    constructor(
        context: Context,
        autoInitialize: Boolean,
        allowParallelSyncs: Boolean
    ) : super(context, autoInitialize, allowParallelSyncs) {
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.contentResolver
    }

    override fun onPerformSync(
        account: Account,
        bundle: Bundle,
        s: String,
        contentProviderClient: ContentProviderClient,
        syncResult: SyncResult
    ) {
        //		if (MyUser.getInstance().isLoggedIn(getContext())) {
        //
        //			new UserMefactory(getContext()).makeRequest().enqueue(new Callback<ApiResponse>() {
        //				@Override
        //				public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
        //					if (response.isSuccessful()) {
        //						if (response.body().isSuccessful()) {
        //							Log.d(TAG, "You data synced");
        //							Utils.updateUserData(getContext(), response.body().getData().toString());
        //						}
        //					}
        //				}
        //
        //				@Override
        //				public void onFailure(Call<ApiResponse> call, Throwable t) {
        //
        //				}
        //			});
        //		}
    }
}
