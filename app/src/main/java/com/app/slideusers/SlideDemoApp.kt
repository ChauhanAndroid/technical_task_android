package com.app.slideusers

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SlideDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}