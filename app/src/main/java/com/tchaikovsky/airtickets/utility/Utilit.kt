package com.tchaikovsky.airtickets.utility

import com.tchaikovsky.airtickets.domain.entity.Price
import java.lang.StringBuilder

fun Price.toStringForUI(): String{
    val stringBuilder = StringBuilder(this.value.toString())
        with(stringBuilder) {
            if (length>3)
                insert(length-3, " ")
            if (length>7)
                insert (length-7, " ")
            append(" ", '₽')
        }
    return stringBuilder.toString()
}