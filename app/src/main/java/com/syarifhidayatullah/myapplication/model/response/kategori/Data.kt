package com.syarifhidayatullah.myapplication.model.response.kategori

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @Expose
    @SerializedName("kd_kategori")
    val kd_kategori: String,
    @Expose
    @SerializedName("nama_kategori")
    val nama_kategori: String,
      @Expose
    @SerializedName("created_at")
    val created_at: Long,
    @Expose
    @SerializedName("updated_at")
    val updated_at: Long
)