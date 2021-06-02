package ru.geekbrains.myweatherappmvpkotlin.mvp.view.list

interface IDailyItemView: IItemView {
    fun setDate(date: String)
    fun setDayTemperature(dayTemperature: String)
    fun setNightTemperature(nightTemperature: String)
    fun loadIcon(url: String)
}