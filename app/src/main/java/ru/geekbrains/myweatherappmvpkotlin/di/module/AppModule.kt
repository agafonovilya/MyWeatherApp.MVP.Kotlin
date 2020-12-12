package ru.geekbrains.myweatherappmvpkotlin.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import ru.geekbrains.myweatherappmvpkotlin.App
import java.util.*

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun  mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun appLanguage(): String  = Locale.getDefault().language
}