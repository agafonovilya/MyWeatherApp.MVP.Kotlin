package ru.geekbrains.myweatherappmvpkotlin.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.api.getIconUrl
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.Daily
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.Hourly
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo.IWeatherRepo
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.savedpreferences.ISavedPreferences
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.list.IDailyListPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.list.IHourlyListPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.IMainScreenView
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.list.IDailyItemView
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.list.IHourlyItemView
import ru.geekbrains.myweatherappmvpkotlin.navigation.Screens
import ru.geekbrains.myweatherappmvpkotlin.ui.sharedpreferences.SharedPrefKeys
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainScreenPresenter: MvpPresenter<IMainScreenView>() {

    @Inject lateinit var weatherRepo: IWeatherRepo
    @Inject lateinit var router: Router
    @Inject lateinit var mainThreadScheduler: Scheduler
    @Inject lateinit var appLanguage: String
    @Inject lateinit var exclude: String
    @Inject lateinit var sharedPreferences: ISavedPreferences

    class HourlyListPresenter : IHourlyListPresenter {
        val hourlyWeather = mutableListOf<Hourly>()

        override fun getCount() = hourlyWeather.size

        override fun bindView(view: IHourlyItemView) {
            val weather = hourlyWeather[view.pos]
            with(weather){
                dt?.let { view.setTime(it) }
                temp?.let{ view.setTemperature(it)}
                weather.weather?.get(0)?.icon?.let { view.loadIcon(getIconUrl(it)) }
            }

        }
    }

    class DailyListPresenter : IDailyListPresenter {
        val dailyWeather = mutableListOf<Daily>()

        override fun getCount() = dailyWeather.size

        override fun bindView(view: IDailyItemView) {
            val weather  = dailyWeather[view.pos]
            with(weather){
                dt?.let{ view.setDate(it) }
                temp?.let {
                    it.day?.let { view.setDayTemperature(it) }
                    it.night?.let { view.setNightTemperature(it) }
                }
                weather.weather?.get(0)?.icon?.let { view.loadIcon(getIconUrl(it)) }
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
        viewState.setRefreshIcon(true)

        weatherRepo.getWeather(latitude = sharedPreferences.getPreferences(SharedPrefKeys.CURRENT_LATITUDE.key),
                                longitude = sharedPreferences.getPreferences(SharedPrefKeys.CURRENT_LONGITUDE.key),
                                exclude = exclude, appLanguage = appLanguage)
            .observeOn(mainThreadScheduler)
            .subscribe({ it ->
                hourlyListPresenter.hourlyWeather.clear()
                it.hourly?.let { hourly -> hourlyListPresenter.hourlyWeather.addAll(hourly) }
                viewState.updateHourlyList()

                dailyListPresenter.dailyWeather.clear()
                it.daily?.let { daily -> dailyListPresenter.dailyWeather.addAll(daily) }
                viewState.updateDailyList()

                viewState.updateCurrentView(temperature = it.current?.temp ?: 0.0,
                        cityName = sharedPreferences.getPreferences(SharedPrefKeys.CURRENT_CITY.key),
                        description = it?.current?.weather?.get(0)?.description ?: "",
                        url = getIconUrl(it.current?.weather?.get(0)?.icon ?: "") )

                viewState.setRefreshIcon(false)

            }, {
                println("Error: ${it.message}")
            }
            )
    }

    fun locationPressed() {
        router.navigateTo(Screens.LocationScreen())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun swipeRefreshLayout() {
        loadWeather()
    }

    fun onResume() {
        loadWeather()
    }
}