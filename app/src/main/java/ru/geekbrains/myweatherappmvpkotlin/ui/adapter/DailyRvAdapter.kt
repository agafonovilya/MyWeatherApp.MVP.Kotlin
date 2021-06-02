package ru.geekbrains.myweatherappmvpkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_daily.view.*
import kotlinx.android.synthetic.main.item_hourly.view.*
import ru.geekbrains.myweatherappmvpkotlin.R
import ru.geekbrains.myweatherappmvpkotlin.mvp.model.image.IImageLoader
import ru.geekbrains.myweatherappmvpkotlin.mvp.presenter.list.IDailyListPresenter
import ru.geekbrains.myweatherappmvpkotlin.mvp.view.list.IDailyItemView
import javax.inject.Inject

class DailyRvAdapter(val presenter: IDailyListPresenter):
        RecyclerView.Adapter<DailyRvAdapter.ViewHolder>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View):
            RecyclerView.ViewHolder(containerView), LayoutContainer, IDailyItemView {

        override var pos = -1

        override fun setDate(date: String) = with(containerView){
            weekCard_date.text = date
        }

        override fun setDayTemperature(dayTemperature: String) = with(containerView){
            weekCard_dayTemperature.text = dayTemperature
        }

        override fun setNightTemperature(nightTemperature: String) = with(containerView) {
            weekCard_nightTemperature.text = nightTemperature
        }

        override fun loadIcon(url: String) = with(containerView){
            imageLoader.loadInto(url, weekCard_icon)
        }
    }
}