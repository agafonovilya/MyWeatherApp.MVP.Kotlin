package ru.geekbrains.myweatherappmvpkotlin.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.ISettingsView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SettingsPresenter:MvpPresenter<ISettingsView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}