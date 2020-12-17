package ru.geekbrains.myweatherappmvpkotlin.mvp.view.list

interface IDailyItemView: IItemView {
    fun setDate(date: Int)
    fun setDayTemperature(dayTemperature: Double)
    fun setNightTemperature(nightTemperature: Double)
    fun loadIcon(url: String)
}