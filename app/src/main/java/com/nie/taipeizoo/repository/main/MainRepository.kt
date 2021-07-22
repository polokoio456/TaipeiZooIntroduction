package com.nie.taipeizoo.repository.main

import com.nie.taipeizoo.data.remote.model.plant.PlantResponse
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShopResponse
import io.reactivex.Single

interface MainRepository {
    fun fetchShop(): Single<AnimalShopResponse>
    fun fetchPlantList(keyword: String): Single<PlantResponse>
}