package com.nie.taipeizoo

import android.app.Application
import com.nie.taipeizoo.module.adapterModule
import com.nie.taipeizoo.module.remoteModule
import com.nie.taipeizoo.module.repositoryModule
import com.nie.taipeizoo.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    adapterModule,
                    repositoryModule,
                    viewModelModule,
                    remoteModule
                )
            )
        }
    }
}