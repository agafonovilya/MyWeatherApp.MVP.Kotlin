package ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Current {
    @SerializedName("temp")
    @Expose
    var temp: Double? = null

    @SerializedName("weather")
    @Expose
    var weather: List<CurrentWeather>? = null
}