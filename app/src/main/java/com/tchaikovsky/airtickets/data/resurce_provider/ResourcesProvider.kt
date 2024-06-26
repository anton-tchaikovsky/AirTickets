package com.tchaikovsky.airtickets.data.resurce_provider

interface ResourcesProvider {
    fun getString(string: StringEnum): String

    fun getFotoId(foto: FotoEnum): Int
}