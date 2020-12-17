package ru.geekbrains.myweatherappmvpkotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_location.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweatherappmvpkotlin.App
import ru.geekbrains.myweatherappmvpkotlin.R
import ru.geekbrains.myweatherappmvpkotlin.di.location.LocationSubcomponent
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.LocationPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.ILocationView
import ru.geekbrains.myweatherappmvpkotlin.ui.IBackButtonListener

class LocationFragment: MvpAppCompatFragment(), ILocationView, IBackButtonListener {
    companion object{
        fun newInstance() = LocationFragment()
    }

    var locationSubcomponent: LocationSubcomponent? = null

    val presenter: LocationPresenter by moxyPresenter {
        locationSubcomponent = App.instance.initLocationSubcomponent()
        LocationPresenter().apply { locationSubcomponent?.inject(this) }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_location, null)

    override fun init() {
        backButton.setOnClickListener { presenter.backPressed() }
    }

    override fun initAutoCompeteTextView(citiesList: List<String>) {
        // Инициализируем AutoCompleteTextView, передаем массив городов

        actv_location.threshold = 1 //Минимальное кол-во символов до начала показа подходящих вариантов

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, citiesList)
        actv_location.setAdapter(adapter)

        // Устанавливаем слушатель на прикосновение к строке ввода города, чтобы сразу открывался весь доступный список городов
        actv_location.setOnTouchListener { v, _ ->
            v.performClick()
            actv_location.showDropDown()
            false
        }

        // Устанавливаем слушатель на выбор города, с последующим переходом на основной экран
        actv_location.onItemClickListener = AdapterView.OnItemClickListener {
            _, _, position, _ -> //Проверяем есть ли такой город в списке. Если есть, то удаляем

            presenter.locationChanged(citiesList[position])
        }

    }

    override fun release() {
        locationSubcomponent = null
        App.instance.releaseLocationSubcomponent()
    }

    override fun backPressed() = presenter.backPressed()

}