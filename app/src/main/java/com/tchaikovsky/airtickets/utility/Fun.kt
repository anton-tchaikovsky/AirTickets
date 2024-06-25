package com.tchaikovsky.airtickets.utility

import com.tchaikovsky.airtickets.domain.entity.Price

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
        0 -> "янв"
        1 -> "февр"
        2 -> "март"
        3 -> "апр"
        4 -> "май"
        5 -> "июнь"
        6 -> "июль"
        7 -> "авг"
        8 -> "сент"
        9 -> "окт"
        10 -> "нояб"
        11 -> "дек"
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