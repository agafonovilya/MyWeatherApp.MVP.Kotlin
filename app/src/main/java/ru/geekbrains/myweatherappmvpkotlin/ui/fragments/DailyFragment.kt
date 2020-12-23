package ru.geekbrains.myweatherappmvpkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_daily.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.myweatherappmvpkotlin.App
import ru.geekbrains.myweatherappmvpkotlin.R
import ru.geekbrains.myweatherappmvpkotlin.di.daily.DailySubcomponent
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.DailyPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.IDailyScreenView
import ru.geekbrains.myweatherappmvpkotlin.ui.IBackButtonListener
import ru.geekbrains.myweatherappmvpkotlin.ui.adapter.DailyAdapter

class DailyFragment: MvpAppCompatFragment(), IDailyScreenView, IBackButtonListener {

    companion object{
        private var unixUTC: Int = -1

        fun newInstance(unixUTC: Int): DailyFragment {
            val dailyFragment = DailyFragment()
            this.unixUTC = unixUTC
            return dailyFragment
        }

    }

    var dailySubcomponent: DailySubcomponent? = null

    val presenter: DailyPresenter by moxyPresenter {
        dailySubcomponent = App.instance.initDailySubcomponent()
        DailyPresenter(unixUTC).apply { dailySubcomponent?.inject(this) }
    }

    private var dailyAdapter: DailyAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_daily, null)

    override fun init() {
        dailyAdapter = view?.let {
            DailyAdapter(it).apply { dailySubcomponent?.inject(this) }
        }

        btn_daily_backButton.setOnClickListener { presenter.backPressed() }
    }

    override fun updateWeatherView(unixUTC: Int, iconURL: String, description: String,
                                   morningTemp: Double, dayTemp: Double, eveningTemp: Double,
                                   nightTemp: Double, wind: Double, humidity: Int) {
        dailyAdapter?.update(unixUTC, iconURL, description, morningTemp, dayTemp, eveningTemp,
                            nightTemp, wind, humidity)
    }


    override fun release() {
        dailySubcomponent = null
        App.instance.releaseDailySubcomponent()
    }

    override fun backPressed() = presenter.backPressed()
}