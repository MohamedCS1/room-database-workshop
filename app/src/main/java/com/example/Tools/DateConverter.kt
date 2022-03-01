package com.example.Tools

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun LongToDate(milliseconds:Long?):Date {
        var date:Date? = null
        if (milliseconds != null) {
            date = Date(milliseconds)
        }
        return date!!
    }

    @TypeConverter
    fun DateToLong(date: Date?):Long
    {
        var long:Long? = null
        if (date != null) {
            long = date.time
        }
        return long!!
    }

}