package ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Temp {
    @SerializedName("day")
    @Expose
    var day: Double? = null

    @SerializedName("night")
    @Expose
    var night: Double? = null
}