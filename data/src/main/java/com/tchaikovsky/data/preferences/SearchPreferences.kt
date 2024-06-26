package com.tchaikovsky.data.preferences

interface SearchPreferences {
    fun readWhere(): String?

    fun readWhereFrom(): String?

    fun saveWhere(where: String?)

    fun saveWhereFrom(whereFrom: String?)
}