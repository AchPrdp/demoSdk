package com.app.yoholiday

import android.app.Application
import com.app.mylibrary.MySdKObject
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MySdKObject.initWithContext(this)
    }
}