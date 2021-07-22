package com.nie.taipeizoo.module

import com.nie.taipeizoo.ui.MainViewModel
import com.nie.taipeizoo.ui.plant.PlantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { PlantViewModel(get()) }
}