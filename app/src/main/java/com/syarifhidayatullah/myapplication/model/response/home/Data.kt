package com.syarifhidayatullah.myapplication.model.response.home


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("kd_produk")
    val kd_produk: String?,
    @Expose
    @SerializedName("kd_kategori")
    val kd_kategori: String?,
    @Expose
    @SerializedName("nama_produk")
    val nama_produk: String?,
    @Expose
    @SerializedName("gambar_produk")
    val gambar_produk: String?,
    @Expose
    @SerializedName("stok")
    val stok: Int?,
    @Expose
    @SerializedName("created_at")
    val createdAt: Long?,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long?,
    @Expose
    @SerializedName("kategori")
    val kategori: Kategori
):Parcelable