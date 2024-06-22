package com.tchaikovsky.airtickets

import android.app.Application
import com.tchaikovsky.airtickets.di.AppComponent
import com.tchaikovsky.airtickets.di.DaggerAppComponent

class AirTicketsApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createAppComponent()
        appComponent.inject(this)
    }

    private fun createAppComponent() =
        DaggerAppComponent.builder()
            .applicationContext(this)
            .build()

    companion object {
        lateinit var instance: AirTicketsApp
    }
}