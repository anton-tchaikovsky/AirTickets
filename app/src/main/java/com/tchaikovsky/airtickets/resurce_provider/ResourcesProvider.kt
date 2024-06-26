package com.tchaikovsky.airtickets.resurce_provider

interface ResourcesProvider {
    fun getString(string: StringEnum): String

    fun getFotoId(foto: FotoEnum): Int
}