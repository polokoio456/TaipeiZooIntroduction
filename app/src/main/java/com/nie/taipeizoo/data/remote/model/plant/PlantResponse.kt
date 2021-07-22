package com.nie.taipeizoo.data.remote.model.plant

import com.google.gson.annotations.SerializedName

data class PlantResponse(
    @SerializedName("result")
    val result: PlantResult
)
