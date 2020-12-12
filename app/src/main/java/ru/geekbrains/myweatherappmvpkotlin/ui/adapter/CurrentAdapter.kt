package ru.geekbrains.myweatherappmvpkotlin.ui.adapter

import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_main_screen.view.*
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.image.IImageLoader
import javax.inject.Inject

class CurrentAdapter(val view: View) {
    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    fun update(temperature: String?, cityName: String?, description: String?, url: String?){
        with(view){
            mainScreen_currentTemperature.text = temperature
            mainScreen_cityName.text = cityName
            mainScreen_currentWeatherDescription.text = description
            url?.let { imageLoader.loadInto(it, mainScreen_currentWeatherIcon) }
        }
    }

}