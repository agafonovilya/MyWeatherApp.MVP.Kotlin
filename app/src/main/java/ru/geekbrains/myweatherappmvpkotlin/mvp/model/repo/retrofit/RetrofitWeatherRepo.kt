package ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo.retrofit

import android.util.Log
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.myweatherappmvpkotlin.BuildConfig
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.api.IDataSource
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.cache.IWeatherCache
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.OneCallRequest
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo.IWeatherRepo

class RetrofitWeatherRepo(val api: IDataSource,
                          val networkStatus: INetworkStatus,
                          val cache: IWeatherCache): IWeatherRepo {

    override fun getWeather(latitude: String,
                            longitude: String,
                            exclude: String,
                            appLanguage: String): Single<OneCallRequest> =
            networkStatus.isOnlineSingle().flatMap { isOnline ->
                if (isOnline) {
                    api.loadWeather(latitude, longitude, exclude, BuildConfig.WEATHER_API_KEY, appLanguage)
                        .flatMap { weather ->
                            cache.putWeather(weather).toSingleDefault(weather)
                        }
                } else {
                    cache.getWeather()
                }
            }.subscribeOn(Schedulers.io())

}