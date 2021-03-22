package com.syarifhidayatullah.myapplication.model.response.transaction_detail


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.syarifhidayatullah.myapplication.model.response.keranjang.Produk
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("kd_transaksi_detail")
    val kd_transaksi_detail: Int,
    @Expose
    @SerializedName("kd_transaksi")
    val kd_transaksi: String,
    @Expose
    @SerializedName("kd_produk")
    val kd_produk: String,
    @Expose
    @SerializedName("jumlah")
    val jumlah: String,
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
    @SerializedName("produk")
    val produk: Produk



):Parcelable