package com.nie.taipeizoo.module

import com.nie.taipeizoo.ui.AnimalViewModel
import com.nie.taipeizoo.ui.plant.PlantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AnimalViewModel(get()) }
    viewModel { PlantViewModel(get()) }
}