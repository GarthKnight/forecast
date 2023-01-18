package com.example.pirates_challenge.domain.interactor

import com.example.pirates_challenge.domain.model.forecast.DailyForecast
import com.example.pirates_challenge.domain.repository.DailyForecastRepository
import com.example.pirates_challenge.domain.repository.LocationRepository
import com.example.pirates_challenge.domain.mapper.DailyForecastMapper
import javax.inject.Inject

class ForecastInteractorImpl @Inject constructor(
    private val dailyForecastRepository: DailyForecastRepository,
    private val locationRepository: LocationRepository,
    private val mapper: DailyForecastMapper
) : ForecastInteractor {

    override suspend fun getDailyForecast(): List<DailyForecast> {
        val location = locationRepository.getCurrentLocation()
        val forecast = dailyForecastRepository.getDailyForecast(location)
        return mapper.invoke(forecast)
    }


}
