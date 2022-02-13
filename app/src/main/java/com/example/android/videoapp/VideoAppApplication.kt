package com.example.android.videoapp

import android.app.Application
import timber.log.Timber

class VideoAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}