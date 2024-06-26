package com.tchaikovsky.airtickets.utility

import com.tchaikovsky.domain.entity.Price

fun Price.toStringForUI(): String {
    val stringBuilder = StringBuilder(this.value.toString())
    with(stringBuilder) {
        if (length > 3)
            insert(length - 3, " ")
        if (length > 7)
            insert(length - 7, " ")
        append(" ", '₽')
    }
    return stringBuilder.toString()
}

fun mapCalendarMonthToMonthUI(calendarMonth: Int) =
    when (calendarMonth) {
        0 -> "янв" to "января"
        1 -> "февр" to "февраля"
        2 -> "март" to "марта"
        3 -> "апр" to "апреля"
        4 -> "май" to "мая"
        5 -> "июнь" to "июня"
        6 -> "июль" to "июля"
        7 -> "авг" to "августа"
        8 -> "сент" to "сентября"
        9 -> "окт" to "октября"
        10 -> "нояб" to "ноября"
        11 -> "дек" to "декабря"
        else -> throw IllegalStateException()
    }

fun mapCalendarDayOfWeekToDayUI(calendarMonth: Int) =
    when (calendarMonth) {
        2 -> "пн"
        3 -> "вт"
        4 -> "ср"
        5 -> "чт"
        6 -> "пт"
        7 -> "сб"
        1 -> "вс"
        else -> throw IllegalStateException()
    }