package com.nie.taipeizoo.data.remote.model.zoo

import com.google.gson.annotations.SerializedName

data class AnimalShopResponse(
    @SerializedName("result")
    val result: AnimalShopResult
)
