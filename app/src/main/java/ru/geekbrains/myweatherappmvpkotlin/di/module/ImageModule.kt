package ru.geekbrains.myweatherappmvpkotlin.di.module

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrains.myweatherappmvpkotlin.App
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.cache.IImageCache
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.cache.room.RoomImageCache
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.room.Database
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.image.IImageLoader
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.myweatherappmvpkotlin.ui.image.GlideImageLoader
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {

    @Named("cacheDir")
    @Singleton
    @Provides
    fun cacheDir(app: App): File = app.cacheDir

    @Singleton
    @Provides
    fun imageCache(database: Database, @Named("cacheDir") cacheDir: File): IImageCache = RoomImageCache(database, cacheDir)

    @Provides
    fun imageLoader(cache: IImageCache, networkStatus: INetworkStatus): IImageLoader<ImageView> = GlideImageLoader(cache, networkStatus)

}