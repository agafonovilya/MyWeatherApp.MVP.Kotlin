package ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.list

import ru.geekbrains.myweatherappmvpkotlin.mvp.view.list.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}