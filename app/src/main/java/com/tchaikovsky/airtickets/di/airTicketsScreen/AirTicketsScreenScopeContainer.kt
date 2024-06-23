package com.tchaikovsky.airtickets.di.airTicketsScreen

interface AirTicketsScreenScopeContainer {
    fun initScope(): AirTicketsScreenSubcomponent

    fun releaseScope()
}