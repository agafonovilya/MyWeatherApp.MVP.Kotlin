package ru.geekbrains.myweatherappmvpkotlin.ui.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import ru.geekbrains.myweatherappmvpkotlin.App
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.location.Location
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.savedpreferences.ISavedPreferences

enum class SharedPrefKeys(val key: String) {
    CURRENT_CITY("currentCity"),
    CURRENT_LONGITUDE("currentLongitude"),
    CURRENT_LATITUDE("currentLatitude")
}

class SharedPreferences: ISavedPreferences {
    private val sharedPreferences: SharedPreferences = App.instance.applicationContext
            .getSharedPreferences("", Context.MODE_PRIVATE)


    override fun putPreferences(key: String, value: String) {
        val editor = sharedPreferences.edit()

        when(key){
            SharedPrefKeys.CURRENT_CITY.key ->
                editor.putString(SharedPrefKeys.CURRENT_CITY.key, value)

            SharedPrefKeys.CURRENT_LONGITUDE.key ->
                editor.putString(SharedPrefKeys.CURRENT_LONGITUDE.key, value)

            SharedPrefKeys.CURRENT_LATITUDE.key ->
                 editor.putString(SharedPrefKeys.CURRENT_LATITUDE.key, value)
        }
        editor.commit()
    }

    override fun getPreferences(key: String): String {
        return when (key) {
            SharedPrefKeys.CURRENT_CITY.key ->
                sharedPreferences.getString(SharedPrefKeys.CURRENT_CITY.key, "Saint-Petersburg") ?: " "
            SharedPrefKeys.CURRENT_LONGITUDE.key ->
                sharedPreferences.getString(SharedPrefKeys.CURRENT_LONGITUDE.key, "30.26") ?: " "
            SharedPrefKeys.CURRENT_LATITUDE.key ->
                sharedPreferences.getString(SharedPrefKeys.CURRENT_LATITUDE.key, "59.89") ?: " "
            else -> ""
        }
    }

}


