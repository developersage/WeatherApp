package com.example.weatherapp.viewmodel

import androidx.lifecycle.*
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.repo.WeatherRepo
import com.example.weatherapp.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repo: WeatherRepo): ViewModel() {

    private val _weatherList = MutableLiveData<List<WeatherData>>()
    val weatherList: LiveData<List<WeatherData>> = _weatherList

    init {
        viewModelScope.launch(Dispatchers.Default) {
            repo.getAllFromDao().distinctUntilChanged().collect {
                _weatherList.postValue(it)
            }
        }
    }

    fun fetchCurrent(q: String) = liveData {
        emit(ViewState.Loading)
        val result = repo.getCurrent(q)
        val state = if (result.isFailure) ViewState.Error("Error")
        else ViewState.Success("Success")
        emit(state)
    }

    fun deleteWeather(weather: WeatherData) {
        viewModelScope.launch {
            repo.deleteWeather(weather)
        }
    }

}