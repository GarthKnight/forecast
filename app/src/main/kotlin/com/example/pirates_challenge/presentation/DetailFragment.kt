package com.example.pirates_challenge.presentation

import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pirates_challenge.R
import com.example.pirates_challenge.common.ImageUtils.setUrl
import com.example.pirates_challenge.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()
    private var binding: FragmentDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        setupUi()
    }

    private fun setupUi() {
        binding?.let {
            val forecast = args.forecast

            it.ivWeather.setUrl(forecast.iconUrl)
            it.tvWeather.text = forecast.weather
            it.tvWeatherDescription.text = forecast.weatherDescription

            it.tvMornTemp.text = forecast.temperature.morn.toString()
            it.tvDayTemp.text = forecast.temperature.day.toString()
            it.tvEveTemp.text = forecast.temperature.eve.toString()
            it.tvNightTemp.text = forecast.temperature.night.toString()

            it.tvMornFeels.text = forecast.feelsTemperature.morn.toString()
            it.tvDayFeels.text = forecast.feelsTemperature.day.toString()
            it.tvEveFeels.text = forecast.feelsTemperature.eve.toString()
            it.tvNightFeels.text = forecast.feelsTemperature.night.toString()

            if (forecast.tempDescription.isNotBlank()) {
                it.tvTempDescription.visibility = VISIBLE
                it.tvTempDescription.text = forecast.tempDescription
            }
        }
    }
}