package ru.geekbrains.myweatherappmvpkotlin.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.ILocationView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class LocationPresenter: MvpPresenter<ILocationView>() {

    @Inject lateinit var router: Router

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