package com.nie.taipeizoo.module

import com.nie.taipeizoo.ui.animal.AnimalShopAdapter
import com.nie.taipeizoo.ui.plant.PlantAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { AnimalShopAdapter() }
    factory { PlantAdapter() }
}