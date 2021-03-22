package com.syarifhidayatullah.myapplication.model.response.transaction_detail


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gudang(
    @Expose
    @SerializedName("nik_petugas")
    val nik_petugas: String,
    @Expose
    @SerializedName("name")
    val name: String,
     @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String?,
    @Expose
    @SerializedName("address")
    val address: String,
    @Expose
    @SerializedName("phone_number")
    val phone_number: String,

    @Expose
    @SerializedName("profil_picture")
    val profil_picture: String,
    @Expose
    @SerializedName("level")
    val level: String,
    @Expose
    @SerializedName("created_at")
    val createdAt: Long,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long?
) : Parcelable