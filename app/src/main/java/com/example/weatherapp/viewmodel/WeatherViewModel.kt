package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _viewState = MutableLiveData<ViewState<List<WeatherData>>>()
    val viewState: LiveData<ViewState<List<WeatherData>>> = _viewState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllFromDao().distinctUntilChanged().collect {
                _viewState.postValue(ViewState.Success(it))
            }
        }
    }

    fun fetchCurrent(q: String) {
        viewModelScope.launch {
            val result = repo.getCurrent(q)
            if (result.isFailure) {
                _viewState.postValue(ViewState.Error("Failed Fetching Current."))
            }
        }
    }

    //Check if delete works
    fun deleteWeather(weather: WeatherData) {
        viewModelScope.launch {
            repo.deleteWeather(weather)
        }
    }

}