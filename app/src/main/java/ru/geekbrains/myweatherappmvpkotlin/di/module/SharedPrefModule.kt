package ru.geekbrains.myweatherappmvpkotlin.di.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.savedpreferences.ISavedPreferences
import ru.geekbrains.myweatherappmvpkotlin.ui.sharedpreferences.SharedPreferences

@Module
class SharedPrefModule {
    @Provides
    fun getSharedPref(): ISavedPreferences = SharedPreferences()
}