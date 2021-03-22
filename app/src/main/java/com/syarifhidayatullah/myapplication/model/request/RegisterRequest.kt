package com.syarifhidayatullah.myapplication.model.request

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class RegisterRequest(
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("email")
    var email: String,
    @Expose
    @SerializedName("password")
    var password: String,
    @Expose
    @SerializedName("password_confirmation")
    var password_confirmation: String,
    @Expose
    @SerializedName("address")
    var address: String,
    @Expose
    @SerializedName("city")
    var city: String,
    @Expose
    @SerializedName("houseNumber")
    var houseNumber: String,
    @Expose
    @SerializedName("phoneNumber")
    var phoneNumber: String,
    @Expose
    @SerializedName("filePath")
    var filePath: Uri? = null
) : Parcelable