package ru.geekbrains.myweatherappmvpkotlin.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ILocationView: MvpView {
    fun init()
    fun release()
}