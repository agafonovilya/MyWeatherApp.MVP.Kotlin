package ru.geekbrains.myweatherappmvpkotlin.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.cache.IWeatherCache
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.*
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.room.Database
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.room.RoomCurrent
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.room.RoomDaily
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.room.RoomHourly

class RoomWeatherCache(val db:Database): IWeatherCache {
    override fun getWeather(): Single<OneCallRequest> {
        return Single.fromCallable {
            val oneCallRequest = OneCallRequest()
            oneCallRequest.current = Current()
            oneCallRequest.hourly = ArrayList()
            oneCallRequest.daily = ArrayList()

            val roomCurrent = db.currentDao.get()[0]
            val roomHourly: List<RoomHourly> = db.hourlyDao.getAll()
            val roomDaily: List<RoomDaily> = db.dailyDao.getAll()

            with(oneCallRequest.current!!) {
                dt = roomCurrent.dt
                temp = roomCurrent.temp
                weather?.get(0)?.description = roomCurrent.description
                weather?.get(0)?.icon = roomCurrent.icon
            }

            roomHourly.forEach {
                val hourlyWeather = HourlyWeather()
                hourlyWeather.icon = it.icon

                val hourly = Hourly()
                hourly.dt = it.dt
                hourly.temp = it.temp
                hourly.weather = mutableListOf(hourlyWeather)

                oneCallRequest.hourly!!.add(hourly)
            }

            roomDaily.forEach {
                val temp = Temp()
                temp.day = it.dayTemperature
                temp.night = it.nightTemperature

                val dailyWeather = DailyWeather()
                dailyWeather.icon = it.icon

                val daily = Daily()
                daily.dt = it.dt
                daily.temp = temp
                daily.weather = listOf(dailyWeather)

                oneCallRequest.daily!!.add(daily)
            }

            return@fromCallable oneCallRequest
        }
    }

    override fun putWeather(oneCallRequest: OneCallRequest): Completable {
        return Completable.fromAction{
            val roomHourly: MutableList<RoomHourly> = ArrayList()
            val roomDaily: MutableList<RoomDaily> = ArrayList()
            val roomCurrent = RoomCurrent(
                    oneCallRequest.current?.dt,
                    oneCallRequest.current?.temp,
                    oneCallRequest.current?.weather?.get(0)?.description,
                    oneCallRequest.current?.weather?.get(0)?.icon)

            oneCallRequest.hourly?.forEach {
                roomHourly.add(RoomHourly(it.dt, it.temp, it.weather?.get(0)?.icon))
            }
            oneCallRequest.daily?.forEach{
                roomDaily.add(RoomDaily(it.dt, it.temp?.day, it.temp?.night, it.weather?.get(0)?.icon))
            }

            with(db.currentDao){
                delete()
                insert(roomCurrent)
            }

            db.hourlyDao.insert(roomHourly)
            db.dailyDao.insert(roomDaily)
        }.subscribeOn(Schedulers.io())
    }
}