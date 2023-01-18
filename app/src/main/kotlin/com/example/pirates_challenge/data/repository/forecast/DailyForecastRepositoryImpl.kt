package com.example.pirates_challenge.data.repository.forecast

import android.location.Location
import com.example.pirates_challenge.data.api.ApiService
import com.example.pirates_challenge.data.model.NWDailyForecast
import com.example.pirates_challenge.domain.repository.DailyForecastRepository
import javax.inject.Inject

class DailyForecastRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : DailyForecastRepository {

    override suspend fun getDailyForecast(location: Location): NWDailyForecast {
        return apiService.getDailyForecast(location.latitude, location.longitude)
    }
}