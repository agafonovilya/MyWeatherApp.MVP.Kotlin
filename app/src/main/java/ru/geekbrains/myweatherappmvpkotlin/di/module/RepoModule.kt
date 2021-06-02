package ru.geekbrains.myweatherappmvpkotlin.di.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.api.IDataSource
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.cache.IWeatherCache
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.cache.room.RoomWeatherCache
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.room.Database
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo.IWeatherRepo
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo.retrofit.RetrofitWeatherRepo
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    fun weatherCache(database: Database): IWeatherCache {
        return  RoomWeatherCache(database)
    }

    @Singleton
    @Provides
    fun weatherRepo(api: IDataSource,
                    networkStatus: INetworkStatus,
                    cache: IWeatherCache): IWeatherRepo = RetrofitWeatherRepo(api, networkStatus, cache)
}