package com.example.android.videoapp

import android.app.Application
import android.util.Log
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import timber.log.Timber

class VideoAppApplication : Application(), CameraXConfig.Provider {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * Set CameraX logging level to Log.ERROR to avoid excessive logcat messages.
     * Refer to https://developer.android.com/reference/androidx/camera/core/CameraXConfig.Builder#setMinimumLoggingLevel(int)
     * for details.
     */
    override fun getCameraXConfig(): CameraXConfig {
        return CameraXConfig.Builder.fromConfig(Camera2Config.defaultConfig())
            .setMinimumLoggingLevel(Log.ERROR).build()
    }
}