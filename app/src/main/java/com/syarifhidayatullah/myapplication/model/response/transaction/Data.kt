package com.syarifhidayatullah.myapplication.model.response.transaction


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("kd_transaksi")
    val kd_transaksi: String,

    @Expose
    @SerializedName("jenis_pekerjaan")
    val jenis_pekerjaan: String,
    @Expose
    @SerializedName("lokasi_pekerjaan")
    val lokasi_pekerjaan: String,
    @Expose
    @SerializedName("created_at")
    val createdAt: Long,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long,
    @Expose
    @SerializedName("gudang")
    val gudang: Gudang,
    @Expose
    @SerializedName("teknisi")
    val teknisi: Teknisi



):Parcelable