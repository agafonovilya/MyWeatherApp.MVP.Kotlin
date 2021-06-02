package ru.geekbrains.myweatherappmvpkotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    override fun release() {
        locationSubcomponent = null
        App.instance.releaseLocationSubcomponent()
    }

    override fun backPressed() = presenter.backPressed()

}