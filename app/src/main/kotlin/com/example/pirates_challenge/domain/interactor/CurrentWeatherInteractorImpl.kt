package com.example.pirates_challenge.domain.interactor

import com.example.pirates_challenge.domain.repository.LocationRepository
import com.example.pirates_challenge.domain.repository.CurrentWeatherRepository
import com.example.pirates_challenge.domain.mapper.CurrentWeatherMapper
import com.example.pirates_challenge.domain.model.weather.CurrentWeather
import javax.inject.Inject

class CurrentWeatherInteractorImpl @Inject constructor(
    private val weatherRepository: CurrentWeatherRepository,
    private val locationRepository: LocationRepository,
    private val mapper: CurrentWeatherMapper
) : CurrentWeatherInteractor {

    override suspend fun getWeather(): CurrentWeather {
        val location = locationRepository.getCurrentLocation()
        val weather = weatherRepository.getCurrentWeather(location)
        return mapper.invoke(weather)
    }
}