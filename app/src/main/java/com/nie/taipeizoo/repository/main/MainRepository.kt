package com.nie.taipeizoo.repository.main

import com.nie.taipeizoo.data.remote.model.plant.PlantResponse
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShopResponse

interface MainRepository {
    suspend fun fetchAnimalShop(): AnimalShopResponse
    suspend fun fetchPlantList(keyword: String): PlantResponse
}