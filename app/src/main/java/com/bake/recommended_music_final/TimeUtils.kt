package com.bake.recommended_music_final

import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class TimeUtils {
    class DateTime(
        var year: Int,
        var month: Int,
        var dayOfMonth: Int,
        var hourOfDay: Int,
        var minute: Int
    )

    fun createCurrentDateTime(): DateTime {
        val current = LocalDateTime.now()

        return DateTime(
            current.year,
            current.month.ordinal,
            current.dayOfMonth,
            current.hour,
            current.minute
        )
    }

    fun dateToString(year: Int, month: Int, dayOfMonth: Int): String {
        return "${year}년 ${month + 1}월 ${dayOfMonth}일"
    }

    fun getTimestamp(dateTime: DateTime): Long {
        return Timestamp(
            dateTime.year - 1900,
            dateTime.month,
            dateTime.dayOfMonth,
            dateTime.hourOfDay,
            dateTime.minute,
            0,
            0
        ).time
    }

    fun getTimestamp(): Long {
        val dateTime = createCurrentDateTime()
        return Timestamp(
            dateTime.year - 1900,
            dateTime.month,
            dateTime.dayOfMonth,
            dateTime.hourOfDay,
            dateTime.minute,
            0,
            0
        ).time
    }

    fun timeToString(hourOfDay: Int, minute: Int): String {
        val hour: String = if (hourOfDay > 10) {
            "$hourOfDay"
        } else {
            "0$hourOfDay"
        }

        val min: String = if (minute > 10) {
            "$minute"
        } else {
            "0$minute"
        }

        return "$hour:$min"
    }

    fun getDateTime(timestamp: Long): DateTime {
        val timeObject =
            Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime()
        return DateTime(
            timeObject.year,
            timeObject.month.ordinal,
            timeObject.dayOfMonth,
            timeObject.hour,
            timeObject.minute
        )
    }

    fun toDateString(timestamp: Long): String {
        val dateTime = getDateTime(timestamp)
        return dateToString(dateTime.year, dateTime.month, dateTime.dayOfMonth)
    }


    fun getWeather() : String{
        var weather ="spring"
        val dateAndTime: LocalDateTime = LocalDateTime.now()    //time 필요할때 사용
        val nowDate: LocalDate = LocalDate.now()
        var nowMonth = nowDate.format(DateTimeFormatter.ofPattern("MM"))
        if(nowMonth=="01"||nowMonth=="02"||nowMonth=="12"){
            weather = "winter"
        }else if(nowMonth=="03"||nowMonth=="04"||nowMonth=="05"){
            weather = "spring"
        }else if(nowMonth=="06"||nowMonth=="07"||nowMonth=="08"){
            weather = "summer"
        }else if(nowMonth=="09"||nowMonth=="10"||nowMonth=="11"){
            weather = "fall"
        }
    return weather
    }

}