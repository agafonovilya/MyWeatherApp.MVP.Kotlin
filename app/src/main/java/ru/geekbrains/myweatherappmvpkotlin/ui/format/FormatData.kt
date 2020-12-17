package ru.geekbrains.myweatherappmvpkotlin.ui.format

import java.text.SimpleDateFormat
import java.util.*

    fun formatTimeHhMm(date: Int): String {
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val unixMilliSeconds = date.toLong() * 1000
        val time = Date(unixMilliSeconds)
        return simpleDateFormat.format(time)
    }

    fun formatDateDdMmmm(date: Int): String{
        val simpleDateFormat = SimpleDateFormat("dd MMMM")
        val unixMilliSeconds = date.toLong() * 1000
        val date = Date(unixMilliSeconds)
        return simpleDateFormat.format(date)
    }

    fun formatTemperature(temp: Double): String{
        return String.format("%+.0f", temp - 273.15)
    }
