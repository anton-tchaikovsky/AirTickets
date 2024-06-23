package com.tchaikovsky.airtickets.data.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SearchPreferencesImpl @Inject constructor(context: Context): SearchPreferences {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE
    )

    override fun readWhere(): String? =
        sharedPreferences.getString(KEY_WHERE, null)

    override fun readWhereFrom(): String? =
        sharedPreferences.getString(KEY_WHERE_FROM, null)

    override fun saveWhere(where: String?) {
        sharedPreferences.edit()
            .putString(KEY_WHERE, where)
            .apply()
    }

    override fun saveWhereFrom(whereFrom: String?) {
        sharedPreferences.edit()
            .putString(KEY_WHERE_FROM, whereFrom)
            .apply()
    }

    companion object {
        private const val NAME_SHARED_PREFERENCES = "air_tickets_pref"
        private const val KEY_WHERE = "KeyWhere"
        private const val KEY_WHERE_FROM = "KeyWhereFrom"
    }
}