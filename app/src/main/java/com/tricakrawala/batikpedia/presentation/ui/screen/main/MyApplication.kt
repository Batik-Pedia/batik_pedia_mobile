package com.tricakrawala.batikpedia.presentation.ui.screen.main

import android.app.Application
import com.tricakrawala.batikpedia.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(dataModule)
        }
    }
}