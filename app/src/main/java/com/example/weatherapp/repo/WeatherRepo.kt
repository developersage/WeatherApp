package com.example.weatherapp.repo

import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.model.WeatherJson
import com.example.weatherapp.repo.local.WeatherDao
import com.example.weatherapp.repo.local.WeatherDatabase
import com.example.weatherapp.repo.remote.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val service: WeatherService,
    private val dao: WeatherDao)
{
    suspend fun getCurrent(q: String) = try {
        val weather = service.currentWeather(query = q)
        loadWeather(weather)
        Result.success(weather)
    } catch (ex: Exception){
        Result.failure(ex.cause ?: Throwable("Could not fetch the weather."))
    }

    //todo: Implement getForecast service call

    fun getAllFromDao() = dao.getAllData().flowOn(Dispatchers.IO)

    private suspend fun loadWeather(weather: WeatherJson) {
        withContext(Dispatchers.IO){
            dao.insertData(weather = WeatherData.convertFromAPI(weather))
        }
    }

    suspend fun deleteWeather(weather: WeatherData) {
        withContext(Dispatchers.IO) {
            dao.deleteData(weather = weather)
        }
    }


}