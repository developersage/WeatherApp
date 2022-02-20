package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemDetailWeatherBinding
import com.example.weatherapp.model.WeatherData

class DetailAdapter(
    private val weatherList: List<WeatherData>,

): RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DetailViewHolder = DetailViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.loadDetail(weatherList[position])
    }
    override fun getItemCount(): Int = weatherList.size


    class DetailViewHolder(
        private val binding: ItemDetailWeatherBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun loadDetail(item: WeatherData) = with(binding){
            tvCity.text = item.name
            tvTemp.text = item.tempC.toString()
        }

        companion object {
            fun getInstance(parent: ViewGroup) = ItemDetailWeatherBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).run { DetailViewHolder(this) }
        }
    }
}