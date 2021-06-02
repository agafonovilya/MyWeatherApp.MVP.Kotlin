package ru.geekbrains.myweatherappmvpkotlin.di.location

import dagger.Subcomponent
import ru.geekbrains.myweatherappmvpkotlin.di.location.module.LocationModule
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.LocationPresenter

@LocationScope
@Subcomponent(modules = [
    LocationModule::class
])
interface LocationSubcomponent {
    fun inject(locationPresenter: LocationPresenter)
}