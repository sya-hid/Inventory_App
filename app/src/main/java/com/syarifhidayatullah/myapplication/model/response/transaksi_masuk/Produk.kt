package com.syarifhidayatullah.myapplication.model.response.transaksi_masuk


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Produk(
    @Expose
  @SerializedName("created_at")
    val createdAt: Long,
    @Expose
  @SerializedName("gambar_produk")
    val gambarProduk: String,
    @Expose
  @SerializedName("id")
    val id: Int,
    @Expose
  @SerializedName("kd_kategori")
    val kdKategori: String,
    @Expose
  @SerializedName("kd_produk")
    val kdProduk: String,
    @Expose
  @SerializedName("nama_produk")
    val namaProduk: String,
    @Expose
  @SerializedName("stok")
    val stok: String,
    @Expose
  @SerializedName("updated_at")
    val updatedAt: Long
):Parcelable