package com.brianvega.startupweekend.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat

object DateConvert {

    private const val DATE_FORMAT = "dd-MM-yyyy"

    fun timestampToString(timestamp: Timestamp): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT)
        return dateFormat.format(timestamp.toDate())
    }

}