package com.syarifhidayatullah.myapplication.model.response.grafik


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @Expose
    @SerializedName("jumlah")
    val jumlah: Int,
    @Expose
    @SerializedName("nama")
    val nama: String
)