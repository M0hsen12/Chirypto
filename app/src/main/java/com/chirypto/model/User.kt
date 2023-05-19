package com.chirypto.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName


open class User(


    @SerializedName("Id")
    var id: Int? = null,

    @SerializedName("Email")
    var email: String? = null,

    @SerializedName("UserName")
    var userName: String? = null,

    @SerializedName("PhoneNo")
    var phone: String? = null,

    @SerializedName("AvatarUrl")
    var avatar: String? = null,

    @SerializedName("Token")
    var token: String? = null,

    @SerializedName("VerifiedDate")
    var verifiedDate: Int? = null,


)

