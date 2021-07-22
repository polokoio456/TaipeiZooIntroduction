package com.nie.taipeizoo.data.remote.model.plant

import com.google.gson.annotations.SerializedName

data class PlantResult(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("sort")
    val sort: String,
    @SerializedName("results")
    val results: List<Plant>
)
