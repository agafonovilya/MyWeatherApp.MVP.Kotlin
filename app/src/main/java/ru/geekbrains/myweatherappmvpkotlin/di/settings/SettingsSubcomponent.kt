package ru.geekbrains.myweatherappmvpkotlin.di.settings

import dagger.Subcomponent
import ru.geekbrains.myweatherappmvpkotlin.di.settings.module.SettingsModule
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.SettingsPresenter

@SettingsScope
@Subcomponent(modules = [
    SettingsModule::class
])
interface SettingsSubcomponent {
    fun inject(settingsPresenter: SettingsPresenter)
}