package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.repo.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repo: WeatherRepo): ViewModel() {

}