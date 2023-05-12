package com.chirypto.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName


open class User(


    @SerializedName("Id")
    var id: Int? = null,


    @SerializedName("FirstName")
    var firstName: String? = null,


    @SerializedName("LastName")
    var lastName: String? = null,


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


    @SerializedName("RefreshToken")
    var refreshToken: String? = null,


    @SerializedName("InviteCode")
    var inviteCode: String? = null,


    @SerializedName("Packages")
    var packages: List<PackagesItem?>? = null,

    @SerializedName("ClientId")
    var clientId: Int? = null,

    @SerializedName("ExpireToken")
    var expireToken: Int? = null,

    @SerializedName("HasGiftCode")
    var hasGiftCode: Boolean? = null,

    @SerializedName("VerifiedDate")
    var verifiedDate: Int? = null,

    @SerializedName("SourceChannel")
    var sourceChannel: String? = null

)  {

    fun getFullName() = "${firstName ?: ""} ${lastName ?: ""}"

    fun getSubscriptionRemainingTime(): Long {
        var remainingTime = 0L
        packages?.forEach {
            remainingTime += it?.remainingTime ?: 0
        }
        return remainingTime
    }
}


data class PackagesItem(

    @SerializedName("Id")
    var id: Int? = null,

    @SerializedName("PackageId")
    var packageId: Int? = null,

    @SerializedName(value = "Name", alternate  = ["PackageName"])
    var name: String? = null,

    @SerializedName("Sku")
    var sku: String? = null,

    @SerializedName("SkuToken")
    var skuToken: String? = null,

    @SerializedName("RemainingTime")
    var remainingTime: Int? = null,

    @SerializedName("Price")
    var price: Int? = null

)