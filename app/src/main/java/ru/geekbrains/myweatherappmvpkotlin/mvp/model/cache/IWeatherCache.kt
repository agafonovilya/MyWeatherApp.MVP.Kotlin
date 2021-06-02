package ru.geekbrains.myweatherappmvpkotlin.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.Daily
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.OneCallRequest

interface IWeatherCache {
    fun getWeather(): Single<OneCallRequest>
    fun getDayWeather(unixUTC: Int?): Single<Daily>
    fun putWeather(oneCallRequest: OneCallRequest): Completable
}