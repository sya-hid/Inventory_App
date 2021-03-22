package com.syarifhidayatullah.myapplication.model.response.keranjang

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @Expose
    @SerializedName("kd_keranjang")
    val kd_keranjang: String,
    @Expose
    @SerializedName("nik_petugas")
    val nik_petugas: String,
    @Expose
    @SerializedName("kd_produk")
    val kd_produk: String,
    @Expose
    @SerializedName("jumlah")
    val jumlah: String,
    @Expose
    @SerializedName("created_at")
    val created_at: Long,
    @Expose
    @SerializedName("updated_at")
    val updated_at: Long,
    @Expose
    @SerializedName("produk")
    val produk: Produk
)