package com.syarifhidayatullah.myapplication.model.response.rop


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataRop(
    @Expose
    @SerializedName("data")
    val `data`: Data,
    @Expose
    @SerializedName("meta")
    val meta: Meta
)