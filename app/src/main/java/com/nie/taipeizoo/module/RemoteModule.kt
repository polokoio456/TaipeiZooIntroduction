package com.nie.taipeizoo.module

import com.nie.taipeizoo.data.remote.NetworkService
import com.nie.taipeizoo.data.remote.api.Api
import org.koin.dsl.module

val remoteModule = module {
    single { NetworkService().create(Api::class.java) }
}