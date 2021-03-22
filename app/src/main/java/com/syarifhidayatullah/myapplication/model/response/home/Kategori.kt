package com.syarifhidayatullah.myapplication.model.response.home


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kategori(
   @Expose
    @SerializedName("kd_kategori")
    val kd_kategori: String,
    @Expose
    @SerializedName("nama_kategori")
    val nama_kategori: String,
     @Expose
    @SerializedName("created_at")
    val createdAt: Long,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long?
) : Parcelable