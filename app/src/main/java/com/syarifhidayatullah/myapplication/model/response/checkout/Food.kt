package com.syarifhidayatullah.myapplication.model.response.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Food(
    @Expose
    @SerializedName("created_at")
    val createdAt: Long,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("ingredients")
    val ingredients: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("picturePath")
    val picturePath: String,
    @Expose
    @SerializedName("price")
    val price: Int,
    @Expose
    @SerializedName("rate")
    val rate: Int,
    @Expose
    @SerializedName("types")
    val types: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long
)