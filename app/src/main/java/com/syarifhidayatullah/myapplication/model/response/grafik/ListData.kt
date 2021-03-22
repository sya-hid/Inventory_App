package com.syarifhidayatullah.myapplication.model.response.grafik


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListData(
    @Expose
    @SerializedName("data")
    val `data`: List<Data>,
    @Expose
    @SerializedName("meta")
    val meta: Meta
)