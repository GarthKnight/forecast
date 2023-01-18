package com.example.pirates_challenge.domain.repository

import android.location.Location
import com.example.pirates_challenge.data.model.NWCurrentWeather

interface CurrentWeatherRepository {

    suspend fun getCurrentWeather(location: Location): NWCurrentWeather
}