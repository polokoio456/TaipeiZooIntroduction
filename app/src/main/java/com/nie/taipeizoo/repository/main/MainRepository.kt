package com.nie.taipeizoo.repository.main

import com.nie.taipeizoo.data.remote.model.plant.PlantResponse
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShopResponse
import io.reactivex.Single

interface MainRepository {
    fun fetchAnimalShop(): Single<AnimalShopResponse>
    fun fetchPlantList(keyword: String): Single<PlantResponse>
}