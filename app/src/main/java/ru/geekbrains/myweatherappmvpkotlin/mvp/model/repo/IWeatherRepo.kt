package ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.OneCallRequest

interface IWeatherRepo {
    fun getWeather(latitude: String,
                   longitude: String,
                   exclude: String,
                   appLanguage: String): Single<OneCallRequest>
}