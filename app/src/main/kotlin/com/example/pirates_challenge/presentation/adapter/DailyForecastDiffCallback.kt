package com.example.pirates_challenge.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pirates_challenge.domain.model.forecast.DailyForecast

class DailyForecastDiffCallback : DiffUtil.ItemCallback<DailyForecast>() {

    override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
        return oldItem == newItem
    }
}