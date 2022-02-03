package com.example.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.viewmodel.WeatherViewModel

class WeatherFragment: Fragment() {

    private val viewModel:WeatherViewModel by viewModels()
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<WeatherFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentWeatherBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadInfo() = with(binding) {
        tvCity.text = args.weather.name
        val temp = args.weather.tempC.toString() + args.weather.tempF.toString()
        tvTemp.text = temp
    }


}