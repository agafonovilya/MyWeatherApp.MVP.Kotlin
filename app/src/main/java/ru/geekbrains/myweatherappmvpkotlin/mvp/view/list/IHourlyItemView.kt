package ru.geekbrains.myweatherappmvpkotlin.mvp.view.list

interface IHourlyItemView: IItemView {
    fun setTime(time: String)
    fun setTemperature(temperature: String)
    fun loadIcon(url: String)
}