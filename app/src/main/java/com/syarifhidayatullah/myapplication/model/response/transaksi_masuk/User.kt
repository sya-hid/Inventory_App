package com.syarifhidayatullah.myapplication.model.response.transaksi_masuk


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
    @Expose
    @SerializedName("address")
    val address: String,
    @Expose
    @SerializedName("created_at")
    val createdAt: Long,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("level")
    val level: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("nik_petugas")
    val nikPetugas: String,
    @Expose
    @SerializedName("phone_number")
    val phoneNumber: String,
    @Expose
    @SerializedName("profil_picture")
    val profilPicture: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long
):Parcelable