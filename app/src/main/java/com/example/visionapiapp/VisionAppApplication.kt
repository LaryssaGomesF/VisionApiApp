package com.example.visionapiapp

import android.app.Application
import org.koin.core.context.startKoin

class VisionAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {

        }
    }
}