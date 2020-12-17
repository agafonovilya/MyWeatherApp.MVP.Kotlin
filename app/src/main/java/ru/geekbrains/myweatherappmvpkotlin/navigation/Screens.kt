package ru.geekbrains.myweatherappmvpkotlin.navigation

import ru.geekbrains.myweatherappmvpkotlin.ui.fragments.LocationFragment
import ru.geekbrains.myweatherappmvpkotlin.ui.fragments.MainScreenFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class MainScreen: SupportAppScreen(){
        override fun getFragment() = MainScreenFragment.newInstance()
    }
    class LocationScreen: SupportAppScreen(){
        override fun getFragment() = LocationFragment.newInstance()
    }
}