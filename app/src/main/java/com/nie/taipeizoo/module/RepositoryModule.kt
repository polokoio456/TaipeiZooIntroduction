package com.nie.taipeizoo.module

import com.nie.taipeizoo.repository.main.MainRepository
import com.nie.taipeizoo.repository.main.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}