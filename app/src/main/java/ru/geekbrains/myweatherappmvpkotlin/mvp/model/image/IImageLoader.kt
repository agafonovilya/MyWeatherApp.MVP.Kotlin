package ru.geekbrains.myweatherappmvpkotlin.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}