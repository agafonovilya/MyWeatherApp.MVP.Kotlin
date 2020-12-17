package ru.geekbrains.myweatherappmvpkotlin.di

import dagger.Component
import ru.geekbrains.myweatherappmvpkotlin.di.location.LocationSubcomponent
import ru.geekbrains.myweatherappmvpkotlin.di.module.*
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.MainPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.MainScreenPresenter
import ru.geekbrains.myweatherappmvpkotlin.ui.activity.MainActivity
import ru.geekbrains.myweatherappmvpkotlin.ui.adapter.CurrentAdapter
import ru.geekbrains.myweatherappmvpkotlin.ui.adapter.DailyRvAdapter
import ru.geekbrains.myweatherappmvpkotlin.ui.adapter.HourlyRvAdapter
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    AppModule::class,
    CiceroneModule::class,
    DatabaseModule::class,
    ImageModule::class,
    RepoModule::class,
    SharedPrefModule::class
])
interface AppComponent {
    fun locationSubcomponent(): LocationSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainScreenPresenter: MainScreenPresenter)

    fun inject(hourlyRvAdapter: HourlyRvAdapter)
    fun inject(dailyRvAdapter: DailyRvAdapter)
    fun inject(currentAdapter: CurrentAdapter)
}