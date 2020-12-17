package ru.geekbrains.myweatherappmvpkotlin.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo.location.LocationRepo
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.savedpreferences.ISavedPreferences
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.ILocationView
import ru.geekbrains.myweatherappmvpkotlin.ui.sharedpreferences.SharedPrefKeys
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class LocationPresenter: MvpPresenter<ILocationView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var sharedPreferences: ISavedPreferences

    private var locationList = LocationRepo().locationArray

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.initAutoCompeteTextView(getCitiesList())
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
    
    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun locationChanged(cityName: String) {
        locationList.forEach {
            if (it.cityName == cityName){
                sharedPreferences.putPreferences(SharedPrefKeys.CURRENT_CITY.key, it.cityName)
                sharedPreferences.putPreferences(SharedPrefKeys.CURRENT_LATITUDE.key, it.latitude)
                sharedPreferences.putPreferences(SharedPrefKeys.CURRENT_LONGITUDE.key, it.longitude)
            }
        }
        router.exit()
    }

    private fun getCitiesList(): MutableList<String> {
        val citiesList: MutableList<String> = ArrayList()
        locationList.forEach {
            citiesList.add(it.cityName)
        }
        return citiesList
    }
}