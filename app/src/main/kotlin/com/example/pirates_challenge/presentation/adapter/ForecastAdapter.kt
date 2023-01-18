package com.example.pirates_challenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.pirates_challenge.common.ImageUtils.setUrl
import com.example.pirates_challenge.databinding.ItemDailyForecastBinding
import com.example.pirates_challenge.domain.model.forecast.DailyForecast
import com.example.pirates_challenge.presentation.ForecastFragmentDirections


class ForecastAdapter : ListAdapter<DailyForecast, ViewHolder>(DailyForecastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDailyForecastBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forecast = getItem(position)
        (holder as ForecastViewHolder).bind(forecast)
    }

    class ForecastViewHolder(
        private val binding: ItemDailyForecastBinding
    ) : ViewHolder(binding.root) {

        fun bind(forecast: DailyForecast) {
            binding.tvDate.text = forecast.readableDate
            binding.tvTemp.text = forecast.temperature.day.toString()
            binding.tvDay.text = forecast.dayOfWeek
            binding.ivWeather.setUrl(forecast.iconUrl)

            binding.root.setOnClickListener {
                val direction =
                    ForecastFragmentDirections.forecastFragmentToDetailFragment(forecast)
                it.findNavController().navigate(direction)
            }
        }
    }
}