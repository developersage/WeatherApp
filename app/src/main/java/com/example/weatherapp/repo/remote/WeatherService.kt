package com.example.weatherapp.repo.remote

import com.example.weatherapp.model.WeatherJson
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    companion object{
        private const val API_KEY = "97757fba6fbd46f49c0164636220601"
    }

    @GET("current.json")
    suspend fun currentWeather(
        @Query(value = "key") apiKey: String = API_KEY,
        @Query(value = "q") query: String,
        @Query(value = "aqi") aqi: String = "no"
    ): WeatherJson

    @GET("forecast.json")
    suspend fun forecastWeather(
        @Query(value = "key") apiKey: String = API_KEY,
        @Query(value = "q") query: String,
        @Query(value = "days") days: Int,
        @Query(value = "aqi") aqi: String = "no",
        @Query(value = "alerts") alerts: String = "no"
    ): WeatherJson
}