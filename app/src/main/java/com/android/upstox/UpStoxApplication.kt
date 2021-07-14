package com.android.upstox

import android.app.Application
import com.android.spacexclient.di.AppComponent
import com.android.spacexclient.di.DaggerAppComponent
import timber.log.Timber

class UpStoxApplication: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().application(this).build()
        Timber.plant(Timber.DebugTree())
    }
}