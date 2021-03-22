package com.syarifhidayatullah.myapplication.model.response.rop


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
   @Expose
    @SerializedName("data")
    val `data`: List<DataX>
)