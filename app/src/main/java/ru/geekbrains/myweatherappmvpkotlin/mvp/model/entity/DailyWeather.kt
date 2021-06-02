package ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DailyWeather {
    @SerializedName("icon")
    @Expose
    var icon: String? = null
}