package com.tchaikovsky.airtickets.resurce_provider

import com.tchaikovsky.airtickets.R


enum class StringEnum(val id: Int) {
    DEFAULT_ERROR(R.string.default_error), NO_SELECTED_WHERE_FROM(R.string.not_selected_where_from),
    NO_SELECTED_WHERE(R.string.not_selected_where),
    ANYWHERE(R.string.anywhere), FLIGHT(R.string.flight), SHORT_DATE(R.string.short_date),
    FLIGHT_TIME(R.string.flight_time), TIME (R.string.time), LONG_DATE(R.string.long_date),
}