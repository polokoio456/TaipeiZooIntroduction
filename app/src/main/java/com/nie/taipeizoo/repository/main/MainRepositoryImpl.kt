package com.nie.taipeizoo.repository.main

import com.nie.taipeizoo.data.remote.api.Api
import com.nie.taipeizoo.data.remote.model.plant.PlantResponse
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShopResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MainRepositoryImpl(private val api: Api) : MainRepository {

    override suspend fun fetchAnimalShop(): AnimalShopResponse {
        return  api.fetchAnimalShop()
    }

    override suspend fun fetchPlantList(keyword: String): PlantResponse {
        return api.fetchPlantList(keyword)
    }
}