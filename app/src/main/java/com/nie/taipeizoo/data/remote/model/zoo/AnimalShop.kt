package com.nie.taipeizoo.data.remote.model.zoo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnimalShop(
    @SerializedName("E_Pic_URL")
    val picUrl: String,
    @SerializedName("E_Geo")
    val geo: String,
    @SerializedName("E_Info")
    val info: String,
    @SerializedName("E_no")
    val no: String,
    @SerializedName("E_Category")
    val category: String,
    @SerializedName("E_Name")
    val name:String,
    @SerializedName("E_Memo")
    val memo: String,
    @SerializedName("_id")
    val id: Int,
    @SerializedName("E_URL")
    val url: String
) : Serializable