package com.syarifhidayatullah.myapplication.model.response.teknisi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
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
    val email_verified_at: Any,
    @Expose
    @SerializedName("address")
    val address: String,
    @Expose
    @SerializedName("phone_number")
    val phone_number: String,
    @Expose
    @SerializedName("level")
    val level: String,
    @Expose
    @SerializedName("profil_picture")
    val profil_picture: String,
    @Expose
    @SerializedName("created_at")
    val created_at: Long,
    @Expose
    @SerializedName("updated_at")
    val updated_at: Long
)