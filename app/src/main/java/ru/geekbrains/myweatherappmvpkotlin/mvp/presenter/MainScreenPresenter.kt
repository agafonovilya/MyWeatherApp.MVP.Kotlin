package ru.geekbrains.myweatherappmvpkotlin.mvp.presenter

import android.util.Log
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.Current
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.Daily
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.Hourly
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo.IWeatherRepo
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.list.IDailyListPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.list.IHourlyListPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.IMainScreenView
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.list.IDailyItemView
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.list.IHourlyItemView
import ru.geekbrains.myweatherappmvpkotlin.navigation.Screens
import ru.terrakok.cicerone.Router
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainScreenPresenter: MvpPresenter<IMainScreenView>() {

    @Inject lateinit var weatherRepo: IWeatherRepo
    @Inject lateinit var router: Router
    @Inject lateinit var mainThreadScheduler: Scheduler
    @Inject lateinit var appLanguage: String
    @Inject lateinit var exclude: String


    class HourlyListPresenter : IHourlyListPresenter {
        val hourlyWeather = mutableListOf<Hourly>()
        override fun getCount() = hourlyWeather.size

        override fun bindView(view: IHourlyItemView) {
            val weather = hourlyWeather[view.pos]
            // TODO: Убрать отсюда обработку
            with(weather){

                dt?.let {
                    val simpleDateFormat = SimpleDateFormat("HH:mm")
                    val unixMilliSeconds = it.toLong() * 1000
                    val time = Date(unixMilliSeconds)
                    val formattedTime = simpleDateFormat.format(time)

                    view.setTime(formattedTime) }
                temp?.let{ view.setTemperature(String.format("%+.0f", it - 273.15))}

                weather.weather?.get(0)?.icon.let { view.loadIcon("https://openweathermap.org/img/wn/" + it + "@4x.png") }
            }

        }
    }

    class DailyListPresenter : IDailyListPresenter {
        val dailyWeather = mutableListOf<Daily>()
        // TODO: Убрать отсюда обработку

        override fun getCount() = dailyWeather.size

        override fun bindView(view: IDailyItemView) {
            val weather  = dailyWeather[view.pos]
            with(weather){
                dt?.let{
                    val simpleDateFormat = SimpleDateFormat("dd MMMM")
                    val unixMilliSeconds = it.toLong() * 1000
                    val date = Date(unixMilliSeconds)
                    val formattedDate = simpleDateFormat.format(date)
                    Log.d("WEATHER", "bindView: $formattedDate")
                    view.setDate(formattedDate)
                }

                temp?.let {
                    it.day?.let { it1 ->
                        view.setDayTemperature(String.format("%+.0f", it1 - 273.15))
                    }

                    it.night?.let { it1 ->
                        view.setNightTemperature(String.format("%+.0f", it1 - 273.15))
                    }
                }

                weather.weather?.get(0)?.icon?.let { view.loadIcon("https://openweathermap.org/img/wn/" + it + "@4x.png") }

            }
        }
    }

    val hourlyListPresenter = HourlyListPresenter()
    val dailyListPresenter = DailyListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadWeather()
    }

    private fun loadWeather(){
        weatherRepo.getWeather("59.57", "30.190", exclude, appLanguage)
            .observeOn(mainThreadScheduler)
            .subscribe({ it ->
                Log.d("WEATHER", "loadWeather: ++++++++++++++++++++++++++++++++++++++")

                hourlyListPresenter.hourlyWeather.clear()
                it.hourly?.let { hourly -> hourlyListPresenter.hourlyWeather.addAll(hourly) }
                viewState.updateHourlyList()

                dailyListPresenter.dailyWeather.clear()
                it.daily?.let { daily -> dailyListPresenter.dailyWeather.addAll(daily) }
                viewState.updateDailyList()

                viewState.updateCurrentView(it.current?.temp.toString(),
                        "Санкт-Петербург",
                        it?.current?.weather?.get(0)?.description ,
                        "https://openweathermap.org/img/wn/" + it.current?.weather?.get(0)!!.icon + "@4x.png")

                viewState.setRefreshIcon(false)

            }, {
                println("Error: ${it.message}")
            }
            )
    }


    fun locationPressed() {
        router.navigateTo(Screens.LocationScreen())
    }

    fun settingsPressed(){
        router.navigateTo(Screens.SettingsScreen())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun swipeRefreshLayout() {
        loadWeather()
    }
}