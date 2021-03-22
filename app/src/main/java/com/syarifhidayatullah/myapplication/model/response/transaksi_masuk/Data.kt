package com.syarifhidayatullah.myapplication.model.response.transaksi_masuk


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("created_at")
    val createdAt: String,
    @Expose
    @SerializedName("jumlah")
    val jumlah: String,
    @Expose
    @SerializedName("kd_produk")
    val kdProduk: String,
    @Expose
    @SerializedName("kd_transaksi_masuk")
    val kdTransaksiMasuk: Int,
    @Expose
    @SerializedName("keterangan")
    val keterangan: String,
    @Expose
    @SerializedName("nik_petugas")
    val nikPetugas: String,
    @Expose
    @SerializedName("produk")
    val produk: Produk,
    @Expose
    @SerializedName("tgl_transaksi")
    val tglTransaksi: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String,
    @Expose
    @SerializedName("user")
    val user: User
) : Parcelable