package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.model.WeatherData

class WeatherAdapter (
    private val weatherList: List<WeatherData>,
    private val weatherSelectedPosition: (Int) -> Unit
): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): WeatherViewHolder = WeatherViewHolder.getInstance(parent).apply {
        itemView.setOnClickListener { weatherSelectedPosition(adapterPosition) }
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.loadWeather(weatherList[position])
    }

    override fun getItemCount(): Int = weatherList.size

    class WeatherViewHolder(
        private val binding: ItemWeatherBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun loadWeather(weather:WeatherData) = with(binding) {
            tvCity.text = weather.name
            tvState.text = weather.region
            val temp = "${weather.tempC}°C/${weather.tempF}°F"
            tvTemp.text = temp
            val lonLat = "Lat: ${weather.latitude}, Lon: ${weather.longitude}"
            tvLonlat.text = lonLat
        }

        companion object {
            fun getInstance(parent: ViewGroup) = ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).run { WeatherViewHolder(this) }
        }

    }
}