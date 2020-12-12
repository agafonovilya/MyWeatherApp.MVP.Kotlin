package ru.geekbrains.myweatherappmvpkotlin.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.IMainView
import ru.geekbrains.myweatherappmvpkotlin.navigation.Screens
import ru.geekbrains.myweatherappmvpkotlin.ui.fragments.MainScreenFragment
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter: MvpPresenter<IMainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.MainScreen())
    }

    fun backClicked() {
        router.exit()
    }
}