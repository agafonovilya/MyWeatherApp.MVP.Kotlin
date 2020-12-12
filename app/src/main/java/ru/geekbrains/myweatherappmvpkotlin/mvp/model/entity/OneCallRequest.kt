package ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OneCallRequest {
    @SerializedName("current")
    @Expose
    var current: Current? = null

    @SerializedName("hourly")
    @Expose
    var hourly: ArrayList<Hourly>? = null

    @SerializedName("daily")
    @Expose
    var daily: ArrayList<Daily>? = null
}