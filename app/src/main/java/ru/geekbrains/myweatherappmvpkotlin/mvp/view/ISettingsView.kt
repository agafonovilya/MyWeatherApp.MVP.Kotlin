package ru.geekbrains.myweatherappmvpkotlin.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ISettingsView:MvpView {
    fun init()
    fun release()
}