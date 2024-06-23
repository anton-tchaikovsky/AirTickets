package com.tchaikovsky.airtickets

import android.app.Application
import com.tchaikovsky.airtickets.di.AppComponent
import com.tchaikovsky.airtickets.di.DaggerAppComponent
import com.tchaikovsky.airtickets.di.airTicketsScreen.AirTicketsScreenScopeContainer
import com.tchaikovsky.airtickets.di.airTicketsScreen.AirTicketsScreenSubcomponent

class AirTicketsApp : Application(), AirTicketsScreenScopeContainer {
    private var airTicketsScreenSubcomponent: AirTicketsScreenSubcomponent? = null

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

    override fun initScope(): AirTicketsScreenSubcomponent =
        appComponent.airTicketsScreenSubcomponent().also {
            airTicketsScreenSubcomponent = it
        }

    override fun releaseScope() {
        airTicketsScreenSubcomponent = null
    }
}