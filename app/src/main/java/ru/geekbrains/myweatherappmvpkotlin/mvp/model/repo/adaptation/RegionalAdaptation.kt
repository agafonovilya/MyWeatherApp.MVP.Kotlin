package ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo.adaptation

import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.OneCallRequest
import java.text.SimpleDateFormat
import java.util.*

class RegionalAdaptation {
    /*fun adaptTemperature(oneCallRequest: OneCallRequest): OneCallRequest {
        oneCallRequest.hourly?.forEach {
            it.dt?.let {
                val simpleDateFormat = SimpleDateFormat("HH:mm")
                val unixMilliSeconds = it.toLong() * 1000
                val time = Date(unixMilliSeconds)
                val formattedTime = simpleDateFormat.format(time)
            set time

            temp?.let{ String.format("%+.0f", it - 273.15)}
        }
    }*/
}