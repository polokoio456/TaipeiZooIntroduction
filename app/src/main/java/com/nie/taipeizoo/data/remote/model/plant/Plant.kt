package com.nie.taipeizoo.data.remote.model.plant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Plant(
    @SerializedName("F_Pic01_URL")
    val picUrl: String,
    @SerializedName("\uFEFFF_Name_Ch")
    val chineseName: String,
    @SerializedName("F_Name_En")
    val englishName: String,
    @SerializedName("F_AlsoKnown")
    val alsoKnown: String,
    @SerializedName("F_Brief")
    val brief: String,
    @SerializedName("F_Feature")
    val feature: String,
    @SerializedName("F_Function＆Application")
    val function: String,
    @SerializedName("F_Update")
    val lastUpdateTime: String,
    @SerializedName("_id")
    val id: Int
) : Serializable
