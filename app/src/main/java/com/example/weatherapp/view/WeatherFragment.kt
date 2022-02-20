package com.example.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherapp.R
import com.example.weatherapp.adapter.DetailAdapter
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.viewmodel.WeatherViewModel

class WeatherFragment: Fragment() {

    private val viewModel:WeatherViewModel by activityViewModels()
    private lateinit var viewPager2: ViewPager2
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

        setUpViewPager()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpViewPager() = with(binding) {
        viewPager2 = vpDetail
        viewPager2.let { pager ->
            pager.adapter = DetailAdapter(viewModel.weatherList.value!!)
            pager.setCurrentItem(args.position, false)
        }
    }

}