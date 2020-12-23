package ru.geekbrains.myweatherappmvpkotlin.di.daily

import dagger.Subcomponent
import ru.geekbrains.myweatherappmvpkotlin.di.daily.module.DailyModule
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.DailyPresenter
import ru.geekbrains.myweatherappmvpkotlin.ui.adapter.DailyAdapter

@DailyScope
@Subcomponent(modules = [
   DailyModule::class
])
interface DailySubcomponent {
    fun inject(dailyPresenter: DailyPresenter)
    fun inject(dailyAdapter: DailyAdapter)
}
