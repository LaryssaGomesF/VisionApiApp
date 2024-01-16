package com.example.visionapiapp.application

import android.app.Application
import com.example.visionapiapp.di.modulesApp
import org.koin.core.context.startKoin

class VisionAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modulesApp
        }
    }
}