package com.example.pirates_challenge.domain.interactor

import com.example.pirates_challenge.domain.model.weather.CurrentWeather

interface CurrentWeatherInteractor {

    suspend fun getWeather(): CurrentWeather
}