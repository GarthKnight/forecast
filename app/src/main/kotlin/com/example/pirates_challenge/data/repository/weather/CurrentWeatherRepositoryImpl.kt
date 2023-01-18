package com.example.pirates_challenge.data.repository.weather

import android.location.Location
import com.example.pirates_challenge.data.api.ApiService
import com.example.pirates_challenge.data.model.NWCurrentWeather
import com.example.pirates_challenge.domain.repository.CurrentWeatherRepository
import javax.inject.Inject

class CurrentWeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CurrentWeatherRepository {

    override suspend fun getCurrentWeather(location: Location): NWCurrentWeather {
        return apiService.getCurrentWeatherInfo(location.latitude, location.longitude)
    }
}