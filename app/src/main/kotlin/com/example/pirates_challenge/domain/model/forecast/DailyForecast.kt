package com.example.pirates_challenge.domain.model.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyForecast(
    val date: Long,
    val readableDate: String,
    val dayOfWeek: String,
    val city: String,
    val temperature: Temperature,
    val feelsTemperature: Temperature,
    val tempDescription: String,
    val weather: String,
    val weatherDescription: String,
    val iconUrl: String
) : Parcelable
