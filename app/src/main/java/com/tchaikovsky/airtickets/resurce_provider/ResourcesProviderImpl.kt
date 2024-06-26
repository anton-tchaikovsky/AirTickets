package com.tchaikovsky.airtickets.resurce_provider

import android.content.Context
import javax.inject.Inject

class ResourcesProviderImpl @Inject constructor(val context: Context) : ResourcesProvider {

    override fun getString(string: StringEnum): String =
        context.applicationContext.resources.getString(string.id)

    override fun getFotoId(foto: FotoEnum): Int =
        foto.id
}