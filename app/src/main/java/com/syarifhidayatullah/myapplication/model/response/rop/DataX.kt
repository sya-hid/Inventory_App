package com.syarifhidayatullah.myapplication.model.response.rop


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataX(
    @Expose
    @SerializedName("Kategori")
    val kategori: String,
    @Expose
    @SerializedName("max")
    val max: Int,
    @Expose
    @SerializedName("rata")
    val rata: Double,
    @Expose
    @SerializedName("rop")
    val rop: Int,
    @Expose
    @SerializedName("stok")
    val stok: Int
):Parcelable