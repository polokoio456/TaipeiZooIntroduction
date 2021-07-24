package com.nie.taipeizoo.repository.main

import com.nie.taipeizoo.data.remote.api.Api
import com.nie.taipeizoo.data.remote.model.plant.PlantResponse
import com.nie.taipeizoo.data.remote.model.zoo.AnimalShopResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MainRepositoryImpl(private val api: Api) : MainRepository {

    override fun fetchAnimalShop(): Single<AnimalShopResponse> {
        return  api.fetchAnimalShop()
            .subscribeOn(Schedulers.io())
    }

    override fun fetchPlantList(keyword: String): Single<PlantResponse> {
        return api.fetchPlantList(keyword)
            .subscribeOn(Schedulers.io())
    }
}