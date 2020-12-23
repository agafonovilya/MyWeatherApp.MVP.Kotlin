package ru.geekbrains.myweatherappmvpkotlin

import android.app.Application
import ru.geekbrains.myweatherappmvpkotlin.di.AppComponent
import ru.geekbrains.myweatherappmvpkotlin.di.DaggerAppComponent
import ru.geekbrains.myweatherappmvpkotlin.di.daily.DailySubcomponent
import ru.geekbrains.myweatherappmvpkotlin.di.location.LocationSubcomponent
import ru.geekbrains.myweatherappmvpkotlin.di.module.AppModule

class App: Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var locationSubcomponent: LocationSubcomponent? = null
        private set
    var dailySubcomponent: DailySubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun initLocationSubcomponent() = appComponent.locationSubcomponent().also { locationSubcomponent = it }
    fun releaseLocationSubcomponent() { locationSubcomponent = null}
    fun initDailySubcomponent() = appComponent.dailySubcomponent().also { dailySubcomponent = it }
    fun releaseDailySubcomponent() { dailySubcomponent = null}
}