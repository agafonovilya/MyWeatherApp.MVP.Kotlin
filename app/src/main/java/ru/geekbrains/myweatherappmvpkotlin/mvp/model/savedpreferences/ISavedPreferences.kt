package ru.geekbrains.myweatherappmvpkotlin.mvp.model.savedpreferences

interface ISavedPreferences {
    fun putPreferences(key: String, value: String)
    fun getPreferences(key: String): String
}