package ru.geekbrains.myweatherappmvpkotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweatherappmvpkotlin.App
import ru.geekbrains.myweatherappmvpkotlin.R
import ru.geekbrains.myweatherappmvpkotlin.di.settings.SettingsSubcomponent
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.SettingsPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.ISettingsView
import ru.geekbrains.myweatherappmvpkotlin.ui.IBackButtonListener

class SettingsFragment: MvpAppCompatFragment(), ISettingsView, IBackButtonListener {
    companion object{
        fun newInstance() = SettingsFragment()
    }

    var settingsSubcomponent: SettingsSubcomponent? = null

    val presenter: SettingsPresenter by moxyPresenter {
        settingsSubcomponent = App.instance.initSettingsSubcomponent()
        SettingsPresenter().apply { settingsSubcomponent?.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_settings, null)

    override fun init() {
    }

    override fun release() {
        settingsSubcomponent = null
        App.instance.releaseSettingsSubcomponent()
    }

    override fun backPressed() = presenter.backPressed()
}