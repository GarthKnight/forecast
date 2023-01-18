package com.example.pirates_challenge.domain.interactor

import com.example.pirates_challenge.domain.model.forecast.DailyForecast

interface ForecastInteractor {

    suspend fun getDailyForecast(): List<DailyForecast>
}