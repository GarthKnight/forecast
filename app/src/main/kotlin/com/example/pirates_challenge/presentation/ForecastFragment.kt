package com.example.pirates_challenge.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pirates_challenge.R
import com.example.pirates_challenge.common.exceptions.LocationPermissionException
import com.example.pirates_challenge.databinding.FragmentForecastBinding
import com.example.pirates_challenge.domain.model.forecast.DailyForecast
import com.example.pirates_challenge.presentation.adapter.ForecastAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment : Fragment(R.layout.fragment_forecast) {

    @Inject
    internal lateinit var observer: AppLifecycleObserver

    private var binding: FragmentForecastBinding? = null
    private val viewModel: ForecastViewModel by viewModels()
    private val adapter = ForecastAdapter()

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                viewModel.getWeatherData()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    notificationPermissionRequest.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
                } else {
                    activity?.lifecycle?.addObserver(observer)
                        ?: viewLifecycleOwner.lifecycle.addObserver(observer)
                }
            }
            else -> {
                viewModel.handleError(LocationPermissionException())
            }
        }
    }

    private val notificationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.POST_NOTIFICATIONS, false) -> {
                viewLifecycleOwner.lifecycle.addObserver(observer)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastBinding.bind(view)

        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))

        setupUi()
        observeValues()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupUi() {
        binding?.forecastList?.adapter = adapter
    }

    private fun observeValues() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.forecastFlow.collect { state ->
                    when (state) {
                        is DailyForecastUiState.Success -> showDailyForecast(state.forecasts)
                        is DailyForecastUiState.Error -> viewModel.handleError(state.throwable)
                    }
                }
            }
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorFlow.collect {
                    showToast(it)
                }
            }
        }
    }

    private fun showDailyForecast(forecasts: List<DailyForecast>) {
        if (forecasts.isNotEmpty()) {
            adapter.submitList(forecasts)
            binding?.tvCity?.text = forecasts.first().city
        }
    }

    private fun showToast(stringId: Int) {
        Toast.makeText(context, getString(stringId), Toast.LENGTH_SHORT).show()
    }
}