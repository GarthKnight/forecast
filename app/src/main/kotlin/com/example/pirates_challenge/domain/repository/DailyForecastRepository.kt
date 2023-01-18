package com.example.pirates_challenge.domain.repository

import android.location.Location
import com.example.pirates_challenge.data.model.NWDailyForecast

interface DailyForecastRepository {

    suspend fun getDailyForecast(location: Location): NWDailyForecast
}