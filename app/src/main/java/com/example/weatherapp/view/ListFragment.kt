package com.example.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapter.SwipeToDelete
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.databinding.FragmentListBinding
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.util.ViewState
import com.example.weatherapp.viewmodel.WeatherViewModel

class ListFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentListBinding.inflate( inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBtnFetch()
        observeViewState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpBtnFetch() = with(binding) {
        btnFetch.setOnClickListener {
            viewModel.fetchCurrent(etSearchbar.text.toString())
        }
    }

    private fun itemSelected(item: WeatherData) = with(findNavController()) {
        val action = ListFragmentDirections.goToWeather(item)
        navigate(action)
    }

    private fun observeViewState() = with(viewModel) {
        viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Loading -> { (activity as? MainActivity)?.showProgress = true }
                is ViewState.Error -> { (activity as? MainActivity)?.showProgress = false }
                is ViewState.Success -> {
                    (activity as? MainActivity)?.showProgress = false
                    binding.rcWeatherList.adapter = WeatherAdapter(state.item, ::itemSelected)

                    val swipeHandler = object : SwipeToDelete() {
                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            deleteWeather(state.item[viewHolder.adapterPosition])
                        }
                    }
                    ItemTouchHelper(swipeHandler).attachToRecyclerView(binding.rcWeatherList)
                }
            }
        }
    }

}